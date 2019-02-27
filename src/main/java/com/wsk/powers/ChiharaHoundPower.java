package com.wsk.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.wsk.utils.ChangeArmsUtil;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/26
 * @desc 兵器：赤原猎犬
 */
public class ChiharaHoundPower extends BaseArchPower {
    public static final String POWER_ID = "MyMod:ChiharaHoundPower";//能力的ID，判断有无能力、能力层数时填写该Id而不是类名。
    public static final String NAME = "兵器：赤原猎犬";//能力的名称。

    public static final String[] DESCRIPTIONS = {"获得", "点力量。攻击时，给予被攻击者", "层 虚弱 。", "层 易伤 "};//需要调用变量的文本描叙，例如力量（Strength）、敏捷（Dexterity）等。

    private static final String IMG = "powers/BurningS.png";
    private static PowerType POWER_TYPE = PowerType.BUFF;

    private boolean update;

    public ChiharaHoundPower(AbstractCreature owner, int amount, boolean update) {
        super(owner, amount);//参数：owner-能力施加对象、amount-施加能力层数。在cards的use里面用ApplyPowerAction调用进行传递。
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.update = update;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        updateDescription();//调用该方法（第36行）的文本更新函数,更新一次文本描叙，不可缺少。
        this.type = POWER_TYPE;//能力种类，可以不填写，会默认为PowerType.BUFF。PowerType.BUFF不会被人工制品抵消，PowerType.DEBUFF会被人工制品抵消。
        updateDescription();
    }

    public void updateDescription() {
        if (update) {
            this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[3]);
        } else {
            this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2]);
        }
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        super.onAttack(info, damageAmount, target);
        //虚弱
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, AbstractDungeon.player,
                new WeakPower(target, this.amount, false), this.amount, true, AbstractGameAction.AttackEffect.NONE));
        if (update) {
            //易伤
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, AbstractDungeon.player,
                    new VulnerablePower(target, this.amount, false), this.amount,
                    true, AbstractGameAction.AttackEffect.NONE));
        }
        super.onAttack(target);
    }

//    //造成伤害时，返回伤害数值
//    public float atDamageFinalReceive(float damage, DamageInfo.DamageType damageType) {
//        super.atDamageFinalReceive(abstractMonster, damage, damageType);
//        return damage;
//    }

    @Override
    public void onRemove() {
        if (!ChangeArmsUtil.retain()) {
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(AbstractDungeon.player, AbstractDungeon.player, StrengthPower.POWER_ID, this.amount));
        }
//        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
//                new StrengthPower(AbstractDungeon.player, -this.amount), -this.amount));
//        super.onRemove();
    }

}

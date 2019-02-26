package com.wsk.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/26
 * @desc 一句话说明
 */
public class KadeboSwordPower extends BaseSwordPower {
    public static final String POWER_ID = "MyMod:KadeboSwordPower";//能力的ID，判断有无能力、能力层数时填写该Id而不是类名。
    public static final String NAME = "兵器：卡拉德波加";//能力的名称。

    public static final String[] DESCRIPTIONS = {"获得", "点力量。攻击时，恢复该攻击卡的数值的", "生命值。"};//需要调用变量的文本描叙，例如力量（Strength）、敏捷（Dexterity）等。

    private static final String IMG = "powers/BurningS.png";
    //以上两种文本描叙只需写一个，更新文本方法在第36行。
    private static PowerType POWER_TYPE = PowerType.BUFF;

    public KadeboSwordPower(AbstractCreature owner, int amount) {
        super(owner,amount);//参数：owner-能力施加对象、amount-施加能力层数。在cards的use里面用ApplyPowerAction调用进行传递。
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        updateDescription();//调用该方法（第36行）的文本更新函数,更新一次文本描叙，不可缺少。
        this.type = POWER_TYPE;//能力种类，可以不填写，会默认为PowerType.BUFF。PowerType.BUFF不会被人工制品抵消，PowerType.DEBUFF会被人工制品抵消。
    }

    public void updateDescription() {
        if (this.amount >= 2) {
            this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + "1/2" + DESCRIPTIONS[2]);
        } else {
            this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + "1/4" + DESCRIPTIONS[2]);
        }
    }

    //触发时机：当玩家攻击时。info.可调用伤害信息。
    public void onAttack(final DamageInfo info, final int damageAmount, final AbstractCreature target) {//参数： info-伤害信息，damageAmount-伤害数值，target-伤害目标
        if (info.type == DamageInfo.DamageType.NORMAL) {
            if (this.amount >= 2) {
                //恢复生命值
                AbstractDungeon.actionManager.addToBottom(new HealAction(this.owner, this.owner, this.amount / 2));
            } else {
                AbstractDungeon.actionManager.addToBottom(new HealAction(this.owner, this.owner, (this.amount / 4)));
            }
        }
        super.onAttack(info, damageAmount, target);
    }

    @Override
    public void onRemove() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new StrengthPower(AbstractDungeon.player, -this.amount), -this.amount));
//        super.onRemove();
    }

}

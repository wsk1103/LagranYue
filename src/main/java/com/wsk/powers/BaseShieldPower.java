package com.wsk.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.wsk.utils.ChangeArmsUtil;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/25
 * @desc 盾能力,当玩家攻击时,获得2点格挡
 */
public class BaseShieldPower extends AbstractArmsPower {
    public static final String POWER_ID = "MyMod:BaseShieldPower";//能力的ID，判断有无能力、能力层数时填写该Id而不是类名。
    public static final String NAME = "兵器：破盾";//能力的名称。

    public static final String[] DESCRIPTIONS = {"获得", "点敏捷。"};//需要调用变量的文本描叙，例如力量（Strength）、敏捷（Dexterity）等。

    String basePower = " 盾 。";

    private static final String IMG = "powers/w15.png";
    private static PowerType POWER_TYPE = PowerType.BUFF;

    public BaseShieldPower(AbstractCreature owner, int amount) {//参数：owner-能力施加对象、amount-施加能力层数。在cards的use里面用ApplyPowerAction调用进行传递。
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        updateDescription();//调用该方法（第36行）的文本更新函数,更新一次文本描叙，不可缺少。
        this.type = POWER_TYPE;//能力种类，可以不填写，会默认为PowerType.BUFF。PowerType.BUFF不会被人工制品抵消，PowerType.DEBUFF会被人工制品抵消。
        updateDescription();
    }

    public void updateDescription() {
        this.description = (basePower + DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
    }


    //触发时机：当玩家攻击时。
    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, 2));
        super.onAttack(info, damageAmount, target);
    }
    @Override
    public void onRemove() {
        if (!ChangeArmsUtil.retain()) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                    new DexterityPower(AbstractDungeon.player, -this.amount), -this.amount));
        }
//        super.onRemove();
    }
}

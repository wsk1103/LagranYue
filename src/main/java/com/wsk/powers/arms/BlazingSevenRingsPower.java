package com.wsk.powers.arms;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.BarricadePower;
import com.wsk.actions.ActionUtil;
import com.wsk.utils.ArmsUtil;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/26
 * @desc 一句话说明
 */
public class BlazingSevenRingsPower extends BaseShieldPower {
    public static final String POWER_ID = "LagranYue:BlazingSevenRingsPower";//能力的ID，判断有无能力、能力层数时填写该Id而不是类名。
    public static final String NAME = "兵器：炽天覆七重圆环";//能力的名称。

    public static String[] DESCRIPTIONS = {"获得", "点敏捷。获得 壁垒 。受到的伤害减少"};//需要调用变量的文本描叙，例如力量（Strength）、敏捷（Dexterity）等。

    private static final String IMG = "powers/w16.png";
    private static PowerType POWER_TYPE = PowerType.BUFF;

    public BlazingSevenRingsPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;

        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
//        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        this.type = POWER_TYPE;
        updateDescription();
    }

    public void hasArms() {
//        ArmsUtil.setHasRings(false);
//        ArmsUtil.addOrChangArms(p, this, amount);
        //获得敏捷
        ActionUtil.dexterityPower(owner, this.amount);
        //获得壁垒
        if (!owner.hasPower(BarricadePower.POWER_ID)) {
            ActionUtil.barricadePower(owner);
//            ArmsUtil.setHasRings(true);
        }
    }

    public void updateDescription() {
        this.description = (super.basePower + DESCRIPTIONS[0] + (this.amount) + DESCRIPTIONS[1] + (this.amount * 3) + "。");
    }

//    @Override
//    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
//        super.onAttack(info, damageAmount, target);
//    }

    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        super.onAfterUseCard(card, action);
    }

    //触发时机：当玩家被攻击时，返回伤害数值，可用来修改伤害数值。info.可调用伤害信息。
    public int onAttacked(DamageInfo info, int damageAmount) {//参数：info-伤害信息，damageAmount-伤害数值
        if (damageAmount <= this.amount * 3) {
            damageAmount = 0;
        } else {
            damageAmount -= this.amount * 3;
        }
        return damageAmount;
    }

    @Override
    public void onRemove() {
        if (!ArmsUtil.retain()) {
            //移除敏捷
            ActionUtil.dexterityPower(owner, -this.amount);
//            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
//                    new DexterityPower(AbstractDungeon.player, -this.amount * 3), -this.amount * 3, AbstractGameAction.AttackEffect.POISON));
        }
        //移除壁垒
//        if (ArmsUtil.isHasRings()) {
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, BarricadePower.POWER_ID));
//        }
//        ArmsUtil.setHasRings(true);
//        super.onRemove();
    }
}

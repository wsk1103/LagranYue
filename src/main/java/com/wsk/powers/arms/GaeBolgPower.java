package com.wsk.powers.arms;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.wsk.actions.ActionUtil;
import com.wsk.utils.ArmsUtil;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/25
 * @desc 兵器：刺穿死棘之枪
 */
public class GaeBolgPower extends AbstractSpearPower {
    public static final String POWER_ID = "LagranYue:GaeBolgPower";//能力的ID，判断有无能力、能力层数时填写该Id而不是类名。
    public static final String NAME = "兵器：刺穿死棘之枪";//能力的名称。

    //    public static final String DESCRIPITON = "攻击伤害增加印记的层数，当层数到达10层的时候，给予100点伤害";//不需要调用变量的文本描叙，例如钢笔尖（PenNibPower）。
    public static String[] DESCRIPTIONS = {"获得", "点力量，打出攻击卡牌后，获得", "层金属化 。"};//需要调用变量的文本描叙，例如力量（Strength）、敏捷（Dexterity）等。

    private static final String IMG = "powers/w1.png";
    //以上两种文本描叙只需写一个，更新文本方法在第36行。
    private static PowerType POWER_TYPE = PowerType.BUFF;

    private static int metallicizePowerNum = 0;

    public GaeBolgPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;

        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
//        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        this.type = POWER_TYPE;
//        hasArms();
        updateDescription();
    }

    @Override
    public void hasArms() {
//        ArmsUtil.addOrChangArms(owner, this, amount);
        ActionUtil.strengthPower(owner, amount);
    }

    public void updateDescription() {
        this.description = (super.basePower + DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2]);
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if ((!card.purgeOnUse) && card.type == AbstractCard.CardType.ATTACK) {
            metallicizePowerNum += this.amount;
            //获得多层护甲
            ActionUtil.metallicizePower(owner, amount);
//            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
//                    new PlatedArmorPower(AbstractDungeon.player, amount), amount, AbstractGameAction.AttackEffect.POISON));
        }
        super.onAfterUseCard(card, action);
    }

//    @Override
//    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
//        //获得多层护甲
//        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
//                new PlatedArmorPower(AbstractDungeon.player, platedArmor), platedArmor, AbstractGameAction.AttackEffect.POISON));
//        super.onAttack(info, damageAmount, target);
//    }

    @Override
    public void onRemove() {
        if (!ArmsUtil.retain()) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                    new StrengthPower(AbstractDungeon.player, -this.amount), -this.amount));
            //移除金属化
            if (owner.hasPower(MetallicizePower.POWER_ID)) {
                int temp = owner.getPower(MetallicizePower.POWER_ID).amount;
                if (temp <= metallicizePowerNum) {
                    ActionUtil.removePower(owner, MetallicizePower.POWER_ID);
                }
                else {
                    ActionUtil.metallicizePower(owner, -metallicizePowerNum);
                }
                metallicizePowerNum = 0;
            }
        }
    }
}


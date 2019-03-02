package com.wsk.powers.arms;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.wsk.actions.ActionUtil;
import com.wsk.utils.ArmsUtil;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/26
 * @desc 兵器：赤原猎犬
 */
public class ChiharaHoundPower extends BaseArchPower {
    public static final String POWER_ID = "LagranYue:ChiharaHoundPower";//能力的ID，判断有无能力、能力层数时填写该Id而不是类名。
    public static final String NAME = "兵器：赤原猎犬";//能力的名称。

    public static final String[] DESCRIPTIONS = {"获得", "点力量。攻击后，给予被攻击者", "层 虚弱 。"};//需要调用变量的文本描叙，例如力量（Strength）、敏捷（Dexterity）等。

    private static final String IMG = "powers/w10.png";
    private static PowerType POWER_TYPE = PowerType.BUFF;

    public ChiharaHoundPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        this.type = POWER_TYPE;
//        hasArms();
        updateDescription();
    }

    public void hasArms() {
//        ArmsUtil.addOrChangArms(owner, this, amount);
        ActionUtil.strengthPower(owner, amount);
    }

    public void updateDescription() {
        this.description = (super.basePower + DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2]);
    }

//    @Override
//    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
////        super.onAttack(info, damageAmount, target);
//        //虚弱
//        ActionUtil.weakPower(owner, target, amount);
////        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, AbstractDungeon.player,
////                new WeakPower(target, this.amount, false), this.amount, true, AbstractGameAction.AttackEffect.POISON));
//        if (update) {
//            //易伤
//            ActionUtil.vulnerablePower(owner, target, amount);
////            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, AbstractDungeon.player,
////                    new VulnerablePower(target, this.amount, false), this.amount,
////                    true, AbstractGameAction.AttackEffect.POISON));
//        }
//    }

    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if ((!card.purgeOnUse) && card.type == AbstractCard.CardType.ATTACK) {
            if (card.target == AbstractCard.CardTarget.ALL
                    || card.target == AbstractCard.CardTarget.ALL_ENEMY) {
                if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                    flash();
                    for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                        if ((!m.isDead) && (!m.isDying)) {
                            ActionUtil.weakPower(owner, m, amount);
//        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, AbstractDungeon.player,
//                new WeakPower(target, this.amount, false), this.amount, true, AbstractGameAction.AttackEffect.POISON));
                        }
                    }
                }
            } else {
                AbstractMonster m = null;
                if (action.target != null) {
                    m = (AbstractMonster) action.target;
                }
                ActionUtil.weakPower(owner, m, amount);
//        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, AbstractDungeon.player,
//                new WeakPower(target, this.amount, false), this.amount, true, AbstractGameAction.AttackEffect.POISON));
            }
        }
        super.onAfterUseCard(card, action);
    }

//    //造成伤害时，返回伤害数值
//    public float atDamageFinalReceive(float damage, DamageInfo.DamageType damageType) {
//        super.atDamageFinalReceive(abstractMonster, damage, damageType);
//        return damage;
//    }

    @Override
    public void onRemove() {
        if (!ArmsUtil.retain()) {
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(AbstractDungeon.player, AbstractDungeon.player, StrengthPower.POWER_ID, this.amount));
        }
//        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
//                new StrengthPower(AbstractDungeon.player, -this.amount), -this.amount));
//        super.onRemove();
    }

}

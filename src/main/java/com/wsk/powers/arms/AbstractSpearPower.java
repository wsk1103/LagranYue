package com.wsk.powers.arms;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.wsk.actions.ActionUtil;
import com.wsk.cards.AbstractArmsCard;
import com.wsk.patches.ArmsEnum;
import com.wsk.powers.proficiency.DistortionPower;

/**
 * @author wsk1103
 * @date 2019/3/20
 * @description 描述
 */
public abstract class AbstractSpearPower extends AbstractArmsPower {
    String basePower = " 枪 。";
    AbstractSpearPower() {
        super(ArmsEnum.Spear);
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if (card instanceof AbstractArmsCard) {
            return;
        }
        if ((!card.purgeOnUse) && card.type == AbstractCard.CardType.ATTACK) {
            if (card.target == AbstractCard.CardTarget.ALL
                    || card.target == AbstractCard.CardTarget.ALL_ENEMY) {
                if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                    flash();
                    for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                        if ((!m.isDead) && (!m.isDying)) {
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, AbstractDungeon.player,
                                    new PoisonPower(m, AbstractDungeon.player, 1), 1,
                                    true, AbstractGameAction.AttackEffect.POISON));
                        }
                    }
                }
            } else {
                AbstractMonster m = null;
                if (action.target != null) {
                    m = (AbstractMonster) action.target;
                }
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, AbstractDungeon.player,
                        new PoisonPower(m, AbstractDungeon.player, 1), 1,
                        true, AbstractGameAction.AttackEffect.POISON));
            }
        } else if ((!card.purgeOnUse) && card.type == AbstractCard.CardType.SKILL) {
            ActionUtil.poisonPower(owner, owner, 1);
        } else if ((!card.purgeOnUse) && card.type == AbstractCard.CardType.POWER) {
            ActionUtil.addPower(owner, owner, new DistortionPower(owner, 1));
        }
        super.onAfterUseCard(card, action);
    }
}

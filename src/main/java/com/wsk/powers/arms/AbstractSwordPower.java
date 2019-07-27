package com.wsk.powers.arms;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import com.megacrit.cardcrawl.powers.EntanglePower;
import com.wsk.actions.ActionUtil;
import com.wsk.patches.ArmsEnum;

/**
 * @author wsk1103
 * @date 2019/3/20
 * @description 描述
 */
public abstract class AbstractSwordPower extends AbstractArmsPower {
    String basePower = " 剑 。";
    AbstractSwordPower() {
        super(ArmsEnum.Sword);
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if ((!card.purgeOnUse) && card.type == AbstractCard.CardType.ATTACK) {
            if (card.target == AbstractCard.CardTarget.ALL
                    || card.target == AbstractCard.CardTarget.ALL_ENEMY) {
                if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                    flash();
                    for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                        if ((!m.isDead) && (!m.isDying)) {
                            ActionUtil.weakPower(AbstractDungeon.player, m, 1);
                        }
                    }
                }
            } else {
                AbstractMonster m = null;
                if (action.target != null) {
                    m = (AbstractMonster) action.target;
                }
                ActionUtil.weakPower(AbstractDungeon.player, m, 1);
            }
        } else if ((!card.purgeOnUse) && card.type == AbstractCard.CardType.SKILL) {
            ActionUtil.addPower(owner, new EntanglePower(owner));
        } else if ((!card.purgeOnUse) && card.type == AbstractCard.CardType.POWER) {
            ActionUtil.addPower(owner, new EnergizedPower(owner, 1));
        }
    }
}

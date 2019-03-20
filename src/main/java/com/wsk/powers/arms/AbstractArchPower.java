package com.wsk.powers.arms;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.wsk.actions.ActionUtil;
import com.wsk.patches.ArmsEnum;

/**
 * @author wsk1103
 * @date 2019/3/20
 * @description 描述
 */
public abstract class AbstractArchPower extends AbstractArmsPower {

    String basePower = " 弩 。";

    AbstractArchPower() {
        super(ArmsEnum.Arch);
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
                            ActionUtil.poisonPower(AbstractDungeon.player, m, 2);
                        }
                    }
                }
            } else {
                AbstractMonster m = null;
                if (action.target != null) {
                    m = (AbstractMonster) action.target;
                }
                ActionUtil.poisonPower(AbstractDungeon.player, m, 2);
            }
        }
    }
}

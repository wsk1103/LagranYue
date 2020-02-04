package com.wsk.powers.arms;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.wsk.actions.ActionUtil;
import com.wsk.cards.AbstractArmsCard;
import com.wsk.patches.ArmsEnum;
import com.wsk.powers.proficiency.AerialAcePower;

/**
 * @author wsk1103
 * @date 2019/3/20
 * @description 描述
 */
public abstract class AbstractShieldPower extends AbstractArmsPower {

    String basePower = " 盾 。";

    public AbstractShieldPower() {
        super(ArmsEnum.Shield);
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if (card instanceof AbstractArmsCard) {
            return;
        }
        if ((!card.purgeOnUse) && card.type == AbstractCard.CardType.ATTACK) {
            ActionUtil.weakPower(owner, owner, 1);
        } else if ((!card.purgeOnUse) && card.type == AbstractCard.CardType.SKILL) {
            ActionUtil.gainBlockAction(AbstractDungeon.player, 2);
        } else if ((!card.purgeOnUse) && card.type == AbstractCard.CardType.POWER) {
            ActionUtil.addPower(AbstractDungeon.player, new AerialAcePower(AbstractDungeon.player, 1));
        }
        super.onAfterUseCard(card, action);
    }
}

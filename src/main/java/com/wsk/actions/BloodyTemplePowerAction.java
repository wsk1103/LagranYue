package com.wsk.actions;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.wsk.cards.attack.AttackLuEnCard;

/**
 * @author wsk1103
 * @date 2019/3/2
 * @desc 鲜血神殿
 */
public class BloodyTemplePowerAction {

    public static void action(AbstractPlayer owner, int amount, boolean upgraded) {
        CustomCard card = new AttackLuEnCard();
        if (upgraded) {
            card.upgrade();
        }
        for (int i = 0; i < amount; i++) {
            cardAddToHand(owner, card);
        }

    }

    static void cardAddToHand(AbstractPlayer owner, CustomCard card) {
        owner.hand.addToTop(card);
        owner.hand.refreshHandLayout();
        owner.hand.applyPowers();
    }

}

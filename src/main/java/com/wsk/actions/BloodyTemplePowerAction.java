package com.wsk.actions;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.wsk.cards.attack.AttackLuEnCard;

/**
 * @author wsk1103
 * @date 2019/3/2
 * @desc 一句话说明
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
//        Random random = new Random();
        owner.hand.addToTop(card);
        owner.hand.refreshHandLayout();
        owner.hand.applyPowers();
//        int temp = random.nextInt(3);
//        switch (temp) {
//            case 0:
//                ActionUtil.weakPower(owner, owner, 1);
//                break;
//            case 1:
//                ActionUtil.vulnerablePower(owner, owner, 1);
//                break;
//            case 2:
//                ActionUtil.frailPower(owner, owner, 1);
//                break;
//            default:
//                break;
//        }
    }

}

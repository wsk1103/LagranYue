package com.wsk.actions;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.wsk.cards.skill.DefendLuEnCard;
import com.wsk.cards.skill.ForgingLuEnCard;

import java.util.Random;

/**
 * @author wsk1103
 * @date 2019/3/2
 * @desc 一句话说明
 */
public class DarkTemplePowerAction {

    public static void action(AbstractPlayer owner, int amount, boolean upgraded) {
        Random random = new Random();
        for (int i = 0; i < amount; i++) {
            CustomCard card;
            if (random.nextInt(100) % 2 == 0) {
                card = new ForgingLuEnCard();
            } else {
                card = new DefendLuEnCard();
            }
            if (upgraded) {
                card.upgrade();
            }
            BloodyTemplePowerAction.cardAddToHand(owner, card);

        }

    }

}

package com.wsk.utils;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.wsk.LagranYue;

import java.util.ArrayList;

/**
 * @author wsk1103
 * @date 2019/3/10
 * @description 描述
 */
public class AllCards {

    public static final ArrayList<AbstractCard> CARDS = LagranYue.ALL_CARS;

    public static final ArrayList<AbstractCard> UN_COMMON_CARD = new ArrayList<>();

    public static final ArrayList<AbstractCard> COMMON_CARD = new ArrayList<>();

    public static final ArrayList<AbstractCard> RARE_CARD = new ArrayList<>();

//    public static final int[] UN_RANDOM_LIST = new int[1000];
//    public static final int[] COM_RANDOM_LIST = new int[1000];
//    public static final int[] RARE_RANDOM_LIST = new int[1000];

    static {
        unCard();
        comCard();
        rareCard();
//        unRandom();
//        comRandom();
//        rareRandom();
//        Collections.shuffle(UN_COMMON_CARD);
//        Collections.shuffle(COMMON_CARD);
//        Collections.shuffle(RARE_CARD);
    }

    private static void unCard() {
        for (AbstractCard card : CARDS) {
            if (card.rarity == AbstractCard.CardRarity.UNCOMMON) {
                UN_COMMON_CARD.add(card);
            }
        }
    }

    private static void comCard() {
        for (AbstractCard card : CARDS) {
            if (card.rarity == AbstractCard.CardRarity.COMMON) {
                COMMON_CARD.add(card);
            }
        }
    }

    private static void rareCard() {
        for (AbstractCard card : CARDS) {
            if (card.rarity == AbstractCard.CardRarity.RARE) {
                RARE_CARD.add(card);
            }
        }
    }

//    private static void unRandom() {
//        Random random = new Random();
//        for (int i = 0; i < UN_RANDOM_LIST.length; i++) {
//            UN_RANDOM_LIST[i] = random.nextInt(UN_COMMON_CARD.size());
//        }
//    }
//
//    private static void comRandom() {
//        Random random = new Random();
//        for (int i = 0; i < COM_RANDOM_LIST.length; i++) {
//            COM_RANDOM_LIST[i] = random.nextInt(COMMON_CARD.size());
//        }
//    }
//
//    private static void rareRandom() {
//        Random random = new Random();
//        for (int i = 0; i < RARE_RANDOM_LIST.length; i++) {
//            RARE_RANDOM_LIST[i] = random.nextInt(RARE_CARD.size());
//        }
//    }

//    public static int[] getUnRandomList() {
//        comRandom();
//        return UN_RANDOM_LIST;
//    }
//
//    public static int[] getComRandomList() {
//        unRandom();
//        return COM_RANDOM_LIST;
//    }
//
//    public static int[] getRareRandomList() {
//        rareRandom();
//        return RARE_RANDOM_LIST;
//    }

}

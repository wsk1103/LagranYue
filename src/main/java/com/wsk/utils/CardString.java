package com.wsk.utils;

import basemod.abstracts.CustomCard;

/**
 * @author wsk1103
 * @date 2019/2/14
 * @desc 卡牌的固定文字
 */
public class CardString {

    /**
     * 默认卡牌前缀
     */
    public static final String PRE_CARD = "MyMod:";

    public static String getPreCard(Class<? extends CustomCard> customCard) {
        return PRE_CARD + customCard.getName();
    }
}

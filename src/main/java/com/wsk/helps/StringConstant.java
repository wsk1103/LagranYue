package com.wsk.helps;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;

/**
 * @author wsk1103
 * @date 2019/3/5
 * @description 描述
 */
public class StringConstant {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    public static final String cantUseMessage;
    public static final String Echo;
    public static final String Exhaust;
    public static final String Ethereal;
    public static final String Innate;
    public static final String Available;
    public static final String PetCallcantUse;
    public static final String IntentBlock_1;
    public static final String IntentBlock_2;
    public static final String IntentHeal_1;
    public static final String IntentHeal_2;
    public static final String IntentLighting_1;
    public static final String IntentLighting_2;
    public static final String IntentLighting_3;
    public static final String IntentLighting_4;
    public static final String IntentThrons_1;
    public static final String IntentThrons_2;
    public static final String Choose;

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("StringConstant");
        TEXT = uiStrings.TEXT;
        cantUseMessage = TEXT[0];
        Echo = TEXT[1];
        Exhaust = TEXT[2];
        Ethereal = TEXT[3];
        Innate = TEXT[4];
        Available = TEXT[5];
        PetCallcantUse = TEXT[6];
        IntentBlock_1 = TEXT[7];
        IntentBlock_2 = TEXT[8];
        IntentHeal_1 = TEXT[9];
        IntentHeal_2 = TEXT[10];
        IntentLighting_1 = TEXT[11];
        IntentLighting_2 = TEXT[12];
        IntentLighting_3 = TEXT[13];
        IntentLighting_4 = TEXT[14];
        IntentThrons_1 = TEXT[15];
        IntentThrons_2 = TEXT[16];
        Choose = TEXT[17];
    }
}


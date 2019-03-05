package com.wsk.helps;

import basemod.helpers.SuperclassFinder;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.ui.buttons.SingingBowlButton;
import com.megacrit.cardcrawl.ui.buttons.SkipCardButton;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * @author wsk1103
 * @date 2019/3/5
 * @description 描述
 */
public class ChooseHelper {
    public static void open(ArrayList<AbstractCard> group) {
        CardRewardScreen cardRewardScreen = AbstractDungeon.cardRewardScreen;

        try {
            Field codexF = SuperclassFinder.getSuperclassField(CardRewardScreen.class, "codex");
            codexF.setAccessible(true);
            Field discoveryF = SuperclassFinder.getSuperclassField(CardRewardScreen.class, "discovery");
            discoveryF.setAccessible(true);
            Field draftF = SuperclassFinder.getSuperclassField(CardRewardScreen.class, "draft");
            draftF.setAccessible(true);
            Field bowlButtonF = SuperclassFinder.getSuperclassField(CardRewardScreen.class, "bowlButton");
            bowlButtonF.setAccessible(true);
            Field skipButtonF = SuperclassFinder.getSuperclassField(CardRewardScreen.class, "skipButton");
            skipButtonF.setAccessible(true);
            Field CARD_TARGET_YF = SuperclassFinder.getSuperclassField(CardRewardScreen.class, "CARD_TARGET_Y");
            CARD_TARGET_YF.setAccessible(true);
            Method placeCardsM = CardRewardScreen.class.getDeclaredMethod("placeCards", Float.TYPE, Float.TYPE);
            placeCardsM.setAccessible(true);
            cardRewardScreen.rItem = null;
            codexF.set(cardRewardScreen, false);
            discoveryF.set(cardRewardScreen, true);
            cardRewardScreen.discoveryCard = null;
            draftF.set(cardRewardScreen, false);
            cardRewardScreen.codexCard = null;
            ((SingingBowlButton) bowlButtonF.get(cardRewardScreen)).hide();
            ((SkipCardButton) skipButtonF.get(cardRewardScreen)).hide();
            cardRewardScreen.onCardSelect = true;
            AbstractDungeon.topPanel.unhoverHitboxes();
            cardRewardScreen.rewardGroup = group;
            AbstractDungeon.isScreenUp = true;
            AbstractDungeon.screen = AbstractDungeon.CurrentScreen.CARD_REWARD;
//            AbstractDungeon.dynamicBanner.appear(StringConstant.Choose);
            placeCardsM.invoke(cardRewardScreen, (float) Settings.WIDTH / 2.0F, (Float) CARD_TARGET_YF.get(cardRewardScreen));
        } catch (NoSuchFieldException var9) {
            var9.printStackTrace();
        } catch (IllegalAccessException var10) {
            var10.printStackTrace();
        } catch (IllegalArgumentException var11) {
            var11.printStackTrace();
        } catch (InvocationTargetException var12) {
            var12.printStackTrace();
        } catch (NoSuchMethodException var13) {
            var13.printStackTrace();
        }

    }
}

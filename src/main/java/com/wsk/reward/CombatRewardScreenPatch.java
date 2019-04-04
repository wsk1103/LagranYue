package com.wsk.reward;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.screens.CombatRewardScreen;
import com.megacrit.cardcrawl.ui.buttons.DynamicButton;
import javassist.CtBehavior;

/**
 * @author wsk1103
 * @date 2019/3/8
 * @description 战斗结束后，奖励界面
 */
@SpirePatch(clz = CombatRewardScreen.class, method = "setupItemReward")
public class CombatRewardScreenPatch {

    public static int magicEyePower = 0;

    public static boolean rareGoldArmor = false;

    @SpireInsertPatch(locator = Locator.class)
    public static void insert(CombatRewardScreen instance) {
        //神性
//        for (int i = 0; i < divinityPower; i++) {
//            GainRelics.receiveRewards(instance.rewards);
//            divinityPower = 0;
//        }
//        for (int i = 0; i < magicPower; i++) {
//            GainPotions.receiveRewards(instance.rewards);
//            magicPower = 0;
//        }
        if (magicEyePower != 0) {
            GainRareCard.receiveRewards(instance.rewards);
        }
        magicEyePower = 0;
        if (rareGoldArmor) {
            GainRareCard.receiveRewards(instance.rewards);
        }
        rareGoldArmor = false;
    }


    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch)
                throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(DynamicButton.class, "hide");
            int[] found = LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher);
            return new int[]{found[0]};
        }
    }
}

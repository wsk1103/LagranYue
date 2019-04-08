package com.wsk.reward;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.potions.AbstractPotion;

import java.util.Random;

/**
 * @author wsk1103
 * @date 2019/3/20
 * @description 获取随机药物
 */
public class GainPotions {

    public static AbstractPotion receiveRewards() {
        AbstractPotion.PotionRarity rarity = AbstractPotion.PotionRarity.COMMON;
        Random random = new Random();
        int r = random.nextInt(100);
        if (r >= 90) {
            rarity = AbstractPotion.PotionRarity.RARE;
        } else if (r >= 60) {
            rarity = AbstractPotion.PotionRarity.UNCOMMON;
        }
        return AbstractDungeon.returnRandomPotion(rarity, false);

    }

}

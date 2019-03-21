package com.wsk.reward;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author wsk1103
 * @date 2019/3/8
 * @description 获取遗物
 */
public class GainRelics {

    public static AbstractRelic receiveRewards() {
/*        boolean hasRelic = false;
        for (RewardItem reward : rewards) {
            if (reward.type == RewardItem.RewardType.RELIC) {
                hasRelic = true;
                break;
            }
        }
        if (!hasRelic) {*/
        return AbstractDungeon.returnRandomRelic(getRandomRelic());
//        rewards.add(new RewardItem());
        /*        }*/

    }

    public static void receiveRewards(ArrayList<RewardItem> rewards) {
        rewards.add(new RewardItem(AbstractDungeon.returnRandomRelic(getRandomRelic())));
    }

    private static AbstractRelic.RelicTier getRandomRelic() {
        Random random = new Random();
        int r = random.nextInt(100);
        AbstractRelic.RelicTier tier = AbstractRelic.RelicTier.COMMON;
        if (r >= 91) {
            tier = AbstractRelic.RelicTier.BOSS;
        } else if (r >= 80) {
            tier = AbstractRelic.RelicTier.RARE;
        } else if (r >= 70) {
            tier = AbstractRelic.RelicTier.UNCOMMON;
        } else if (r >= 60) {
            tier = AbstractRelic.RelicTier.SHOP;
        }
        return tier;
    }
}

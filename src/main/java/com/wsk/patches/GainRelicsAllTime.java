package com.wsk.patches;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.RewardItem;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author wsk1103
 * @date 2019/3/8
 * @description 描述
 */
public class GainRelicsAllTime {

    public void receiveRewards(ArrayList<RewardItem> rewards) {
        boolean hasRelic = false;
        for (RewardItem reward : rewards) {
            if (reward.type == RewardItem.RewardType.RELIC) {
                hasRelic = true;
                break;
            }
        }
        if (!hasRelic) {
            Random random = new Random();
            int r = random.nextInt(100);
            AbstractRelic.RelicTier tier = AbstractRelic.RelicTier.COMMON;
            if (r >= 91) {
                tier = AbstractRelic.RelicTier.BOSS;
            } else if (r >= 80) {
                tier = AbstractRelic.RelicTier.RARE;
            } else if (r >= 65) {
                tier = AbstractRelic.RelicTier.UNCOMMON;
            } else if (r >= 45) {
                tier = AbstractRelic.RelicTier.SHOP;
            }
            rewards.add(new RewardItem(AbstractDungeon.returnRandomRelic(tier)));
        }

    }
}

package com.wsk.reward;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.RewardItem;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author wsk1103
 * @date 2019/3/20
 * @description 多获得一次卡牌奖励
 */
public class GainRareCard {

    public static void receiveRewards(ArrayList<RewardItem> rewards) {
        RewardItem item = new RewardItem(AbstractCard.CardColor.COLORLESS);
        ArrayList<AbstractCard> cards = item.cards;
        cards.clear();
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            int r = random.nextInt(100);
            if (r >= 80){
                cards.add(AbstractDungeon.getCardWithoutRng(AbstractCard.CardRarity.RARE));
            } else if (r >= 50) {
                cards.add(AbstractDungeon.getCardWithoutRng(AbstractCard.CardRarity.UNCOMMON));
            } else {
                cards.add(AbstractDungeon.getCardWithoutRng(AbstractCard.CardRarity.COMMON));
            }
        }
        rewards.add(item);
    }
}

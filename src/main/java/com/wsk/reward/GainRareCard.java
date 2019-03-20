package com.wsk.reward;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.RewardItem;

import java.util.ArrayList;

/**
 * @author wsk1103
 * @date 2019/3/20
 * @description 获得金卡
 */
public class GainRareCard {

    public static void receiveRewards(ArrayList<RewardItem> rewards) {
        RewardItem item = new RewardItem(AbstractCard.CardColor.COLORLESS);
        ArrayList<AbstractCard> cards = item.cards;
        cards.clear();
        cards.add(AbstractDungeon.getCardWithoutRng(AbstractCard.CardRarity.RARE));
        rewards.add(item);
    }
}

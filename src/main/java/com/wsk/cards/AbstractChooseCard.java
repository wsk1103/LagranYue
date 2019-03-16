package com.wsk.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;

import java.util.ArrayList;

/**
 * @author wsk1103
 * @date 2019/3/5
 * @description 定义打出卡牌时，可以选择打出的效果
 */
public abstract class AbstractChooseCard extends CustomCard {
    public AbstractChooseCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }

    protected ArrayList<String> chooseDesc = new ArrayList<>();

    public abstract void choose(int var1);

    protected CardGroup getChooseCardGroup() {
        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

        for (String s : this.chooseDesc) {
            AbstractCard c = this.makeStatEquivalentCopy();
            c.rawDescription = s;
            c.initializeDescription();
            group.addToTop(c);
        }

        return group;
    }
}

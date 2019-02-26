package com.wsk.cards;

/**
 * @author wsk1103
 * @date 2019/2/25
 * @desc 盾
 */
public abstract class AbstractShieldCard extends AbstractArmsCard{
    public AbstractShieldCard(String id, String name, String img, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }
}

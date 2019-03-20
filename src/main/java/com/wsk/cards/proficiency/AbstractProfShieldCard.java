package com.wsk.cards.proficiency;

import com.wsk.patches.ArmsEnum;

/**
 * @author wsk1103
 * @date 2019/3/20
 * @description 盾
 */
public abstract class AbstractProfShieldCard extends AbstractProficiencyCard {

    private final static ArmsEnum ARMS = ArmsEnum.Shield;

    public AbstractProfShieldCard(String id, String name, String imgUrl, int cost, String rawDescription, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, imgUrl, cost, rawDescription, type, color, rarity, target, ARMS);
    }
}

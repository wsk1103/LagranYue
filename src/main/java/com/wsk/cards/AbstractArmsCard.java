package com.wsk.cards;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.wsk.patches.ArmsEnum;

/**
 * @author wsk1103
 * @date 2019/2/25
 * @desc 定义武器卡牌
 */
public abstract class AbstractArmsCard extends AbstractChooseCard {

    /**
     * 兵器类型
     */
    public ArmsEnum arms;

    /**
     * 耐久度
     */
    public int durability = 0;
    public int baseDurability;

    public boolean isDurabilityModified = true;

    public AbstractArmsCard(String id, String name, String img, int cost,
                            String rawDescription, CardType type, CardColor color,
                            CardRarity rarity, CardTarget target, ArmsEnum arms) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
        this.arms = arms;
        upgradeDurability(0);
    }

    public void upgradeDurability(int durability) {
        if (AbstractDungeon.player != null) {
            if (AbstractDungeon.player.drawPile.contains(this) || AbstractDungeon.player.hand.contains(this) || AbstractDungeon.player.discardPile.contains(this) || AbstractDungeon.player.exhaustPile.contains(this)) {
                this.baseDurability += durability;
            }
            this.durability = this.baseDurability;
            if (durability > 0) {
                this.isDurabilityModified = true;
            }
        }
    }

}

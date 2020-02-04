package com.wsk.powers.arms;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.wsk.actions.ActionUtil;
import com.wsk.patches.ArmsEnum;

/**
 * @author wsk1103
 * @date 2019/2/26
 * @desc 武器能力
 */
public abstract class AbstractArmsPower extends AbstractPower {

    /**
     * 兵器类型
     */
    public ArmsEnum arms;

    /**
     * 兵器的等级
     */
    private int level = 1;

    /**
     * 兵器的耐久度
     */
    private int durability = 1;

    /**
     * 获得兵器的时候，给予的增益效果.
     */
    public abstract void hasArms();

    /**
     * 新增1层兵器等级
     */
    public abstract void upgradeArms();

    AbstractArmsPower(ArmsEnum arms){
        this.arms = arms;
        setDurability(amount);
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        amount--;
        if (amount <= 0) {
            ActionUtil.removePower(owner, this);
        }
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if (level <= 0) {
            ActionUtil.removePower(AbstractDungeon.player, this);
            this.level = 0;
        } else {
            this.level = level;
        }

    }

    public int upgrade() {
        this.flash();
        this.amount = this.durability;
        upgradeArms();
        setLevel(level + 1);
//        hasArms();
        updateDescription();
        return level;
    }

    public void initDurability() {
        this.flash();
        this.durability = this.amount;
    }

    public int upgrade(int level) {
        setLevel(this.level + level);
        return level;
    }

    public int subLevel() {
        setLevel(level - 1);
        return level;
    }

    public int subLevel(int level) {
        setLevel(this.level - level);
        return level;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }
}

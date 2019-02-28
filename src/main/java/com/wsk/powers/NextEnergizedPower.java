package com.wsk.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/28
 * @desc 一句话说明
 */
public class NextEnergizedPower extends AbstractPower {
    public static final String POWER_ID = "LagranYue:NextEnergizedPower";
    public static final String NAME = "冲刺";
    public static PowerType POWER_TYPE = PowerType.BUFF;
    public static final String IMG = "powers/w21.png";

    public static final String[] DESCRIPTIONS = {"下回合获得", "能量"};


    public NextEnergizedPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        updateDescription();//调用该方法（第36行）的文本更新函数,更新一次文本描叙，不可缺少。
        this.type = POWER_TYPE;//能力种类，可以不填写，会默认为PowerType.BUFF。PowerType.BUFF不会被人工制品抵消，PowerType.DEBUFF会被人工制品抵消。
        updateDescription();

    }

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        if (this.amount >= 999) {
            this.amount = 999;
        }
    }

    public void updateDescription() {
        this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
    }

    public void onEnergyRecharge() {
        flash();
        AbstractDungeon.player.gainEnergy(this.amount);
        AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(this.owner, this.owner, NextEnergizedPower.POWER_ID));
    }
}

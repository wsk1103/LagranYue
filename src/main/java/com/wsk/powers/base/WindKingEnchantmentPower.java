package com.wsk.powers.base;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/26
 * @desc 一句话说明
 */
public class WindKingEnchantmentPower extends AbstractPower {
    public static final String POWER_ID = "LagranYue:WindKingEnchantmentPower";//能力的ID，判断有无能力、能力层数时填写该Id而不是类名。
    public static final String NAME = "风王的结界";//能力的名称。

    public static String[] DESCRIPTIONS = {"接下来的4个回合内，每回合开始获得", "格挡 ，已经过了", "回合"};

    private static final String IMG = "powers/w29.png";
    private static PowerType POWER_TYPE = PowerType.BUFF;

    public WindKingEnchantmentPower(AbstractCreature owner, int amount) {//参数：owner-能力施加对象、amount-施加能力层数。在cards的use里面用ApplyPowerAction调用进行传递。
        this.ID = POWER_ID;
        this.DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;

        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
//        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        updateDescription();//调用该方法（第36行）的文本更新函数,更新一次文本描叙，不可缺少。
        this.type = POWER_TYPE;//能力种类，可以不填写，会默认为PowerType.BUFF。PowerType.BUFF不会被人工制品抵消，PowerType.DEBUFF会被人工制品抵消。
        updateDescription();
    }

    public void updateDescription() {
        this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + (4 - this.amount) + DESCRIPTIONS[2]);
    }

    @Override
    public void atStartOfTurn() {
        this.flash();
        if (this.amount > 0) {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.owner, this.owner, this.amount));
            //层数-1
            AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, WindKingEnchantmentPower.POWER_ID, 1));
        }
        if (this.amount <= 0) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, WindKingEnchantmentPower.POWER_ID));
        }
    }
}

package com.wsk.powers.base;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.wsk.actions.UncharnelAction;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/28
 * @desc 一句话说明
 */
public class DreamPowerUpgraded extends AbstractPower {
    public static final String POWER_ID = "LagranYue:DreamPowerUpgraded";
    public static final String NAME = "远离尘世的理想+";//能力的名称。

    public static String[] DESCRIPTIONS = {"每回合开始，选择获得", "张被消耗的卡牌，并且费用置为0。"};//需要调用变量的文本描叙，例如力量（Strength）、敏捷（Dexterity）等。

    private static final String IMG = "powers/w22.png";
    private static PowerType POWER_TYPE = PowerType.BUFF;

    public DreamPowerUpgraded(AbstractCreature owner, int amount) {//参数：owner-能力施加对象、amount-施加能力层数。在cards的use里面用ApplyPowerAction调用进行传递。
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
        this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
    }

    @Override
    public void atStartOfTurn() {
        this.flash();
        for (int i = 0; i < this.amount; i++) {
            AbstractDungeon.actionManager.addToBottom(new UncharnelAction(true, false));
        }
    }
}

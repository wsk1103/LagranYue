package com.wsk.powers.base;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/24
 * @desc 胜利契约
 */
public class VictoryPower extends AbstractPower {
    public static final String POWER_ID = "LagranYue:VictoryPower";
    public static final String NAME = "胜利契约";//能力的名称。

    //    public static final String DESCRIPITON = "攻击伤害增加印记的层数，当层数到达10层的时候，给予100点伤害";//不需要调用变量的文本描叙，例如钢笔尖（PenNibPower）。
    public static String[] DESCRIPTIONS = {"战斗结束后，恢复", "点生命值，增加", "点最大生命值。层数最大值为10。"};//需要调用变量的文本描叙，例如力量（Strength）、敏捷（Dexterity）等。

    private static final String IMG = "powers/w18.png";
    //以上两种文本描叙只需写一个，更新文本方法在第36行。
    private static PowerType POWER_TYPE = PowerType.BUFF;

    public VictoryPower(AbstractCreature owner, int amount) {//参数：owner-能力施加对象、amount-施加能力层数。在cards的use里面用ApplyPowerAction调用进行传递。
        this.ID = POWER_ID;
        this.DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;

        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
//        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        this.type = POWER_TYPE;//能力种类，可以不填写，会默认为PowerType.BUFF。PowerType.BUFF不会被人工制品抵消，PowerType.DEBUFF会被人工制品抵消。
        updateDescription();
    }

    public void updateDescription() {
        this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2]);
    }

    //触发时机：当一个房间获胜时。
    public void onVictory() {
        //ce层数最多为10层
        if (this.amount >= 10) {
            this.amount = 10;
        }
        this.flash();
        //恢复生命值
        AbstractDungeon.actionManager.addToBottom(new HealAction(this.owner, this.owner, this.amount));
        //增加生命值
        AbstractDungeon.player.increaseMaxHp(this.amount, false);
    }
}

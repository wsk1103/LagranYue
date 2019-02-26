package com.wsk.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/24
 * @desc 胜利契约
 */
public class VictoryPower extends AbstractPower {
    public static final String POWER_ID = "MyMod:VictoryPower";//能力的ID，判断有无能力、能力层数时填写该Id而不是类名。
    public static final String NAME = "胜利契约";//能力的名称。

    //    public static final String DESCRIPITON = "攻击伤害增加印记的层数，当层数到达10层的时候，给予100点伤害";//不需要调用变量的文本描叙，例如钢笔尖（PenNibPower）。
    public static final String[] DESCRIPTIONS = {"战斗结束后，恢复", "点生命值，增加", "点最大生命值。"};//需要调用变量的文本描叙，例如力量（Strength）、敏捷（Dexterity）等。

    private static final String IMG = "powers/BurningS.png";
    //以上两种文本描叙只需写一个，更新文本方法在第36行。
    private static PowerType POWER_TYPE = PowerType.BUFF;

    public VictoryPower(AbstractCreature owner, int amount) {//参数：owner-能力施加对象、amount-施加能力层数。在cards的use里面用ApplyPowerAction调用进行传递。
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        updateDescription();//调用该方法（第36行）的文本更新函数,更新一次文本描叙，不可缺少。
        this.type = POWER_TYPE;//能力种类，可以不填写，会默认为PowerType.BUFF。PowerType.BUFF不会被人工制品抵消，PowerType.DEBUFF会被人工制品抵消。
    }

    public void updateDescription() {
//        this.description = DESCRIPITON;//不需要调用变量的文本更新方式。
        this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2]);
        //this.description = (DESCRIPTIONS[0] + 变量1 + DESCRIPTIONS[1] + 变量2 + DESCRIPTIONS[3] + ······);需要调用变量的文本更新方式。
        //例： public static final String[] DESCRIPTIONS = {"在你回合开始时获得","点力量."};
        //   this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
        //   通过该方式更新后的文本:在你回合开始时获得amount层力量.
        //   另外一提，除变量this.amount（能力层数对应的变量）外，其他变量被赋值后需要人为调用updateDescription函数进行文本更新。
    }

    //触发时机：当一个房间获胜时。
    public void onVictory() {
        //ce层数最多为10层
        if (this.amount >= 10) {
            this.amount = 10;
        }
        //恢复生命值
        AbstractDungeon.actionManager.addToBottom(new HealAction(this.owner, this.owner, this.amount));
        //增加生命值
        AbstractDungeon.player.increaseMaxHp(this.amount, false);
    }
}

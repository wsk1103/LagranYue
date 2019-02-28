package com.wsk.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/24
 * @desc 死亡印记
 */
public class ImprintPower extends AbstractPower {
    public static final String POWER_ID = "LagranYue:ImprintPower";//能力的ID，判断有无能力、能力层数时填写该Id而不是类名。
    public static final String NAME = "死亡印记";//能力的名称。

    public static final String[] DESCRIPTIONS = {"造成伤害增加","点，当层数到达10层的时候，给予50点伤害。然后移除所有印记。"};//需要调用变量的文本描叙，例如力量（Strength）、敏捷（Dexterity）等。

    private static final String IMG = "powers/w19.png";
    //以上两种文本描叙只需写一个，更新文本方法在第36行。
    private static PowerType POWER_TYPE = PowerType.DEBUFF;

    private AbstractCreature source;

    public ImprintPower(AbstractCreature owner, AbstractCreature source, int amount) {//参数：owner-能力施加对象、amount-施加能力层数。在cards的use里面用ApplyPowerAction调用进行传递。
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        this.source = source;
        updateDescription();//调用该方法（第36行）的文本更新函数,更新一次文本描叙，不可缺少。
        this.type = POWER_TYPE;//能力种类，可以不填写，会默认为PowerType.BUFF。PowerType.BUFF不会被人工制品抵消，PowerType.DEBUFF会被人工制品抵消。
        updateDescription();
    }

    public void updateDescription() {
//        this.description = DESCRIPITON;//不需要调用变量的文本更新方式。
        this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
    }

    //造成伤害时，返回伤害数值
    public float atDamageFinalReceive(float damage, DamageInfo.DamageType damageType) {
        if (damageType == DamageInfo.DamageType.NORMAL) {
            return damage + this.amount;
        }
        return damage;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        //回合结束的时候，层数减少1
        AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(this.owner, this.owner, ImprintPower.POWER_ID, 1));
    }

    //触发时机：当回合开始时触发。
    public void atStartOfTurn() {
        if (this.amount >= 10) {
            if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                this.flashWithoutSound();
                AbstractDungeon.actionManager.addToBottom(new LoseHPAction(this.owner, this.source, 50, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, ImprintPower.POWER_ID));
            }
        }
    }
}

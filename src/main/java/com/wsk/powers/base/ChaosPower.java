package com.wsk.powers.base;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.wsk.actions.ActionUtil;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/3/2
 * @desc 一句话说明
 */
public class ChaosPower extends AbstractPower {
    public static final String POWER_ID = "LagranYue:ChaosPower";//能力的ID，判断有无能力、能力层数时填写该Id而不是类名。
    public static final String NAME = "混沌";//能力的名称。

    public static final String[] DESCRIPTIONS = {"临时使装备兵器的上限+1，装备兵器后消失。只能同时拥有1层混沌。回合结束时失去该能力。"};

    private static final String IMG = "powers/w30-2.png";
    private static PowerType POWER_TYPE = PowerType.BUFF;

    public ChaosPower(AbstractCreature owner, int amount) {//参数：owner-能力施加对象、amount-施加能力层数。在cards的use里面用ApplyPowerAction调用进行传递。
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        this.type = POWER_TYPE;//能力种类，可以不填写，会默认为PowerType.BUFF。PowerType.BUFF不会被人工制品抵消，PowerType.DEBUFF会被人工制品抵消。
        updateDescription();
    }

    public void updateDescription() {
        this.description = (DESCRIPTIONS[0]);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        this.flash();
        ActionUtil.removePower(owner, this);
    }
}

package com.wsk.powers.base;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.wsk.actions.ActionUtil;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/3/2
 * @desc 一句话说明
 */
public class ChaosPower extends AbstractPower {
    public static final String POWER_ID = "LagranYue:ChaosPower";
    public static final String NAME = "混沌";

    public static String[] DESCRIPTIONS = {"临时使装备兵器的上限+1，装备兵器后消失。只能同时拥有1层混沌。回合结束时失去该能力。"};

    private static final String IMG = "powers/w30-2.png";
    private static PowerType POWER_TYPE = PowerType.BUFF;

    public ChaosPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;

        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
//        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        this.type = POWER_TYPE;
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

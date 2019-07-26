package com.wsk.powers.base;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.wsk.actions.ActionUtil;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/3/1
 * @desc 熔炉
 */
public class FurnacePower extends AbstractPower {
    public static final String POWER_ID = "LagranYue:FurnacePower";//能力的ID，判断有无能力、能力层数时填写该Id而不是类名。
    public static final String NAME = "熔炉";//能力的名称。

    public static String[] DESCRIPTIONS = {"每回合开始，兵器获得", "次 锻造 "};

    private static final String IMG = "powers/w30-1.png";
    private static PowerType POWER_TYPE = PowerType.BUFF;

    public FurnacePower(AbstractCreature owner, int amount) {//参数：owner-能力施加对象、amount-施加能力层数。在cards的use里面用ApplyPowerAction调用进行传递。
        this.ID = POWER_ID;
        this.DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;

        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
        this.owner = owner;
        this.amount = amount;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        this.type = POWER_TYPE;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
    }

    @Override
    public void atStartOfTurn() {
        this.flash();
        ActionUtil.forgingAction(owner, 1, this.amount);
//        ActionUtil.forgingAction(owner, 1, amount);
    }
}



package com.wsk.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/15
 * @desc 每回合开始获取力量
 */
@Deprecated
public class ModRitualPower extends AbstractPower {
    public static final String POWER_ID = "MyMod:ModRitualPower";
    public static final String NAME = "来自WSK的祝福";
    private static AbstractPower.PowerType POWER_TYPE = AbstractPower.PowerType.BUFF;
    private static final String IMG = "powers/ritual.png";

    public static String[] DESCRIPTIONS;

    public ModRitualPower(AbstractCreature owner, int strAmt) {

        this.name = NAME;

        this.ID = POWER_ID;
        this.amount = strAmt;

        this.owner = owner;


        this.img = new Texture(CommonUtil.getResourcePath(IMG));

        this.type = POWER_TYPE;

        this.DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;

        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;

        updateDescription();
    }

    public void updateDescription() {
        this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
    }

    //触发时机：当玩家回合开始时触发。
    public void atStartOfTurn() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, this.amount), this.amount));

    }

}

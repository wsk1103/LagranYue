package com.wsk.powers.base;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
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
public class WindKingEnchantmentPowerUpgraded extends AbstractPower {
    public static final String POWER_ID = "LagranYue:WindKingEnchantmentPowerUpgraded";
    public static final String NAME = "风王的结界+";

    public static String[] DESCRIPTIONS = {"回合开始获得", "格挡 "};

    private static final String IMG = "powers/w29.png";
    private static PowerType POWER_TYPE = PowerType.BUFF;

    public WindKingEnchantmentPowerUpgraded(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;

        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
//        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        this.type = POWER_TYPE;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = (DESCRIPTIONS[0] + (this.amount * 4) + DESCRIPTIONS[1]);
    }

    @Override
    public void atStartOfTurn() {
        flash();
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(this.owner, this.owner, (this.amount * 4)));
    }
}


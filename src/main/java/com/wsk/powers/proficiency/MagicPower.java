package com.wsk.powers.proficiency;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.wsk.reward.GainPotions;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/3/20
 * @description 魔术
 */
public class MagicPower extends AbstractPower {
    public static final String POWER_ID = "LagranYue:MagicPower";

    public static String[] DESCRIPTIONS;

    private static final String IMG = "powers/6.png";
    private static PowerType POWER_TYPE = PowerType.BUFF;


    public MagicPower(AbstractCreature owner) {
        this.ID = POWER_ID;
        DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;
        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
        this.owner = owner;
        this.amount = -99;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        this.type = POWER_TYPE;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = (DESCRIPTIONS[0] + 1 + DESCRIPTIONS[1]);
    }

    @Override
    public void onVictory() {
        AbstractDungeon.getCurrRoom().addPotionToRewards(GainPotions.receiveRewards());
    }
}


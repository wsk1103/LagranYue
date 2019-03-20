package com.wsk.powers.proficiency;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/3/20
 * @description 失真
 */
public class DistortionPower extends AbstractPower {
    public static final String POWER_ID = "LagranYue:DistortionPower";

    public static String[] DESCRIPTIONS;

    private static final String IMG = "powers/w26.png";
    private static PowerType POWER_TYPE = PowerType.DEBUFF;


    public DistortionPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;
        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
        this.owner = owner;
        this.amount = amount;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        this.type = POWER_TYPE;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = (DESCRIPTIONS[0] + this.amount * 5);
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType damageType) {
        return damage + this.amount * 5;
    }
}
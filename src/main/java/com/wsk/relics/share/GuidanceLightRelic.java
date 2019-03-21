package com.wsk.relics.share;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.wsk.actions.ActionUtil;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/3/20
 * @description 描述
 */
public class GuidanceLightRelic extends CustomRelic {

    public static final String ID = "LagranYue:GuidanceLightRelic";
    public static final String IMG = "relics/w35.png";
    public static final String OUTLINE = "relics/w36.png";

    public GuidanceLightRelic() {
        super(ID, new Texture(CommonUtil.getResourcePath(IMG)), new Texture(CommonUtil.getResourcePath(OUTLINE)), RelicTier.COMMON, LandingSound.FLAT);
        this.counter = 0;
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new GuidanceLightRelic();
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction action) {
        ActionUtil.relicAboveCreatureAction(AbstractDungeon.player, this);
        if (targetCard.exhaust) {
            action.exhaustCard = false;
        }
        this.flash();
    }

    @Override
    public void onEquip() {
        --AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public void onUnequip() {
        ++AbstractDungeon.player.energy.energyMaster;
    }
}


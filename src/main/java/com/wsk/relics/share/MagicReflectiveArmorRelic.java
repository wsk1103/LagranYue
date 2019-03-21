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
public class MagicReflectiveArmorRelic extends CustomRelic {

    public static final String ID = "LagranYue:MagicReflectiveArmorRelic";
public static final String IMG = "relics/r7.png";
    public static final String OUTLINE = "relics/r8.png";

    public MagicReflectiveArmorRelic() {
        super(ID, new Texture(CommonUtil.getResourcePath(IMG)), new Texture(CommonUtil.getResourcePath(OUTLINE)), RelicTier.COMMON, LandingSound.FLAT);
        this.counter = 0;
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new MagicReflectiveArmorRelic();
    }

    @Override
    public void onVictory() {
        counter = 0;
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        ActionUtil.relicAboveCreatureAction(AbstractDungeon.player, this);
        if (counter >= 9) {
            ActionUtil.buffAction(AbstractDungeon.player, 1);
            counter = 0;
        }
        this.counter++;
        flash();
    }
}
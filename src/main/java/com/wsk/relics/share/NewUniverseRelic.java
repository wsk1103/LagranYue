package com.wsk.relics.share;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.wsk.actions.ActionUtil;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/3/20
 * @description 描述
 */
public class NewUniverseRelic extends CustomRelic {

    public static final String ID = "LagranYue:NewUniverseRelic";
    public static final String IMG = "relics/r17.png";
    public static final String OUTLINE = "relics/r18.png";


    public NewUniverseRelic() {
        super(ID, new Texture(CommonUtil.getResourcePath(IMG)), new Texture(CommonUtil.getResourcePath(OUTLINE)), RelicTier.SHOP, LandingSound.FLAT);
        this.counter = -1;
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new NewUniverseRelic();
    }


    @Override
    public void onManualDiscard() {

        flash();
        ActionUtil.gainEnergy(1);
        ActionUtil.drawCard(AbstractDungeon.player, 1);
    }
}

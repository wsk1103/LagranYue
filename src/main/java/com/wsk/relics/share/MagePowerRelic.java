package com.wsk.relics.share;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/3/20
 * @description 描述
 */
@Deprecated
public class MagePowerRelic extends CustomRelic {

    public static final String ID = "LagranYue:MagePowerRelic";
    public static final String IMG = "relics/r27.png";
    public static final String OUTLINE = "relics/r28.png";

    public MagePowerRelic() {
        super(ID, new Texture(CommonUtil.getResourcePath(IMG)), new Texture(CommonUtil.getResourcePath(OUTLINE)), RelicTier.BOSS, LandingSound.FLAT);
        this.counter = 0;
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new MagePowerRelic();
    }

    @Override
    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
    }
}


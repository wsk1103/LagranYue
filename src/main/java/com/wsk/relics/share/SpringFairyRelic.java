package com.wsk.relics.share;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.wsk.actions.ActionUtil;
import com.wsk.powers.relics.SpringFairyPower;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/3/20
 * @description 描述
 */
public class SpringFairyRelic extends CustomRelic {

    public static final String ID = "LagranYue:SpringFairyRelic";
    public static final String IMG = "relics/w35.png";
    public static final String OUTLINE = "relics/w36.png";

    public SpringFairyRelic() {
        super(ID, new Texture(CommonUtil.getResourcePath(IMG)), new Texture(CommonUtil.getResourcePath(OUTLINE)), RelicTier.UNCOMMON, LandingSound.FLAT);
        this.counter = -1;
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new SpringFairyRelic();
    }

    @Override
    public void atBattleStart() {
        flash();
        ActionUtil.addPower(AbstractDungeon.player, new SpringFairyPower(AbstractDungeon.player, 1));
    }
}
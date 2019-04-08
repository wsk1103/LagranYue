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
 * @description 死灵魔导书 战斗开始时，获得 #y壁垒 ，但获得的 #y格挡 减少 #r2 。
 */
public class GuideDeadRelic extends CustomRelic {

    public static final String ID = "LagranYue:GuideDeadRelic";
public static final String IMG = "relics/r13.png";
    public static final String OUTLINE = "relics/r14.png";

    public GuideDeadRelic() {
        super(ID, new Texture(CommonUtil.getResourcePath(IMG)), new Texture(CommonUtil.getResourcePath(OUTLINE)), RelicTier.RARE, LandingSound.FLAT);
        this.counter = -1;
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new GuideDeadRelic();
    }

    @Override
    public void atBattleStart() {
        ActionUtil.barricadePower(AbstractDungeon.player);
    }

    @Override
    public int onPlayerGainedBlock(float blockAmount) {
        if (blockAmount >= 2) {
            return (int) blockAmount - 2;
        } else {
            return (int) blockAmount;
        }
    }
}

package com.wsk.relics.share;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.cardManip.ExhaustCardEffect;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/7/25
 * @description 在每回合开始时获得 [E] 。15秒之后回合自动结束。
 */
public class TimeWarpRelice extends CustomRelic {

    public static final String ID = "LagranYue:WskJewelryRelic";
    public static final String IMG = "relics/r29.png";
    public static final String OUTLINE = "relics/r30.png";

    public TimeWarpRelice() {
        super(ID, new Texture(CommonUtil.getResourcePath(IMG)), new Texture(CommonUtil.getResourcePath(OUTLINE)), RelicTier.BOSS, LandingSound.FLAT);
        this.counter = 15;
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new WskJewelryRelic();
    }

    @Override
    public void atTurnStart() {
        this.counter = 15;
        while (this.counter > 0) {
            this.counter--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        AbstractDungeon.actionManager.cardQueue.clear();

        for (AbstractCard c : AbstractDungeon.player.limbo.group) {
            AbstractDungeon.effectList.add(new ExhaustCardEffect(c));
        }

        AbstractDungeon.player.limbo.group.clear();
        AbstractDungeon.player.releaseCard();
        AbstractDungeon.overlayMenu.endTurnButton.disable(true);
        this.counter = 15;
    }

}

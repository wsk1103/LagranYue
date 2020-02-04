package com.wsk.relics;

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
 * @date 2019/3/2
 * @desc 一句话说明
 */
public class DarkRelics extends CustomRelic {
    public static final String ID = "LagranYue:DarkRelics";
    public static final String IMG = "relics/w45.png";
    public static final String OUTLINE = "relics/w46.png";//遗物外轮廓路径

    public DarkRelics() {
        super(ID, new Texture(CommonUtil.getResourcePath(IMG)), new Texture(CommonUtil.getResourcePath(OUTLINE)), RelicTier.COMMON, LandingSound.CLINK);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new DarkRelics();
    }//复制该遗物信息的方法。

    public void onUseCard(AbstractCard c, UseCardAction action) {
        if (c.type == AbstractCard.CardType.ATTACK) {
            this.counter++;
            if (counter >= 4) {
                this.counter = 0;
                ActionUtil.forgingAction(AbstractDungeon.player);
            }
            this.flash();
        }
    }

    //触发时机：每一场战斗
    public void atPreBattle() {
        this.flash();
        this.counter = 0;
    }

}

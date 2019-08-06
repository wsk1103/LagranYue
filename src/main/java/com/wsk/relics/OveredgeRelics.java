package com.wsk.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.wsk.actions.ActionUtil;
import com.wsk.cards.AbstractArmsCard;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/3/5
 * @description 秘密·彷徨
 */
public class OveredgeRelics extends CustomRelic {
    public static final String ID = "LagranYue:OveredgeRelics";
    public static final String IMG = "relics/s3.png";
    public static final String OUTLINE = "relics/s4.png";//遗物外轮廓路径

    private static boolean isUsedArms = false;

    public OveredgeRelics() {
        super(ID, new Texture(CommonUtil.getResourcePath(IMG)), new Texture(CommonUtil.getResourcePath(OUTLINE)), RelicTier.RARE, LandingSound.CLINK);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new OveredgeRelics();
    }//复制该遗物信息的方法。

    public void onUseCard(AbstractCard c, UseCardAction action) {
        if (c instanceof AbstractArmsCard) {
            isUsedArms = true;
            this.flash();
        }
    }

    //触发时机：每一场战斗
    public void atPreBattle() {
        this.flash();
        isUsedArms = true;
    }

    @Override
    public void atTurnStart() {
        if (!isUsedArms) {
            ActionUtil.gainEnergy(1);
        }
        isUsedArms = false;
    }
}

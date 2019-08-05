package com.wsk.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.wsk.utils.ArmsUtil;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/3/5
 * @description 描述
 */
public class LuEnSwordRelics extends CustomRelic {
    public static final String ID = "LagranYue:LuEnSwordRelics";
    public static final String IMG = "relics/s5.png";//遗物图片路径
    public static final String OUTLINE = "relics/s6.png";//遗物外轮廓路径


    public LuEnSwordRelics() {
        super(ID, new Texture(CommonUtil.getResourcePath(IMG)), new Texture(CommonUtil.getResourcePath(OUTLINE)), RelicTier.COMMON, LandingSound.CLINK);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new LuEnSwordRelics();
    }//复制该遗物信息的方法。


    @Override
    public void atTurnStart() {
        int num = ArmsUtil.getArmsNum();
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(null,
                DamageInfo.createDamageMatrix(num, true),
                DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE));
    }
}


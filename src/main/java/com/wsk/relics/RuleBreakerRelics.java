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
 * @date 2019/2/27
 * @desc 万符必将破戒
 */
public class RuleBreakerRelics extends CustomRelic {
    public static final String ID = "LagranYue:RuleBreakerRelics";
    public static final String IMG = "relics/w43.png";
    public static final String OUTLINE = "relics/w44.png";//遗物外轮廓路径

    public static final String DESCRIPTION = "在本轮战斗中，每使用 #r8 张牌 ，执行 #b1 次 #y锻造。";//遗物效果的文本描叙。

    public RuleBreakerRelics() {
        super(ID, new Texture(CommonUtil.getResourcePath(IMG)), new Texture(CommonUtil.getResourcePath(OUTLINE)), RelicTier.UNCOMMON, LandingSound.CLINK);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new RuleBreakerRelics();
    }//复制该遗物信息的方法。

    //触发时机：当一张卡被打出且卡牌效果生效后。(参考死灵之书)
    public void onUseCard(AbstractCard c, UseCardAction action) {
//        if (c instanceof AbstractArmsCard) {
            this.counter++;
            if (this.counter % 8 == 0) {
                if (!c.purgeOnUse) {
                    ActionUtil.forgingAction(AbstractDungeon.player);
//                    action.exhaustCard = false;
                    this.flash();
                }
            }
//        }

    }

    //触发时机：在玩家回合开始时。
    public void atTurnStart() {
//        this.counter = 0;
    }

//    @Override
//    public void atBattleStart() {
//        this.flash();
//        this.counter = 0;
//    }

    //触发时机：每一场战斗
    public void atPreBattle() {
        this.flash();
        this.counter = 0;
    }

}
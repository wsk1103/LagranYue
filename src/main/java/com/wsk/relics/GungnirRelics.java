package com.wsk.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.wsk.cards.AbstractArmsCard;
import com.wsk.utils.CommonUtil;

import java.util.Random;

/**
 * @author wsk1103
 * @date 2019/2/27
 * @desc 大神宣言，在本场战斗，每当兵器被消耗的时候，不被消耗的概率 +1%，基础值0%。最大值10%
 */
public class GungnirRelics extends CustomRelic {
    public static final String ID = "LagranYue:GungnirRelics";
    public static final String IMG = "relics/w33.png";//遗物图片路径
    public static final String OUTLINE = "relics/w34.png";//遗物外轮廓路径

    private Random r = new Random();

    public static final String DESCRIPTION = "在本场战斗，每当 #y兵器 被 #y消耗 的时候，下一张不被 #y消耗 的概率 +1%。基础值0%。最大值10%";//遗物效果的文本描叙。

    public GungnirRelics() {
        super(ID, new Texture(CommonUtil.getResourcePath(IMG)), new Texture(CommonUtil.getResourcePath(OUTLINE)), RelicTier.COMMON, LandingSound.CLINK);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new GungnirRelics();
    }//复制该遗物信息的方法。

    //触发时机：当一张卡被打出且卡牌效果生效后。(参考死灵之书)
    public void onUseCard(AbstractCard c, UseCardAction action) {
        if (c instanceof AbstractArmsCard) {
            int i = r.nextInt(100) + 1;
            if (i <= this.counter + 20) {
                if (!c.purgeOnUse && action.exhaustCard) {
                    action.exhaustCard = false;
                    this.flash();
                }
            }
        }

    }

    //触发时机：当你消耗一张 卡牌时。(参考卡戍之灰)
    public void onExhaust(final AbstractCard card) {
        if (card instanceof AbstractArmsCard) {
            if (this.counter < 30) {
                this.counter += 1;
                this.flash();
            }
        }
    }

    //触发时机：每一场战斗
    public void atPreBattle() {
        this.counter = 0;
    }

}

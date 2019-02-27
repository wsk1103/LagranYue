package com.wsk.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.wsk.cards.AbstractArmsCard;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/27
 * @desc 万符必将破戒，在本次战斗中，一回合内，使用的第 N 张 #y兵器 牌将不会被 #y消耗 (N为3的倍数)
 */
public class RuleBreakerRelics extends CustomRelic {
    public static final String ID = "MyMod:EnkiduRelics";//遗物Id，添加遗物、替换遗物时填写该id而不是遗物类名。
    public static final String IMG = "relics/greedOoze.png";//遗物图片路径
    public static final String OUTLINE = "relics/greedOozeOutline.png";//遗物外轮廓路径

    public static final String DESCRIPTION = "在本次战斗中，一回合内，使用的第 N 张 #y兵器 牌将不会被 #y消耗 (N为3的倍数)";//遗物效果的文本描叙。

    public RuleBreakerRelics() {
        super(ID, new Texture(CommonUtil.getResourcePath(IMG)), new Texture(CommonUtil.getResourcePath(OUTLINE)), RelicTier.RARE, LandingSound.CLINK);
    }

    public String getUpdatedDescription() {
        return DESCRIPTION;
    }//文本更新方法，当你修改了DESCRIPTION时，调用该方法。

    public AbstractRelic makeCopy() {
        return new RuleBreakerRelics();
    }//复制该遗物信息的方法。

    //触发时机：当一张卡被打出且卡牌效果生效后。(参考死灵之书)
    public void onUseCard(AbstractCard c, UseCardAction action) {
        if (c instanceof AbstractArmsCard) {
            this.counter++;
            if (this.counter % 3 == 0)
                if (!c.purgeOnUse && action.exhaustCard) {
                    action.exhaustCard = false;
                    this.flash();
                }
        }

    }

    //触发时机：在玩家回合开始时。
    public void atTurnStart() {
        this.counter = 0;
    }

    //触发时机：每一场战斗
    public void atPreBattle() {
        this.counter = 0;
    }

}
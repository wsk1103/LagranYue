package com.wsk.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.wsk.cards.AbstractArmsCard;
import com.wsk.powers.DoubleArmsPower;
import com.wsk.utils.CommonUtil;

import java.util.Random;

/**
 * @author wsk1103
 * @date 2019/2/27
 * @desc 无限剑制，在本场战斗，每当使用武器卡牌的时候，获得1层 能力-武器大师 的概率 + 1%。基础0%，最大5%
 */
public class UnlimitedBladeWorksRelics extends CustomRelic {
    public static final String ID = "LagranYue:UnlimitedBladeWorksRelics";//遗物Id，添加遗物、替换遗物时填写该id而不是遗物类名。
    public static final String IMG = "relics/w37.png";//遗物图片路径
    public static final String OUTLINE = "relics/w38.png";//遗物外轮廓路径

    private Random r = new Random();

    public static final String DESCRIPTION = "在本场战斗，每当使用 #y兵器 卡牌的时候，获得1层 #y能力:武器大师 的概率 + 1%。基础0%，最大5%";//遗物效果的文本描叙。

    public UnlimitedBladeWorksRelics() {
        super(ID, new Texture(CommonUtil.getResourcePath(IMG)), new Texture(CommonUtil.getResourcePath(OUTLINE)), RelicTier.BOSS, LandingSound.HEAVY);
    }

    public String getUpdatedDescription() {
        return DESCRIPTION;
    }//文本更新方法，当你修改了DESCRIPTION时，调用该方法。

    public AbstractRelic makeCopy() {
        return new UnlimitedBladeWorksRelics();
    }//复制该遗物信息的方法。

    //触发时机：当一张卡被打出且卡牌效果生效后。(参考死灵之书)
    public void onUseCard(AbstractCard c, UseCardAction action) {
        if (c instanceof AbstractArmsCard) {
            this.flash();
            if (this.counter < 5) {
                this.counter++;
            }
            int i = r.nextInt(100) + 1;
            if (i <= this.counter) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                        new DoubleArmsPower(AbstractDungeon.player, 1), 1));
            }
        }
    }

    //触发时机：每一场战斗
    public void atPreBattle() {
        this.counter = 0;
    }

}
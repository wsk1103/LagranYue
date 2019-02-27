package com.wsk.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.wsk.cards.AbstractArmsCard;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/27
 * @desc 妄想心音，在一回合内，打出第3张兵器牌后，获得一层 缓冲
 */
public class ZabaniyaRelics extends CustomRelic {
    public static final String ID = "MyMod:ZabaniyaRelics";//遗物Id，添加遗物、替换遗物时填写该id而不是遗物类名。
    public static final String IMG = "relics/w41.png";//遗物图片路径
    public static final String OUTLINE = "relics/w42.png";//遗物外轮廓路径

    public static final String DESCRIPTION = "在一回合内，打出第3张兵器牌后，获得一层 #y缓冲 ";//遗物效果的文本描叙。

    public ZabaniyaRelics() {
        super(ID, new Texture(CommonUtil.getResourcePath(IMG)), new Texture(CommonUtil.getResourcePath(OUTLINE)), RelicTier.UNCOMMON, LandingSound.FLAT);
        //参数：ID-遗物Id，new Texture(Gdx.files.internal(IMG))-遗物图片，new Texture(Gdx.files.internal(OUTLINE))-遗物轮廓，RelicTier.BOSS-遗物种类，LandingSound.FLAT-遗物音效。
    }

    public String getUpdatedDescription() {
        return DESCRIPTION;
    }//文本更新方法，当你修改了DESCRIPTION时，调用该方法。

    public AbstractRelic makeCopy() {
        return new ZabaniyaRelics();
    }//复制该遗物信息的方法。

    //触发时机：当一张卡被打出且卡牌效果生效后
    public void onUseCard(final AbstractCard card, final UseCardAction useCardAction) {
        if (card instanceof AbstractArmsCard) {
            this.counter ++;
            if (counter >= 3) {
                this.flash();
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                        new BufferPower(AbstractDungeon.player, 1), 1));
                this.counter = 0;
            }
        }
    }

    //触发时机：在玩家回合开始时。
    public void atTurnStart() {
        this.counter = 0;
    }

}

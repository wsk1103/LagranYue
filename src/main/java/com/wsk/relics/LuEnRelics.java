package com.wsk.relics;

import basemod.abstracts.CustomCard;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.wsk.cards.AbstractArmsCard;
import com.wsk.cards.attack.AttackLuEnCard;
import com.wsk.cards.skill.DefendLuEnCard;
import com.wsk.cards.skill.ForgingLuEnCard;
import com.wsk.utils.CommonUtil;

import java.util.Random;

/**
 * @author wsk1103
 * @date 2019/3/2
 * @desc 一句话说明
 */
public class LuEnRelics extends CustomRelic {
    public static final String ID = "LagranYue:LuEnRelics";
    public static final String IMG = "relics/w35.png";//遗物图片路径
    public static final String OUTLINE = "relics/w36.png";//遗物外轮廓路径

    private Random random = new Random();

    public LuEnRelics() {
        super(ID, new Texture(CommonUtil.getResourcePath(IMG)), new Texture(CommonUtil.getResourcePath(OUTLINE)), RelicTier.RARE, LandingSound.CLINK);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new LuEnRelics();
    }//复制该遗物信息的方法。

    public void onUseCard(AbstractCard c, UseCardAction action) {
        if (c instanceof AbstractArmsCard) {
            int temp = random.nextInt(100);
            CustomCard card = null;
            if (temp < 20) {
                card = new AttackLuEnCard();
            } else if (temp < 40) {
                card = new DefendLuEnCard();
            } else if (temp < 60) {
                card = new ForgingLuEnCard();
            }
            if (card != null) {
                AbstractDungeon.player.hand.addToTop(card);
                AbstractDungeon.player.hand.refreshHandLayout();
                AbstractDungeon.player.hand.applyPowers();
                this.flash();
            }
        }
    }

    //触发时机：每一场战斗
    public void atPreBattle() {
        this.flash();
    }

}

package com.wsk.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.wsk.cards.AbstractArmsCard;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/3/5
 * @description 幻想崩坏
 */
public class BrokenPhantasmRelics extends CustomRelic {
    public static final String ID = "LagranYue:BrokenPhantasmRelics";
    public static final String IMG = "relics/s1.png";//遗物图片路径
    public static final String OUTLINE = "relics/s2.png";//遗物外轮廓路径

    private static boolean onlyOne = true;

    public BrokenPhantasmRelics() {
        super(ID, new Texture(CommonUtil.getResourcePath(IMG)), new Texture(CommonUtil.getResourcePath(OUTLINE)), RelicTier.BOSS, LandingSound.CLINK);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[2] + this.DESCRIPTIONS[0] + 1 + this.DESCRIPTIONS[1];
    }

    public AbstractRelic makeCopy() {
        return new BrokenPhantasmRelics();
    }//复制该遗物信息的方法。


    @Override
    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (this.counter < 1 && card instanceof AbstractArmsCard) {
            ++this.counter;
            if (this.counter >= 1) {
                this.flash();
            }
        }

    }

    @Override
    public boolean canPlay(AbstractCard card) {
        if (this.counter >= 1 && card instanceof AbstractArmsCard) {
            card.cantUseMessage = this.DESCRIPTIONS[3] + 1 + this.DESCRIPTIONS[1];
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onPlayerEndTurn() {
        this.counter = 0;
    }

    @Override
    public void onVictory() {
        this.counter = 0;
    }
}
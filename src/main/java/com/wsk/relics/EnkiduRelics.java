package com.wsk.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.wsk.cards.AbstractArmsCard;
import com.wsk.powers.base.DoubleArmsPower;
import com.wsk.utils.ArmsUtil;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/27
 * @desc 天之锁, 切换兵器的时候，保留第一次的增益效果.
 */
public class EnkiduRelics extends CustomRelic {
    public static final String ID = "LagranYue:EnkiduRelics";
    public static final String IMG = "relics/w31.png";
    public static final String OUTLINE = "relics/w32.png";

//    //记录第一次保存第一次切换武器，当被使用过后，会被置为 false
//    private static boolean once = true;

    public static final String DESCRIPTION = "每次战斗仅一次，切换 #y兵器 的时候，保留之前一把武器获得的力量/敏捷加成效果。";

    public EnkiduRelics() {
        super(ID, new Texture(CommonUtil.getResourcePath(IMG)), new Texture(CommonUtil.getResourcePath(OUTLINE)), RelicTier.STARTER, LandingSound.FLAT);
        this.counter = 2;
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new EnkiduRelics();
    }//复制该遗物信息的方法。

    @Override
    public void onPlayCard(final AbstractCard c, final AbstractMonster m) {//参数：c-使用的卡牌，m-目标敌人。
        if (c instanceof AbstractArmsCard) {
            if (counter == 1) {
                this.flash();
            }
            //如果该遗物还有效果，闪烁一下.
            counter--;
        }
    }//触发时机：当一张卡被打出且卡牌效果生效前。

    @Override
    public void atBattleStart() {
        counter = 2;
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new DoubleArmsPower(AbstractDungeon.player, 0), 0, AbstractGameAction.AttackEffect.POISON));

    }

    @Override
    public void onVictory() {
        //重置
//        EnkiduRelics.once = true;
        ArmsUtil.setArms();
    }


//    public static boolean getOnce(){
//        return once;
//    }
//
//    public static void setOnce() {
//        once = false;
//    }
}

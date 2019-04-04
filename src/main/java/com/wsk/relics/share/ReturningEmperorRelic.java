package com.wsk.relics.share;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.wsk.actions.ActionUtil;
import com.wsk.utils.CommonUtil;

import java.util.ArrayList;

/**
 * @author wsk1103
 * @date 2019/3/20
 * @description 描述
 */
public class ReturningEmperorRelic extends CustomRelic {

    public static final String ID = "LagranYue:ReturningEmperorRelic";
public static final String IMG = "relics/r11.png";
    public static final String OUTLINE = "relics/r12.png";

    public ReturningEmperorRelic() {
        super(ID, new Texture(CommonUtil.getResourcePath(IMG)), new Texture(CommonUtil.getResourcePath(OUTLINE)), RelicTier.RARE, LandingSound.FLAT);
        this.counter = 0;
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new ReturningEmperorRelic();
    }

    @Override
    public void onVictory() {
        counter = 0;
    }

    @Override
    public void atTurnStart() {
        flash();
        if (counter != 0 && counter % 3 == 0) {
            ArrayList<AbstractPower> powers = AbstractDungeon.player.powers;
            a:for (AbstractPower power : powers) {
                if (power.type == AbstractPower.PowerType.DEBUFF) {
                    ActionUtil.removePower(AbstractDungeon.player, power);
                    ArrayList<AbstractMonster> monsters = AbstractDungeon.getCurrRoom().monsters.monsters;
                    for (AbstractMonster monster : monsters) {
                        if (monster.isDead) {
                            continue;
                        }
                        ActionUtil.addPower(monster, power);
                        break a;
                    }
                }
            }
        }
        counter++;
    }

}
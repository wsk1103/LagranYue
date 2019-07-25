package com.wsk.relics.share;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.wsk.actions.ActionUtil;
import com.wsk.utils.CommonUtil;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author wsk1103
 * @date 2019/3/20
 * @description 每过 #b3 个回合，随机使 #b1 个 #gBuff/DeBuff 增加 #b1 层。
 */
public class WskJewelryRelic extends CustomRelic {

    public static final String ID = "LagranYue:WskJewelryRelic";
    public static final String IMG = "relics/r29.png";
    public static final String OUTLINE = "relics/r30.png";

    public WskJewelryRelic() {
        super(ID, new Texture(CommonUtil.getResourcePath(IMG)), new Texture(CommonUtil.getResourcePath(OUTLINE)), RelicTier.RARE, LandingSound.FLAT);
        this.counter = 0;
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new WskJewelryRelic();
    }

    @Override
    public void atTurnStart() {
        counter++;
        if (counter != 0 && counter % 3 == 0) {
            ArrayList<AbstractPower> powers = AbstractDungeon.player.powers;
            if (powers != null && powers.size() > 0) {
                Random random = new Random();
                AbstractPower power = powers.get(random.nextInt(powers.size()));
                power.amount = 1;
                ActionUtil.addPower(AbstractDungeon.player, power);
            }
        }
        flash();
    }
}


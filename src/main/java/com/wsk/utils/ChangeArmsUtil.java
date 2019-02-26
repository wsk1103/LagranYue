package com.wsk.utils;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.wsk.powers.*;

/**
 * @author wsk1103
 * @date 2019/2/25
 * @desc 切换兵器时，需要减到之前兵器增加的力量
 */
public class ChangeArmsUtil {

    public static void change(AbstractPlayer p) {
        //装备数量+1
        DoubleArmsPower.addArms();
        //1. 判断有没有双持这个能力
        boolean doubleArms = p.hasPower(DoubleArmsPower.POWER_ID);
        //如果拥有
        if (doubleArms) {
            //层数小于2，直接返回，表示可以继续装备
            if (DoubleArmsPower.getArms() <= AbstractDungeon.player.getPower(DoubleArmsPower.POWER_ID).amount) {
                return;
            }
        }
        //移除所有装备
        for (AbstractPower power : AbstractDungeon.player.powers) {
            if (power instanceof AbstractArmsPower) {
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, power.ID));
                //装备数量-1
                DoubleArmsPower.subArms();
            }
        }
    }

}

package com.wsk.utils;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.wsk.powers.AbstractArmsPower;
import com.wsk.powers.DoubleArmsPower;
import com.wsk.relics.EnkiduRelics;

/**
 * @author wsk1103
 * @date 2019/2/25
 * @desc 切换兵器时，需要减到之前兵器增加的力量
 */
public class ChangeArmsUtil {

    //当连续2次使用炽天赋7圆环的时候，会直接移除所有的壁垒。
    private static boolean hasRings = false;

    //记录装备的兵器
    private static int arms = 0;

    public static void change(AbstractCreature p) {
        //1. 判断有没有双持这个能力
        boolean doubleArms = p.hasPower(DoubleArmsPower.POWER_ID);
        //如果拥有
        if (doubleArms) {
            //层数小于2，直接返回，表示可以继续装备
            if (getArmsNum() <= p.getPower(DoubleArmsPower.POWER_ID).amount) {
                arms ++;
                return;
            }
        }
        //移除所有装备
        for (AbstractPower power : AbstractDungeon.player.powers) {
            if (power instanceof AbstractArmsPower) {
                AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(p, p, power.ID));
            }
        }
        arms = 0;
        arms ++;
    }

    //移除第一个兵器
    public static void changeOne(AbstractPlayer p) {
        for (AbstractPower power : AbstractDungeon.player.powers) {
            if (power instanceof AbstractArmsPower) {
                AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(p, p, power.ID));
                break;
            }
        }
    }

    //获得装备的兵器的数量
    public static int getArmsNum() {
//        int result = 0;
//        for (AbstractPower power : p.powers) {
//            if (power instanceof AbstractArmsPower) {
//                result ++;
//            }
//        }
//        return result;
        return arms;
    }

    public static void setArms(){
        arms = 0;
    }

    public static boolean retain() {
        if (EnkiduRelics.getOnce()) {
            EnkiduRelics.setOnce();
            return true;
        }
        return false;
    }

    public static boolean isHasRings() {
        return hasRings;
    }

    public static void setHasRings(boolean b) {
        hasRings = b;
    }

}

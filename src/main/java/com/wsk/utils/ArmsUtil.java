package com.wsk.utils;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.wsk.actions.ActionUtil;
import com.wsk.actions.ForgingAction;
import com.wsk.powers.arms.AbstractArmsPower;
import com.wsk.powers.base.ChaosPower;
import com.wsk.powers.base.DoubleArmsPower;
import com.wsk.powers.base.MaxArmsPliesPower;
import com.wsk.relics.EnkiduRelics;

/**
 * @author wsk1103
 * @date 2019/2/25
 * @desc 切换兵器时，需要减到之前兵器增加的力量
 */
public class ArmsUtil {

    //当连续2次使用炽天赋7圆环的时候，会直接移除所有的壁垒。
    private static boolean hasRings = false;

    //记录装备的兵器
    private static int arms = 0;

    //临时使装备兵器的数量+1
    private static boolean temporaryArms = false;

    //装备兵器
    public static void addOrChangArms(AbstractCreature p, AbstractArmsPower armsPower) {

        if (areYouHasArmsPower(armsPower)) {
            //使用相同兵器后，增加相应的层数
            ForgingAction.addArmsNum(armsPower, armsPower.amount);
            setTemporaryArms(false);
            return;
//            return false;
        }
        if (isTemporaryArms()) {
            arms++;
            ActionUtil.addArms(p, armsPower);
            setTemporaryArms(false);
            return;
        }
        arms = getArmsNum();
        //1. 判断有没有双持这个能力
        boolean doubleArms = p.hasPower(DoubleArmsPower.POWER_ID);
        //如果拥有
        if (doubleArms) {
            arms++;
            //层数小于2，直接返回，表示可以继续装备
            if (getArmsNum() <= p.getPower(DoubleArmsPower.POWER_ID).amount) {
                ActionUtil.addArms(p, armsPower);
                return;
            }
        }
        removeAllArms();
//        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, armsPower, armsPower.amount));
//        armsPower.hasArms();
        ActionUtil.addArms(p, armsPower);
        arms = 1;
    }

    //移除所有武器
    public static void removeAllArms() {
        //移除所有装备
        for (AbstractPower power : AbstractDungeon.player.powers) {
            if (power instanceof AbstractArmsPower) {
                AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, power.ID));
            }
        }
    }

    //判断是否拥有该兵器
    private static boolean areYouHasArmsPower(AbstractArmsPower arms) {

        for (AbstractPower power : AbstractDungeon.player.powers) {
            if (power instanceof AbstractArmsPower) {
                boolean eq = power.ID.equals(arms.ID);
                if (eq) {
                    return true;
                }
            }
        }
        return false;
    }

    //移除第i个兵器
    public static void removeOnce(AbstractPlayer p, int i) {
        int temp = 1;
        for (AbstractPower power : AbstractDungeon.player.powers) {
            if (power instanceof AbstractArmsPower) {
                if (temp == i) {
                    AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(p, p, power.ID));
                    arms--;
                    break;
                }
                temp++;
            }
        }
    }

    //移除第i个兵器之后的所有兵器
    public static void removeArmsAfter(int i) {
        int temp = 1;
        for (AbstractPower power : AbstractDungeon.player.powers) {
            if (power instanceof AbstractArmsPower) {
                if (temp > i) {
                    AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, power.ID));
                    arms--;
                }
                temp++;
            }
        }
    }

    //获得i件兵器的层数
    public static int onceArmsPlies(int i) {
        int temp = 1;
        for (AbstractPower power : AbstractDungeon.player.powers) {
            if (power instanceof AbstractArmsPower) {
                if (temp == i) {
                    return power.amount;
                } else {
                    temp++;
                }
            }
        }
        if (temp > i) {
            temp = 0;
        }
        return temp;
    }

    //当前武器的层数
    public static int currentArmsNum(AbstractArmsPower arms) {
        if (AbstractDungeon.player.hasPower(arms.ID)) {
            return AbstractDungeon.player.getPower(arms.ID).amount;
        }
        return 0;
    }

    //第i件兵器之后所有层数之和
    public static int currentArmsNum(int i) {
        int temp = 1;
        int result = 0;
        for (AbstractPower power : AbstractDungeon.player.powers) {
            if (power instanceof AbstractArmsPower) {
                if (i < temp) {
                    result += power.amount;
                }
                temp++;
            }
        }
        return result;
    }

    //当前兵器总层数
    public static int currentAllArmsNum() {
//        int result = 0;
//        for (AbstractPower power : AbstractDungeon.player.powers) {
//            if (power instanceof AbstractArmsPower) {
//                result += power.amount;
//            }
//        }
//        return result;
        return currentArmsNum(0);
    }

    //当前角色的武器最大层数
    public static int currentMaxArmsPlies() {
        if (AbstractDungeon.player.hasPower(MaxArmsPliesPower.POWER_ID)) {
            return 3 + AbstractDungeon.player.getPower(MaxArmsPliesPower.POWER_ID).amount;
        }
        return 3;
    }

    //获得装备的兵器的数量
    public static int getArmsNum() {
        return arms;
    }

    public static void setArms() {
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

    public static boolean isTemporaryArms() {
        return temporaryArms;
    }

    public static void setTemporaryArms(boolean temporaryArms) {
        ArmsUtil.temporaryArms = temporaryArms;
        if (temporaryArms) {
            if (!AbstractDungeon.player.hasPower(ChaosPower.POWER_ID)) {
                ActionUtil.addPower(AbstractDungeon.player, new ChaosPower(AbstractDungeon.player, 0));
            }
        } else {
            ActionUtil.removePower(AbstractDungeon.player, ChaosPower.POWER_ID);
        }
    }
}

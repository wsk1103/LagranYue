package com.wsk.utils;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.wsk.actions.ActionUtil;
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

    /**
     * 记录装备的兵器
     */
    private static int arms = 0;

    /**
     * 临时使装备兵器的数量+1
     */
    private static boolean temporaryArms = false;

    //装备兵器
    public static void addOrChangArms(AbstractCreature p, AbstractArmsPower armsPower) {

        if (areYouHasArmsPower(armsPower)) {
            //使用相同兵器后，升级
//            ForgingAction.addArmsNum(armsPower, armsPower.amount);
            ActionUtil.upgradeArms(p, armsPower);
//            armsPower.upgradeArms();
            setTemporaryArms(false);
        } else {
                if (isTemporaryArms()) {
                arms++;
                ActionUtil.addArms(p, armsPower);
                setTemporaryArms(false);
            } else {
                arms = getArmsNum();
                if (getArmsNum() > getMaxArmsNum()) {
                    //移除前几把超过兵器数量上限的兵器。
                    for (int i = 0; i <= getArmsNum() - getMaxArmsNum(); i++) {
                        //每次移除第一件兵器
                        ArmsUtil.removeOnce((AbstractPlayer) p, 1);
                    }
                } else if (getArmsNum() == getMaxArmsNum()) {
                    //移除第一件兵器
                    ArmsUtil.removeOnce((AbstractPlayer) p, 1);
                }
                arms++;
                ActionUtil.addArms(p, armsPower);
            }
        }
/*        if (!p.hasPower(DoubleArmsPower.POWER_ID)) {
            AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p,
                    new DoubleArmsPower(p, 0), 0, AbstractGameAction.AttackEffect.POISON));
        }*/
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

    //判断第i层兵器层数是否达到该上限
    public static boolean areMaxArmsPlies(int i) {
        if (i > getArmsNum()) {
            return true;
        }
        int once = onceArmsPlies(i);
        int currentAll = currentMaxArmsPlies();
        return once >= currentAll;
    }

    //判断第i层兵器层数是否达到该上限
    public static boolean areMaxArmsPlies(AbstractArmsPower power) {
        int once = power.amount;
        int currentAll = currentMaxArmsPlies();
        return once >= currentAll;
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

    //当前角色装备兵器数量的上限
    public static int getMaxArmsNum() {
        boolean doubleArms = AbstractDungeon.player.hasPower(DoubleArmsPower.POWER_ID);
        if (doubleArms) {
            return 1 + AbstractDungeon.player.getPower(DoubleArmsPower.POWER_ID).amount;
        }
        return 1;
    }

    public static void setArms() {
        arms = 0;
    }

    public static boolean retain() {
        if (AbstractDungeon.player.hasRelic(EnkiduRelics.ID)) {
            return AbstractDungeon.player.getRelic(EnkiduRelics.ID).counter >= 0;
        }
//        if (EnkiduRelics.getOnce()) {
//            EnkiduRelics.setOnce();
//            return true;
//        }
        return false;
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

    public static AbstractArmsPower getFirstArmsPower() {
        for (AbstractPower power : AbstractDungeon.player.powers) {
            if (power instanceof AbstractArmsPower) {
                return (AbstractArmsPower)power;
            }
        }
        return null;
    }
}

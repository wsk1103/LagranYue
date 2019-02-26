package com.wsk.utils;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.wsk.powers.DeathBolgPower;
import com.wsk.powers.DoubleArmsPower;
import com.wsk.powers.ExplosiveArmorPower;
import com.wsk.powers.GaeBolgPower;

/**
 * @author wsk1103
 * @date 2019/2/25
 * @desc 切换兵器时，需要减到之前兵器增加的力量
 */
public class ChangeArmsUtil {

    public static void change(AbstractPlayer p) {
        //1. 判断有没有双持这个能力
        boolean doubleArms = p.hasPower(DoubleArmsPower.POWER_ID);
        //如果拥有
        if (doubleArms) {
            //层数小于2，直接返回，表示可以继续装备
            if (DoubleArmsPower.getArms() < 2) {
                return;
            }
        }
        boolean hasPower = false;
//        boolean isAttack = true;//是 - 武器，否 - 防具
        //移除第一次添加的装备
        for (AbstractPower power : AbstractDungeon.player.powers) {
            switch (power.ID) {
                case GaeBolgPower.POWER_ID:
                    AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, GaeBolgPower.POWER_ID));
                    hasPower = true;
                    break;
                case DeathBolgPower.POWER_ID:
                    AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, DeathBolgPower.POWER_ID));
                    hasPower = true;
                    break;
                case ExplosiveArmorPower.POWER_ID:
                    AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, ExplosiveArmorPower.POWER_ID));
                    hasPower = true;
                    break;
                default:
                    break;
            }
        }
        //再减去增加的能力
        if (hasPower) {
//            add = 0 - add;
            if (doubleArms) {
                DoubleArmsPower.subArms();
            }
//            if (isAttack) {
//                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, add), add));
//            } else {
//                //敏捷
//                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, add), add));
//            }
        }
        if (doubleArms) {
            //最后装备数量+1
            DoubleArmsPower.addArms();
        }
    }

}

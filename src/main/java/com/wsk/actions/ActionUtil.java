package com.wsk.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.*;
import com.wsk.powers.arms.AbstractArmsPower;
import com.wsk.powers.base.ImprintPower;
import com.wsk.powers.base.NextEnergizedPower;
import com.wsk.powers.base.VictoryPower;
import com.wsk.utils.ArmsUtil;

/**
 * @author wsk1103
 * @date 2019/3/1
 * @desc 一句话说明
 */
public class ActionUtil {

    //给予死亡印记
    public static void imprintPower(AbstractCreature from, AbstractCreature to, int amount) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(to, from,
                new ImprintPower(to, from, amount), amount, true, AbstractGameAction.AttackEffect.POISON));
    }

    //给予中毒
    public static void poisonPower(AbstractCreature from, AbstractCreature to, int amount) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(to, from,
                new PoisonPower(to, from, amount), amount, true, AbstractGameAction.AttackEffect.POISON));
    }

    //获得格挡
    public static void gainBlockAction(AbstractCreature from, int amount) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(from, from, amount));
    }

    //给予虚弱
    public static void weakPower(AbstractCreature from, AbstractCreature to, int amount) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(to, from,
                new WeakPower(to, amount, false), amount,
                true, AbstractGameAction.AttackEffect.POISON));
    }

    //改变力量
    public static void strengthPower(AbstractCreature from, int amount) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(from, from,
                new StrengthPower(from, amount), amount));
    }

    //获得敏捷
    public static void dexterityPower(AbstractCreature p, int amount) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, amount), amount));
    }

    //获得壁垒
    public static void barricadePower(AbstractCreature p) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BarricadePower(p)));
    }

    //给予易伤
    public static void vulnerablePower(AbstractCreature from, AbstractCreature to, int amount) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(to, from,
                new VulnerablePower(to, amount, false), amount,
                true, AbstractGameAction.AttackEffect.POISON));
    }

    //脆弱
    public static void frailPower(AbstractCreature from, AbstractCreature to, int amount) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(to, from,
                new FrailPower(to, amount, false), amount,
                true));
    }

    //获得荆棘
    public static void thornsPower(AbstractCreature p, int amount) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new ThornsPower(p, amount), amount));
    }

    //获得多层护甲
    public static void platedArmorPower(AbstractCreature p, int amount) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new PlatedArmorPower(p, amount), amount));
    }

    //获得金金属化
    public static void metallicizePower(AbstractCreature p, int amount) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new MetallicizePower(p, amount), amount));
    }

    //直接移除能力
    public static void removePower(AbstractCreature p, String powerId) {
        if (p.hasPower(powerId)) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, powerId));
        }
    }

    //下回合抽牌
    public static void nextEnergizedPower(AbstractCreature p, int amount) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new NextEnergizedPower(p, amount), amount));
    }

    //获得胜利契约
    public static void victoryPower(AbstractCreature p, int amount) {
        if (amount > 10) {
            amount = 10;
        }
        if (p.hasPower(VictoryPower.POWER_ID)) {
            int a = p.getPower(VictoryPower.POWER_ID).amount;
            if (a >= 10) {
                amount = 0;
            } else if (a + amount > 10) {
                amount = 10 - a;
            }
        }
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new VictoryPower(p, amount), amount, true, AbstractGameAction.AttackEffect.POISON));
    }

    //锻造
    public static void forgingAction(AbstractCreature from, int armsNo, int amount) {
//        int temp = 0;
//        for (AbstractPower power : from.powers) {
//            if (power instanceof AbstractArmsPower) {
//                int num = power.amount;
//                temp ++;
//                if (num < ArmsUtil.currentMaxArmsPlies()) {
//                    ActionUtil.forgingAction(from, temp, amount);
//                    break;
//                }
//            }
//        }
        for (int i = armsNo; i <= ArmsUtil.getArmsNum(); i++) {
            if (!ArmsUtil.areMaxArmsPlies(i)) {
                AbstractDungeon.actionManager.addToBottom(new ForgingAction(from, i, amount));
                break;
            }
        }
    }

    //获取 兵器
    public static void addArms(AbstractCreature p, AbstractArmsPower armsPower) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, armsPower, armsPower.amount));
        armsPower.hasArms();
    }

    //添加能力
    public static void addPower(AbstractCreature p, AbstractPower power) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, power, power.amount));
    }

    //添加能力
    public static void gainEnerg(int amount) {
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(amount));
    }

    //移除能力
    public static void removePower(AbstractCreature p, AbstractPower power) {
        removePower(p, power.ID);
    }

}

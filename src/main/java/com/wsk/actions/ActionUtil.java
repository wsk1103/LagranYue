package com.wsk.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.wsk.powers.arms.AbstractArmsPower;
import com.wsk.powers.base.ImprintPower;
import com.wsk.powers.base.NextEnergizedPower;
import com.wsk.powers.base.VictoryPower;
import com.wsk.utils.ArmsUtil;

/**
 * @author wsk1103
 * @date 2019/3/1
 * @desc 集成了基本所有的操作，简便开发
 */
public class ActionUtil {

    //给予死亡印记
    public static void imprintPower(AbstractCreature from, AbstractCreature to, int amount) {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(to, from,
                new ImprintPower(to, from, amount), amount, true, AbstractGameAction.AttackEffect.POISON));
    }

    //给予中毒
    public static void poisonPower(AbstractCreature from, AbstractCreature to, int amount) {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(to, from,
                new PoisonPower(to, from, amount), amount, true, AbstractGameAction.AttackEffect.POISON));
    }

    //获得格挡
    public static void gainBlockAction(AbstractCreature from, int amount) {
        AbstractDungeon.actionManager.addToTop(new GainBlockAction(from, from, amount));
    }

    //给予虚弱
    public static void weakPower(AbstractCreature from, AbstractCreature to, int amount) {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(to, from,
                new WeakPower(to, amount, false), amount,
                true, AbstractGameAction.AttackEffect.POISON));
    }

    //改变力量
    public static void strengthPower(AbstractCreature from, int amount) {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(from, from,
                new StrengthPower(from, amount), amount));
    }

    //获得敏捷
    public static void dexterityPower(AbstractCreature p, int amount) {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new DexterityPower(p, amount), amount));
    }

    //获得壁垒
    public static void barricadePower(AbstractCreature p) {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new BarricadePower(p)));
    }

    //给予易伤
    public static void vulnerablePower(AbstractCreature from, AbstractCreature to, int amount) {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(to, from,
                new VulnerablePower(to, amount, false), amount,
                true, AbstractGameAction.AttackEffect.POISON));
    }

    //脆弱
    public static void frailPower(AbstractCreature from, AbstractCreature to, int amount) {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(to, from,
                new FrailPower(to, amount, false), amount,
                true));
    }

    //获得荆棘
    public static void thornsPower(AbstractCreature p, int amount) {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p,
                new ThornsPower(p, amount), amount));
    }

    //获得多层护甲
    public static void platedArmorPower(AbstractCreature p, int amount) {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p,
                new PlatedArmorPower(p, amount), amount));
    }

    //获得金金属化
    public static void metallicizePower(AbstractCreature p, int amount) {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new MetallicizePower(p, amount), amount));
    }

    //直接移除能力
    public static void removePower(AbstractCreature p, String powerId) {
        if (p.hasPower(powerId)) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(p, p, powerId));
        }
    }

    //下回合抽牌
    public static void nextEnergizedPower(AbstractCreature p, int amount) {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new NextEnergizedPower(p, amount), amount));
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
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p,
                new VictoryPower(p, amount), amount, true, AbstractGameAction.AttackEffect.POISON));
    }

    //锻造
    public static void forgingAction(AbstractCreature from, int armsNo, int amount) {
        for (int i = armsNo; i <= ArmsUtil.getArmsNum(); i++) {
            if (!ArmsUtil.areMaxArmsPlies(i)) {
                AbstractDungeon.actionManager.addToTop(new ForgingAction(from, i, amount));
                break;
            }
        }
    }

    //获取 兵器
    public static void addArms(AbstractCreature p, AbstractArmsPower armsPower) {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, armsPower, armsPower.amount));
        armsPower.hasArms();
    }

    //添加能力
    public static void addPower(AbstractCreature p, AbstractPower power) {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, power, power.amount));
    }

    //添加能力
    public static void addPower(AbstractCreature s, AbstractCreature t, AbstractPower power) {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(t, s, power, power.amount));
    }

    //添加能力
    public static void gainEnergy(int amount) {
        AbstractDungeon.actionManager.addToTop(new GainEnergyAction(amount));
    }

    //移除能力
    public static void removePower(AbstractCreature p, AbstractPower power) {
        removePower(p, power.ID);
    }

    /**
     * 减少能力的层数
     * @param p 拥有者
     * @param power 能力
     * @param amount 层数
     */
    public static void reducePower(AbstractCreature p, AbstractPower power, int amount){
        reducePower(p, power.ID, amount);
    }

    /**
     * 减少能力的层数
     * @param p 拥有者
     * @param power 能力
     * @param amount 层数
     */
    public static void reducePower(AbstractCreature p, String power, int amount){
        AbstractPower playerP = p.getPower(power);
        if (playerP != null) {
            int i = playerP.amount;
            if (i <= amount) {
                removePower(p, power);
            } else {
                AbstractDungeon.actionManager.addToTop(new ReducePowerAction(p, p, power, amount));
            }
        }
    }

    public static void addPower(AbstractCreature p, AbstractPower power, int amount) {
        power.amount = amount;
        addPower(p, power);
    }

    /**
     * 失去生命值
     * @param p 失去者
     * @param amount 多少
     */
    public static void loseHP(AbstractCreature p, int amount) {
        AbstractDungeon.actionManager.addToTop(new LoseHPAction(p, p, amount, AbstractGameAction.AttackEffect.SMASH));
    }

    /**
     * 添加缓冲
     * @param p 对象
     * @param amount 数量
     */
    public static void buffAction(AbstractCreature p, int amount) {
        addPower(p, new BufferPower(p, amount));
    }

    /**
     * 抽牌
     * @param p 对象
     * @param amount 数量
     */
    public static void drawCard(AbstractCreature p, int amount) {
        AbstractDungeon.actionManager.addToTop(new DrawCardAction(p, amount));
    }

    public static void relicAboveCreatureAction(AbstractCreature source, AbstractRelic relic) {
        AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(source, relic));
    }

}

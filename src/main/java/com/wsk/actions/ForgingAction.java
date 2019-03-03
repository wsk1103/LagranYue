package com.wsk.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.wsk.powers.arms.AbstractArmsPower;
import com.wsk.utils.ArmsUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static basemod.patches.com.megacrit.cardcrawl.screens.options.OptionsPanel.RefreshSwitch.logger;

/**
 * @author wsk1103
 * @date 2019/3/1
 * @desc 锻造，使第armsNo件武器的层数+amount
 */
public class ForgingAction extends AbstractGameAction {

    private AbstractCreature p;
    private int amount;
    private int armsNo;

    public ForgingAction(AbstractCreature creature, int armsNo, int amount) {
        this.p = creature;
        this.amount = amount;
        this.armsNo = armsNo;
        this.setValues(this.p, AbstractDungeon.player, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
    }

    @Override
    public void update() {
        for (AbstractPower power : p.powers) {
            int temp = 0;
            if (power instanceof AbstractArmsPower) {
                temp ++;
                if (temp == armsNo) {
                    addArmsNum((AbstractArmsPower) power, amount);
                    break;
                }
            }
        }
        this.isDone = true;
        tickDuration();
    }

    public static boolean addArmsNum(AbstractArmsPower power, int amount) {
        int armsNum = ArmsUtil.currentArmsNum(power);
        int maxNum = ArmsUtil.currentMaxArmsPlies();
        if (armsNum >= maxNum) {
            return false;
        } else if (amount + armsNum > maxNum) {
            amount = maxNum - armsNum;
        } else if (amount < 0) {
            return false;
        }
        AbstractArmsPower newPower = null;
        try {
            String name = power.getClass().getName();
            Class cl = Class.forName(name);
            Constructor<AbstractArmsPower> constructor = cl.getConstructor(AbstractCreature.class, int.class);
            newPower = constructor.newInstance(AbstractDungeon.player, amount);

        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            logger.info("反射失败，无法实例化该power:" + power.ID, e);
        }
        if (newPower == null) {
            logger.info("反射失败，无法实例化该power:" + power.ID);
        } else {
            ActionUtil.addArms(AbstractDungeon.player, newPower);
//            //增加层数
//            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, newPower, amount));
//            //获取加成
//            newPower.hasArms();
        }
        return true;
    }

}

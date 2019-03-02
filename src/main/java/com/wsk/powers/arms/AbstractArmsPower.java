package com.wsk.powers.arms;

import com.megacrit.cardcrawl.powers.AbstractPower;

/**
 * @author wsk1103
 * @date 2019/2/26
 * @desc 武器能力
 */
public abstract class AbstractArmsPower extends AbstractPower implements Cloneable {

    public abstract void hasArms();

    public Object clone() {
        AbstractArmsPower power = null;
        try {
            power = (AbstractArmsPower) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return power;
    }

}

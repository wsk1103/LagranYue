package com.wsk.powers.arms;

import com.megacrit.cardcrawl.powers.AbstractPower;
import com.wsk.patches.ArmsEnum;

/**
 * @author wsk1103
 * @date 2019/2/26
 * @desc 武器能力
 */
public abstract class AbstractArmsPower extends AbstractPower {

    /**
     * 兵器类型
     */
    public ArmsEnum arms;

    /**
     * 获得兵器的时候，给予的增益效果.
     */
    public abstract void hasArms();

    AbstractArmsPower(ArmsEnum arms){
        this.arms = arms;
    }

}

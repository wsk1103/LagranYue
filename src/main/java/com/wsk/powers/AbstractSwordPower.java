package com.wsk.powers;

import com.megacrit.cardcrawl.cards.DamageInfo;

/**
 * @author wsk1103
 * @date 2019/2/26
 * @desc 剑能力，造成的攻击伤害 + 2;
 */
public class AbstractSwordPower extends AbstractArmsPower {
    //造成伤害时，返回伤害数值
    public float atDamageFinalReceive(float damage, DamageInfo.DamageType damageType) {
        if (damageType == DamageInfo.DamageType.NORMAL) {
            return damage + 2;
        }
        return damage;
    }
}

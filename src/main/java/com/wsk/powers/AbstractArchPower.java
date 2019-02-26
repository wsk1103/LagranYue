package com.wsk.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;

/**
 * @author wsk1103
 * @date 2019/2/26
 * @desc 弓能力，造成伤害时，给予2层剧毒
 */
public class AbstractArchPower extends AbstractArmsPower {
    //造成伤害时，返回伤害数值
    public void atDamageFinalReceive(AbstractMonster m, float damage, DamageInfo.DamageType damageType) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, AbstractDungeon.player,
                new PoisonPower(m, AbstractDungeon.player, 2), 2, true, AbstractGameAction.AttackEffect.POISON));
    }
}

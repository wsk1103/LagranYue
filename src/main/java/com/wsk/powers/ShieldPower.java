package com.wsk.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;

/**
 * @author wsk1103
 * @date 2019/2/25
 * @desc 盾能力
 */
public class ShieldPower extends AbstractPower {
    //触发时机：当玩家攻击时。
    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        int vulnerable = 1;
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, AbstractDungeon.player,
                new VulnerablePower(target, vulnerable, false), vulnerable,
                true, AbstractGameAction.AttackEffect.NONE));
        super.onAttack(info, damageAmount, target);
    }
}

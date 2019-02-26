package com.wsk.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

/**
 * @author wsk1103
 * @date 2019/2/25
 * @desc 盾能力,当玩家攻击时,获得2点格挡
 */
public class AbstractShieldPower extends AbstractArmsPower {
    //触发时机：当玩家攻击时。
    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, 2));
        super.onAttack(info, damageAmount, target);
    }
}

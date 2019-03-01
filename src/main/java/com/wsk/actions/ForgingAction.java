package com.wsk.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.wsk.powers.AbstractArmsPower;

/**
 * @author wsk1103
 * @date 2019/3/1
 * @desc 锻造，使第一件武器的层数+1
 */
public class ForgingAction extends AbstractGameAction {

    private AbstractCreature p;
    private int amount;

    public ForgingAction(AbstractCreature creature, int amount) {
        this.p = creature;
        this.amount = amount;
        this.setValues(this.p, AbstractDungeon.player, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
    }

    @Override
    public void update() {
        for (AbstractPower power : p.powers) {
            if (power instanceof AbstractArmsPower) {
                AbstractArmsPower newPower;
                newPower = (AbstractArmsPower) ((AbstractArmsPower) power).clone();
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                        newPower, amount, AttackEffect.POISON));
                break;
            }
        }
        this.isDone = true;
        tickDuration();
    }
}

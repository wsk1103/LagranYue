package com.wsk.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.wsk.utils.ArmsUtil;

/**
 * @author wsk1103
 * @date 2019/3/1
 * @desc 移除第 !M! 件 兵器 之后的所有兵器，然后将移除的 兵器 的总层数增加到第 !M! 件 兵器
 */
@Deprecated
public class OneKingAction extends AbstractGameAction {

    private AbstractCreature p;
    private int amount;

    public OneKingAction(AbstractCreature creature, int amount) {
        this.p = creature;
        this.amount = amount;
        this.setValues(this.p, AbstractDungeon.player, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
    }

    @Override
    public void update() {
        int num = ArmsUtil.currentArmsNum(amount);
        ArmsUtil.removeArmsAfter(amount);
//        ActionUtil.forgingAction(p, amount, num);
        this.isDone = true;
        tickDuration();
    }
}

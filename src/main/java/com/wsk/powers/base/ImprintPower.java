package com.wsk.powers.base;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.wsk.actions.ActionUtil;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/24
 * @desc 死亡印记
 */
public class ImprintPower extends AbstractPower {
    public static final String POWER_ID = "LagranYue:ImprintPower";
    public static final String NAME = "死亡印记";

    private static final String IMG = "powers/w19.png";
    private static PowerType POWER_TYPE = PowerType.DEBUFF;

    private AbstractCreature source;

    public ImprintPower(AbstractCreature owner, AbstractCreature source, int amount) {//参数：owner-能力施加对象、amount-施加能力层数。在cards的use里面用ApplyPowerAction调用进行传递。
        this.ID = POWER_ID;
        DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;

        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
        this.owner = owner;
        this.amount = amount;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        this.source = source;
        this.type = POWER_TYPE;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
    }

    @Override
    public float atDamageFinalReceive(float damage, DamageInfo.DamageType damageType) {
        if (damageType == DamageInfo.DamageType.NORMAL) {
            return damage + this.amount;
        }
        return damage;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        this.flash();
        //回合结束的时候，层数减少1
        ActionUtil.reducePower(owner, this, 1);
    }

    @Override
    public void atStartOfTurn() {
        if (this.amount >= 10) {
            if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                this.flashWithoutSound();
                AbstractDungeon.actionManager.addToBottom(new LoseHPAction(this.owner, this.source, 50, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
                ActionUtil.removePower(owner, this);
            }
        }
    }

}

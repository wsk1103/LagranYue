package com.wsk.powers.base;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.wsk.cards.skill.PolyBeamCard;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/21
 * @desc 一句话说明
 */
@Deprecated
public class PolyBeamAgainPower extends AbstractPower {
    public static final String POWER_ID = "Slimebound:PolyBeamAgainPower";
    public static final String NAME = "Potency";
    public static PowerType POWER_TYPE = PowerType.BUFF;
    public static final String IMG = "powers/polybeampower.png";

    public static String[] DESCRIPTIONS;
    private AbstractCard card;
    private AbstractCreature source;

    public PolyBeamAgainPower(AbstractCreature owner, AbstractCreature source, int amount, AbstractCard card) {
this.DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;

        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
//        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.card = card;
        this.img = new com.badlogic.gdx.graphics.Texture(CommonUtil.getResourcePath(IMG));
        this.type = POWER_TYPE;
        this.amount = amount;
        this.source = source;
        this.DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;
        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
        updateDescription();
    }


    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = (DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2]);
        }
    }


    public void atStartOfTurn() {

        flash();

        AbstractMonster m = AbstractDungeon.getMonsters().getRandomMonster(true);


        AbstractCard tmp = card.makeCopy();
        AbstractDungeon.player.limbo.addToBottom(tmp);
        tmp.current_x = card.current_x;
        tmp.current_y = card.current_y;
        tmp.target_x = (Settings.WIDTH / 2.0F - 300.0F * Settings.scale);
        tmp.target_y = (Settings.HEIGHT / 2.0F);
        tmp.freeToPlayOnce = true;

        if (m != null) {
            tmp.calculateCardDamage(m);
        }

        tmp.purgeOnUse = true;
        PolyBeamCard tmpPB = (PolyBeamCard) tmp;
        tmpPB.isACopy = true;
        tmpPB.rawDescription = tmpPB.UPGRADED_DESCRIPTION;
        tmpPB.initializeDescription();
        AbstractDungeon.actionManager.cardQueue.add(new com.megacrit.cardcrawl.cards.CardQueueItem(tmpPB, m, card.energyOnUse));
        if (this.amount <= 1) {
            AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction(this.owner, this.owner, PolyBeamAgainPower.POWER_ID));
        } else {
            AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.ReducePowerAction(this.owner, this.owner, PolyBeamAgainPower.POWER_ID, 1));
        }
    }


}

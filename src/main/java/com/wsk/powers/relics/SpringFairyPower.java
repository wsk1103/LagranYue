package com.wsk.powers.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/3/20
 * @description 泉之精灵
 */
public class SpringFairyPower extends AbstractPower {
    public static final String POWER_ID = "LagranYue:SpringFairyPower";

    private static final String IMG = "powers/w26.png";
    private static PowerType POWER_TYPE = PowerType.BUFF;

    private AbstractCard card;

    public SpringFairyPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;

        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
        this.owner = owner;
        this.amount = amount;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        this.type = POWER_TYPE;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = (DESCRIPTIONS[0] + (this.amount + 1) + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2]);
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        card = c;
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        this.card = null;
    }

    @Override
    public void atStartOfTurn() {
        card = null;
    }

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return sum(damage);
    }

    @Override
    public int onPlayerGainedBlock(int blockAmount) {
        return (int) sum(blockAmount);
    }

    private float sum(float a) {
        if (card != null) {
            if (card.rarity == AbstractCard.CardRarity.RARE) {
                a += a * amount;
            } else if (card.rarity == AbstractCard.CardRarity.COMMON || card.rarity == AbstractCard.CardRarity.BASIC) {
                a -= a * amount;
            }
        }
        return a >= 0 ? a : 0;
    }
}
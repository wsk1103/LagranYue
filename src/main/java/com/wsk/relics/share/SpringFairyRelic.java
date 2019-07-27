package com.wsk.relics.share;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/3/20
 * @description 描述
 */
public class SpringFairyRelic extends CustomRelic {

    public static final String ID = "LagranYue:SpringFairyRelic";
    public static final String IMG = "relics/r23.png";
    public static final String OUTLINE = "relics/r24.png";

    private AbstractCard card;

    public SpringFairyRelic() {
        super(ID, new Texture(CommonUtil.getResourcePath(IMG)), new Texture(CommonUtil.getResourcePath(OUTLINE)), RelicTier.UNCOMMON, LandingSound.FLAT);
        this.counter = -1;
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new SpringFairyRelic();
    }

    @Override
    public void onUseCard(AbstractCard targetCard, UseCardAction useCardAction) {
        this.card = targetCard;
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        this.card = c;
    }

    @Override
    public int onPlayerGainedBlock(float blockAmount) {
        return (int) sum(blockAmount);
    }

    private float sum(float a) {
        if (a <= 0) {
            setCard();
            return a;
        }
        if (card != null) {
            flash();
            if (card.rarity == AbstractCard.CardRarity.RARE) {
                a += a;
            } else if (card.rarity == AbstractCard.CardRarity.COMMON || card.rarity == AbstractCard.CardRarity.BASIC) {
                a -= Math.floor(a * 1.00 / 2);
            }
        }
        setCard();
        return a >= 0 ? a : 0;
    }

    private void setCard() {
        card = null;
    }
}
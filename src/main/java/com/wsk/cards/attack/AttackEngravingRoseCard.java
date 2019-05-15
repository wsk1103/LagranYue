package com.wsk.cards.attack;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.wsk.actions.ActionUtil;
import com.wsk.patches.AbstractCardEnum;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/3/21
 * @description 描述
 */
public class AttackEngravingRoseCard extends CustomCard {
    public static final String ID = "LagranYue:AttackEngravingRoseCard";
    private static final String NAME;

    private static final String DESCRIPTION;
    private static final CardStrings cardStrings;
    private static final String IMG = "cards/AttackEngravingRoseCard.png";

    private static final int COST = 1;

    public AttackEngravingRoseCard() {
        super(ID, NAME, CommonUtil.getResourcePath(IMG), COST, DESCRIPTION,
                CardType.ATTACK, AbstractCardEnum.LagranYue,
                CardRarity.COMMON, CardTarget.ALL_ENEMY);
        this.baseDamage = 1;
        this.magicNumber = this.baseMagicNumber = 5;
    }

    @Override
    public AbstractCard makeCopy() {
        return new AttackEngravingRoseCard();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(2);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ActionUtil.randomAttack(p, this.baseDamage, this.magicNumber);
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
//        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }

}


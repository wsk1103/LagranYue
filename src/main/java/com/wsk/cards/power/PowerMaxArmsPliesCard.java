package com.wsk.cards.power;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.wsk.patches.AbstractCardEnum;
import com.wsk.powers.base.MaxArmsPliesPower;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/3/1
 * @desc 一句话说明
 */
public class PowerMaxArmsPliesCard extends CustomCard {
    public static final String ID = "LagranYue:PowerMaxArmsPliesCard";
    private static final String NAME;

    private static final String DESCRIPTION;
    private static final String UPGRADED_DESCRIPTION;

    private static final CardStrings cardStrings;

    private static final String IMG = "cards/PowerMaxArmsPliesCard.png";

    private static final int COST = 1;

    public PowerMaxArmsPliesCard() {
        super(ID, NAME, CommonUtil.getResourcePath(IMG), COST, DESCRIPTION,
                CardType.POWER,
                AbstractCardEnum.LagranYue,
                CardRarity.RARE, CardTarget.SELF);
        this.isEthereal = true;//虚无属性，false不虚无，true虚无。可在该类里调用改变。不虚无就可以赋值为false或者删掉这一行
        this.exhaust = false;//消耗属性，false不消耗，true消耗。可在该类里调用改变。不消耗就可以赋值为false或者删掉这一行
        this.magicNumber = this.baseMagicNumber = 2;
        this.isInnate = false;//固有属性，false不固有，true固有。可在该类里调用改变。不固有就可以赋值为false或者删掉这一行
    }

    @Override
    public AbstractCard makeCopy() {
        return new PowerMaxArmsPliesCard();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.isEthereal = false;
            this.upgradeBaseCost(1);
            this.rawDescription = UPGRADED_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new MaxArmsPliesPower(p, this.magicNumber), this.magicNumber));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }

}

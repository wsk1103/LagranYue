package com.wsk.cards.skill;

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
public class SkillDeadlyBloomCard extends CustomCard {
    public static final String ID = "LagranYue:SkillDeadlyBloomCard";
    private static final String NAME;

    private static final String DESCRIPTION;

    private static final CardStrings cardStrings;

    private static final String IMG = "cards/FantasyCard.png";

    private static final int COST = 1;

    public SkillDeadlyBloomCard() {
        super(ID, NAME, CommonUtil.getResourcePath(IMG), COST, DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.LagranYue,
                CardRarity.COMMON, CardTarget.SELF_AND_ENEMY);
        this.baseBlock = 7;
        this.magicNumber = this.baseMagicNumber = 2;
        this.exhaust = false;
    }

    @Override
    public AbstractCard makeCopy() {
        return new SkillDeadlyBloomCard();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();//升级名称。必带。
            this.upgradeBlock(2);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ActionUtil.gainBlockAction(p, this.block);
        ActionUtil.imprintPower(p, m, this.magicNumber);
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
    }

}

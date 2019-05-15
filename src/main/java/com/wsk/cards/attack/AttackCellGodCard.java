package com.wsk.cards.attack;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.wsk.actions.ActionUtil;
import com.wsk.patches.AbstractCardEnum;
import com.wsk.powers.proficiency.PermeationPower;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/3/21
 * @description 描述
 */
public class AttackCellGodCard extends CustomCard {
    public static final String ID = "LagranYue:AttackCellGodCard";
    private static final String NAME;

    private static final String DESCRIPTION;
    private static final CardStrings cardStrings;
    private static final String IMG = "cards/AttackCellGodCard.png";

    private static final int COST = 2;

    public AttackCellGodCard() {
        this(0);
    }

    public AttackCellGodCard(int upgrades) {
        super(ID, NAME, CommonUtil.getResourcePath(IMG), COST, DESCRIPTION,
                CardType.ATTACK, AbstractCardEnum.LagranYue,
                CardRarity.RARE, CardTarget.ENEMY);
        this.baseDamage = 1;
        this.magicNumber = this.baseMagicNumber = 8;
        this.exhaust = true;
        this.timesUpgraded = upgrades;
    }

    @Override
    public AbstractCard makeCopy() {
        return new AttackCellGodCard();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; i++) {
            if (i % 2 == 0) {
                AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
                        new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SHIELD));
            } else {
                AbstractDungeon.actionManager.addToBottom(new DamageAction(m,
                        new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
            }
        }
        ActionUtil.addPower(p, m, new PermeationPower(m, 1));
    }

    @Override
    public void upgrade() {
        ++this.timesUpgraded;
        this.upgradeMagicNumber(2);
        this.upgraded = true;
        this.name = NAME + "+" + this.timesUpgraded;
        this.initializeTitle();
    }

    @Override
    public boolean canUpgrade() {
        return true;
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
//        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }

}

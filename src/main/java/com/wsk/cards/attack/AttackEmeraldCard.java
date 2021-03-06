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
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/24
 * @desc 绿宝石的狂欢
 */
public class AttackEmeraldCard extends CustomCard {
    public static final String ID = "LagranYue:AttackEmeraldCard";
    private static final String NAME ;

    private static final String DESCRIPTION;
    private static final CardStrings CARD_STRINGS;
    private static final String IMG = "cards/AttackEmeraldCard.png";


    private static final int COST = 3;

    public AttackEmeraldCard() {
        super(ID, NAME, CommonUtil.getResourcePath(IMG), COST, DESCRIPTION,
                CardType.ATTACK, AbstractCardEnum.LagranYue,
                CardRarity.RARE, CardTarget.SELF_AND_ENEMY);
        this.baseDamage = 27;
        this.exhaust = true;
        this.magicNumber = this.baseMagicNumber = 3;
    }

    @Override
    public AbstractCard makeCopy() {
        return new AttackEmeraldCard();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(8);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ActionUtil.victoryPower(p, this.magicNumber);
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));

    }

    static {
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = CARD_STRINGS.NAME;
        DESCRIPTION = CARD_STRINGS.DESCRIPTION;
//        UPGRADED_DESCRIPTION = CARD_STRINGS.UPGRADE_DESCRIPTION;
    }

}

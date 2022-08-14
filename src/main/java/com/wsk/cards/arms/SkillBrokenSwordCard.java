package com.wsk.cards.arms;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.wsk.actions.ChooseAction;
import com.wsk.cards.AbstractSwordCard;
import com.wsk.helps.LogHelper;
import com.wsk.patches.AbstractCardEnum;
import com.wsk.powers.arms.BaseSwordPower;
import com.wsk.utils.ArmsUtil;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/26
 * @desc 一句话说明
 */
public class SkillBrokenSwordCard extends AbstractSwordCard {
    public static final String ID = "LagranYue:SkillBrokenSwordCard";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String         NAME = cardStrings.NAME;

    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;


    private static final String IMG = "cards/SkillBrokenSwordCard.png";

    private static final int COST = 1;
    private static final int DURABILITY = 3;


    public SkillBrokenSwordCard() {
        super(ID, NAME, CommonUtil.getResourcePath(IMG), COST, DESCRIPTION,
                CardType.ATTACK,
                AbstractCardEnum.LagranYue,
                CardRarity.COMMON, CardTarget.ALL);
        this.durability = this.baseDurability =  DURABILITY;
        this.magicNumber = this.baseMagicNumber = durability;
        this.isEthereal = false;
//        this.exhaust = true;
        this.isInnate = false;
        this.chooseDesc.add(EXTENDED_DESCRIPTION[0]);
        this.chooseDesc.add(EXTENDED_DESCRIPTION[1]);
        this.baseDamage = 2;
        this.isMultiDamage = true;
    }

    public AbstractCard makeCopy() {
        return new SkillBrokenSwordCard();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(2);
            this.upgradeDurability(1);
//            this.upgradeBaseCost(0);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ChooseAction(this, this.getChooseCardGroup()));
    }

    @Override
    public void choose(int num) {
        this.applyPowers();
        if (num == 0) {
            BaseSwordPower power = new BaseSwordPower(AbstractDungeon.player, this.magicNumber);
            ArmsUtil.addOrChangArms(AbstractDungeon.player, power);
        } else {
            if (num != 1) {
                LogHelper.logger.info("choose card error...........");
                return;
            }
            AbstractDungeon.actionManager.addToBottom(
                    new DamageAllEnemiesAction(AbstractDungeon.player, this.multiDamage, this.damageTypeForTurn,
                            AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }

    }
}

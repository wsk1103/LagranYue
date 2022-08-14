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
import com.wsk.powers.arms.VictorySwordPower;
import com.wsk.utils.ArmsUtil;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/26
 * @desc 一句话说明
 */
public class SkillVictorySwordCard extends AbstractSwordCard {
    public static final String ID = "LagranYue:SkillVictorySwordCard";
    private static final String NAME;

    private static final String DESCRIPTION;
    private static final String UPGRADED_DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    private static final CardStrings cardStrings;

    private static final String IMG = "cards/SkillVictorySwordCard.png";

    private static final int COST = 2;
    private static final int DURABILITY = 5;

    public SkillVictorySwordCard() {
        super(ID, NAME, CommonUtil.getResourcePath(IMG), COST, DESCRIPTION,
                CardType.ATTACK,
                AbstractCardEnum.LagranYue,
                CardRarity.RARE, CardTarget.ALL);
        this.durability = this.baseDurability =  DURABILITY;
        this.magicNumber = this.baseMagicNumber = durability;
        this.isEthereal = false;
//        this.exhaust = true;
        this.isInnate = false;
        this.chooseDesc.add(EXTENDED_DESCRIPTION[0]);
        this.chooseDesc.add(EXTENDED_DESCRIPTION[1]);
        this.baseDamage = 14;
        this.isMultiDamage = true;
    }

    public AbstractCard makeCopy() {
        return new SkillVictorySwordCard();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(4);
            this.upgradeDurability(1);
            this.isInnate = true;
            this.rawDescription = UPGRADED_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        //兵器：誓约胜利之剑
//        VictorySwordPower power = new VictorySwordPower(abstractPlayer, this.magicNumber);
//        ArmsUtil.addOrChangArms(abstractPlayer, power);
        AbstractDungeon.actionManager.addToBottom(new ChooseAction(this, this.getChooseCardGroup()));
    }

    @Override
    public void choose(int num) {
        this.applyPowers();
        if (num == 0) {
            VictorySwordPower power = new VictorySwordPower(AbstractDungeon.player, this.magicNumber);
            ArmsUtil.addOrChangArms(AbstractDungeon.player, power);
        } else {
            if (num != 1) {
                LogHelper.logger.info("choose card error...........");
                return;
            }
            AbstractDungeon.actionManager.addToBottom(
                    new DamageAllEnemiesAction(AbstractDungeon.player, this.multiDamage, this.damageTypeForTurn,
                            AbstractGameAction.AttackEffect.SLASH_HEAVY));
        }

    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    }

}

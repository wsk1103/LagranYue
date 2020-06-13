package com.wsk.cards.arms;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.wsk.actions.ActionUtil;
import com.wsk.actions.ChooseAction;
import com.wsk.cards.AbstractShieldCard;
import com.wsk.helps.LogHelper;
import com.wsk.patches.AbstractCardEnum;
import com.wsk.powers.arms.BaseShieldPower;
import com.wsk.utils.ArmsUtil;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/26
 * @desc 一句话说明
 */
public class SkillBrokenShieldCard extends AbstractShieldCard {
    public static final String ID = "LagranYue:SkillBrokenShieldCard";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;

    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;


    private static final String IMG = "cards/SkillBrokenShieldCard.png";

    private static final int COST = 1;
    private static final int DURABILITY = 3;


    public SkillBrokenShieldCard() {
        super(ID, NAME, CommonUtil.getResourcePath(IMG), COST, DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.LagranYue,
                CardRarity.COMMON, CardTarget.SELF);
        this.durability = this.baseDurability =  DURABILITY;
        this.magicNumber = this.baseMagicNumber = durability;
        this.baseBlock = 4;
        this.isEthereal = false;
//        this.exhaust = true;
        this.isInnate = false;
        this.chooseDesc.add(EXTENDED_DESCRIPTION[0]);
        this.chooseDesc.add(EXTENDED_DESCRIPTION[1]);
    }

    @Override
    public AbstractCard makeCopy() {
        return new SkillBrokenShieldCard();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(0);
            this.upgradeDurability(1);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
//        BaseShieldPower power = new BaseShieldPower(p, this.magicNumber);
//        ArmsUtil.addOrChangArms(p, power);
        AbstractDungeon.actionManager.addToBottom(new ChooseAction(this, this.getChooseCardGroup()));
    }

    @Override
    public void choose(int num) {
        this.applyPowers();
        if (num == 0) {
            BaseShieldPower power = new BaseShieldPower(AbstractDungeon.player, this.magicNumber);
            ArmsUtil.addOrChangArms(AbstractDungeon.player, power);
        } else {
            if (num != 1) {
                LogHelper.logger.info("choose card error...........");
                return;
            }
            ActionUtil.gainBlockAction(AbstractDungeon.player, this.block);
        }

    }

}

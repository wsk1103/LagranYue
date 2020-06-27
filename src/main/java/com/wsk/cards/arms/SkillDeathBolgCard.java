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
import com.wsk.cards.AbstractSpearCard;
import com.wsk.helps.LogHelper;
import com.wsk.patches.AbstractCardEnum;
import com.wsk.powers.arms.DeathBolgPower;
import com.wsk.utils.ArmsUtil;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/25
 * @desc 兵器：突穿死翔之枪
 */
public class SkillDeathBolgCard extends AbstractSpearCard {
    public static final String ID = "LagranYue:SkillDeathBolgCard";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = cardStrings.NAME;

    private static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;


    private static final String IMG = "cards/SkillDeathBolgCard.png";

    private static final int COST = 1;
    private static final int DURABILITY = 4;

    public SkillDeathBolgCard() {
        super(ID, NAME, CommonUtil.getResourcePath(IMG), COST, DESCRIPTION,
                CardType.ATTACK,
                AbstractCardEnum.LagranYue,
                CardRarity.UNCOMMON, CardTarget.ALL);
        this.durability = this.baseDurability = DURABILITY;
        this.magicNumber = this.baseMagicNumber = durability;
        this.isEthereal = false;
//        this.exhaust = true;
        this.isInnate = false;
        this.chooseDesc.add(EXTENDED_DESCRIPTION[0]);
        this.chooseDesc.add(EXTENDED_DESCRIPTION[1]);
        this.baseDamage = 4;
    }

    @Override
    public AbstractCard makeCopy() {
        return new SkillDeathBolgCard();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(2);
            this.upgradeDurability(1);
//            this.upgradeBaseCost(1);//升级后的费用。注意括号内的值即为费用，与上方不同！！！！
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        //兵器：突穿死翔之枪
//        DeathBolgPower power = new DeathBolgPower(abstractPlayer, this.magicNumber);
//        ArmsUtil.addOrChangArms(abstractPlayer, power);
        AbstractDungeon.actionManager.addToBottom(new ChooseAction(this, this.getChooseCardGroup()));
    }


    @Override
    public void choose(int num) {
        this.applyPowers();
        if (num == 0) {
            DeathBolgPower power = new DeathBolgPower(AbstractDungeon.player, this.magicNumber);
            ArmsUtil.addOrChangArms(AbstractDungeon.player, power);
        } else {
            if (num != 1) {
                LogHelper.logger.info("choose card error...........");
                return;
            }
            AbstractDungeon.actionManager.addToBottom(
                    new DamageAllEnemiesAction(AbstractDungeon.player, this.multiDamage, this.damageTypeForTurn,
                            AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }

    }

}

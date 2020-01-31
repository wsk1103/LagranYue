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
import com.wsk.powers.arms.BlazingSevenRingsPower;
import com.wsk.utils.ArmsUtil;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/26
 * @desc 兵器：炽天覆七重圆环
 */
public class SkillBlazingSevenRingsCard extends AbstractShieldCard {
    public static final String ID = "LagranYue:SkillBlazingSevenRingsCard";
    private static final String NAME;

    private static final String DESCRIPTION;
    private static final String UPGRADED_DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION;

    private static final CardStrings cardStrings;

    private static final String IMG = "cards/SkillBlazingSevenRingsCard.png";

    private static final int COST = 2;
    private static final int DURABILITY = 5;

    public SkillBlazingSevenRingsCard() {
        super(ID, NAME, CommonUtil.getResourcePath(IMG), COST, DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.LagranYue,
                CardRarity.RARE, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 1;
        this.baseBlock = 15;

        this.isEthereal = false;

        this.exhaust = true;

        this.isInnate = false;
        this.durability = this.baseDurability =  DURABILITY;
        this.chooseDesc.add(EXTENDED_DESCRIPTION[0]);
        this.chooseDesc.add(EXTENDED_DESCRIPTION[1]);
    }

    @Override
    public AbstractCard makeCopy() {
        return new SkillBlazingSevenRingsCard();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBlock(3);
            this.upgradeDurability(1);
            this.isInnate = true;
            this.rawDescription = UPGRADED_DESCRIPTION;
            this.initializeDescription();
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
            BlazingSevenRingsPower power = new BlazingSevenRingsPower(AbstractDungeon.player, this.magicNumber);
            ArmsUtil.addOrChangArms(AbstractDungeon.player, power);
        } else {
            if (num != 1) {
                LogHelper.logger.info("选择卡牌失败");
                return;
            }
            ActionUtil.gainBlockAction(AbstractDungeon.player, this.block);
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

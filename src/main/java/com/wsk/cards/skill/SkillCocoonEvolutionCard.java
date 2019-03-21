package com.wsk.cards.skill;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.wsk.actions.ActionUtil;
import com.wsk.patches.AbstractCardEnum;
import com.wsk.relics.ArmsProficiencyRelics;
import com.wsk.utils.CommonUtil;

import java.util.ArrayList;

/**
 * @author wsk1103
 * @date 2019/3/21
 * @description 描述
 */
public class SkillCocoonEvolutionCard extends CustomCard {
    public static final String ID = "LagranYue:SkillCocoonEvolutionCard";
    private static final String NAME;

    private static final String DESCRIPTION;

    private static final CardStrings cardStrings;

    private static final String IMG = "cards/FantasyCard.png";

    private static final int COST = 0;

    public SkillCocoonEvolutionCard() {
        super(ID, NAME, CommonUtil.getResourcePath(IMG), COST, DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.LagranYue,
                CardRarity.RARE, CardTarget.SELF);
        this.baseBlock = 10;
        this.magicNumber = this.baseMagicNumber = 1;
        this.exhaust = false;
    }

    @Override
    public AbstractCard makeCopy() {
        return new SkillCocoonEvolutionCard();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();//升级名称。必带。
            this.upgradeBlock(5);
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ActionUtil.gainBlockAction(p, this.block);
        ArrayList<AbstractRelic> relics = AbstractDungeon.player.relics;
        for (AbstractRelic relic : relics) {
            if (relic instanceof ArmsProficiencyRelics) {
                ((ArmsProficiencyRelics) relic).addArch(1);
                ((ArmsProficiencyRelics) relic).addShield(1);
                ((ArmsProficiencyRelics) relic).addSword(1);
                ((ArmsProficiencyRelics) relic).addSpear(1);
                ((ArmsProficiencyRelics) relic).use();
                return;
            }
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
    }

}

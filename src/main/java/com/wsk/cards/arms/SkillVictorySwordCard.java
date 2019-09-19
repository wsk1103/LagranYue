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

    private static final String DESCRIPTION;//卡牌下方的描叙内容。
    private static final String UPGRADED_DESCRIPTION;//卡牌下方的描叙内容。
    public static final String[] EXTENDED_DESCRIPTION;

    private static final CardStrings cardStrings;

    private static final String IMG = "cards/SkillVictorySwordCard.png";//卡牌牌面的图片路径。

    private static final int COST = 2;
    private static final int DURABILITY = 5;

    public SkillVictorySwordCard() {
        super(ID, NAME, CommonUtil.getResourcePath(IMG), COST, DESCRIPTION,
                CardType.ATTACK,
                AbstractCardEnum.LagranYue,
                CardRarity.RARE, CardTarget.ALL);
        this.magicNumber = this.baseMagicNumber = 1;
        this.isEthereal = false;//虚无属性，false不虚无，true虚无。可在该类里调用改变。不虚无就可以赋值为false或者删掉这一行
        this.exhaust = true;//消耗属性，false不消耗，true消耗。可在该类里调用改变。不消耗就可以赋值为false或者删掉这一行
        this.isInnate = false;//固有属性，false不固有，true固有。可在该类里调用改变。不固有就可以赋值为false或者删掉这一行
        this.chooseDesc.add(EXTENDED_DESCRIPTION[0]);
        this.chooseDesc.add(EXTENDED_DESCRIPTION[1]);
        this.baseDamage = 16;
        this.durability = this.baseDurability =  DURABILITY;
    }

    public AbstractCard makeCopy() {
        return new SkillVictorySwordCard();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();//升级名称。必带。
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

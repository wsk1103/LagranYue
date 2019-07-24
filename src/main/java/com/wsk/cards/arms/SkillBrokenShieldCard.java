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
    private static final String NAME;//卡牌显示的名称

    private static final String DESCRIPTION;//卡牌下方的描叙内容。
    public static final String[] EXTENDED_DESCRIPTION;

    private static final CardStrings cardStrings;

    private static final String IMG = "cards/SkillBrokenShieldCard.png";//卡牌牌面的图片路径。

    private static final int COST = 1;


    public SkillBrokenShieldCard() {
        super(ID, NAME, CommonUtil.getResourcePath(IMG), COST, DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.LagranYue,
                CardRarity.COMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 1;
        this.baseBlock = 6;
        //虚无属性，false不虚无，true虚无。可在该类里调用改变。不虚无就可以赋值为false或者删掉这一行
        this.isEthereal = false;
        //消耗属性，false不消耗，true消耗。可在该类里调用改变。不消耗就可以赋值为false或者删掉这一行
        this.exhaust = true;
        //固有属性，false不固有，true固有。可在该类里调用改变。不固有就可以赋值为false或者删掉这一行
        this.isInnate = false;
        this.chooseDesc.add(EXTENDED_DESCRIPTION[0]);
        this.chooseDesc.add(EXTENDED_DESCRIPTION[1]);
    }

    public AbstractCard makeCopy() {
        return new SkillBrokenShieldCard();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();//升级名称。必带。
            this.upgradeBaseCost(0);
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

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    }
}

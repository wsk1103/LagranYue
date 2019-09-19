package com.wsk.cards.skill;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.wsk.actions.ActionUtil;
import com.wsk.patches.AbstractCardEnum;
import com.wsk.utils.ArmsUtil;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/26
 * @desc 绿宝石的悔恨
 */
public class EmeraldRemorseCard extends CustomCard {
    public static final String ID = "LagranYue:EmeraldRemorseCard";
    private static final String NAME;

    private static final String DESCRIPTION;//卡牌下方的描叙内容。
    private static final String[] EXTENDED_DESCRIPTION;//卡牌下方的描叙内容。

    private static final CardStrings cardStrings;

    private static final String IMG = "cards/EmeraldRemorseCard.png";//卡牌牌面的图片路径。
    //例：img/cards/claw/attack/BloodSuckingClaw_Orange.png  详细情况请根据自己项目的路径布置进行填写。

    private static final int COST = 0;

    public EmeraldRemorseCard() {
        super(ID, NAME, CommonUtil.getResourcePath(IMG), COST, DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.LagranYue,
                CardRarity.COMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 1;
        this.exhaust =false;
    }

    //用于显示在卡牌一览里。同时也是诸多卡牌复制效果所需要调用的基本方法，用来获得一张该卡的原始模板修改后加入手牌/抽牌堆/弃牌堆/牌组。
    public AbstractCard makeCopy() {
        return new EmeraldRemorseCard();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();//升级名称。必带。
            this.upgradeMagicNumber(1);

        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        ArmsUtil.removeOnce(p, this.magicNumber);
        ActionUtil.dexterityPower(p, this.magicNumber);
        ActionUtil.strengthPower(p, this.magicNumber);
//        AbstractDungeon.player.addPower(new StrengthPower(AbstractDungeon.player, this.magicNumber));
//        AbstractDungeon.player.addPower(new DexterityPower(AbstractDungeon.player, this.magicNumber));
    }

    //卡牌不能被打出
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (ArmsUtil.getArmsNum() < 1) {
            //diy区。
            this.cantUseMessage = EXTENDED_DESCRIPTION[0];//卡牌不能被打出时所提示的文本。
            return false;
        }
        return true;
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    }

}

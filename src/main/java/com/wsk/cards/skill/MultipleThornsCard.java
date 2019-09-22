package com.wsk.cards.skill;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.wsk.actions.ActionUtil;
import com.wsk.patches.AbstractCardEnum;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/21
 * @desc 一句话说明
 */
public class MultipleThornsCard extends CustomCard {
    public static final String ID = "LagranYue:MultipleThornsCard";
    private static final String NAME /*= "來自WSK的愛"*/;

    private static final String DESCRIPTION /*= "WSK給予 !B! 点 格挡 。"*/;

    private static final CardStrings cardStrings;

    private static final String IMG = "cards/MultipleThornsCard.png";

    private static final int COST = 1;

    private static final int DEFEND = 7;

    public MultipleThornsCard() {
        super(ID, NAME, CommonUtil.getResourcePath(IMG), COST, DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.LagranYue,
                CardRarity.COMMON, CardTarget.SELF);
        this.baseBlock = DEFEND;//基础格挡值，除升级以外无任何其他加成. this.block为有敏捷等加成的格挡值.
        this.magicNumber = this.baseMagicNumber = 1;
        this.isEthereal = false;
        this.exhaust = true;//消耗属性，false不消耗，true消耗。可在该类里调用改变。不消耗就可以赋值为false或者删掉这一行
        this.isInnate = false;//固有属性，false不固有，true固有。可在该类里调用改变。不固有就可以赋值为false或者删掉这一行
    }

    public AbstractCard makeCopy() {
        return new MultipleThornsCard();
    }//用于显示在卡牌一览里。同时也是诸多卡牌复制效果所需要调用的基本方法，用来获得一张该卡的原始模板修改后加入手牌/抽牌堆/弃牌堆/牌组。

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();//升级名称。必带。
            this.upgradeBlock(3);//升级而增加的护甲。增加的是baseDamage
        }
    }//注：该部分为升级的效果部分，此处展示的代码为只能升级一次的代码，如需无限升级，卡牌代码有些许不同但不便于例出，请自行查看灼热攻击源码。

    //以上为卡牌的必备内容，不可缺少。
    public void use(AbstractPlayer p, AbstractMonster m) {//局部变量：p-玩家，m敌人。
//        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        ActionUtil.gainBlockAction(p, this.block);
        ActionUtil.thornsPower(p, this.magicNumber);
//        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ThornsPower(p, this.magicNumber), this.magicNumber, AbstractGameAction.AttackEffect.POISON));
    }//注：卡牌效果的diy区。

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
//        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }

}

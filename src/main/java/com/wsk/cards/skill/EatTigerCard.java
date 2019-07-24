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
 * @date 2019/2/26
 * @desc 一句话说明
 */
public class EatTigerCard extends CustomCard {
    public static final String ID = "LagranYue:EatTigerCard";
    private static final String NAME;//卡牌显示的名称

    private static final String DESCRIPTION;//卡牌下方的描叙内容。

    private static final CardStrings cardStrings;

    private static final String IMG = "cards/EatTigerCard.png";//卡牌牌面的图片路径。
    //例：img/cards/claw/attack/BloodSuckingClaw_Orange.png  详细情况请根据自己项目的路径布置进行填写。

    private static final int COST = 1;

    public EatTigerCard() {
        super(ID, NAME, CommonUtil.getResourcePath(IMG), COST, DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.LagranYue,
                CardRarity.COMMON, CardTarget.SELF_AND_ENEMY);
        this.baseBlock = 7;
        this.magicNumber = this.baseMagicNumber = 1;
    }

    //用于显示在卡牌一览里。同时也是诸多卡牌复制效果所需要调用的基本方法，用来获得一张该卡的原始模板修改后加入手牌/抽牌堆/弃牌堆/牌组。
    public AbstractCard makeCopy() {
        return new EatTigerCard();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();//升级名称。必带。
            this.upgradeBlock(2);
            this.upgradeMagicNumber(1);

        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
//        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
//        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(abstractMonster, p,
//                new WeakPower(abstractMonster, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.POISON));
        ActionUtil.gainBlockAction(p, this.block);
        ActionUtil.weakPower(p, abstractMonster, this.magicNumber);
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
    }

}


package com.wsk.cards.skill;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.wsk.patches.AbstractCardEnum;
import com.wsk.utils.ArmsUtil;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/26
 * @desc 蓝宝石的祈祷
 */
public class SapphirePrayerCard extends CustomCard {
    public static final String ID = "LagranYue:SapphirePrayerCard";
    private static final String NAME;//卡牌显示的名称

    private static final String DESCRIPTION;//卡牌下方的描叙内容。

    private static final CardStrings cardStrings;

    private static final String IMG = "cards/SapphirePrayerCard.png";//卡牌牌面的图片路径。
    //例：img/cards/claw/attack/BloodSuckingClaw_Orange.png  详细情况请根据自己项目的路径布置进行填写。

    private static final int COST = 0;//卡牌的费用。

    public SapphirePrayerCard() {
        super(ID, NAME, CommonUtil.getResourcePath(IMG), COST, DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.LagranYue,
                CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = 7;
        this.magicNumber = this.baseMagicNumber = 2;
        this.exhaust = true;
    }

    //用于显示在卡牌一览里。同时也是诸多卡牌复制效果所需要调用的基本方法，用来获得一张该卡的原始模板修改后加入手牌/抽牌堆/弃牌堆/牌组。
    public AbstractCard makeCopy() {
        return new SapphirePrayerCard();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();//升级名称。必带。
            this.upgradeBlock(2);
            this.upgradeMagicNumber(-1);

        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster abstractMonster) {
        AbstractDungeon.actionManager.addToBottom(new LoseHPAction(p, p, this.magicNumber, AbstractGameAction.AttackEffect.POISON));
        //升级手中的卡牌
        CardGroup cardGroup = p.hand;
        for (AbstractCard c : cardGroup.group) {
            if (c.cardID.equals(SapphirePrayerCard.ID)) {
                continue;
            }
            if (c.canUpgrade()) {
                if (cardGroup.type == CardGroup.CardGroupType.HAND) {
                    c.superFlash();
                }
                c.upgrade();
                c.applyPowers();
            }
        }
        ArmsUtil.setTemporaryArms(true);
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
    }

}

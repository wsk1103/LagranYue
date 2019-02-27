package com.wsk.cards.power;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.wsk.patches.AbstractCardEnum;
import com.wsk.powers.DarkTemplePower;
import com.wsk.powers.DarkTemplePowerUpgraded;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/26
 * @desc 一句话说明
 */
public class PowerDarkTempleCard extends CustomCard {
    public static final String ID = "MyMod:PowerDarkTempleCard";//卡牌在游戏中的id
    private static final String NAME/* = "来自WSK的庇护"*/;//卡牌显示的名称

    private static final String DESCRIPTION /*= "获得 2 点 力量"*/;//卡牌下方的描叙内容。
    private static final String UPGRADED_DESCRIPTION /*= "获得 2 点 力量"*/;//卡牌下方的描叙内容。

    private static final CardStrings cardStrings;

    private static final String IMG = "cards/slimepunch.png";//卡牌牌面的图片路径。
    //例：img/cards/claw/attack/BloodSuckingClaw_Orange.png  详细情况请根据自己项目的路径布置进行填写。

    private static final int COST = 3;//卡牌的费用。

    public PowerDarkTempleCard() {
        super(ID, NAME, CommonUtil.getResourcePath(IMG), COST, DESCRIPTION,
                CardType.POWER,
                AbstractCardEnum.MyModCard,
                CardRarity.UNCOMMON, CardTarget.SELF);
        this.isEthereal = true;//虚无属性，false不虚无，true虚无。可在该类里调用改变。不虚无就可以赋值为false或者删掉这一行
        this.exhaust = false;//消耗属性，false不消耗，true消耗。可在该类里调用改变。不消耗就可以赋值为false或者删掉这一行
        this.magicNumber = this.baseMagicNumber = 1;
        this.isInnate = false;//固有属性，false不固有，true固有。可在该类里调用改变。不固有就可以赋值为false或者删掉这一行
    }

    //用于显示在卡牌一览里。同时也是诸多卡牌复制效果所需要调用的基本方法，用来获得一张该卡的原始模板修改后加入手牌/抽牌堆/弃牌堆/牌组。
    public AbstractCard makeCopy() {
        return new PowerDarkTempleCard();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();//升级名称。必带。
//            this.upgradeBlock(defind * 2);//升级而增加的护甲。增加的是baseDamage
//            this.upgradeDamage(wskAttack * 2);//升级而增加的伤害。增加的是baseBlock
            this.upgradeMagicNumber(1);//升级而增加的特殊值。增加的是baseMagicNumber
//            this.upgradeBaseCost(3);//升级后的费用。注意括号内的值即为费用，与上方不同！！！！
            this.isEthereal = false;//虚无属性。
            this.rawDescription = UPGRADED_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (upgraded) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(abstractPlayer, abstractPlayer,
                    new DarkTemplePowerUpgraded(abstractPlayer, 1), 1));
        } else {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(abstractPlayer, abstractPlayer
                    , new DarkTemplePower(abstractPlayer, 1), 1));
        }
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }
}

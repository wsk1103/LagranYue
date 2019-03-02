package com.wsk.cards.arms;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.wsk.cards.AbstractSpearCard;
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
    public static final String ID = "LagranYue:SkillDeathBolgCard";//卡牌在游戏中的id
    private static final String NAME/* = "来自WSK的庇护"*/;//卡牌显示的名称

    private static final String DESCRIPTION /*= "获得 2 点 力量"*/;//卡牌下方的描叙内容。

    private static final CardStrings cardStrings;

    private static final String IMG = "cards/SkillDeathBolgCard.png";//卡牌牌面的图片路径。

    private static final int COST = 2;//卡牌的费用。

    public SkillDeathBolgCard() {
        super(ID, NAME, CommonUtil.getResourcePath(IMG), COST, DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.LagranYue,
                CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = 1;
        this.isEthereal = false;//虚无属性，false不虚无，true虚无。可在该类里调用改变。不虚无就可以赋值为false或者删掉这一行
        this.exhaust = true;//消耗属性，false不消耗，true消耗。可在该类里调用改变。不消耗就可以赋值为false或者删掉这一行
        this.isInnate = false;//固有属性，false不固有，true固有。可在该类里调用改变。不固有就可以赋值为false或者删掉这一行
    }

    //用于显示在卡牌一览里。同时也是诸多卡牌复制效果所需要调用的基本方法，用来获得一张该卡的原始模板修改后加入手牌/抽牌堆/弃牌堆/牌组。
    public AbstractCard makeCopy() {
        return new SkillDeathBolgCard();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();//升级名称。必带。
            this.upgradeBaseCost(1);//升级后的费用。注意括号内的值即为费用，与上方不同！！！！
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        //先为自己增加2点力量
//        ArmsUtil.addOrChangArms(abstractPlayer);
//        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(abstractPlayer, abstractPlayer,
//                new StrengthPower(abstractPlayer, this.magicNumber), this.magicNumber, AbstractGameAction.AttackEffect.POISON));
        //兵器：突穿死翔之枪
        DeathBolgPower power = new DeathBolgPower(abstractPlayer, this.magicNumber);
        ArmsUtil.addOrChangArms(abstractPlayer, power);
//        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(abstractPlayer, abstractPlayer,power
//                , this.magicNumber, AbstractGameAction.AttackEffect.POISON));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
//        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }
}

package com.wsk.cards.arms;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.wsk.actions.ActionUtil;
import com.wsk.cards.AbstractShieldCard;
import com.wsk.patches.AbstractCardEnum;
import com.wsk.powers.arms.ExplosiveArmorPower;
import com.wsk.utils.ArmsUtil;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/25
 * @desc 兵器：炸裂装甲
 */
public class SkillExplosiveArmorCard extends AbstractShieldCard {
    public static final String ID = "LagranYue:SkillExplosiveArmorCard";//卡牌在游戏中的id
    private static final String NAME/* = "来自WSK的庇护"*/;//卡牌显示的名称

    private static final String DESCRIPTION /*= "获得 2 点 力量"*/;//卡牌下方的描叙内容。
    private static final String UPGRADED_DESCRIPTION /*= "获得 2 点 力量"*/;//卡牌下方的描叙内容。

    private static final CardStrings cardStrings;

    private static final String IMG = "cards/SkillExplosiveArmorCard.png";//卡牌牌面的图片路径。

    private static final int COST = 2;//卡牌的费用。

    private static final int cardDefend = 12;

    public SkillExplosiveArmorCard() {
        super(ID, NAME, CommonUtil.getResourcePath(IMG), COST, DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.LagranYue,
                CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseBlock = cardDefend;
        this.magicNumber = this.baseMagicNumber = 1;
        this.isEthereal = false;//虚无属性，false不虚无，true虚无。可在该类里调用改变。不虚无就可以赋值为false或者删掉这一行
        this.exhaust = true;//消耗属性，false不消耗，true消耗。可在该类里调用改变。不消耗就可以赋值为false或者删掉这一行
        this.isInnate = false;//固有属性，false不固有，true固有。可在该类里调用改变。不固有就可以赋值为false或者删掉这一行
    }

    public AbstractCard makeCopy() {
        return new SkillExplosiveArmorCard();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();//升级名称。必带。
            this.upgradeBlock(3);
            this.isInnate = true;
            this.rawDescription = UPGRADED_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //获得格挡
        ActionUtil.gainBlockAction(p, this.block);
//        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        ExplosiveArmorPower power = new ExplosiveArmorPower(p, this.magicNumber);
        ArmsUtil.addOrChangArms(p, power);
//        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, power
//                , this.magicNumber, AbstractGameAction.AttackEffect.POISON));
//        ActionUtil.dexterityPower(p, this.magicNumber);
//        ActionUtil.thornsPower(p, this.magicNumber);
        //获得敏捷
//        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, this.magicNumber), this.magicNumber, AbstractGameAction.AttackEffect.POISON));
        //获得荆棘
//        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ThornsPower(p, this.magicNumber), this.magicNumber, AbstractGameAction.AttackEffect.POISON));
    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }
}

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
import com.wsk.cards.AbstractSpearCard;
import com.wsk.helps.LogHelper;
import com.wsk.patches.AbstractCardEnum;
import com.wsk.powers.arms.GaeBolgPower;
import com.wsk.utils.ArmsUtil;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/25
 * @desc 一句话说明
 */
public class SkillGaeBolgCard extends AbstractSpearCard {
    public static final String ID = "LagranYue:SkillGaeBolgCard";//卡牌在游戏中的id
    private static final String NAME/* = "来自WSK的庇护"*/;//卡牌显示的名称

    private static final String DESCRIPTION /*= "获得 2 点 力量"*/;//卡牌下方的描叙内容。
    public static final String[] EXTENDED_DESCRIPTION;

    private static final CardStrings cardStrings;

    private static final String IMG = "cards/SkillGaeBolgCard.png";//卡牌牌面的图片路径。

    private static final int COST = 2;//卡牌的费用。

    public SkillGaeBolgCard() {
        super(ID, NAME, CommonUtil.getResourcePath(IMG), COST, DESCRIPTION,
                CardType.ATTACK,
                AbstractCardEnum.LagranYue,
                CardRarity.UNCOMMON, CardTarget.ALL);
        this.magicNumber = this.baseMagicNumber = 1;
        this.isEthereal = false;//虚无属性，false不虚无，true虚无。可在该类里调用改变。不虚无就可以赋值为false或者删掉这一行
        this.exhaust = true;//消耗属性，false不消耗，true消耗。可在该类里调用改变。不消耗就可以赋值为false或者删掉这一行
        this.isInnate = false;//固有属性，false不固有，true固有。可在该类里调用改变。不固有就可以赋值为false或者删掉这一行
        this.chooseDesc.add(EXTENDED_DESCRIPTION[0]);
        this.chooseDesc.add(EXTENDED_DESCRIPTION[1]);
        this.baseDamage = 8;
    }

    //用于显示在卡牌一览里。同时也是诸多卡牌复制效果所需要调用的基本方法，用来获得一张该卡的原始模板修改后加入手牌/抽牌堆/弃牌堆/牌组。
    public AbstractCard makeCopy() {
        return new SkillGaeBolgCard();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();//升级名称。必带。
            this.upgradeDamage(4);
//            this.upgradeBaseCost(1);//升级后的费用。注意括号内的值即为费用，与上方不同！！！！
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        //兵器：刺穿死棘之枪
//        GaeBolgPower power = new GaeBolgPower(abstractPlayer, this.magicNumber);
//        ArmsUtil.addOrChangArms(abstractPlayer, power);
        AbstractDungeon.actionManager.addToBottom(new ChooseAction(this, this.getChooseCardGroup()));
    }

    @Override
    public void choose(int num) {
        this.applyPowers();
        if (num == 0) {
            GaeBolgPower power = new GaeBolgPower(AbstractDungeon.player, this.magicNumber);
            ArmsUtil.addOrChangArms(AbstractDungeon.player, power);
        } else {
            if (num != 1) {
                LogHelper.logger.info("choose card error...........");
                return;
            }
            AbstractDungeon.actionManager.addToBottom(
                    new DamageAllEnemiesAction(AbstractDungeon.player, this.multiDamage, this.damageTypeForTurn,
                            AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        }

    }

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
//        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
    }
}

package com.wsk.cards.proficiency;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.wsk.actions.ActionUtil;
import com.wsk.patches.AbstractCardEnum;
import com.wsk.powers.proficiency.MagicPower;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/3/20
 * @description 银河零式
 */
public class AttackGalacticZeroCard extends AbstractProfSpearCard {

    /**
     * 卡牌在游戏中的id
     */
    public static final String ID = "LagranYue:AttackGalacticZeroCard";
    /**
     * 卡牌显示的名称
     */
    private static final String NAME;

    /**
     * 卡牌下方的描叙内容。
     */
    private static final String DESCRIPTION;

    private static final CardStrings CARD_STRINGS;

    /**
     * 卡牌牌面的图片路径。
     */
    private static final String IMG = "cards/AttackGalacticZeroCard.png";

    /**
     * 卡牌的费用。
     */
    private static final int COST = 1;


    public AttackGalacticZeroCard() {
        super(ID, NAME, CommonUtil.getResourcePath(IMG), COST, DESCRIPTION,
                CardType.ATTACK,
                AbstractCardEnum.LagranYue,
                CardRarity.RARE,
                CardTarget.ALL_ENEMY);
        this.proficiency = this.baseProficiency = 10;
        //基础伤害值，除升级以外无任何其他加成. this.damage为有力量、钢笔尖等加成的伤害值.
        this.baseDamage = 5;
        this.magicNumber = this.baseMagicNumber = 3;
        this.isMultiDamage = true;
        this.isEthereal = false;

        this.exhaust = false;

        this.isInnate = false;
        //保留属性
        this.retain = false;
    }

    /**
     * //用于显示在卡牌一览里。同时也是诸多卡牌复制效果所需要调用的基本方法，
     * 用来获得一张该卡的原始模板修改后加入手牌/抽牌堆/弃牌堆/牌组。
     *
     * @return AbstractCard
     */
    @Override
    public AbstractCard makeCopy() {
        return new AttackGalacticZeroCard();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {

            this.upgradeName();

            this.upgradeDamage(5);

//            this.upgradeMagicNumber(2);
            // 升级后的费用。注意括号内的值即为费用，与上方不同！！！！
//            this.upgradeBaseCost(1);
            // 虚无属性。
//            this.isEthereal = false;
            // 消耗属性。
//            this.exhaust = false;
            // 固有属性。
//            this.isInnate = false;
        }
    }

    /**
     * @param p 玩家
     * @param m m敌人
     */
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; i++) {
            if (i % 2 == 0) {
                AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            } else {
                AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
            }
        }
        ActionUtil.addPower(p, new MagicPower(p));
    }

    static {
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = CARD_STRINGS.NAME;
        DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    }
}


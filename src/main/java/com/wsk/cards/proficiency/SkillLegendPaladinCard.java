package com.wsk.cards.proficiency;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.wsk.actions.ActionUtil;
import com.wsk.patches.AbstractCardEnum;
import com.wsk.powers.proficiency.DivinityPower;
import com.wsk.utils.ArmsUtil;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/3/20
 * @description 圣骑士传说的终幕
 */
public class SkillLegendPaladinCard extends AbstractProfShieldCard {

    /**
     * 卡牌在游戏中的id
     */
    public static final String ID = "LagranYue:SkillLegendPaladinCard";
    /**
     * 卡牌显示的名称
     */
    private static final String NAME;

    /**
     * 卡牌下方的描叙内容。
     */
    private static final String DESCRIPTION;
    private static final String UPGRADED_DESCRIPTION;

    private static final CardStrings CARD_STRINGS;

    /**
     * 卡牌牌面的图片路径。
     */
    private static final String IMG = "cards/SkillLegendPaladinCard.png";

    /**
     * 卡牌的费用。
     */
    private static final int COST = 3;


    public SkillLegendPaladinCard() {
        super(ID, NAME, CommonUtil.getResourcePath(IMG), COST, DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.LagranYue,
                CardRarity.RARE,
                CardTarget.SELF);
        this.proficiency = this.baseProficiency = 4;
        //基础伤害值，除升级以外无任何其他加成. this.damage为有力量、钢笔尖等加成的伤害值.
        this.baseBlock = 4;
        this.magicNumber = this.baseMagicNumber = 3;

        this.isEthereal = false;

        this.exhaust = true;

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
        return new SkillLegendPaladinCard();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {

            this.upgradeName();
            this.upgradeBaseCost(2);
            this.rawDescription = UPGRADED_DESCRIPTION;
            this.initializeDescription();
//            this.upgradeMagicNumber(1);
            // 升级后的费用。注意括号内的值即为费用，与上方不同！！！！
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
        ActionUtil.addPower(p, new DivinityPower(p));
        int u;
        if (upgraded) {
            u = ArmsUtil.getArmsNum();
        } else {
            u = ArmsUtil.currentAllArmsNum();
        }
        for (int i = 0; i < this.magicNumber + u; i++) {
            ActionUtil.gainBlockAction(p, this.block);
        }
    }

    static {
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = CARD_STRINGS.NAME;
        DESCRIPTION = CARD_STRINGS.DESCRIPTION;
        UPGRADED_DESCRIPTION = CARD_STRINGS.UPGRADE_DESCRIPTION;
    }
}

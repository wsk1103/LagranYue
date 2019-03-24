package com.wsk.cards.proficiency;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.wsk.actions.ActionUtil;
import com.wsk.patches.AbstractCardEnum;
import com.wsk.powers.proficiency.WindShelterPower;
import com.wsk.utils.ArmsUtil;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/3/20
 * @description 灾祸的装备品
 */
public class SkillDisasterEquipmentCard extends AbstractProfShieldCard {

    /**
     * 卡牌在游戏中的id
     */
    public static final String ID = "LagranYue:SkillDisasterEquipmentCard";
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
    private static final String IMG = "cards/SkillDisasterEquipmentCard.png";

    /**
     * 卡牌的费用。
     */
    private static final int COST = 1;


    public SkillDisasterEquipmentCard() {
        super(ID, NAME, CommonUtil.getResourcePath(IMG), COST, DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.LagranYue,
                CardRarity.RARE,
                CardTarget.SELF);
        this.proficiency = 3;
        //基础伤害值，除升级以外无任何其他加成. this.damage为有力量、钢笔尖等加成的伤害值.
        this.baseBlock = 8;
        this.magicNumber = this.baseMagicNumber = 3;
        //虚无属性，false不虚无，true虚无。可在该类里调用改变。不虚无就可以赋值为false或者删掉这一行
        this.isEthereal = false;
        //消耗属性，false不消耗，true消耗。可在该类里调用改变。不消耗就可以赋值为false或者删掉这一行
        this.exhaust = false;
        //固有属性，false不固有，true固有。可在该类里调用改变。不固有就可以赋值为false或者删掉这一行
        this.isInnate = false;
        //保留属性
        this.retain = true;
    }

    /**
     * //用于显示在卡牌一览里。同时也是诸多卡牌复制效果所需要调用的基本方法，
     * 用来获得一张该卡的原始模板修改后加入手牌/抽牌堆/弃牌堆/牌组。
     *
     * @return AbstractCard
     */
    @Override
    public AbstractCard makeCopy() {
        return new SkillDisasterEquipmentCard();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            //升级名称。必带。
            this.upgradeName();

            this.upgradeBlock(2);
            this.rawDescription = UPGRADED_DESCRIPTION;
            this.initializeDescription();
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

    @Override
    public void applyPowers() {
        super.applyPowers();
        this.retain = true;
    }

    /**
     * @param p 玩家
     * @param m m敌人
     */
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int u;
        if (upgraded) {
            u = ArmsUtil.getArmsNum();
        } else {
            u = ArmsUtil.currentAllArmsNum();
        }
        for (int i = 0; i < this.magicNumber + u; i++) {
            ActionUtil.gainBlockAction(p, this.block);
        }
        ActionUtil.addPower(p, new WindShelterPower(p, 1));
    }

    static {
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = CARD_STRINGS.NAME;
        DESCRIPTION = CARD_STRINGS.DESCRIPTION;
        UPGRADED_DESCRIPTION = CARD_STRINGS.UPGRADE_DESCRIPTION;
    }
}
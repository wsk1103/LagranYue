package com.wsk.cards.attack;

import basemod.abstracts.CustomCard;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.wsk.patches.AbstractCardEnum;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/14
 * @desc 一句话说明
 */
public class BaseAttackCard extends CustomCard {

    /**
     * 卡牌在游戏中的id
     */
    public static final String ID = "LagranYue:BaseAttackCard";
    /**
     * 卡牌显示的名称
     */
    private static final String NAME;

    /**
     * 卡牌下方的描叙内容。
     */
    private static final String DESCRIPTION;

    //注：
    //描叙中存在几个特殊的字符串：
    // 1. !D! 、 !M! 、 !B!。
    //该字符串全为英文字符，使用时前后需用空格完整地与其他文本前后隔开，从左至右依次表示damage、magicnumber、block三个变量的值，当三个变量值与baseDamage、baseMagicnumber、baseBlock不同时发生对应的颜色变化。
    //这六个变量的具体信息见：
    // 2. [R]、[G]、[B]
    //该字符串全为英文字符，使用时前后需用空格完整地与其他文本前后隔开，从左至右依次表示战士的能量、猎手的能量、机器人的能量。
    // 3. 关键字，诸如 力量 、 敏捷。
    //该字符串无中英文限制，使用时前后需用空格完整地与其他文本前后隔开，游戏中已有的关键字可以直接使用，需要自定义的可以去接口部分自己编辑关键字。（详参ModCore的receiveEditKeywords部分）
    //
    //实例：  “获得 !M! 层 力量 。如果 力量 超过3层，额外获得 [R] 。”;

    private static final CardStrings CARD_STRINGS;

    /**
     * 卡牌牌面的图片路径。
     */
    private static final String IMG = "cards/BaseAttackCard.png";

    /**
     * 卡牌的费用。
     */
    private static final int COST = 1;

    public BaseAttackCard() {
        super(ID, NAME, CommonUtil.getResourcePath(IMG), COST, DESCRIPTION,
                CardType.ATTACK,
                AbstractCardEnum.LagranYue,
                CardRarity.BASIC,
                CardTarget.ENEMY);

//        this.baseBlock = defind;//基础格挡值，除升级以外无任何其他加成. this.block为有敏捷等加成的格挡值.
        //基础伤害值，除升级以外无任何其他加成. this.damage为有力量、钢笔尖等加成的伤害值.
        this.baseDamage = 6;
//        this.baseMagicNumber = 0;//特殊值。一般用于表示给予power的层数，也可用于表示一些需要出现在描叙文本里的值。和下一行连用。
//        this.magicNumber = this.baseMagicNumber;
        this.tags.add(BaseModCardTags.BASIC_STRIKE);
        this.tags.add(AbstractCard.CardTags.STRIKE);
        //虚无属性，false不虚无，true虚无。可在该类里调用改变。不虚无就可以赋值为false或者删掉这一行
        this.isEthereal = false;
        //消耗属性，false不消耗，true消耗。可在该类里调用改变。不消耗就可以赋值为false或者删掉这一行
        this.exhaust = false;
        //固有属性，false不固有，true固有。可在该类里调用改变。不固有就可以赋值为false或者删掉这一行
        this.isInnate = false;
        //保留属性
        this.retain = false;
    }
    //AbstractCard.CardType.ATTACK ———— 卡牌种类。决定诸如技能卡、能力卡、攻击卡这种种类。
    //AbstractCardEnum.Type        ———— 卡牌颜色。决定该卡牌属于哪个角色。
    //AbstractCard.CardRarity.BASIC———— 卡牌稀有度。决定卡牌的稀有度，同时稀有度决定卡牌的出现概率。
    //AbstractCard.CardTarget.ENEMY———— 卡牌目标。决定你打出卡牌时指向效果。

    //卡牌种类：AbstractCard.CardType.SKILL——技能，AbstractCard.CardType.ATTACK——攻击，AbstractCard.CardType.POWER——能力，AbstractCard.CardType.CURSE——诅咒，AbstractCard.CardType.STATU——状态；
    //卡牌分类：AbstractCard.CardRarity.BASIC——基础牌，AbstractCard.CardRarity.COMMON——白卡，AbstractCard.CardRarity.RARE——金卡，AbstractCard.CardRarity.UNCOMMON——蓝卡
    //卡牌目标（就是选择了卡牌后能选的目标）：AbstractCard.CardTarget.SELF——自己，AbstractCard.CardTarget.SELF_AND_ENEMY——自己以及敌人，AbstractCard.CardTarget.ALL_ENEMY——所有敌人，AbstractCard.CardTarget.ENEMY——敌人；

    /**
     * //用于显示在卡牌一览里。同时也是诸多卡牌复制效果所需要调用的基本方法，
     * 用来获得一张该卡的原始模板修改后加入手牌/抽牌堆/弃牌堆/牌组。
     *
     * @return AbstractCard
     */
    @Override
    public AbstractCard makeCopy() {
        return new BaseAttackCard();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            //升级名称。必带。
            this.upgradeName();
            //升级而增加的护甲。增加的是baseDamage
//            this.upgradeBlock(defind * 2);

            //升级而增加的伤害。增加的是baseBlock
            this.upgradeDamage(3);
            // 升级而增加的特殊值。增加的是baseMagicNumber
//            this.upgradeMagicNumber(1);
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
    //注：该部分为升级的效果部分，此处展示的代码为只能升级一次的代码，如需无限升级，卡牌代码有些许不同但不便于例出，请自行查看灼热攻击源码。

    /**
     *
     * @param p 玩家
     * @param m m敌人
     */
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
/*
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, 1), 1));
        //给予power。ApplyPowerAction参数：执行者，给予者，具体的power(执行者，计数层数)，power层数。一般power层数和计数层数一致。

        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AttackEffect.SLASH_DIAGONAL));
        //单体伤害。参数：DamageAction参数：受伤害者，伤害信息(给予伤害者，伤害数值，伤害种类)，伤害动画效果。
        //伤害数值：如果数值直接填写阿拉伯数字即为不受任何其他能力、遗物等效果所影响的固定伤害。如果填写this.damage即为受力量、钢笔尖、升级等影响的伤害。如果填写this.baseDamage即为只受升级所影响的伤害。
        //伤害种类：this.damageTypeForTurn一般情况为DamageType.NORMAL。DamageType.NORMAL——可触发能力的伤害。DamageType.HP_LOSS——直接失去生命值的伤害。DamageType.THORNS——不可触发能力又计算护甲的伤害。

        AbstractDungeon.actionManager.addToBottom(new DualWieldAction(p, this.magicNumber));
        //将非技能牌复制。DualWieldAction参数：执行者，复制次数。

        AbstractDungeon.actionManager.addToBottom(new ExhaustAction(p, p, this.magicNumber, false));
        //消耗手牌。ExhaustAction参数：执行者，给予者，消耗卡牌张数，是否随机消耗（true随机，false不随机）。

        AbstractDungeon.actionManager.addToBottom(new DiscardAction(p, p, this.magicNumber, false));
        //丢弃手牌。DiscardAction参数：执行者，给予者，丢弃卡牌张数，是否随机丢牌（true随机，false不随机）。

        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new TestAttackCard().makeCopy(), this.magicNumber));
        //复制卡牌。MakeTempCardInHandAction参数：被复制的卡牌，复制张数。
        //注：如果需要对被复制的卡牌执行一些改动，可先写（将卡牌实例化）：AbstractCard c = new CardName().makeCopy;然后用c.执行一系列方法后，在被复制卡牌处填写c即可。（c可更改为其他名称）

        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));
        //抽卡。DrawCardAction参数：执行者，抽卡张数。

        AbstractDungeon.actionManager.addToBottom(new ArmamentsAction(false));
        //本场战斗中升级手牌。ArmamentsAction参数：是否升级全部手牌（true手牌全部升级，false升级一张手牌）。

//        AbstractDungeon.actionManager.addToBottom(new HavocAction(AbstractDungeon.getRandomMonster(), 1));
        //使用并消耗牌库顶的卡。HavocAction参数：卡牌效果目标，使用张数。
        //注：AbstractDungeon.getRandomMonster() 为随机敌人。

        AbstractDungeon.actionManager.addToBottom(new DiscardPileToTopOfDeckAction(p));
        //将弃牌堆中的一张卡放到抽牌堆顶端。

        AbstractDungeon.actionManager.addToBottom(new ApplyBulletTimeAction());
        //使你该回合内所有卡牌费用为0。

        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(2));
        //获得能量。GainEnergyAction参数：获得能量数。

        AbstractDungeon.actionManager.addToBottom(new RemoveDebuffsAction(p));
        //解除debuff。RemoveDebuffsAction参数：执行者。
        //注：debuff的判断和效果是否有利于执行者无关，仅与power的参数PowerType有关。

        AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, this.magicNumber));
        //恢复生命。HealAction参数：执行者，给予者，恢复生命数值。

        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        //获得格挡。GainBlockAction参数：执行者，给予者，获得格挡数值。
        //注：this.block为包含敏捷、升级的格挡值。this.baseBlock为仅包含升级的格挡数值。阿拉伯数字为不受任何影响的格挡值。
*/
    }//注：卡牌效果的diy区。

    /*

        //canUse返回值为true可以被打出，为false不能被打出。通过自行添加代码可控制卡牌打出与否。可不写此方法。
        public boolean canUse(AbstractPlayer p, AbstractMonster m) {
            boolean canUse = super.canUse(p, m);
            //diy区。
            this.cantUseMessage = "能量不够";//卡牌不能被打出时所提示的文本。
            return canUse;
        }

        //canUpgrade返回值为true可以被升级，返回值为false不可被升级。通过添加代码可控制升级次数。可不写此方法。
        public boolean canUpgrade() {
            boolean canUpgrade = true;
            //diy区
            return canUpgrade;
        }

        public void triggerWhenDrawn() {
        }//触发时机：当卡牌被抽到时。

        public void onMoveToDiscard() {
            this.rawDescription = "原文本";
            this.initializeDescription();//这两行作用见下applyPower方法。
        }//触发时机：当卡牌被丢去弃牌堆时。

        public void triggerWhenCopied() {
        }//触发时机：当卡牌被复制时。

        public void triggerOnEndOfPlayerTurn() {
        }//触发时机：当玩家结束回合时。（可能与虚无属性冲突，未测试。如冲突，解决办法：将虚无的三行代码（AbstractCard的2155-2157行）复制丢入。）

        public void triggerOnOtherCardPlayed(final AbstractCard c) {
        }//触发时机：当其他卡被打出时。

        public void triggerOnGainEnergy(final int e, final boolean dueToCard) {
        }//触发时机：当玩家获得能量时。（回合开始时获得能量是否也会触发未测试。）

        public void triggerOnCardPlayed(final AbstractCard cardPlayed) {
        }//触发时机：当一张卡被打出后。

        public void onPlayCard(final AbstractCard c, final AbstractMonster m) {
        }//触发时机：当一张卡被打出时。

        //注：猜测以上两个时机，前者为卡牌效果生效后，后者为卡牌效果生效前。未测试，仅提供参考。

        public void atTurnStart() {
        }//触发时机：回合开始时。

        public void triggerOnExhaust() {
        }//触发时机：当卡牌被消耗时。

        public void applyPowers() {
            this.rawDescription = "修改后的文本";
            this.initializeDescription();
        }//触发时机较为复杂。官方用用途之一为抽到卡时更新卡面描叙。方法内两句需连用。如果需要恢复原文本，则在上方的卡牌被丢进抽牌堆的时机内this.rewDescription = 原文本;this.initializeDescription();即可。
    */

    static {
        CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = CARD_STRINGS.NAME;
        DESCRIPTION = CARD_STRINGS.DESCRIPTION;
//        UPGRADED_DESCRIPTION = CARD_STRINGS.UPGRADE_DESCRIPTION;
    }

}

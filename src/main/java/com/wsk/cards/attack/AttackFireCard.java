package com.wsk.cards.attack;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.wsk.patches.AbstractCardEnum;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/21
 * @desc 一句话说明
 */
public class AttackFireCard extends CustomCard {
    public static final String ID = "LagranYue:AttackFireCard";//卡牌在游戏中的id
    private static final String NAME /*= "来自WSK的攻击"*/;//卡牌显示的名称

    private static final String DESCRIPTION /*= "造成 !D! 点伤害。"*/;//卡牌下方的描叙内容。
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
    private static final CardStrings cardStrings;
    private static final String IMG = "cards/AttackFireCard.png";//卡牌牌面的图片路径。
    //例：img/cards/claw/attack/BloodSuckingClaw_Orange.png  详细情况请根据自己项目的路径布置进行填写。

    private static final int COST = 1;//卡牌的费用。

    private static final int wskAttack = 8;

    public AttackFireCard() {
        super(ID, NAME, CommonUtil.getResourcePath(IMG), COST, DESCRIPTION,
                CardType.ATTACK,
                AbstractCardEnum.LagranYue,
                CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        //上一行为继承basemod的CustomCard类里的构造方法。五个参数（ID、NAME、IMG、COST、DESCRIPTION）为上方已声明出的变量，如果不在上方声明，可以在此处对应位置直接填写内容。

//        this.baseBlock = defind;//基础格挡值，除升级以外无任何其他加成. this.block为有敏捷等加成的格挡值.
        this.baseDamage = wskAttack;//基础伤害值，除升级以外无任何其他加成. this.damage为有力量、钢笔尖等加成的伤害值.
//        this.baseMagicNumber = 0;//特殊值。一般用于表示给予power的层数，也可用于表示一些需要出现在描叙文本里的值。和下一行连用。
//        this.magicNumber = this.baseMagicNumber;
//        this.tags.add(BaseModCardTags.BASIC_STRIKE);
//        this.tags.add(AbstractCard.CardTags.STRIKE);
        this.isEthereal = false;//虚无属性，false不虚无，true虚无。可在该类里调用改变。不虚无就可以赋值为false或者删掉这一行
        this.exhaust = false;//消耗属性，false不消耗，true消耗。可在该类里调用改变。不消耗就可以赋值为false或者删掉这一行
        this.isInnate = false;//固有属性，false不固有，true固有。可在该类里调用改变。不固有就可以赋值为false或者删掉这一行
        //例：我需要在升级后虚无、消耗、固有。即可在下方upgrade()方法里this.isEthereal/this.exhaust/this.isInnate调用 赋值为 true。
        //   使用时满足条件不虚无/消耗，在use中填写if判定语句，满足条件时，this.isEthereal/this.exhaust调用 赋值为false即可。实例不详细赘叙。
    }
    //super中参数的含义：        super(ID, NAME, IMG, COST, DESCRIPTION, AbstractCard.CardType.ATTACK, AbstractCardEnum.Type, AbstractCard.CardRarity.BASIC, AbstractCard.CardTarget.ENEMY);
    //前五项已在声明部分有过解释不再赘述。
    //AbstractCard.CardType.ATTACK ———— 卡牌种类。决定诸如技能卡、能力卡、攻击卡这种种类。
    //AbstractCardEnum.Type        ———— 卡牌颜色。决定该卡牌属于哪个角色。
    //AbstractCard.CardRarity.BASIC———— 卡牌稀有度。决定卡牌的稀有度，同时稀有度决定卡牌的出现概率。
    //AbstractCard.CardTarget.ENEMY———— 卡牌目标。决定你打出卡牌时指向效果。

    //卡牌种类：AbstractCard.CardType.SKILL——技能，AbstractCard.CardType.ATTACK——攻击，AbstractCard.CardType.POWER——能力，AbstractCard.CardType.CURSE——诅咒，AbstractCard.CardType.STATU——状态；
    //卡牌分类：AbstractCard.CardRarity.BASIC——基础牌，AbstractCard.CardRarity.COMMON——白卡，AbstractCard.CardRarity.RARE——金卡，AbstractCard.CardRarity.UNCOMMON——蓝卡
    //卡牌颜色：需要新建一个AbstractCardEnum类import进该类后使用。代码如下：@SpireEnum /换行  public static AbstractCard.CardColor Color; Color为你的角色对应的颜色）
    //卡牌目标（就是选择了卡牌后能选的目标）：AbstractCard.CardTarget.SELF——自己，AbstractCard.CardTarget.SELF_AND_ENEMY——自己以及敌人，AbstractCard.CardTarget.ALL_ENEMY——所有敌人，AbstractCard.CardTarget.ENEMY——敌人；

    //注：该部分为卡牌初始化的部分。

    public AbstractCard makeCopy() {
        return new AttackFireCard();
    }//用于显示在卡牌一览里。同时也是诸多卡牌复制效果所需要调用的基本方法，用来获得一张该卡的原始模板修改后加入手牌/抽牌堆/弃牌堆/牌组。

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();//升级名称。必带。
//            this.upgradeBlock(defind * 2);//升级而增加的护甲。增加的是baseDamage
            this.upgradeDamage(4);//升级而增加的伤害。增加的是baseBlock
//            this.upgradeMagicNumber(1);//升级而增加的特殊值。增加的是baseMagicNumber
//            this.upgradeBaseCost(1);//升级后的费用。注意括号内的值即为费用，与上方不同！！！！
//            this.isEthereal = false;//虚无属性。
//            this.exhaust = false;//消耗属性。
//            this.isInnate = false;//固有属性。
        }
    }//注：该部分为升级的效果部分，此处展示的代码为只能升级一次的代码，如需无限升级，卡牌代码有些许不同但不便于例出，请自行查看灼热攻击源码。

    //以上为卡牌的必备内容，不可缺少。
    public void use(AbstractPlayer p, AbstractMonster m) {//局部变量：p-玩家，m敌人。
        //注：以下注释里：执行者 指动作效果生效的目标。给予者 指产生动作效果的来源。

        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
    }//注：卡牌效果的diy区。
    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
//        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }
}

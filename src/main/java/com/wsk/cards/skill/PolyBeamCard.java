package com.wsk.cards.skill;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.SmallLaserEffect;
import com.wsk.patches.AbstractCardEnum;
import com.wsk.powers.PolyBeamAgainPower;
import com.wsk.powers.PolyBeamAgainPowerUpgraded;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/21
 * @desc 一句话说明
 */
public class PolyBeamCard extends CustomCard {
    public static final String ID = "MyMod:PolyBeamCard";//卡牌在游戏中的id
    private static final String NAME /*= "來自WSK的愛"*/;//卡牌显示的名称

    private static final String DESCRIPTION /*= "WSK給予 !B! 点 格挡 。"*/;//卡牌下方的描叙内容。

    private static final CardStrings cardStrings;

    private static final String IMG = "cards/slimepunch.png";//卡牌牌面的图片路径。
    //例：img/cards/claw/attack/BloodSuckingClaw_Orange.png  详细情况请根据自己项目的路径布置进行填写。

    private static final int COST = 1;//卡牌的费用。

    public boolean isACopy = false;

    public static final String UPGRADED_DESCRIPTION;

    //注：以上声明的五个变量并非强制需要。仅出于代码的美观考虑而写。

    public PolyBeamCard() {
        super(ID, NAME, CommonUtil.getResourcePath(IMG), COST, DESCRIPTION,
                CardType.SKILL,
                AbstractCardEnum.MyModCard,
                CardRarity.RARE, CardTarget.SELF);
        //上一行为继承basemod的CustomCard类里的构造方法。五个参数（ID、NAME、IMG、COST、DESCRIPTION）为上方已声明出的变量，如果不在上方声明，可以在此处对应位置直接填写内容。

        this.baseDamage = 3;
        this.baseMagicNumber = this.magicNumber = 1;
//        this.baseBlock = DEFEND;//基础格挡值，除升级以外无任何其他加成. this.block为有敏捷等加成的格挡值.
//        this.baseMagicNumber = 0;//特殊值。一般用于表示给予power的层数，也可用于表示一些需要出现在描叙文本里的值。和下一行连用。
//        this.magicNumber = this.baseMagicNumber;
//        this.tags.add(BaseModCardTags.BASIC_DEFEND);
//        this.isEthereal = false;//虚无属性，false不虚无，true虚无。可在该类里调用改变。不虚无就可以赋值为false或者删掉这一行
        this.exhaust = true;//消耗属性，false不消耗，true消耗。可在该类里调用改变。不消耗就可以赋值为false或者删掉这一行
//        this.isInnate = false;//固有属性，false不固有，true固有。可在该类里调用改变。不固有就可以赋值为false或者删掉这一行
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
        return new PolyBeamCard();
    }//用于显示在卡牌一览里。同时也是诸多卡牌复制效果所需要调用的基本方法，用来获得一张该卡的原始模板修改后加入手牌/抽牌堆/弃牌堆/牌组。

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(1);
        }
    }//注：该部分为升级的效果部分，此处展示的代码为只能升级一次的代码，如需无限升级，卡牌代码有些许不同但不便于例出，请自行查看灼热攻击源码。

    //以上为卡牌的必备内容，不可缺少。
    public void use(AbstractPlayer p, AbstractMonster m) {//局部变量：p-玩家，m敌人。
        AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_MAGIC_BEAM_SHORT", 0.5F));

        AbstractDungeon.actionManager.addToBottom(new VFXAction(new SmallLaserEffect(m.hb.cX, m.hb.cY, p.hb.cX, p.hb.cY), 0.3F));
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));

        if (!this.isACopy) {
            if (upgraded) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PolyBeamAgainPowerUpgraded(p, p, this.magicNumber, this), this.magicNumber));
            } else {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new PolyBeamAgainPower(p, p, this.magicNumber, this), this.magicNumber));

            }
        }
    }//注：卡牌效果的diy区。

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }
}

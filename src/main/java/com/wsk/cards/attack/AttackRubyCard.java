package com.wsk.cards.attack;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
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
 * @date 2019/2/24
 * @desc 红宝石的狂袭
 */
public class AttackRubyCard extends CustomCard {
    public static final String ID = "MyMod:AttackRubyCard";//卡牌在游戏中的id
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
    private static final String IMG = "cards/AttackRubyCard.png";//卡牌牌面的图片路径。
    //例：img/cards/claw/attack/BloodSuckingClaw_Orange.png  详细情况请根据自己项目的路径布置进行填写。

    private static final int COST = 1;//卡牌的费用。

    public AttackRubyCard() {
        super(ID, NAME, CommonUtil.getResourcePath(IMG), COST, DESCRIPTION,
                CardType.ATTACK,
                AbstractCardEnum.MyModCard,
                CardRarity.COMMON, CardTarget.ALL_ENEMY);
        //上一行为继承basemod的CustomCard类里的构造方法。五个参数（ID、NAME、IMG、COST、DESCRIPTION）为上方已声明出的变量，如果不在上方声明，可以在此处对应位置直接填写内容。
        this.baseDamage = 6;//基础伤害值，除升级以外无任何其他加成. this.damage为有力量、钢笔尖等加成的伤害值.
        this.isEthereal = false;//虚无属性，false不虚无，true虚无。可在该类里调用改变。不虚无就可以赋值为false或者删掉这一行
        this.exhaust = false;//消耗属性，false不消耗，true消耗。可在该类里调用改变。不消耗就可以赋值为false或者删掉这一行
        this.isInnate = false;//固有属性，false不固有，true固有。可在该类里调用改变。不固有就可以赋值为false或者删掉这一行
        //例：我需要在升级后虚无、消耗、固有。即可在下方upgrade()方法里this.isEthereal/this.exhaust/this.isInnate调用 赋值为 true。
        //   使用时满足条件不虚无/消耗，在use中填写if判定语句，满足条件时，this.isEthereal/this.exhaust调用 赋值为false即可。实例不详细赘叙。
    }

    public AbstractCard makeCopy() {
        return new AttackRubyCard();
    }//用于显示在卡牌一览里。同时也是诸多卡牌复制效果所需要调用的基本方法，用来获得一张该卡的原始模板修改后加入手牌/抽牌堆/弃牌堆/牌组。

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();//升级名称。必带。
            this.upgradeDamage(2);//升级而增加的伤害。增加的是baseBlock
//            this.upgradeBlock(900);
        }
    }//注：该部分为升级的效果部分，此处展示的代码为只能升级一次的代码，如需无限升级，卡牌代码有些许不同但不便于例出，请自行查看灼热攻击源码。

    //以上为卡牌的必备内容，不可缺少。
    public void use(AbstractPlayer p, AbstractMonster m) {//局部变量：p-玩家，m敌人。
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            flash();
            for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                if ((!monster.isDead) && (!monster.isDying)) {
                    if (monster != null) {
                        if (monster.intent == AbstractMonster.Intent.ATTACK
                                || monster.intent == AbstractMonster.Intent.ATTACK_BUFF
                                || monster.intent == AbstractMonster.Intent.ATTACK_DEBUFF
                                || monster.intent == AbstractMonster.Intent.ATTACK_DEFEND) {
                            //AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(monster, p, new WeakPower(monster, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
                        } else {
                            AbstractDungeon.actionManager.addToBottom(new DamageAction(monster,
                                    new DamageInfo(p, this.damage, this.damageTypeForTurn),
                                    AbstractGameAction.AttackEffect.BLUNT_HEAVY));
                        }
                    }
                }
            }
        }
    }//注：卡牌效果的diy区。

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
//        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }

}

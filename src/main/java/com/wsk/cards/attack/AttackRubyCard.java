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
    public static final String ID = "LagranYue:AttackRubyCard";
    private static final String NAME /*= "来自WSK的攻击"*/;

    private static final String DESCRIPTION /*= "造成 !D! 点伤害。"*/;
    private static final CardStrings cardStrings;
    private static final String IMG = "cards/AttackRubyCard.png";

    private static final int COST = 1;

    public AttackRubyCard() {
        super(ID, NAME, CommonUtil.getResourcePath(IMG), COST, DESCRIPTION,
                CardType.ATTACK,
                AbstractCardEnum.LagranYue,
                CardRarity.COMMON, CardTarget.ALL_ENEMY);
        //上一行为继承basemod的CustomCard类里的构造方法。五个参数（ID、NAME、IMG、COST、DESCRIPTION）为上方已声明出的变量，如果不在上方声明，可以在此处对应位置直接填写内容。
        this.baseDamage = 5;//基础伤害值，除升级以外无任何其他加成. this.damage为有力量、钢笔尖等加成的伤害值.
        this.isEthereal = false;
        this.exhaust = false;
        this.isInnate = false;
        this.isMultiDamage = true;
    }

    public AbstractCard makeCopy() {
        return new AttackRubyCard();
    }//用于显示在卡牌一览里。同时也是诸多卡牌复制效果所需要调用的基本方法，用来获得一张该卡的原始模板修改后加入手牌/抽牌堆/弃牌堆/牌组。

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(2);//升级而增加的伤害。增加的是baseBlock
//            this.upgradeBlock(900);
        }
    }//注：该部分为升级的效果部分，此处展示的代码为只能升级一次的代码，如需无限升级，卡牌代码有些许不同但不便于例出，请自行查看灼热攻击源码。

    //以上为卡牌的必备内容，不可缺少。
    public void use(AbstractPlayer p, AbstractMonster m) {//局部变量：p-玩家，m敌人。
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            flash();
            for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                if ((!monster.isDead) && (!monster.isDying)) {
                    if (monster != null) {
                        if (monster.intent == AbstractMonster.Intent.ATTACK
                                || monster.intent == AbstractMonster.Intent.ATTACK_BUFF
                                || monster.intent == AbstractMonster.Intent.ATTACK_DEBUFF
                                || monster.intent == AbstractMonster.Intent.ATTACK_DEFEND) {
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

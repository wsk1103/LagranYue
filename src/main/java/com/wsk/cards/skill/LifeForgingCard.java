package com.wsk.cards.skill;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.ChemicalX;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.wsk.actions.ActionUtil;
import com.wsk.patches.AbstractCardEnum;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/3/1
 * @desc 一句话说明
 */
public class LifeForgingCard extends CustomCard {
    public static final String ID = "LagranYue:LifeForgingCard";
    private static final String NAME /*= "来自WSK的攻击"*/;

    private static final String DESCRIPTION /*= "造成 !D! 点伤害。"*/;
    private static final String UPGRADED_DESCRIPTION /*= "造成 !D! 点伤害。"*/;
    private static final CardStrings cardStrings;
    private static final String IMG = "cards/LifeForgingCard.png";

    private static final int COST = -1;

    private static final int wskAttack = 9;

    public LifeForgingCard() {
        super(ID, NAME, CommonUtil.getResourcePath(IMG), COST, DESCRIPTION,
                CardType.SKILL, AbstractCardEnum.LagranYue,
                CardRarity.UNCOMMON, CardTarget.SELF);
        //上一行为继承basemod的CustomCard类里的构造方法。五个参数（ID、NAME、IMG、COST、DESCRIPTION）为上方已声明出的变量，如果不在上方声明，可以在此处对应位置直接填写内容。
        this.baseDamage = wskAttack;//基础伤害值，除升级以外无任何其他加成. this.damage为有力量、钢笔尖等加成的伤害值.
        this.isEthereal = false;
        this.exhaust = false;
        this.isInnate = false;//固有属性，false不固有，true固有。可在该类里调用改变。不固有就可以赋值为false或者删掉这一行
    }

    public AbstractCard makeCopy() {
        return new LifeForgingCard();
    }//用于显示在卡牌一览里。同时也是诸多卡牌复制效果所需要调用的基本方法，用来获得一张该卡的原始模板修改后加入手牌/抽牌堆/弃牌堆/牌组。

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();//升级名称。必带。
            this.upgradeDamage(4);//升级而增加的伤害。增加的是baseBlock
            this.rawDescription = UPGRADED_DESCRIPTION;
            this.initializeDescription();
        }
    }//注：该部分为升级的效果部分，此处展示的代码为只能升级一次的代码，如需无限升级，卡牌代码有些许不同但不便于例出，请自行查看灼热攻击源码。

    //以上为卡牌的必备内容，不可缺少。
    public void use(AbstractPlayer p, AbstractMonster m) {//局部变量：p-玩家，m敌人。
        //注：以下注释里：执行者 指动作效果生效的目标。给予者 指产生动作效果的来源。
//        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        if (this.energyOnUse < EnergyPanel.totalCount) {
            this.energyOnUse = EnergyPanel.totalCount;
        }
        if (upgraded) {
            this.energyOnUse++;
        }
        if (p.hasRelic(ChemicalX.ID)) {
            this.energyOnUse += 2;
            p.getRelic(ChemicalX.ID).flash();
        }
        for (int i = 0; i < energyOnUse; i++) {
            ActionUtil.forgingAction(p, 1, 1);
        }
        p.energy.use(EnergyPanel.totalCount);
//        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
    }//注：卡牌效果的diy区。

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }

}

package com.wsk.cards.attack;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.wsk.actions.ActionUtil;
import com.wsk.patches.AbstractCardEnum;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/24
 * @desc 绿宝石的狂欢
 */
public class AttackEmeraldCard extends CustomCard {
    public static final String ID = "LagranYue:AttackEmeraldCard";
    private static final String NAME ;

    private static final String DESCRIPTION;
    private static final CardStrings cardStrings;
    private static final String IMG = "cards/AttackEmeraldCard.png";


    private static final int COST = 3;

    public AttackEmeraldCard() {
        super(ID, NAME, CommonUtil.getResourcePath(IMG), COST, DESCRIPTION,
                CardType.ATTACK, AbstractCardEnum.LagranYue,
                CardRarity.RARE, CardTarget.SELF_AND_ENEMY);
        this.baseDamage = 27;
        this.exhaust = true;
        this.magicNumber = this.baseMagicNumber = 3;
    }

    public AbstractCard makeCopy() {
        return new AttackEmeraldCard();
    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();//升级名称。必带。
            this.upgradeDamage(8);
        }
    }//注：该部分为升级的效果部分，此处展示的代码为只能升级一次的代码，如需无限升级，卡牌代码有些许不同但不便于例出，请自行查看灼热攻击源码。

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {//局部变量：p-玩家，m敌人。
        ActionUtil.victoryPower(p, this.magicNumber);
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));

    }//注：卡牌效果的diy区。

    static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
//        UPGRADED_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    }

}

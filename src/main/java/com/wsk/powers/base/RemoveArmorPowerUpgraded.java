package com.wsk.powers.base;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.wsk.cards.AbstractArmsCard;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/26
 * @desc 一句话说明
 */
public class RemoveArmorPowerUpgraded extends AbstractPower {
    public static final String POWER_ID = "LagranYue:RemoveArmorPowerUpgraded";
    public static final String NAME = "卡路里的顺劈+";

    public static String[] DESCRIPTIONS = {"每打出1张 兵器 牌，对所有敌人造成", "点伤害。"};

    private static final String IMG = "powers/w27.png";
    private static PowerType POWER_TYPE = PowerType.BUFF;

    public RemoveArmorPowerUpgraded(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;

        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
//        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        updateDescription();//调用该方法（第36行）的文本更新函数,更新一次文本描叙，不可缺少。
        this.type = POWER_TYPE;
        updateDescription();
    }

    public void updateDescription() {
        this.description = (DESCRIPTIONS[0] + (this.amount * 6) + DESCRIPTIONS[1]);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card instanceof AbstractArmsCard) {
            this.flash();
            //对所有敌人造成固定伤害
            AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(null,
                    DamageInfo.createDamageMatrix(this.amount * 6, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
    }
}

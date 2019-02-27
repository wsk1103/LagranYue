package com.wsk.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/26
 * @desc 一句话说明
 */
public class DarkTemplePower extends AbstractPower {
    public static final String POWER_ID = "MyMod:DarkTemplePower";//能力的ID，判断有无能力、能力层数时填写该Id而不是类名。
    public static final String NAME = "自我封印·暗黑神殿";//能力的名称。

    public static final String[] DESCRIPTIONS = {"每回合开始给予所有敌人", "层 死亡印记。"};

    private static final String IMG = "powers/w24.png";
    private static PowerType POWER_TYPE = PowerType.BUFF;

    public DarkTemplePower(AbstractCreature owner, int amount) {//参数：owner-能力施加对象、amount-施加能力层数。在cards的use里面用ApplyPowerAction调用进行传递。
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        updateDescription();//调用该方法（第36行）的文本更新函数,更新一次文本描叙，不可缺少。
        this.type = POWER_TYPE;//能力种类，可以不填写，会默认为PowerType.BUFF。PowerType.BUFF不会被人工制品抵消，PowerType.DEBUFF会被人工制品抵消。
        updateDescription();
    }

    public void updateDescription() {
        this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            flash();
            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                if ((!m.isDead) && (!m.isDying)) {
                    AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, this.owner,
                            new ImprintPower(m, this.owner, this.amount), this.amount, true, AbstractGameAction.AttackEffect.NONE));
                }
            }
        }
    }
}

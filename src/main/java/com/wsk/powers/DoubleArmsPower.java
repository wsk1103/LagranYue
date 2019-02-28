package com.wsk.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.wsk.cards.AbstractArmsCard;
import com.wsk.utils.ChangeArmsUtil;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/25
 * @desc 双持能力
 */
public class DoubleArmsPower extends AbstractPower {
    public static final String POWER_ID = "LagranYue:DoubleArmsPower";//能力的ID，判断有无能力、能力层数时填写该Id而不是类名。
    public static final String NAME = "武器大师";//能力的名称。

    public static final String[] DESCRIPTIONS = {"装备 兵器 的数量上限变为", "， 疲惫 。已装备数量:"};//需要调用变量的文本描叙，例如力量（Strength）、敏捷（Dexterity）等。

    private static final String IMG = "powers/w28.png";
    //以上两种文本描叙只需写一个，更新文本方法在第36行。
    private static PowerType POWER_TYPE = PowerType.BUFF;

    public DoubleArmsPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        this.type = POWER_TYPE;//能力种类，可以不填写，会默认为PowerType.BUFF。PowerType.BUFF不会被人工制品抵消，PowerType.DEBUFF会被人工制品抵消。
        updateDescription();
    }

    public void updateDescription() {
        this.description = (DESCRIPTIONS[0] + (this.amount + 1) + DESCRIPTIONS[1] + ChangeArmsUtil.getArmsNum());
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card instanceof AbstractArmsCard) {
            this.flash();
        }
        updateDescription();
    }

    @Override
    public void onVictory() {
        ChangeArmsUtil.setArms();
    }
}
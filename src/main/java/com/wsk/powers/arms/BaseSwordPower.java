package com.wsk.powers.arms;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.wsk.actions.ActionUtil;
import com.wsk.utils.ArmsUtil;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/26
 * @desc 剑能力，攻击时，给予1层 虚弱
 */
public class BaseSwordPower extends AbstractSwordPower {
    public static final String POWER_ID = "LagranYue:BaseSwordPower";//能力的ID，判断有无能力、能力层数时填写该Id而不是类名。
    public static final String NAME = "兵器：断剑";//能力的名称。

    public static String[] DESCRIPTIONS = {"获得", "点力量。"};//需要调用变量的文本描叙，例如力量（Strength）、敏捷（Dexterity）等。

    private static final String IMG = "powers/w4.png";
    private static PowerType POWER_TYPE = PowerType.BUFF;

    public BaseSwordPower(AbstractCreature owner, int amount) {

        this.ID = POWER_ID;
        DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;

        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
//        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        this.type = POWER_TYPE;
//        hasArms();
        updateDescription();
    }

    @Override
    public void hasArms() {
        ActionUtil.strengthPower(owner, amount);
    }

    @Override
    public void updateDescription() {
        this.description = (basePower + DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        super.onAfterUseCard(card, action);
    }

    @Override
    public void onRemove() {
        if (!ArmsUtil.retain()) {
            ActionUtil.strengthPower(owner, -amount);
        }
    }
}

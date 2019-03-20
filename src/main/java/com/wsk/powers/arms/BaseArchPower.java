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
 * @desc 弩能力，攻击时，给予2层剧毒
 */
public class BaseArchPower extends AbstractArchPower {
    public static final String POWER_ID = "LagranYue:BaseArchPower";//能力的ID，判断有无能力、能力层数时填写该Id而不是类名。
    public static final String NAME = "兵器：残弩";//能力的名称。

    public static String[] DESCRIPTIONS/* = {"获得", "点力量。"}*/;//需要调用变量的文本描叙，例如力量（Strength）、敏捷（Dexterity）等。

    private static final String IMG = "powers/w9.png";
    private static PowerType POWER_TYPE = PowerType.BUFF;

    public BaseArchPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;

        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
        this.owner = owner;
        this.amount = amount;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        //能力种类，可以不填写，会默认为PowerType.BUFF。PowerType.BUFF不会被人工制品抵消，PowerType.DEBUFF会被人工制品抵消。
        this.type = POWER_TYPE;
//        hasArms();
        updateDescription();
    }

    @Override
    public void hasArms() {
//        ArmsUtil.addOrChangArms(owner, this, amount);
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
            ActionUtil.strengthPower(owner, -this.amount);
//            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
//                    new StrengthPower(AbstractDungeon.player, -this.amount), -this.amount));
        }
//        super.onRemove();
    }
}

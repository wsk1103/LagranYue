package com.wsk.powers.arms;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.wsk.actions.ActionUtil;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/25
 * @desc 枪，攻击时，给予一层易伤
 */
public class BaseSpearPower extends AbstractSpearPower {

    public static final String POWER_ID = "LagranYue:BaseSpearPower";
    public static final String NAME = "兵器：竹枪";

    public static String[] DESCRIPTIONS;

    private static final String IMG = "powers/w2.png";
    private static PowerType POWER_TYPE = PowerType.BUFF;

    public BaseSpearPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;

        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
//        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        this.type = POWER_TYPE;
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

}

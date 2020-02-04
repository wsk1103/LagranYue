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
    public static final String POWER_ID = "LagranYue:BaseArchPower";
    public static final String NAME = "兵器：残弩";

    public static String[] DESCRIPTIONS;

    private static final String IMG = "powers/w9.png";
    private static PowerType POWER_TYPE = PowerType.BUFF;

    public BaseArchPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;

        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
        this.owner = owner;
        this.amount = amount;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));

        this.type = POWER_TYPE;
//        hasArms();
        updateDescription();
        initDurability();
    }

    @Override
    public void hasArms() {
        ActionUtil.strengthPower(owner, getLevel());
    }

    @Override
    public void upgradeArms() {
        ActionUtil.strengthPower(owner, 1);
    }


    @Override
    public void updateDescription() {
        this.description = (basePower + DESCRIPTIONS[0] + this.getLevel() + DESCRIPTIONS[1]
                + DESCRIPTIONS[2] + this.getLevel());
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        super.onAfterUseCard(card, action);
    }

    @Override
    public void onRemove() {
        if (!ArmsUtil.retain()) {
            ActionUtil.strengthPower(owner, -this.getLevel());
//            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
//                    new StrengthPower(AbstractDungeon.player, -this.amount), -this.amount));
        }
//        super.onRemove();
    }
}

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
 * @date 2019/2/25
 * @desc 盾能力, 当玩家攻击时, 获得2点格挡
 */
public class BaseShieldPower extends AbstractShieldPower {
    public static final String POWER_ID = "LagranYue:BaseShieldPower";
    public static final String NAME = "兵器：破盾";

    public static String[] DESCRIPTIONS/* = {"获得", "点敏捷。"}*/;//需要调用变量的文本描叙，例如力量（Strength）、敏捷（Dexterity）等。

    private static final String IMG = "powers/w15.png";
    private static PowerType POWER_TYPE = PowerType.BUFF;

    public BaseShieldPower(AbstractCreature owner, int amount) {
        super();

        this.ID = POWER_ID;
        DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;

        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
        this.owner = owner;
        this.amount = amount;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        this.type = POWER_TYPE;
//        hasArms(owner);
        updateDescription();
    }

    @Override
    public void hasArms() {
        ActionUtil.dexterityPower(owner, amount);
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
            ActionUtil.dexterityPower(owner, -this.amount);
//            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
//                    new DexterityPower(AbstractDungeon.player, -this.amount), -this.amount, AbstractGameAction.AttackEffect.POISON));
        }
//        super.onRemove();
    }
}

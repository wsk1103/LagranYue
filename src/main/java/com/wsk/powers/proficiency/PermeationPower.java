package com.wsk.powers.proficiency;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.wsk.actions.ActionUtil;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/3/20
 * @description 透化
 */
public class PermeationPower extends AbstractPower {
    public static final String POWER_ID = "LagranYue:PermeationPower";

    public static String[] DESCRIPTIONS;

    private static final String IMG = "powers/8.png";
    private static PowerType POWER_TYPE = PowerType.DEBUFF;


    public PermeationPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;
        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
        this.owner = owner;
        this.amount = amount;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        this.type = POWER_TYPE;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = (DESCRIPTIONS[0] + this.amount * 5 + DESCRIPTIONS[1]);
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type == DamageInfo.DamageType.NORMAL) {
            ActionUtil.loseHP(owner, (int) (owner.maxHealth * amount * 0.05));
        }
        return damageAmount;
    }
}

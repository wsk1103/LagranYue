package com.wsk.powers.proficiency;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.wsk.actions.BottomDrawCardFeeZeroAction;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/3/20
 * @description 避矢之加护
 */
public class ProtectionArrowsPower extends AbstractPower {
    public static final String POWER_ID = "LagranYue:ProtectionArrowsPower";

    public static String[] DESCRIPTIONS;

    private static final String IMG = "powers/9.png";
    private static PowerType POWER_TYPE = PowerType.BUFF;


    public ProtectionArrowsPower(AbstractCreature owner, int amount) {
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
        this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        for (int i = 0; i < amount; i++) {
            if (info.type == DamageInfo.DamageType.NORMAL) {
                AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                    @Override
                    public void update() {
                        AbstractDungeon.actionManager.addToBottom(new BottomDrawCardFeeZeroAction(AbstractDungeon.getCurrRoom().monsters.getRandomMonster(null, true, AbstractDungeon.cardRandomRng), false));
                        this.isDone = true;
                    }
                });
            }
        }
        return damageAmount;
    }
}
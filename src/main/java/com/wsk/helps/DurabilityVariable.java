package com.wsk.helps;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.wsk.cards.AbstractArmsCard;

/**
 * @author wsk1103
 * @date 2019/7/27
 * @description 描述
 */
public class DurabilityVariable extends DynamicVariable {
    @Override
    public String key() {
        return "LagranYueDurability";
    }

    @Override
    public boolean isModified(AbstractCard card) {
        if (card instanceof AbstractArmsCard) {
            AbstractArmsCard asc = (AbstractArmsCard) card;
            return asc.isDurabilityModified;
        } else {
            return false;
        }
    }

    @Override
    public int value(AbstractCard card) {
        if (card instanceof AbstractArmsCard) {
            AbstractArmsCard asc = (AbstractArmsCard) card;
            return asc.durability;
        } else {
            return 0;
        }
    }

    @Override
    public int baseValue(AbstractCard card) {
        if (card instanceof AbstractArmsCard) {
            AbstractArmsCard asc = (AbstractArmsCard) card;
            return asc.durability;
        } else {
            return 0;
        }
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        if (card instanceof AbstractArmsCard) {
            AbstractArmsCard asc = (AbstractArmsCard) card;
            return asc.isDurabilityModified;
        } else {
            return false;
        }
    }
}

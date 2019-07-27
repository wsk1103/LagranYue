package com.wsk.helps;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.wsk.cards.proficiency.AbstractProficiencyCard;

/**
 * @author wsk1103
 * @date 2019/5/16
 * @description 描述
 */
public class SkillPointVariable extends DynamicVariable {
    @Override
    public String key() {
        return "SkillPoint";
    }

    @Override
    public boolean isModified(AbstractCard card) {
        if (card instanceof AbstractProficiencyCard) {
            AbstractProficiencyCard asc = (AbstractProficiencyCard) card;
            return asc.isProficiencyModified;
        } else {
            return false;
        }
    }

    @Override
    public int value(AbstractCard card) {
        if (card instanceof AbstractProficiencyCard) {
            AbstractProficiencyCard asc = (AbstractProficiencyCard) card;
            return asc.proficiency;
        } else {
            return 0;
        }
    }

    @Override
    public int baseValue(AbstractCard card) {
        if (card instanceof AbstractProficiencyCard) {
            AbstractProficiencyCard asc = (AbstractProficiencyCard) card;
            return asc.proficiency;
        } else {
            return 0;
        }
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        if (card instanceof AbstractProficiencyCard) {
            AbstractProficiencyCard asc = (AbstractProficiencyCard) card;
            return asc.isProficiencyModified;
        } else {
            return false;
        }
    }
}

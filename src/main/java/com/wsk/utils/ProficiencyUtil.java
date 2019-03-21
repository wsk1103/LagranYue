package com.wsk.utils;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.wsk.cards.proficiency.AbstractProficiencyCard;
import com.wsk.relics.ArmsProficiencyRelics;

/**
 * @author wsk1103
 * @date 2019/3/20
 * @description 熟练度工具类
 */
public class ProficiencyUtil {

    /**
     * 判断兵器熟练度是否到达条件。
     *
     * @param card
     * @return
     */
    public static boolean isProficiency(AbstractProficiencyCard card) {
        float proficiency = getProficiency(card);
        return card.proficiency <= proficiency;
    }

    /**
     * 获取兵器的熟练度.
     *
     * @param card
     * @return
     */
    public static float getProficiency(AbstractProficiencyCard card) {
        ArmsProficiencyRelics relics = (ArmsProficiencyRelics) AbstractDungeon.player.getRelic(ArmsProficiencyRelics.ID);
        return relics.getSkillPoint();
/*        switch (card.arms) {
            case Arch:
                return relics.getArch();
            case Shield:
                return relics.getShield();
            case Spear:
                return relics.getSpear();
            case Sword:
                return relics.getSword();
            default:
                break;
        }
        return 0;*/
    }

}

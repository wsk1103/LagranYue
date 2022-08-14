package com.wsk.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.wsk.cards.proficiency.AbstractProficiencyCard;
import com.wsk.utils.ArmsUtil;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/3/20
 * @description 技能点系统
 */
public final class ArmsProficiencyRelics extends CustomRelic {
    public static final String ID = "LagranYue:ArmsProficiencyRelics";
    public static final String IMG = "relics/r1.png";
    public static final String OUTLINE = "relics/r2.png";

    public static int point = 1;

    private final int init = 10;

    private int skillPoint = init;

    public ArmsProficiencyRelics() {
        super(ID, new Texture(CommonUtil.getResourcePath(IMG)), new Texture(CommonUtil.getResourcePath(OUTLINE)), RelicTier.STARTER, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + skillPoint;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new ArmsProficiencyRelics();
    }

    @Override
    public void atPreBattle() {
        use();
    }

    @Override
    public void atTurnStart() {
        flash();
//        addSkillPoint(point);
        use();
    }

    @Override
    public void onUseCard(AbstractCard c, UseCardAction action) {
        reduce(c);
        int add = 0;
        if (ArmsUtil.getArmsNum() > 0) {
            switch (c.rarity) {
                case BASIC:
                    add = 2;
                    break;
                case SPECIAL:
                    add = 2;
                    break;
                case COMMON:
                    add = 2;
                    break;
                case UNCOMMON:
                    add = 3;
                    break;
                case RARE:
                    add = 5;
                    break;
                case CURSE:
                    break;
                default:
                    break;
            }
        } else {
            add = point;
        }
        addSkillPoint(add);
        flash();
        use();
    }

    public void use() {
        this.description = getUpdatedDescription();
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        counter = skillPoint;
        this.initializeTips();
    }

    @Override
    public void onVictory() {
        setSkillPoint(init);
        use();
    }

    /**
     * 打出卡牌后，需要减少相应的技能点。
     *
     * @param c 卡牌
     */
    private void reduce(AbstractCard c) {
        if (c instanceof AbstractProficiencyCard) {
            addSkillPoint(-((AbstractProficiencyCard) c).proficiency);
        }
    }

    public int getSkillPoint() {
        return skillPoint;
    }

    public void setSkillPoint(int skillPoint) {
        this.skillPoint = skillPoint > 0 ? skillPoint : 0;
    }

    public void addSkillPoint(float f) {
        this.skillPoint += f;
        if (skillPoint < 0) {
            skillPoint = 0;
        }
    }
}

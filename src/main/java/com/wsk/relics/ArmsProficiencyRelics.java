package com.wsk.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.wsk.cards.proficiency.AbstractProficiencyCard;
import com.wsk.powers.arms.AbstractArmsPower;
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

/*    private float arch = 0F;
    private float shield = 0F;
    private float spear = 0F;
    private float sword = 0F;*/
    private float skillPoint = 0F;

    public ArmsProficiencyRelics() {
        super(ID, new Texture(CommonUtil.getResourcePath(IMG)), new Texture(CommonUtil.getResourcePath(OUTLINE)), RelicTier.STARTER, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() {
/*        String ar = String.format("%.1f", arch);
        String sh = String.format("%.1f", shield);
        String sp = String.format("%.1f", spear);
        String sw = String.format("%.1f", sword);
        return this.DESCRIPTIONS[0] + ar + DESCRIPTIONS[1]
                + sh + DESCRIPTIONS[2] + sp + DESCRIPTIONS[3] + sw;*/
        String skill = String.format("%.1f", skillPoint);
        return this.DESCRIPTIONS[0] + skill;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new ArmsProficiencyRelics();
    }

    @Override
    public void atTurnStart() {
        flash();
//        float da = Math.max(arch, Math.max(shield, Math.max(sword, spear)));
        int result = Math.round(skillPoint);
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(null,
                DamageInfo.createDamageMatrix(result, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));

    }

    @Override
    public void onUseCard(AbstractCard c, UseCardAction action) {
        reduce(c);
        for (AbstractPower power : AbstractDungeon.player.powers) {
            if (power instanceof AbstractArmsPower) {
                float add = 0;
                switch (c.rarity) {
                    case BASIC:
                        add = 0.2F;
                        break;
                    case SPECIAL:
                        add = 0.2F;
                        break;
                    case COMMON:
                        add = 0.2F;
                        break;
                    case UNCOMMON:
                        add = 0.4F;
                        break;
                    case RARE:
                        add = 1F;
                        break;
                    case CURSE:
                        break;
                    default:
                        break;
                }
                addSkillPoint(add);

/*                switch (((AbstractArmsPower) power).arms) {
                    case Arch:
                        addArch(add);
                        break;
                    case Shield:
                        addShield(add);
                        break;
                    case Spear:
                        addSpear(add);
                        break;
                    case Sword:
                        addSword(add);
                        break;
                    default:
                        break;
                }*/
                flash();
                use();
            }
        }
    }

    public void use() {
        this.description = getUpdatedDescription();
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.initializeTips();
    }

    @Override
    public void onVictory() {
/*        setArch(0);
        setShield(0);
        setSpear(0);
        setSword(0);*/
        setSkillPoint(0F);
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
/*            switch (((AbstractProficiencyCard) c).arms) {
                case Arch:
                    float ar = arch - ((AbstractProficiencyCard) c).proficiency;
                    if (ar <= 0) {
                        setArch(0);
                    } else {
                        addArch(-((AbstractProficiencyCard) c).proficiency);
                    }
                    break;
                case Spear:
                    float sp = spear - ((AbstractProficiencyCard) c).proficiency;
                    if (sp <= 0) {
                        setSpear(0);
                    } else {
                        addSpear(-((AbstractProficiencyCard) c).proficiency);
                    }
                    break;
                case Sword:
                    float sw = sword - ((AbstractProficiencyCard) c).proficiency;
                    if (sw <= 0) {
                        setSword(0);
                    } else {
                        addSword(-((AbstractProficiencyCard) c).proficiency);
                    }
                    break;
                case Shield:
                    float sh = spear - ((AbstractProficiencyCard) c).proficiency;
                    if (sh <= 0) {
                        setShield(0);
                    } else {
                        addShield(-((AbstractProficiencyCard) c).proficiency);
                    }
                    break;
                default:
                    break;
            }*/
        }
    }


/*    public void addArch(float arch) {
        this.arch += arch;
        if (this.arch < 0) {
            this.arch = 0;
        }
    }

    public void addShield(float a) {
        this.shield += a;
        if (this.shield < 0) {
            this.shield = 0;
        }
    }

    public void addSpear(float b) {
        this.spear += b;
        if (this.spear < 0) {
            this.spear = 0;
        }
    }

    public void addSword(float b) {
        this.sword += b;
        if (this.sword < 0) {
            this.sword = 0;
        }
    }

    public float getArch() {
        return arch;
    }

    public void setArch(float i) {
        this.arch = i > 0 ? i : 0;
    }

    public float getShield() {
        return shield;
    }

    public void setShield(float shield) {
        this.shield = shield > 0 ? shield : 0;
    }

    public float getSpear() {
        return spear;
    }

    public void setSpear(float spear) {
        this.spear = spear > 0 ? spear : 0;
    }

    public float getSword() {
        return sword;
    }

    public void setSword(float sword) {
        this.sword = sword > 0 ? sword : 0;
    }*/

    public float getSkillPoint() {
        return skillPoint;
    }

    public void setSkillPoint(float skillPoint) {
        this.skillPoint = skillPoint > 0 ? skillPoint : 0;
    }

    public void addSkillPoint(float f) {
        this.skillPoint += f;
        if (skillPoint < 0) {
            skillPoint = 0;
        }
    }
}

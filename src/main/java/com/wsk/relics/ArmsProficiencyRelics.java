package com.wsk.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
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
public class ArmsProficiencyRelics extends CustomRelic {
    public static final String ID = "LagranYue:ArmsProficiencyRelics";
    public static final String IMG = "relics/w35.png";
    public static final String OUTLINE = "relics/w36.png";

    private float arch = 0F;
    private float shield = 0F;
    private float spear = 0F;
    private float sword = 0F;

    public ArmsProficiencyRelics() {
        super(ID, new Texture(CommonUtil.getResourcePath(IMG)), new Texture(CommonUtil.getResourcePath(OUTLINE)), RelicTier.STARTER, LandingSound.CLINK);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + arch + "；\n" + DESCRIPTIONS[1] + shield + "；\n" + DESCRIPTIONS[2] + spear + "；\n" + DESCRIPTIONS[3] + sword;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new ArmsProficiencyRelics();
    }

    @Override
    public void onUseCard(AbstractCard c, UseCardAction action) {

        reduce(c);

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
        if (add != 0) {
            for (AbstractPower power : AbstractDungeon.player.powers) {
                if (power instanceof AbstractArmsPower) {
                    switch (((AbstractArmsPower) power).arms) {
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
                    }
                }
            }

        }
        flash();
        update();
    }


    /**
     * 触发时机：每一场战斗
     */
    @Override
    public void atPreBattle() {
        this.flash();
        setArch(0);
        setShield(0);
        setSpear(0);
        setSword(0);
    }

    /**
     * 打出卡牌后，需要减少相应的技能点。
     * @param c 卡牌
     */
    private void reduce(AbstractCard c) {
        if (c instanceof AbstractProficiencyCard) {
            switch (((AbstractProficiencyCard) c).arms) {
                case Arch:
                    setArch(-((AbstractProficiencyCard) c).proficiency);
                    break;
                case Spear:
                    setSpear(-((AbstractProficiencyCard) c).proficiency);
                    break;
                case Sword:
                    setSword(-((AbstractProficiencyCard) c).proficiency);
                    break;
                case Shield:
                    setShield(-((AbstractProficiencyCard) c).proficiency);
                    break;
                default:
                    break;
            }
        }
    }


    public void addArch(float arch) {
        this.arch += arch;
    }

    public void addShield(float a) {
        this.shield += a;
    }

    public void addSpear(float b) {
        this.spear += b;
    }

    public void addSword(float b) {
        this.sword += b;
    }

    public float getArch() {
        return arch;
    }

    public void setArch(float i) {
        this.arch = i;
    }

    public float getShield() {
        return shield;
    }

    public void setShield(float shield) {
        this.shield = shield;
    }

    public float getSpear() {
        return spear;
    }

    public void setSpear(float spear) {
        this.spear = spear;
    }

    public float getSword() {
        return sword;
    }

    public void setSword(float sword) {
        this.sword = sword;
    }
}

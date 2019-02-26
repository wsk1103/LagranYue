package com.wsk.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/25
 * @desc 双持能力
 */
public class DoubleArmsPower extends AbstractPower {
    public static final String POWER_ID = "MyMod:DoubleArmsPower";//能力的ID，判断有无能力、能力层数时填写该Id而不是类名。
    public static final String NAME = "双持";//能力的名称。

    public static final String[] DESCRIPTIONS = {"允许装备2件 兵器 "};//需要调用变量的文本描叙，例如力量（Strength）、敏捷（Dexterity）等。

    private static final String IMG = "powers/ritual.png";
    //以上两种文本描叙只需写一个，更新文本方法在第36行。
    private static PowerType POWER_TYPE = PowerType.BUFF;

    private static int arms = 0;

    public DoubleArmsPower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        updateDescription();//调用该方法（第36行）的文本更新函数,更新一次文本描叙，不可缺少。
        this.type = POWER_TYPE;//能力种类，可以不填写，会默认为PowerType.BUFF。PowerType.BUFF不会被人工制品抵消，PowerType.DEBUFF会被人工制品抵消。
    }

    public void updateDescription() {
        this.description = (DESCRIPTIONS[0]);
    }

    public static int getArms() {
        return arms;
    }

    public static void addArms() {
        arms = arms + 1;
    }

    public static void subArms() {
        arms = arms - 1;
    }
}
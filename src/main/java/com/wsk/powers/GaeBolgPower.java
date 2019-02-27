package com.wsk.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.wsk.utils.ChangeArmsUtil;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/25
 * @desc 兵器：刺穿死棘之枪
 */
public class GaeBolgPower extends BaseSpearPower {
    public static final String POWER_ID = "MyMod:GaeBolgPower";//能力的ID，判断有无能力、能力层数时填写该Id而不是类名。
    public static final String NAME = "兵器：刺穿死棘之枪";//能力的名称。

    //    public static final String DESCRIPITON = "攻击伤害增加印记的层数，当层数到达10层的时候，给予100点伤害";//不需要调用变量的文本描叙，例如钢笔尖（PenNibPower）。
    public static final String[] DESCRIPTIONS = {"获得", "点力量，攻击时，获得", "多层护甲 。"};//需要调用变量的文本描叙，例如力量（Strength）、敏捷（Dexterity）等。

    private static final String IMG = "powers/excessslime.png";
    //以上两种文本描叙只需写一个，更新文本方法在第36行。
    private static PowerType POWER_TYPE = PowerType.BUFF;
    private int platedArmor;

    public GaeBolgPower(AbstractCreature owner, int amount, int platedArmor) {
        super(owner, amount);//参数：owner-能力施加对象、amount-施加能力层数。在cards的use里面用ApplyPowerAction调用进行传递。
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.platedArmor = platedArmor;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        updateDescription();//调用该方法（第36行）的文本更新函数,更新一次文本描叙，不可缺少。
        this.type = POWER_TYPE;//能力种类，可以不填写，会默认为PowerType.BUFF。PowerType.BUFF不会被人工制品抵消，PowerType.DEBUFF会被人工制品抵消。
        updateDescription();
    }

    public void updateDescription() {
        this.description = (super.basePower + DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.platedArmor + DESCRIPTIONS[2]);
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        //获得多层护甲
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new PlatedArmorPower(AbstractDungeon.player, platedArmor), platedArmor));
        super.onAttack(info, damageAmount, target);
    }

    @Override
    public void onRemove() {
//        AbstractPower power = AbstractDungeon.player.getPower(PlatedArmorPower.POWER_ID);
        if (!ChangeArmsUtil.retain()) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                    new StrengthPower(AbstractDungeon.player, -this.amount), -this.amount));
        }
//        //所有多层护甲
//        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
//                new PlatedArmorPower(AbstractDungeon.player, -power.amount), -power.amount));
//        super.onRemove();
    }
}


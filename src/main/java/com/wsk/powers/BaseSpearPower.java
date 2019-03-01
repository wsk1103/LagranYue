package com.wsk.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.wsk.actions.ActionUtil;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/25
 * @desc 枪，攻击时，给予一层易伤
 */
public class BaseSpearPower extends AbstractArmsPower {

    public static final String POWER_ID = "LagranYue:BaseSpearPower";//能力的ID，判断有无能力、能力层数时填写该Id而不是类名。
    public static final String NAME = "兵器：竹枪";//能力的名称。

    public static final String[] DESCRIPTIONS = {"获得", "点力量。"};//需要调用变量的文本描叙，例如力量（Strength）、敏捷（Dexterity）等。
    String basePower = " 枪 。";
    private static final String IMG = "powers/w2.png";
    private static PowerType POWER_TYPE = PowerType.BUFF;

    public BaseSpearPower(){
    }

    public BaseSpearPower(AbstractCreature owner, int amount) {//参数：owner-能力施加对象、amount-施加能力层数。在cards的use里面用ApplyPowerAction调用进行传递。
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        this.type = POWER_TYPE;//能力种类，可以不填写，会默认为PowerType.BUFF。PowerType.BUFF不会被人工制品抵消，PowerType.DEBUFF会被人工制品抵消。
        hasArms();
        updateDescription();
    }

    private void hasArms(){
        ActionUtil.strengthPower(owner, amount);
    }

    public void updateDescription() {
        this.description = (basePower + DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if ((!card.purgeOnUse) && card.type == AbstractCard.CardType.ATTACK) {
            if (card.target == AbstractCard.CardTarget.ALL
                    || card.target == AbstractCard.CardTarget.ALL_ENEMY) {
                if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                    flash();
                    for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                        if ((!m.isDead) && (!m.isDying)) {
                            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, AbstractDungeon.player,
                                    new VulnerablePower(m, 1, false), 1,
                                    true, AbstractGameAction.AttackEffect.POISON));
                        }
                    }
                }
            } else {
                AbstractMonster m = null;
                if (action.target != null) {
                    m = (AbstractMonster) action.target;
                }
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, AbstractDungeon.player,
                        new VulnerablePower(m, 1, false), 1,
                        true, AbstractGameAction.AttackEffect.POISON));
            }
        }
//        if ((!card.purgeOnUse) && card.type == AbstractCard.CardType.ATTACK) {
//            AbstractMonster m = null;
//            if (action.target != null) {
//                m = (AbstractMonster) action.target;
//            }
//            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, AbstractDungeon.player,
//                    new VulnerablePower(m, 1, false), 1,
//                    true, AbstractGameAction.AttackEffect.POISON));
//        }
    }

    //    //触发时机：当玩家攻击时。
//    @Override
//    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
//        int vulnerable = 1;
//        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(target, AbstractDungeon.player,
//                new VulnerablePower(target, vulnerable, false), vulnerable,
//                true, AbstractGameAction.AttackEffect.POISON));
//    }
//    @Override
//    public void onRemove() {
//        if (!ChangeArmsUtil.retain()) {
//            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
//                    new StrengthPower(AbstractDungeon.player, -this.amount), -this.amount, AbstractGameAction.AttackEffect.POISON));
//        }
////        super.onRemove();
//    }

    public Object clone() {
        BaseSpearPower power;
        power = (BaseSpearPower) super.clone();
        return power;
    }
}

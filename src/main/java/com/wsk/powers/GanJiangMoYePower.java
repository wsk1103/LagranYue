package com.wsk.powers;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/26
 * @desc 兵器：干将莫邪
 */
public class GanJiangMoYePower extends BaseSwordPower {
    public static final String POWER_ID = "MyMod:GanJiangMoYePower";//能力的ID，判断有无能力、能力层数时填写该Id而不是类名。
    public static final String NAME = "兵器：干将莫邪";//能力的名称。

    public static final String[] DESCRIPTIONS = {"获得", "点力量。每回合你使用的目标为敌人的牌都会被重复打出", "次"};//需要调用变量的文本描叙，例如力量（Strength）、敏捷（Dexterity）等。

    private static final String IMG = "powers/BurningS.png";
    //以上两种文本描叙只需写一个，更新文本方法在第36行。
    private static PowerType POWER_TYPE = PowerType.BUFF;


    public GanJiangMoYePower(AbstractCreature owner, int amount) {
        super(owner,amount);//参数：owner-能力施加对象、amount-施加能力层数。在cards的use里面用ApplyPowerAction调用进行传递。
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        updateDescription();//调用该方法（第36行）的文本更新函数,更新一次文本描叙，不可缺少。
        this.type = POWER_TYPE;//能力种类，可以不填写，会默认为PowerType.BUFF。PowerType.BUFF不会被人工制品抵消，PowerType.DEBUFF会被人工制品抵消。
    }

    public void updateDescription() {
        this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2]);
    }

    //每回合你使用的目标为敌人的牌都会被重复打出 M 次
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if ((!card.purgeOnUse) && (this.amount > 0) && (card.target == AbstractCard.CardTarget.ENEMY || card.target == AbstractCard.CardTarget.ALL_ENEMY)) {
            for (int i = 0; i < this.amount; i++) {
                AbstractMonster m = null;
                if (action.target != null) {
                    m = (AbstractMonster) action.target;
                }
                AbstractCard tmp = card.makeSameInstanceOf();
                AbstractDungeon.player.limbo.addToBottom(tmp);
                tmp.current_x = card.current_x;
                tmp.current_y = card.current_y;
                tmp.target_x = (Settings.WIDTH / 2.0F - 300.0F * Settings.scale);
                tmp.target_y = (Settings.HEIGHT / 2.0F);
                tmp.freeToPlayOnce = true;

                if (m != null) {
                    tmp.calculateCardDamage(m);
                }
                tmp.purgeOnUse = true;
                AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(tmp, m, card.energyOnUse));
            }
        }
    }

    //造成伤害时，返回伤害数值
    public float atDamageFinalReceive(float damage, DamageInfo.DamageType damageType) {
        return super.atDamageFinalReceive(damage, damageType);
    }

    @Override
    public void onRemove() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                new StrengthPower(AbstractDungeon.player, -this.amount), -this.amount));
//        super.onRemove();
    }

}

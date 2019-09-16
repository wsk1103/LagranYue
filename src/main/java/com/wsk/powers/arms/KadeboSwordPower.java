package com.wsk.powers.arms;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.wsk.actions.ActionUtil;
import com.wsk.utils.ArmsUtil;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/26
 * @desc 一句话说明
 */
public class KadeboSwordPower extends AbstractSwordPower {
    public static final String POWER_ID = "LagranYue:KadeboSwordPower";
    public static final String NAME = "兵器：喋血卡波剑";

    public static String[] DESCRIPTIONS = {"获得", "点力量。攻击时，恢复该攻击卡的数值的", "倍生命值。"};

    private static final String IMG = "powers/w6.png";
    //以上两种文本描叙只需写一个，更新文本方法在第36行。
    private static PowerType POWER_TYPE = PowerType.BUFF;

    public KadeboSwordPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;

        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
//        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        updateDescription();//调用该方法（第36行）的文本更新函数,更新一次文本描叙，不可缺少。
        this.type = POWER_TYPE;//能力种类，可以不填写，会默认为PowerType.BUFF。PowerType.BUFF不会被人工制品抵消，PowerType.DEBUFF会被人工制品抵消。
//        hasArms();
        updateDescription();
    }

    @Override
    public void hasArms(){
//        ArmsUtil.addOrChangArms(owner, this, amount);
        ActionUtil.strengthPower(owner, amount);
    }

    public void updateDescription() {
        this.description = (super.basePower + DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + (this.amount + "/6") + DESCRIPTIONS[2]);
    }

    //触发时机：当玩家攻击时。info.可调用伤害信息。
    public void onAttack(final DamageInfo info, final int damageAmount, final AbstractCreature target) {//参数： info-伤害信息，damageAmount-伤害数值，target-伤害目标
        super.onAttack(info, damageAmount, target);
        if (info.type == DamageInfo.DamageType.NORMAL) {
            this.flash();
            //恢复生命值
            AbstractDungeon.actionManager.addToBottom(new HealAction(this.owner, this.owner, damageAmount * this.amount / 6));
        }
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        super.onAfterUseCard(card, action);
    }

    @Override
    public void onRemove() {
        if (!ArmsUtil.retain()) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                    new StrengthPower(AbstractDungeon.player, -this.amount), -this.amount));
        }
    }

}

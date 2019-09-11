package com.wsk.powers.arms;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.wsk.actions.ActionUtil;
import com.wsk.cards.AbstractArmsCard;
import com.wsk.utils.ArmsUtil;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/26
 * @desc 兵器：干将莫邪
 */
public class GanJiangMoYePower extends AbstractSwordPower {
    public static final String POWER_ID = "LagranYue:GanJiangMoYePower";
    public static final String NAME = "兵器：干将莫邪";//能力的名称。

    public static String[] DESCRIPTIONS = {"获得", "点力量。每回合你使用的第1张目标为敌人的攻击牌(非兵器牌)会被重复打出", "次。"};//需要调用变量的文本描叙，例如力量（Strength）、敏捷（Dexterity）等。

    private static final String IMG = "powers/w7.png";
    //以上两种文本描叙只需写一个，更新文本方法在第36行。
    private static PowerType POWER_TYPE = PowerType.BUFF;

    private static boolean action = true;

    public GanJiangMoYePower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;

        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
//        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        this.type = POWER_TYPE;
//        hasArms();
        updateDescription();
    }

    @Override
    public void hasArms() {
//        ArmsUtil.addOrChangArms(owner, this, amount);
        ActionUtil.strengthPower(owner, amount);
    }

    public void updateDescription() {
        this.description = (super.basePower + DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2]);
    }

    //每回合你使用的目标为敌人的牌都会被重复打出 M 次
    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (GanJiangMoYePower.action) {
            if ((!card.purgeOnUse) && (this.amount > 0)
                    && (card.target == AbstractCard.CardTarget.ENEMY || card.target == AbstractCard.CardTarget.ALL_ENEMY
                    || card.target == AbstractCard.CardTarget.SELF_AND_ENEMY)
                    && (card.type == AbstractCard.CardType.ATTACK && !(card instanceof AbstractArmsCard))) {
                this.flash();
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
                GanJiangMoYePower.action = false;
            }
        }
    }

    @Override
    public void atStartOfTurn() {
        GanJiangMoYePower.action = true;
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        super.onAfterUseCard(card, action);
    }

//    @Override
//    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
//        super.onAttack(info, damageAmount, target);
//    }

    @Override
    public void onRemove() {
        if (!ArmsUtil.retain()) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
                    new StrengthPower(AbstractDungeon.player, -this.amount), -this.amount));
        }
    }

    @Override
    public void onVictory() {
        GanJiangMoYePower.action = true;
    }
}

package com.wsk.powers.arms;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.wsk.actions.ActionUtil;
import com.wsk.utils.ArmsUtil;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/25
 * @desc 兵器：刺穿死棘之枪
 */
public class GaeBolgPower extends AbstractSpearPower {
    public static final String POWER_ID = "LagranYue:GaeBolgPower";
    public static final String NAME = "兵器：刺穿死棘之枪";

    //    public static final String DESCRIPITON = "攻击伤害增加印记的层数，当层数到达10层的时候，给予100点伤害";//不需要调用变量的文本描叙，例如钢笔尖（PenNibPower）。
    public static String[] DESCRIPTIONS = {"获得", "点力量，打出攻击卡牌后，获得", "层金属化 。"};

    private static final String IMG = "powers/w1.png";
    //以上两种文本描叙只需写一个，更新文本方法在第36行。
    private static PowerType POWER_TYPE = PowerType.BUFF;

    public GaeBolgPower(AbstractCreature owner, int amount) {
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
        initDurability();
    }

    @Override
    public void hasArms() {
//        ArmsUtil.addOrChangArms(owner, this, amount);
        ActionUtil.strengthPower(owner, getLevel());
    }

    @Override
    public void upgradeArms() {
        ActionUtil.strengthPower(owner, 1);
    }

    @Override
    public void updateDescription() {
        this.description = (super.basePower + DESCRIPTIONS[0] + this.getLevel()
                + DESCRIPTIONS[1] + this.getLevel() + DESCRIPTIONS[2]
                + DESCRIPTIONS[3] + this.getLevel());
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if ((!card.purgeOnUse) && card.type == AbstractCard.CardType.SKILL) {
            int imprintPower = getLevel() * 2;
            if (card.target == AbstractCard.CardTarget.ALL
                    || card.target == AbstractCard.CardTarget.ALL_ENEMY) {
                if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                    flash();
                    for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                        if ((!m.isDead) && (!m.isDying)) {
                            ActionUtil.imprintPower(AbstractDungeon.player, m, imprintPower);
                        }
                    }
                }
            } else {
                AbstractMonster m = null;
                if (action.target != null) {
                    m = (AbstractMonster) action.target;
                }
                ActionUtil.imprintPower(AbstractDungeon.player, m, imprintPower);
            }
        }
        super.onAfterUseCard(card, action);
/*        if ((!card.purgeOnUse) && card.type == AbstractCard.CardType.ATTACK) {
            //获得多层护甲
            ActionUtil.metallicizePower(owner, getLevel());
//            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
//                    new PlatedArmorPower(AbstractDungeon.player, amount), amount, AbstractGameAction.AttackEffect.POISON));
        }
        super.onAfterUseCard(card, action);
        */
    }

//    @Override
//    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
//        //获得多层护甲
//        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player,
//                new PlatedArmorPower(AbstractDungeon.player, platedArmor), platedArmor, AbstractGameAction.AttackEffect.POISON));
//        super.onAttack(info, damageAmount, target);
//    }

    @Override
    public void onRemove() {
        if (!ArmsUtil.retain()) {
            ActionUtil.strengthPower(AbstractDungeon.player, -this.getLevel());
            //移除金属化
            ActionUtil.reducePower(AbstractDungeon.player, MetallicizePower.POWER_ID, this.getLevel());
        }
    }
}


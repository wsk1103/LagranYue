package com.wsk.powers.arms;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.wsk.actions.ActionUtil;
import com.wsk.utils.ArmsUtil;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/25
 * @desc 一句话说明
 */
public class ExplosiveArmorPower extends AbstractShieldPower {
    public static final String POWER_ID = "LagranYue:ExplosiveArmorPower";
    public static final String NAME = "兵器：炸裂装甲";

    //    public static final String DESCRIPITON = "攻击伤害增加印记的层数，当层数到达10层的时候，给予100点伤害";//不需要调用变量的文本描叙，例如钢笔尖（PenNibPower）。
    public static String[] DESCRIPTIONS = {"增加", "点敏捷。增加", "点荆棘。"};

    private static final String IMG = "powers/w17.png";
    //以上两种文本描叙只需写一个，更新文本方法在第36行。
    private static PowerType POWER_TYPE = PowerType.BUFF;

    public ExplosiveArmorPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;

        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
//        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        updateDescription();//调用该方法（第36行）的文本更新函数,更新一次文本描叙，不可缺少。
        this.type = POWER_TYPE;
//        hasArms();
        updateDescription();
        initDurability();
    }

    @Override
    public void hasArms() {
//        ArmsUtil.addOrChangArms(owner, this, this.amount);
        ActionUtil.dexterityPower(owner, getLevel());
        ActionUtil.thornsPower(owner, getLevel() * 3);
    }

    @Override
    public void upgradeArms() {
        ActionUtil.dexterityPower(owner, 1);
        ActionUtil.thornsPower(owner, 1 * 3);
    }

    public void updateDescription() {
        this.description = (super.basePower + DESCRIPTIONS[0] + this.getLevel()
                + DESCRIPTIONS[1] + this.getLevel() * 3 + DESCRIPTIONS[2]
                + DESCRIPTIONS[3] + this.getLevel());
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        super.onAfterUseCard(card, action);
    }

    @Override
    public void onRemove() {
        if (!ArmsUtil.retain()) {
            //移除敏捷
            ActionUtil.dexterityPower(owner, -this.getLevel());
            //移除荆棘
            ActionUtil.reducePower(owner, ThornsPower.POWER_ID, this.getLevel() * 3);
        }
    }
}

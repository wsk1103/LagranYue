package com.wsk.powers.base;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.wsk.actions.ActionUtil;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/26
 * @desc 一句话说明
 */
public class WindKingEnchantmentPower extends AbstractPower {
    public static final String POWER_ID = "LagranYue:WindKingEnchantmentPower";
    public static final String NAME = "风王的结界";

    public static String[] DESCRIPTIONS = {"回合开始获得", "格挡 。"};

    private static final String IMG = "powers/w29.png";
    private static PowerType POWER_TYPE = PowerType.BUFF;

    public WindKingEnchantmentPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;

        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
//        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        updateDescription();//调用该方法（第36行）的文本更新函数,更新一次文本描叙，不可缺少。
        this.type = POWER_TYPE;
        updateDescription();
    }

    public void updateDescription() {
        this.description = (DESCRIPTIONS[0] + 8 + DESCRIPTIONS[1]);
    }

    @Override
    public void atStartOfTurn() {
        this.flash();
        ActionUtil.reducePower(owner, this.ID, 1);
    }
}

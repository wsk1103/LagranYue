package com.wsk.powers.base;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.wsk.actions.BloodyTemplePowerAction;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/26
 * @desc 一句话说明
 */
public class BloodyTemplePowerUpgraded extends AbstractPower {
    public static final String POWER_ID = "LagranYue:BloodyTemplePowerUpgraded";
    public static final String NAME = "他者封印·鲜血神殿";

    public static String[] DESCRIPTIONS = {"每回合开始获得", "张0费的 卢恩符文·攻+ 。"};

    private static final String IMG = "powers/w26.png";
    private static PowerType POWER_TYPE = PowerType.BUFF;

    public BloodyTemplePowerUpgraded(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;

        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
//        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        this.type = POWER_TYPE;
        updateDescription();
    }

    public void updateDescription() {
        this.description = (DESCRIPTIONS[0]);
    }

    @Override
    public void atStartOfTurn() {
        //恢复生命值
        this.flash();
        BloodyTemplePowerAction.action((AbstractPlayer) owner, amount, true);
//        AbstractDungeon.actionManager.addToBottom(new HealAction(this.owner, this.owner, this.amount * 2));
    }
}

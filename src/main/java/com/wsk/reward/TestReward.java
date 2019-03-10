package com.wsk.reward;

import basemod.abstracts.CustomReward;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.wsk.cards.arms.SkillBlazingSevenRingsCard;
import com.wsk.cards.arms.SkillBrokenShieldCard;
import com.wsk.cards.arms.SkillDeathBolgCard;
import com.wsk.helps.LogHelper;
import com.wsk.patches.TestRewardPatch;
import com.wsk.utils.CommonUtil;
import com.wsk.utils.Utilities;

import java.util.ArrayList;

/**
 * @author wsk1103
 * @date 2019/3/8
 * @description 描述
 */
public class TestReward extends CustomReward {

    private static final Texture ICON = new Texture(CommonUtil.getResourcePath("rewards/sapphire_key.png"));

    public int amount;

    private boolean skip = false;

    public TestReward(int amount) {
        super(ICON, "Heal " + amount + " hp.", TestRewardPatch.LagranYue);
        this.amount = amount;
        this.cards = new ArrayList<>();
        //在这里添加卡牌奖励.
        cards.add(new SkillBlazingSevenRingsCard());
        cards.add(new SkillBrokenShieldCard());
        cards.add(new SkillDeathBolgCard());
        CardGroup group = AbstractDungeon.commonCardPool;
        CardGroup group1 = AbstractDungeon.srcCommonCardPool;
        System.out.println("test");
    }

    @Override
    public void update() {
        super.update();
        //提示语
        if (this.hb.hovered) {
            TipHelper.renderGenericTip(360.0F * Settings.scale, InputHelper.mY, "WSK1103",
                    "body");
        }
    }

    @Override
    public boolean claimReward() {
//        AbstractDungeon.player.heal(this.amount);
        LogHelper.logger.info("TestReward.claimReward()");
        if (this.skip) {
            return true;
        }
        ArrayList<RewardItem> rewards = AbstractDungeon.combatRewardScreen.rewards;
        int i = 0;
        while (i < rewards.size()) {
            TestReward other = Utilities.SafeCast(rewards.get(i), TestReward.class);
            if ((other != null) && (other != this)) {
                other.isDone = true;
                other.skip = true;
            }
            i++;
        }
        if (AbstractDungeon.player.hasRelic("Question Card")) {
            AbstractDungeon.player.getRelic("Question Card").flash();
        }
        if (AbstractDungeon.player.hasRelic("Busted Crown")) {
            AbstractDungeon.player.getRelic("Busted Crown").flash();
        }
        if (AbstractDungeon.screen == AbstractDungeon.CurrentScreen.COMBAT_REWARD) {
            AbstractDungeon.cardRewardScreen.open(this.cards, this, TEXT[4]);
            AbstractDungeon.previousScreen = AbstractDungeon.CurrentScreen.COMBAT_REWARD;
        }
        this.isDone = false;

        return false;
//        return true;
    }
}

package com.wsk.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.wsk.cards.AbstractChooseCard;
import com.wsk.helps.ChooseHelper;

/**
 * @author wsk1103
 * @date 2019/3/5
 * @description 选择卡牌打出
 */
public class ChooseAction extends AbstractGameAction {
    private boolean retrieveCard = false;
    private AbstractChooseCard card;
    private CardGroup choices;

    public ChooseAction(AbstractChooseCard c, CardGroup choices) {
        AbstractPlayer p = AbstractDungeon.player;
        this.card = c;
        this.choices = choices;
        this.setValues(p, p, choices.size());
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            ChooseHelper.open(this.choices.group);
            this.tickDuration();
        } else {
            if (!this.retrieveCard) {
                if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
                    AbstractCard c = AbstractDungeon.cardRewardScreen.discoveryCard;
                    int num = this.choices.group.indexOf(c);
                    this.card.choose(num);
                    AbstractDungeon.cardRewardScreen.discoveryCard = null;
                }

                this.retrieveCard = true;
            }

            this.tickDuration();
        }
    }
}

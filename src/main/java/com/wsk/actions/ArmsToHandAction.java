package com.wsk.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.wsk.cards.AbstractArmsCard;

import java.util.Iterator;

/**
 * @author wsk1103
 * @date 2019/2/25
 * @desc 将武器卡牌加入到手中
 */
public class ArmsToHandAction extends AbstractGameAction {
    public static final String TEXT;
    private AbstractPlayer p;
    private boolean update;

    static {
        TEXT = "选择一张 兵器 牌添加到你的手牌中。";
    }

    public ArmsToHandAction(int amount, boolean update) {
        this.p = AbstractDungeon.player;
        this.setValues(this.p, AbstractDungeon.player, amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_MED;
        this.update = update;
    }

    public void update() {
        AbstractCard card;
        Iterator<AbstractCard> iterator;
        if (this.duration == Settings.ACTION_DUR_MED) {
            CardGroup cardGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            iterator = this.p.drawPile.group.iterator();
            while (iterator.hasNext()) {
                card = iterator.next();
                if (card instanceof AbstractArmsCard) {
                    cardGroup.addToRandomSpot(card);
                }
            }

            if (cardGroup.size() == 0) {
                this.isDone = true;
            } else if (cardGroup.size() == 1) {
                card = cardGroup.getTopCard();
                if (update && card.canUpgrade()) {
                    card.upgrade();
                }
                if (this.p.hand.size() == 10) {
                    this.p.drawPile.moveToDiscardPile(card);
                    this.p.createHandIsFullDialog();
                } else {
                    card.unhover();
                    card.lighten(true);
                    card.setAngle(0.0F);
                    card.drawScale = 0.12F;
                    card.targetDrawScale = 0.75F;
                    card.current_x = CardGroup.DRAW_PILE_X;
                    card.current_y = CardGroup.DRAW_PILE_Y;
                    this.p.drawPile.removeCard(card);
                    //如果手牌未满，设置保留属性
                    card.retain = true;
                    AbstractDungeon.player.hand.addToTop(card);
                    AbstractDungeon.player.hand.refreshHandLayout();
                    AbstractDungeon.player.hand.applyPowers();
                }

                this.isDone = true;
            } else {
                AbstractDungeon.gridSelectScreen.open(cardGroup, this.amount, TEXT, false);
                this.tickDuration();
            }
        } else {
            if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
                iterator = AbstractDungeon.gridSelectScreen.selectedCards.iterator();

                while (iterator.hasNext()) {
                    card = iterator.next();
                    if (update && card.canUpgrade()) {
                        card.upgrade();
                    }
                    card.unhover();
                    if (this.p.hand.size() == 10) {
                        this.p.drawPile.moveToDiscardPile(card);
                        this.p.createHandIsFullDialog();
                    } else {
                        this.p.drawPile.removeCard(card);
                        //如果手牌未满，设置保留属性
                        card.retain = true;
                        this.p.hand.addToTop(card);
                    }

                    this.p.hand.refreshHandLayout();
                    this.p.hand.applyPowers();
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                this.p.hand.refreshHandLayout();
            }

            this.tickDuration();
        }
    }
}
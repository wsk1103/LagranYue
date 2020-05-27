package com.wsk.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author wsk1103
 * @date 2019/2/25
 * @desc 将消耗区中的一张牌加到手中，如果是升级过的，将费用降为0
 */
public class UnCharnelAction extends AbstractGameAction {
    private final AbstractPlayer p;
    private final boolean upgrade;
    private final boolean costToZero;
    private static final UIStrings UI_STRINGS;
    public static final String[] TEXT;
    private final ArrayList<AbstractCard> exhumes = new ArrayList<>();

    public UnCharnelAction(boolean costToZero, boolean upgrade) {
        this.upgrade = upgrade;
        this.costToZero = costToZero;
        this.setValues(this.p = AbstractDungeon.player, AbstractDungeon.player, this.amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        AbstractCard abstractCard;
        Iterator<AbstractCard> iterator;
        if (this.duration != Settings.ACTION_DUR_FAST) {
            if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
                for (iterator = AbstractDungeon.gridSelectScreen.selectedCards.iterator(); iterator.hasNext(); abstractCard.unhover()) {
                    abstractCard = iterator.next();
                    this.p.hand.addToRandomSpot(abstractCard);
                    this.p.exhaustPile.removeCard(abstractCard);
                    if (this.costToZero) {
                        abstractCard.modifyCostForCombat(-abstractCard.cost);
                    }
                    if (this.upgrade && abstractCard.canUpgrade()) {
                        abstractCard.upgrade();
                    }
                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                this.p.hand.refreshHandLayout();
                this.p.exhaustPile.group.addAll(this.exhumes);
                this.exhumes.clear();

                for (iterator = this.p.exhaustPile.group.iterator(); iterator.hasNext(); abstractCard.target_y = 0.0F) {
                    abstractCard = iterator.next();
                    abstractCard.unhover();
                    abstractCard.target_x = (float) CardGroup.DISCARD_PILE_X;
                }
            }

            this.tickDuration();
        } else if (AbstractDungeon.player.hand.size() == 10) {
            AbstractDungeon.player.createHandIsFullDialog();
            this.isDone = true;
        } else if (this.p.exhaustPile.isEmpty()) {
            this.isDone = true;
        } else if (this.p.exhaustPile.size() == 1) {
            AbstractCard topCard = this.p.exhaustPile.getTopCard();
            topCard.unfadeOut();

            this.p.exhaustPile.removeCard(topCard);
            this.p.hand.addToRandomSpot(topCard);
            if (this.costToZero) {
                topCard.modifyCostForCombat(-topCard.cost);
            }
            if (this.upgrade && topCard.canUpgrade()) {
                topCard.upgrade();
            }
            (topCard).unhover();
            (topCard).fadingOut = false;
            this.isDone = true;
        } else {
            iterator = this.p.exhaustPile.group.iterator();

            while (iterator.hasNext()) {
                abstractCard = (AbstractCard) ((Iterator) iterator).next();
                abstractCard.stopGlowing();
                abstractCard.unhover();
                abstractCard.unfadeOut();
            }
            Iterator<AbstractCard> cardIterator = this.p.exhaustPile.group.iterator();
            while (true) {
                AbstractCard card;
                do {
                    if (!(cardIterator).hasNext()) {
                        if (this.p.exhaustPile.isEmpty()) {
                            this.p.exhaustPile.group.addAll(this.exhumes);
                            this.exhumes.clear();
                            this.isDone = true;
                            return;
                        }
                        AbstractDungeon.gridSelectScreen.open(this.p.exhaustPile, 1, TEXT[0], false);
                        this.tickDuration();
                        return;
                    }
                    card = (cardIterator).next();
                } while (!card.cardID.equals("Uncharnel") && !card.cardID.equals("Creater_Tree"));
                (cardIterator).remove();
                this.exhumes.add(card);
            }
        }
    }

    static {
        UI_STRINGS = CardCrawlGame.languagePack.getUIString("ExhumeAction");
        TEXT = UI_STRINGS.TEXT;
    }
}

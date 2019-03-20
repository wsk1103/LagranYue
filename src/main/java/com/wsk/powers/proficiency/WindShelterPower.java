package com.wsk.powers.proficiency;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.wsk.utils.CommonUtil;

import java.util.ArrayList;

/**
 * @author wsk1103
 * @date 2019/3/20
 * @description 避风的加护
 */
public class WindShelterPower extends AbstractPower {
    public static final String POWER_ID = "LagranYue:WindShelterPower";

    public static String[] DESCRIPTIONS;

    private static final String IMG = "powers/w26.png";
    private static PowerType POWER_TYPE = PowerType.BUFF;


    public WindShelterPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(this.ID).DESCRIPTIONS;
        this.name = CardCrawlGame.languagePack.getPowerStrings(this.ID).NAME;
        this.owner = owner;
        this.amount = amount;
        this.img = new Texture(CommonUtil.getResourcePath(IMG));
        this.type = POWER_TYPE;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        ArrayList<AbstractCard> cards = AbstractDungeon.player.drawPile.group;
        if (cards != null && cards.size() != 0) {
            ArrayList<AbstractMonster> monsters = AbstractDungeon.getCurrRoom().monsters.monsters;
            for (AbstractMonster monster : monsters) {
                if (monster.isDead) {
                    continue;
                }
                cards.get(0).use((AbstractPlayer) owner, monster);
                break;
            }
        }
        return damageAmount;
    }
}
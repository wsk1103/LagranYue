package com.wsk.utils;

import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static basemod.DevConsole.logger;

/**
 * @author wsk1103
 * @date 2019/2/24
 * @desc 解析卡牌属性
 */
public class AnalyticCardAttribute {

//    private static final List<CardAttribute> cardAttributes = getCardAttributes();

//    private static final Map<String, CardAttribute> mapCardAttribute;

    private static final Gson gson = new Gson();

    public static AbstractCard.CardTarget getCardTarget(String CardTarget) {
        switch (CardTarget) {
            case "SELF":
                return AbstractCard.CardTarget.SELF;
            case "SELF_AND_ENEMY":
                return AbstractCard.CardTarget.SELF_AND_ENEMY;
            case "ALL_ENEMY":
                return AbstractCard.CardTarget.ALL_ENEMY;
            case "ENEMY":
                return AbstractCard.CardTarget.ENEMY;
            default:
                throw new RuntimeException("unknown CardTarget!");
        }
    }

    public static AbstractCard.CardType getCardType(String CardType) {
        switch (CardType) {
            case "SKILL":
                return AbstractCard.CardType.SKILL;
            case "ATTACK":
                return AbstractCard.CardType.ATTACK;
            case "POWER":
                return AbstractCard.CardType.POWER;
            case "CURSE":
                return AbstractCard.CardType.CURSE;
            case "STATUS":
                return AbstractCard.CardType.STATUS;
            default:
                throw new RuntimeException("unknown CardType!");
        }
    }

    public static AbstractCard.CardRarity getCardRarity(String CardRarity) {
        switch (CardRarity) {
            case "BASIC":
                return AbstractCard.CardRarity.BASIC;
            case "COMMON":
                return AbstractCard.CardRarity.COMMON;
            case "RARE":
                return AbstractCard.CardRarity.RARE;
            case "UNCOMMON":
                return AbstractCard.CardRarity.UNCOMMON;
            case "SPECIAL":
                return AbstractCard.CardRarity.SPECIAL;
            default:
                throw new RuntimeException("unknown CardRarity!");
        }
    }

//    public static CardAttribute getCardAttribute(String name) {
//        return mapCardAttribute.get(name);
//    }

    private static List<CardAttribute> getCardAttributes() {
        logger.debug("开始解析json");
        List<CardAttribute> cardAttributes;
        String cardStrings = Gdx.files.internal("localization/" + CommonUtil.getLanguage() + "/LagranYue-Cards.json")
                .readString(String.valueOf(StandardCharsets.UTF_8));
        logger.debug("json--->\n" + cardStrings);
        cardAttributes = gson.fromJson(cardStrings, new TypeToken<List<CardAttribute>>() {
        }.getType());
        return cardAttributes;
    }

    private static Map<String, CardAttribute> getMapCardAttribute() {
        List<CardAttribute> cardAttributes = getCardAttributes();
        Map<String, CardAttribute> map = new HashMap<>(cardAttributes.size());
        for (CardAttribute cardAttribute : cardAttributes) {
            map.put(cardAttribute.NAME, cardAttribute);
        }
        return map;
    }

//    static {
//        mapCardAttribute = getMapCardAttribute();
//    }

}

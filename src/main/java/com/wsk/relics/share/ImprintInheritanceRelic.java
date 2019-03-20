package com.wsk.relics.share;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.wsk.utils.CommonUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author wsk1103
 * @date 2019/3/20
 * @description 描述
 */
public class ImprintInheritanceRelic extends CustomRelic {

    public static final String ID = "LagranYue:ImprintInheritanceRelic";
    public static final String IMG = "relics/w35.png";
    public static final String OUTLINE = "relics/w36.png";
    public static final String FILEPATH = "saves/ImprintInheritanceRelic.txt";

    public ImprintInheritanceRelic() {
        super(ID, new Texture(CommonUtil.getResourcePath(IMG)), new Texture(CommonUtil.getResourcePath(OUTLINE)), RelicTier.UNCOMMON, LandingSound.FLAT);
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new ImprintInheritanceRelic();
    }

    @Override
    public void onEquip() {

        ArrayList<AbstractRelic> relics = AbstractDungeon.player.relics;

        if (relics == null || relics.size() == 0) {
            return;
        }
        String randomRelic = null;
        for (int i = relics.size() - 1; i >= 0; i--) {
            AbstractRelic relic = relics.get(i);
            if (relic.tier != RelicTier.STARTER) {
                randomRelic = relic.getClass().getName();
                break;
            }
        }
        if (null == randomRelic) {
            return;
        }

        File saveToNext = new File(FILEPATH);
        try {
            if (!saveToNext.exists()) {
                saveToNext.createNewFile();
            }
            try (FileWriter fileWriter = new FileWriter(saveToNext.getName())) {
                fileWriter.write(randomRelic);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("ImprintInheritanceRelic save success!");
    }

    @Override
    public void onUnequip() {
        File saveToNext = new File("saves/ImprintInheritanceRelic.txt");
        if (saveToNext.exists()) {
            saveToNext.delete();
            System.out.println("ImprintInheritanceRelic save success!");
        } else {
            System.out.println("ImprintInheritanceRelic file not!");
        }
    }
}
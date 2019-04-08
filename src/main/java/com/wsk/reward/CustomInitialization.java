package com.wsk.reward;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.neow.NeowEvent;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.wsk.LagranYue;
import com.wsk.relics.share.RandomDrawCardRelic;

import java.util.ArrayList;

/**
 * @author wsk1103
 * @date 2019/3/17
 * @description 自定义neow事件
 */

@SpirePatch(
        clz = NeowEvent.class,
        method = "<ctor>",
        paramtypez = {boolean.class}
)
public class CustomInitialization {

    public static void Postfix(NeowEvent a, boolean b) {
        AbstractPlayer player = AbstractDungeon.player;
        if (null == player) {
            return;
        }
        final ArrayList<AbstractRelic> all = new ArrayList<>();
        if (AbstractDungeon.floorNum < 1) {
            if (LagranYue.contentSharing_relics) {
                boolean hasRandom = false;
                for (AbstractRelic relic : player.relics) {
                    if (relic.relicId.equals(RandomDrawCardRelic.ID)) {
                        hasRandom = true;
                        break;
                    }
                }
                if (!hasRandom) {
                    all.add(new RandomDrawCardRelic());
                }
            }
/*            File saveToNext = new File(ImprintInheritanceRelic.FILEPATH);
            String name = null;
            if (saveToNext.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(saveToNext))) {
                    String tempString = reader.readLine();
                    name = tempString;
                    System.out.println("relic " + ": " + tempString);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (name != null && name.length() > 0) {
                    try {
                        Class c = Class.forName(name);
                        AbstractRelic relic = (AbstractRelic) c.newInstance();
                        all.add(relic);
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                //删除遗物
                saveToNext.delete();
            }*/
        }

        for (AbstractRelic relic : all) {
            int slot = player.relics.size();
            relic.makeCopy().instantObtain(player, slot, true);
        }
    }
}

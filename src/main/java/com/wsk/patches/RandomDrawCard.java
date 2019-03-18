package com.wsk.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.neow.NeowEvent;

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
public class RandomDrawCard {

    public static void Postfix(NeowEvent a, boolean b) {
/*        AbstractPlayer player = AbstractDungeon.player;
        if (AbstractDungeon.floorNum <= 1) {
            System.out.println("aa");
            if (null != player) {
                int slot = player.relics.size();
                AbstractRelic r = new DarkRelics();
                r.makeCopy().instantObtain(player, slot, true);
//                player.relics.add(new DarkRelics());
//                player.relic
                System.out.println(player.relics);
            }
        }*/
    }
}

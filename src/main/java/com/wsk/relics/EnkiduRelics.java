package com.wsk.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.wsk.cards.AbstractArmsCard;
import com.wsk.utils.CommonUtil;

/**
 * @author wsk1103
 * @date 2019/2/27
 * @desc 天之锁, 切换兵器的时候，保留第一次的增益效果.
 */
public class EnkiduRelics extends CustomRelic {
    public static final String ID = "LagranYue:EnkiduRelics";//遗物Id，添加遗物、替换遗物时填写该id而不是遗物类名。
    public static final String IMG = "relics/w31.png";//遗物图片路径
    public static final String OUTLINE = "relics/w32.png";//遗物外轮廓路径

    //记录第一次保存第一次切换武器，当被使用过后，会被置为 false
    private static boolean once = true;

    public static final String DESCRIPTION = "每次战斗仅一次，切换 #y兵器 的时候，保留之前一把武器获得的力量/敏捷加成效果。";//遗物效果的文本描叙。

    public EnkiduRelics() {
        super(ID, new Texture(CommonUtil.getResourcePath(IMG)), new Texture(CommonUtil.getResourcePath(OUTLINE)), RelicTier.STARTER, LandingSound.FLAT);
        //参数：ID-遗物Id，new Texture(Gdx.files.internal(IMG))-遗物图片，new Texture(Gdx.files.internal(OUTLINE))-遗物轮廓，RelicTier.BOSS-遗物种类，LandingSound.FLAT-遗物音效。
    }
    //遗物种类：RelicTier.BOSS-boss遗物, RelicTier.COMMON-一般遗物, RelicTier.RARE-罕见遗物, RelicTier.SHOP-商店遗物, RelicTier.SPECIAL-事件遗物, RelicTier.STARTER-初始遗物, RelicTier.UNCOMMON-稀有遗物。
    //遗物音效：LandingSound.CLINK,LandingSound.FLAT,LandingSound.HEAVY,LandingSound.MAGICAL,LandingSound.SOLID  具体音效请到游戏内听。

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new EnkiduRelics();
    }//复制该遗物信息的方法。

    public void onPlayCard(final AbstractCard c, final AbstractMonster m) {//参数：c-使用的卡牌，m-目标敌人。
        if (c instanceof AbstractArmsCard) {
            if (once) {
                //如果该遗物还有效果，闪烁一下.
                this.flash();
            }
        }
    }//触发时机：当一张卡被打出且卡牌效果生效前。

    public void onEnterRoom(final AbstractRoom room) {//参数：room-进入的房间。
        //重置
        EnkiduRelics.once = true;
    }//触发时机：当玩家进入房间时。(参考永恒羽毛)

    public static boolean getOnce(){
        return once;
    }

    public static void setOnce() {
        once = false;
    }
}

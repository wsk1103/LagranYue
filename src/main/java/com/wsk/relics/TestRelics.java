package com.wsk.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

/**
 * @author wsk1103
 * @date 2019/2/4
 * @desc 创建遗物
 */
public class TestRelics extends CustomRelic{

    public static final String ID = "ModBaseRelics";//遗物Id，添加遗物、替换遗物时填写该id而不是遗物类名。
    public static final String IMG = "遗物图片路径";//遗物图片路径
    public static final String OUTLINE = "遗物外轮廓路径";//遗物外轮廓路径

    public static final String DESCRIPTION = "文本描叙";//遗物效果的文本描叙。
    //特殊格式： 1.文本描叙中#r、#y、#b、#g分别能使文本变成红、黄、蓝、绿色。
    //         使用方法：将需要变色的部分无空格放在#r(#y/#b/#g)后面，然后将这一块前后用空格与其他文本隔开。
    //         例：public static final DESCRIPTION = "回合开始时获得 #b3 点力量.";
    //       2.文本描叙中[R]、[G]、[B]分别对应战士、猎手、机器人的能量。
    //         使用方法同上，不再赘叙。

    public TestRelics() {
        super(ID, new Texture(Gdx.files.internal(IMG)), new Texture(Gdx.files.internal(OUTLINE)), RelicTier.BOSS, LandingSound.FLAT);
        //参数：ID-遗物Id，new Texture(Gdx.files.internal(IMG))-遗物图片，new Texture(Gdx.files.internal(OUTLINE))-遗物轮廓，RelicTier.BOSS-遗物种类，LandingSound.FLAT-遗物音效。
    }
    //遗物种类：RelicTier.BOSS-boss遗物, RelicTier.COMMON-一般遗物, RelicTier.RARE-稀有遗物, RelicTier.SHOP-商店遗物, RelicTier.SPECIAL-事件遗物, RelicTier.STARTER-初始遗物, RelicTier.UNCOMMON-罕见遗物。
    //遗物音效：LandingSound.CLINK,LandingSound.FLAT,LandingSound.HEAVY,LandingSound.MAGICAL,LandingSound.SOLID  具体音效请到游戏内听。
    //初始，普通，罕见，稀有，BOSS
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0];
    }

    public AbstractRelic makeCopy() {
        return new TestRelics();
    }//复制该遗物信息的方法。

    //触发时机，以下部分触发时机仅翻译英文，具体效果未知，如有错误，请见谅。如仍无法满足DIY需求，请详参desktop的各遗物源码或AbstractRelics类的源码。
    //小tips：在以下触发时机里，需要的闪烁的，可调用flash();让遗物闪一下.
    //       在以下触发时机里，需要遗物计数的可调用this.counter进行运算，用来计数。也可在上方的ModBaseRelics() super以后调用获得初始计数。

    public void onPlayCard(final AbstractCard c, final AbstractMonster m) {//参数：c-使用的卡牌，m-目标敌人。
    }//触发时机：当一张卡被打出且卡牌效果生效前。
    //c.可调用卡牌信息,比如稀有度、费用。

    public void onUseCard(final AbstractCard targetCard, final UseCardAction useCardAction) {
    }//触发时机：当一张卡被打出且卡牌效果生效后。(参考死灵之书)
    //targetCard.可调用卡牌信息,比如稀有度、费用。

    public void onExhaust(final AbstractCard card) {
    }//触发时机：当你消耗一张 卡牌时。(参考卡戍之灰)
    //card.可调用卡牌信息,比如稀有度、费用。

    public void onCardDraw(final AbstractCard drawnCard) {
    }//触发时机：当你抽一张牌时。
    //drawnCard.可调用卡牌信息,比如稀有度、费用。

    //卡牌信息:c.cardID-卡牌Id,c.cost-卡牌费用,c.costForTurn-卡牌一回合内消耗的费用(如旋风斩的费用),c.color-卡牌颜色,c.damage-含力量、钢笔尖等加成的伤害,c.block-含敏捷等加成的格挡,c.baseDamage-不含力量、钢笔尖等加成的伤害,c.baseBlock-不含敏捷等加成的格挡
    //       c.magicNumber-卡牌特殊值,c.type-卡牌种类,c.rarity-卡牌稀有度.

    public void onGainGold() {
    }//触发时机：当玩家获得金币时。(参考金神像)

    public void onLoseGold() {
    }//触发时机：当玩家失去金币时。(参考鲜血神像)

    public void onEquip() {
    }//触发时机：当玩家获得该遗物时。(参考灵体外质、诅咒钥匙、天鹅绒项圈等)

    public void onUnequip() {
    }//触发时机：当玩家失去该遗物时。(参考灵体外质、诅咒钥匙、天鹅绒项圈等)

    public void atPreBattle() {
    }//触发时机：每一场战斗（具体作用时机未知）

    public void atBattleStart() {
    }//触发时机：当玩家战斗开始时，在第一轮抽牌之后。(参考金刚杵、缩放仪)

    public void atBattleStartPreDraw() {
    }//触发时机：当玩家战斗开始时，在第一轮抽牌的时候，每抽一次牌触发一次。（猜测效果，具体触发时机请实测）

    public void atTurnStart() {
    }//触发时机：在玩家回合开始时。

    public void onPlayerEndTurn() {
    }//触发时机：在玩家回合结束时。

    public void onManualDiscard() {
    }//触发时机：当你手动弃牌时。

    public void onVictory() {
    }//触发时机：当玩家战斗胜利时。(参考精致折扇)

    public void onMonsterDeath(final AbstractMonster m) {//参数：m-死亡的敌人。
    }//触发时机：当一名敌人死亡时。
    //m.可调用敌人的信息。

    public int onPlayerGainBlock(final int blockAmount) {
        return blockAmount;
    }//触发时机：当玩家获得格挡时，返回格挡值，可用来修改获得的格挡值。

    public int onPlayerHeal(final int healAmount) {
        return healAmount;
    }//触发时机：当玩家回复生命值时，返回生命值回复数量，可用来修改生命值回复数量。

    public void onEnterRestRoom() {
    }//触发时机：当玩家进入篝火时。

    public void onRest() {
    }//触发时机：当玩家在篝火内休息时。

    public void onAttack(final DamageInfo info, final int damageAmount, final AbstractCreature target) {//参数： info-伤害信息，damageAmount-伤害数值，target-伤害目标
    }//触发时机：当玩家攻击时。info.可调用伤害信息。

    public int onAttacked(final DamageInfo info, final int damageAmount) {//参数：info-伤害信息，damageAmount-伤害数值
        return damageAmount;//该damaeAmount为未被格挡的伤害（参考鸟居）。
    }//触发时机：当玩家被攻击时，返回伤害数值，可用来修改伤害数值。info.可调用伤害信息。

    //伤害信息：info.owner (该次伤害的攻击者) info.type(该次伤害的种类，可利用info.type.调用伤害种类)
    //伤害种类:DamageInfo.DamageType.HP_LOSS (失去生命，无法被格挡，无法触发原版的【受到伤害时】的条件)  DamageInfo.DamageType.NORMAL (一般伤害，可以被格挡，能触发原版的【受到伤害时】的条件)  DamageInfo.DamageType.THORNS (荆棘伤害，可以被格挡，无法触发原版的【受到伤害时】的条件)

    public void onEnterRoom(final AbstractRoom room) {//参数：room-进入的房间。
    }//触发时机：当玩家进入房间时。(参考永恒羽毛)
    //room.可调用房间信息。

    public void onChestOpen(final boolean bossChest) {//参数：bossChest-是否为boss宝箱，true是boss宝箱，false不是boss宝箱。
    }//触发时机：当玩家打开一个箱子时。(详参遗物黑星、套娃、诅咒钥匙)

    public void onDrawOrDiscard() {
    }//触发时机：当玩家抽卡或者弃卡时。

}

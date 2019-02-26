package com.wsk.characters;

import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.wsk.cards.attack.AttackEmeraldCard;
import com.wsk.cards.attack.AttackPurpleCard;
import com.wsk.cards.attack.AttackSummerCard;
import com.wsk.cards.attack.BaseAttackCard;
import com.wsk.cards.power.PowerDoubleArmsCard;
import com.wsk.cards.skill.*;
import com.wsk.patches.AbstractCardEnum;
import com.wsk.patches.CharacterEnum;
import com.wsk.utils.CommonUtil;

import java.util.ArrayList;

/**
 * @author wsk1103
 * @date 2019/2/14
 * @desc 一句话说明
 */
public class MyModCharacter extends CustomPlayer {
    private static final int ENERGY_PER_TURN = 10;//角色的初始能量值。
    private static final String[] orbTextures = {"mymod/char/orb/layer1.png",
            "mymod/char/orb/layer2.png",
            "mymod/char/orb/layer3.png",
            "mymod/char/orb/layer4.png",
            "mymod/char/orb/layer5.png",
            "mymod/char/orb/layer6.png",
            "mymod/char/orb/layer1d.png",
            "mymod/char/orb/layer2d.png",
            "mymod/char/orb/layer3d.png",
            "mymod/char/orb/layer4d.png",
            "mymod/char/orb/layer5d.png"};
    //战斗界面左下角的能量图标组，详参压缩包内img文件夹内容。
    //也是战斗界面左下角的能量图标的一部分，详参压缩包内img文件夹内容。
    private static final String orbVfx = "mymod/char/orb/vfx.png";

    private static Color cardRenderColor = new Color(0.0F, 0.1F, 0.0F, 1.0F);

    public MyModCharacter(String name, AbstractPlayer.PlayerClass setClass) {
        super(name, setClass, orbTextures, orbVfx,
                CommonUtil.getResourcePath("MyMod.g3dj")/* 人物角色立绘动画文件路径*/,
                "MyMod_Render|idle"/*人物角色立绘图片名*/);
        //人物角色立绘动画文件路径  格式："img/char/CharacterName/CharacterName_md.g3dj"。g3dj文件决定人物动画。
        //人物角色立绘图片名  格式："CharacterName_md|idle"。该文件为png格式，和CharacterName_md.g3dj放在同一目录下。
        //g3dj文件见压缩包内img文件夹内内容。

//        this.dialogX = this.drawX + 0.0f * Settings.scale;
//        this.dialogY = this.drawY + 240.0f * Settings.scale;
        //决定初始的XY坐标，游戏内“能量不足”等文本提示位置基于该坐标。

//        this.initializeClass("人物角色立绘图片路径", "篝火内休息前图片路径", "篝火内休息后图片路径", "死亡结算界面图片路径", getLoadout(), 20.0F, -10.0F, 220.0F, 290.0F, new EnergyManager(ENERGY_PER_TURN));
        this.initializeClass((String) null,
                "mymod/char/shoulder2.png",
                "mymod/char/shoulder.png",
                "mymod/char/corpse.png",
                this.getLoadout(),
                0.0F, 0.0F, 300.0F, 180.0F,
                new EnergyManager(ENERGY_PER_TURN));
//        this.reloadAnimation();
        this.dialogY += -100 * Settings.scale;
//        initializeSlotPositions();
    }

    //添加初始牌组
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
//        retVal.add(AttackBetterCard.ID);
        retVal.add(AttackEmeraldCard.ID);
        retVal.add(AttackPurpleCard.ID);
        retVal.add(SkillGaeBolgCard.ID);
//        retVal.add(AttackSapphireCard.ID);
//        retVal.add(AttackRubyCard.ID);
//        retVal.add(BaseAttackCard.ID);
//        retVal.add(SkillDeathBolgCard.ID);
        retVal.add(AttackSummerCard.ID);
        retVal.add(SkillExplosiveArmorCard.ID);

//        retVal.add(SkillVictorySwordCard.ID);
        retVal.add(SkillGanJiangMoYeSwordCard.ID);
        retVal.add(SkillKadeboSwordCard.ID);
        retVal.add(PowerDoubleArmsCard.ID);
        retVal.add(SkillBlazingSevenRingsCard.ID);
        retVal.add(SkillChiharaHoundCard.ID);
//        retVal.add(AttackFireCard.ID);
//        retVal.add(AttackIceCard.ID);
//        retVal.add(BaseDefendCard.ID);
//        retVal.add(DefendCard2.ID);
//        retVal.add(PolyBeamCard.ID);
//        retVal.add(RoseCard.ID);
//        retVal.add(BasePowerCard.ID);
        return retVal;
    }

    //添加初始遗物
    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        //先用史莱姆的遗物ID
//        retVal.add(AbsorbEndCombat.ID);
//        UnlockTracker.markRelicAsSeen(AbsorbEndCombat.ID);
        return retVal;
    }

    //在角色选择界面的一些描叙内容。
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo("爱的供养", "爱的供养-----MyMod",
                580, 580, 0, 9999, 10,
                //参数含义：80，80 - 初始拥有血量和初始最大血量   0-球球数  99-初始金币数量  5-每回合抽牌数量
                this, getStartingRelics(), getStartingDeck(), false);
        //null位置对应参数：CharacterEnum.CharacterName
        //需要新建一个CharacterEnum类import后使用。代码如下：
        //@SpireEnum
        //public static AbstractPlayer.PlayerClass CharacterName; CharacterName为你的角色对应的英文名称
    }

    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return "爱的供养";
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return AbstractCardEnum.MyModCard;
    }

    @Override
    public Color getCardRenderColor() {
        return cardRenderColor;
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new BaseAttackCard();
    }

    @Override
    public Color getCardTrailColor() {
        return cardRenderColor.cpy();
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 10;
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontGreen;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("my-mod", MathUtils.random(-0.2F, 0.2F));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, true);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "my-mod";
    }

    @Override
    public String getLocalizedCharacterName() {
        return "爱的供养";
    }

    @Override
    public AbstractPlayer newInstance() {
        return new MyModCharacter("爱的供养", CharacterEnum.MyModCharacter);
    }

    @Override
    public String getSpireHeartText() {
        return "getSpireHeartText";
    }

    @Override
    public Color getSlashAttackColor() {
        return Color.GREEN;
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.SMASH,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.SMASH,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY};
    }

    @Override
    public String getVampireText() {
        return com.megacrit.cardcrawl.events.city.Vampires.DESCRIPTIONS[5];
    }


/*    private void reloadAnimation() {
        this.loadAnimation(atlasURL, this.currentJson, renderscale);
        AnimationState.TrackEntry e = this.state.setAnimation(0, "Idle", true);
        e.setTime(e.getEndTime() * MathUtils.random());
        this.state.addListener(new SlimeAnimListener());
    }

    public void initializeSlotPositions() {
        orbPositionsX[0] = xStartOffset + (xSpaceBetweenSlots * 1);
        orbPositionsX[1] = xStartOffset + (xSpaceBetweenSlots * 1) + xSpaceBottomAlternatingOffset;
        orbPositionsX[2] = xStartOffset + (xSpaceBetweenSlots * 2);
        orbPositionsX[3] = xStartOffset + (xSpaceBetweenSlots * 2) + xSpaceBottomAlternatingOffset;
        orbPositionsX[4] = xStartOffset + (xSpaceBetweenSlots * 3);
        orbPositionsX[5] = xStartOffset + (xSpaceBetweenSlots * 3) + xSpaceBottomAlternatingOffset;
        orbPositionsX[6] = xStartOffset + (xSpaceBetweenSlots * 4);
        orbPositionsX[7] = xStartOffset + (xSpaceBetweenSlots * 4) + xSpaceBottomAlternatingOffset;
        orbPositionsX[8] = xStartOffset + (xSpaceBetweenSlots * 5);
        orbPositionsX[9] = xStartOffset + (xSpaceBetweenSlots * 5) + xSpaceBottomAlternatingOffset;

        orbPositionsY[0] = yStartOffset;
        orbPositionsY[1] = yStartOffset + ySpaceBottomAlternatingOffset;
        orbPositionsY[2] = yStartOffset + ySpaceAlternatingOffset;
        orbPositionsY[3] = yStartOffset + ySpaceBottomAlternatingOffset + ySpaceAlternatingOffset;
        orbPositionsY[4] = yStartOffset;
        orbPositionsY[5] = yStartOffset + ySpaceBottomAlternatingOffset;
        orbPositionsY[6] = yStartOffset + ySpaceAlternatingOffset;
        orbPositionsY[7] = yStartOffset + ySpaceBottomAlternatingOffset + ySpaceAlternatingOffset;
        orbPositionsY[8] = yStartOffset;
        orbPositionsY[9] = yStartOffset + ySpaceBottomAlternatingOffset;
    }

    @Override
    public void applyStartOfTurnCards() {
        //Failsafe, should be removed through victory or intangible being removed, but just in case of weird buff nullify effects I don't know about...
        super.applyStartOfTurnCards();
        if (this.puddleForm && !this.hasPower(IntangiblePlayerPower.POWER_ID)) {
            removePuddleForm();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        if (!this.moved) this.movePosition((float) Settings.WIDTH * this.leftScale, AbstractDungeon.floorY);
        this.moved = true;


        this.hatX = this.skeleton.findBone("eyeback1").getX();
        this.hatY = this.skeleton.findBone("eyeback1").getY();

    }

    @SpireOverride
    public void renderPowerIcons(SpriteBatch sb, float x, float y) {
        float offset = 10.0F;
        int powersIterated = 0;
        float YOffset = 0;
        Iterator var5;
        AbstractPower p;
        for (var5 = this.powers.iterator(); var5.hasNext(); offset += 48.0F) {
            p = (AbstractPower) var5.next();
            p.renderIcons(sb, x + (offset * Settings.scale), y + ((-48.0F + YOffset) * Settings.scale), Color.WHITE);
            powersIterated++;
            if (powersIterated == 9 || powersIterated == 18) {
                YOffset += -42F;
                offset = -38.0F;
            }
        }

        offset = 0.0F;
        powersIterated = 0;
        YOffset = 0.0F;

        for (var5 = this.powers.iterator(); var5.hasNext(); offset += 48.0F) {
            p = (AbstractPower) var5.next();
            p.renderAmount(sb, x + ((offset + 32.0F) * Settings.scale), y + ((-66.0F + YOffset) * Settings.scale), Color.WHITE);
            powersIterated++;
            if (powersIterated == 9 || powersIterated == 18) {
                YOffset += -42F;
                offset = -48.0F;
            }
        }
    }

    private void removePuddleForm() {
        if (this.puddleForm) {
            this.currentJson = jsonURL;
            reloadAnimation();
            this.puddleForm = false;
        }
    }*/
}

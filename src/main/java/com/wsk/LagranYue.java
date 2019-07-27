package com.wsk;

import basemod.BaseMod;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.relics.Cauldron;
import com.megacrit.cardcrawl.relics.Orrery;
import com.megacrit.cardcrawl.relics.TinyHouse;
import com.wsk.cards.arms.*;
import com.wsk.cards.attack.*;
import com.wsk.cards.power.*;
import com.wsk.cards.proficiency.*;
import com.wsk.cards.skill.*;
import com.wsk.characters.LagranYueCharacter;
import com.wsk.helps.DurabilityVariable;
import com.wsk.helps.SkillPointVariable;
import com.wsk.patches.AbstractCardEnum;
import com.wsk.patches.CharacterEnum;
import com.wsk.relics.*;
import com.wsk.relics.share.*;
import com.wsk.utils.CommonUtil;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Properties;

import static basemod.DevConsole.logger;

/**
 * @author wsk1103
 * @date 2019/2/14
 * @desc 启动类
 */
@SpireInitializer
public class LagranYue implements PostInitializeSubscriber,
        EditCharactersSubscriber, EditCardsSubscriber,
        EditRelicsSubscriber, EditStringsSubscriber,
        EditKeywordsSubscriber {
    //以上继承6个接口，来注入人物mod所需的全部类。其中包括：Cards(卡牌)、Power(能力)、Action(动作)、relics(遗物)、KeyWord(关键字)、Character(角色)。更多接口可详参basemod。

    /**
     * Mod名称。
     */
    private static final String MODNAME = "Lagran Yue";
    /**
     * mod作者。
     */
    private static final String AUTHOR = "wsk1103";
    /**
     * Mod描叙，随便写。
     */
    private static final String DESCRIPTION = "v1.4.0\n Make by Sky.";
    /**
     * mod人物对应的颜色。getColor所需的三个参数分别对应颜色的三个色相R、G、U。查找色相请打开系统自带画图，编辑颜色窗口，右下角的RGU三栏。（仅以Win10的自带画图为例）
     */
    private static final Color COLOR = CardHelper.getColor(76, 151, 226);

    /**
     * 卡牌背景
     */
    private static final String ATTACK_CARD = "512/bg_attack_LagranYue.png";
    private static final String SKILL_CARD = "512/bg_skill_LagranYue.png";
    private static final String POWER_CARD = "512/bg_power_LagranYue.png";
    private static final String ENERGY_ORB = "512/card_LagranYue_orb.png";
    private static final String CARD_ENERGY_ORB = "512/card_small_orb.png";

    private static final String ATTACK_CARD_PORTRAIT = "1024/bg_attack_LagranYue.png";
    private static final String SKILL_CARD_PORTRAIT = "1024/bg_skill_LagranYue.png";
    private static final String POWER_CARD_PORTRAIT = "1024/bg_power_LagranYue.png";
    private static final String ENERGY_ORB_PORTRAIT = "1024/card_LagranYue_orb.png";

    public static final ArrayList<AbstractCard> ALL_CARS = new ArrayList<>();

    public static boolean contentSharing_relics = true;

    private static final String PROP_RELIC_SHARING = "contentSharing_relics";

    private static Properties lagranYueProperties = new Properties();


    public LagranYue() {
        logger.info("============================ 监听初始化事件 ============================");

        //监听事件，确保正常注入，不可缺少。
        BaseMod.subscribe(this);

        logger.info("============================ 监听初始化事件成功 ============================");
        logger.info("========================正在注入新卡片相关信息========================");

        BaseMod.addColor(AbstractCardEnum.LagranYue,
                COLOR, COLOR, COLOR, COLOR, COLOR, COLOR, COLOR,
                CommonUtil.getResourcePath(ATTACK_CARD), CommonUtil.getResourcePath(SKILL_CARD),
                CommonUtil.getResourcePath(POWER_CARD), CommonUtil.getResourcePath(ENERGY_ORB),
                CommonUtil.getResourcePath(ATTACK_CARD_PORTRAIT), CommonUtil.getResourcePath(SKILL_CARD_PORTRAIT),
                CommonUtil.getResourcePath(POWER_CARD_PORTRAIT), CommonUtil.getResourcePath(ENERGY_ORB_PORTRAIT),
                CommonUtil.getResourcePath(CARD_ENERGY_ORB));
/*                "攻击卡卡背图片路径（小号图片，512x512）", "技能卡卡背图片路径（小号图片，512x512）",
                "能力卡卡背图片路径（小号图片，512x512）", "卡片左上角能量图片路径（小号图片，512x512）",
                "攻击卡卡背图片路径（大号图片，1024x1024）", "技能卡卡背图片路径（大号图片，1024x1024）",
                "能力卡卡背图片路径（大号图片，1024x1024）", "卡片左上角能量图片路径（大号图片，1024x1024）");*/
        //null位置对应参数：AbstractCardEnum.Color.toString()
        //需要新建一个AbstractCardEnum类import后使用。代码如下：
        //@SpireEnum
        //public static AbstractCard.CardColor Color; Color为你的角色对应的颜色

        lagranYueProperties.setProperty(PROP_RELIC_SHARING, "TRUE");

        loadConfigData();
        logger.info(String.format("====注入新卡片相关信息成功,%s======", AbstractCardEnum.LagranYue.toString()));
    }

    public static void initialize() {
        new LagranYue();//初始化角色mod，必备。
    }

    /**
     * ModTheSpire正常启动mod后，在主界面里多出一项Mods选项来显示启用的Mod信息。以上部分为填写被显示的信息。
     */
    @Override
    public void receivePostInitialize() {

        UIStrings configStrings = CardCrawlGame.languagePack.getUIString("LagranYueConfigMenuText");

        ModPanel settingsPanel = new ModPanel();

        ModLabeledToggleButton contentSharingBtnRelics = new ModLabeledToggleButton(configStrings.TEXT[0],
                350.0f, 650.0f, Settings.CREAM_COLOR, FontHelper.charDescFont,
                contentSharing_relics, settingsPanel, (label) -> {
        }, (button) -> {
            contentSharing_relics = !button.enabled;
            saveData();
        });

        Texture badgeTexture = new Texture(CommonUtil.getResourcePath("badge.png"));
        settingsPanel.addUIElement(contentSharingBtnRelics);
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);
        logger.info("======BaseMod.registerCustomReward======");
    }

    @Override
    public void receiveEditCharacters() {
        logger.info("========================正在注入Mod人物信息========================");

        logger.info("add " + CharacterEnum.LagranYueCharacter.toString());
        LagranYueCharacter exampleModCharacter = new LagranYueCharacter("Test", CharacterEnum.LagranYueCharacter);
        BaseMod.addCharacter(exampleModCharacter, CommonUtil.getResourcePath("charSelect/LagranYue.png"),
                CommonUtil.getResourcePath("charSelect/portrait.png"), CharacterEnum.LagranYueCharacter);

        logger.info("========================注入Mod人物信息成功========================");
    }

    /**
     * 负责注入你所编辑好的遗物。
     */
    @Override
    public void receiveEditRelics() {
        logger.info("=========================正在加载新的遗物内容=========================");
        BaseMod.addRelicToCustomPool(new DarkRelics(), AbstractCardEnum.LagranYue);
        BaseMod.addRelicToCustomPool(new EnkiduRelics(), AbstractCardEnum.LagranYue);
        BaseMod.addRelicToCustomPool(new GungnirRelics(), AbstractCardEnum.LagranYue);
        BaseMod.addRelicToCustomPool(new LuEnRelics(), AbstractCardEnum.LagranYue);
        BaseMod.addRelicToCustomPool(new RuleBreakerRelics(), AbstractCardEnum.LagranYue);
        BaseMod.addRelicToCustomPool(new UnlimitedBladeWorksRelics(), AbstractCardEnum.LagranYue);
        BaseMod.addRelicToCustomPool(new ZabaniyaRelics(), AbstractCardEnum.LagranYue);
        BaseMod.addRelicToCustomPool(new BrokenPhantasmRelics(), AbstractCardEnum.LagranYue);
        BaseMod.addRelicToCustomPool(new LuEnSwordRelics(), AbstractCardEnum.LagranYue);
        BaseMod.addRelicToCustomPool(new OveredgeRelics(), AbstractCardEnum.LagranYue);
        BaseMod.addRelicToCustomPool(new ArmsProficiencyRelics(), AbstractCardEnum.LagranYue);

        BaseMod.addRelic(new ContractPerformanceRelic(), RelicType.SHARED);
        BaseMod.addRelic(new GuidanceLightRelic(), RelicType.SHARED);
        BaseMod.addRelic(new GuideDeadRelic(), RelicType.SHARED);
//        BaseMod.addRelic(new ImprintInheritanceRelic(), RelicType.SHARED);
        BaseMod.addRelic(new MagicReflectiveArmorRelic(), RelicType.SHARED);
//        BaseMod.addRelic(new MoonMirrorShieldRelic(), RelicType.SHARED);
        BaseMod.addRelic(new NewUniverseRelic(), RelicType.SHARED);
        BaseMod.addRelic(new RandomDrawCardRelic(), RelicType.SHARED);
        BaseMod.addRelic(new RareGoldArmorRelic(), RelicType.SHARED);
        BaseMod.addRelic(new ReturningEmperorRelic(), RelicType.SHARED);
        BaseMod.addRelic(new SoulBurningRelic(), RelicType.SHARED);
//        BaseMod.addRelic(new SpringFairyRelic(), RelicType.SHARED);
        BaseMod.addRelic(new WskJewelryRelic(), RelicType.SHARED);

        AbstractDungeon.bossRelicPool.remove(Orrery.ID);
        AbstractDungeon.bossRelicPool.remove(TinyHouse.ID);
        AbstractDungeon.rareRelicPool.remove(TinyHouse.ID);
        AbstractDungeon.shopRelicPool.remove(Cauldron.ID);
//        BaseMod.removeRelic(new Orrery());
//        BaseMod.removeRelic(new TinyHouse());
//        BaseMod.removeRelic(new DeadBranch());
//        BaseMod.removeRelic(new Cauldron());

        logger.info("=========================加载新的遗容成功=========================");
    }

    /**
     * 负责注入你所编辑好的卡牌。
     */
    @Override
    public void receiveEditCards() {

        BaseMod.addDynamicVariable(new SkillPointVariable());
        BaseMod.addDynamicVariable(new DurabilityVariable());

        ALL_CARS.add(new LifeForgingCard());
        ALL_CARS.add(new AttackAerCard());
        ALL_CARS.add(new AttackBetterCard());
        ALL_CARS.add(new AttackCellGodCard());
        ALL_CARS.add(new AttackDevilCard());
        ALL_CARS.add(new AttackEmeraldCard());
        ALL_CARS.add(new AttackEngravingRoseCard());
        ALL_CARS.add(new AttackFaithCard());
        ALL_CARS.add(new AttackFireCard());
        ALL_CARS.add(new AttackFlintCard());
        ALL_CARS.add(new AttackFutureCard());
        ALL_CARS.add(new AttackGoldCard());
        ALL_CARS.add(new AttackIceCard());
        ALL_CARS.add(new AttackKillerCard());
        ALL_CARS.add(new AttackKingCard());
        ALL_CARS.add(new AttackLakeCard());
        ALL_CARS.add(new AttackPurpleCard());
        ALL_CARS.add(new AttackRavagedCard());
        ALL_CARS.add(new AttackRubyCard());
        ALL_CARS.add(new AttackSapphireCard());
        ALL_CARS.add(new AttackSummerCard());
        ALL_CARS.add(new AttackTopazCard());
        ALL_CARS.add(new AttackWaltzCard());
        ALL_CARS.add(new BaseAttackCard());

        ALL_CARS.add(new BasePowerCard());
        ALL_CARS.add(new PowerBloodyTempleCard());
        ALL_CARS.add(new PowerDarkTempleCard());
        ALL_CARS.add(new PowerDoubleArmsCard());
        ALL_CARS.add(new PowerDreamCard());
        ALL_CARS.add(new PowerFurnaceCard());
        ALL_CARS.add(new PowerGoldStartCard());
        ALL_CARS.add(new PowerKingTreasureCard());
        ALL_CARS.add(new PowerMaxArmsPliesCard());
        ALL_CARS.add(new PowerRemoveArmorCard());
        ALL_CARS.add(new PowerTwelveTrialsCard());
        ALL_CARS.add(new PowerWindKingEnchantmentCard());

        ALL_CARS.add(new BaseDefendCard());
        ALL_CARS.add(new BraveTemperamentCard());
        ALL_CARS.add(new DefendBetterCard());
        ALL_CARS.add(new EatTigerCard());
        ALL_CARS.add(new EmeraldRemorseCard());
        ALL_CARS.add(new FantasyCard());
        ALL_CARS.add(new ForgingCard());
        ALL_CARS.add(new GoldForgingCard());
        ALL_CARS.add(new LifeForgingCard());
        ALL_CARS.add(new MagicSkyrocketCard());
        ALL_CARS.add(new MultipleThornsCard());
        ALL_CARS.add(new OneKingCard());
        ALL_CARS.add(new PhysicalFitnessCard());
        ALL_CARS.add(new PrematureBurialCard());
        ALL_CARS.add(new ProjectionCard());
        ALL_CARS.add(new PurpleNightmareCard());
        ALL_CARS.add(new RoseCard());
        ALL_CARS.add(new RubyDefendCard());
        ALL_CARS.add(new SapphirePrayerCard());
        ALL_CARS.add(new SkillCocoonEvolutionCard());
        ALL_CARS.add(new SkillDeadlyBloomCard());
        ALL_CARS.add(new SkillDevilKissCard());
        ALL_CARS.add(new SkillDistortionCard());
        ALL_CARS.add(new SkillFalseAerialAceCard());
        ALL_CARS.add(new SkillTestamentCard());

        ALL_CARS.add(new SkillBlazingSevenRingsCard());
        ALL_CARS.add(new SkillBrokenShieldCard());
        ALL_CARS.add(new SkillBrokenSwordCard());
        ALL_CARS.add(new SkillChiharaHoundCard());
        ALL_CARS.add(new SkillDeathBolgCard());
        ALL_CARS.add(new SkillExplosiveArmorCard());
        ALL_CARS.add(new SkillGaeBolgCard());
        ALL_CARS.add(new SkillGanJiangMoYeSwordCard());
        ALL_CARS.add(new SkillIncompleteArchCard());
        ALL_CARS.add(new SkillKadeboSwordCard());
        ALL_CARS.add(new SkillVictorySwordCard());
        ALL_CARS.add(new SkillWoodSpearCard());

        ALL_CARS.add(new AttackLuEnCard());
        ALL_CARS.add(new DefendLuEnCard());
        ALL_CARS.add(new ForgingLuEnCard());

        ALL_CARS.add(new TestChooseCard());

        ALL_CARS.add(new AttackCrowCrossbowCard());
        ALL_CARS.add(new AttackDegenerateCard());
        ALL_CARS.add(new AttackDivineGuidanceCard());
        ALL_CARS.add(new AttackEnchantmentCard());
        ALL_CARS.add(new AttackGalacticZeroCard());
        ALL_CARS.add(new AttackGodArchCard());
        ALL_CARS.add(new AttackGodSpearCard());
        ALL_CARS.add(new AttackGodSwordCard());
        ALL_CARS.add(new AttackMagicMoonCard());
        ALL_CARS.add(new AttackMagicStarsCard());
//        ALL_CARS.add(new AttackMagicSwordCard());
        ALL_CARS.add(new SkillDisasterEquipmentCard());
        ALL_CARS.add(new SkillEightFeetJadeCard());
        ALL_CARS.add(new SkillHeartWaterCard());
        ALL_CARS.add(new SkillLegendPaladinCard());
        ALL_CARS.add(new SkillLiberationCard());
        //加入卡牌格式:BaseMod.addCard(new 卡牌类名());
        //解锁卡牌格式:UnlockTracker.unlockCard("卡牌Id");

        for (AbstractCard card : ALL_CARS) {
            BaseMod.addCard(card);
        }

//        CustomUnlockBundle unlocks0 = new CustomUnlockBundle(
//                OneKingCard.ID, PowerKingTreasureCard.ID, AttackLakeCard.ID);
//
//        CustomUnlockBundle unlocks1 = new CustomUnlockBundle(
//                SkillKadeboSwordCard.ID, AttackSummerCard.ID, SkillBlazingSevenRingsCard.ID);
//
//        CustomUnlockBundle unlocks2 = new CustomUnlockBundle(
//                AttackFaithCard.ID, PowerFurnaceCard.ID, PowerDreamCard.ID);

    }

    /**
     * 负责加载你编辑好的文本信息。,例如卡牌说明，遗物说明等等
     */
    @Override
    public void receiveEditStrings() {

        String language = CommonUtil.getLanguage();

        logger.info("begin editing strings");
        String relicStrings = Gdx.files.internal("localization/" + language + "/LagranYue-Relics.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
        String powerStrings = Gdx.files.internal("localization/" + language + "/LagranYue-Powers.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);
        String cardStrings = Gdx.files.internal("localization/" + language + "/LagranYue-Cards.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
        String charStrings = Gdx.files.internal("localization/" + language + "/LagranYue-Characters.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CharacterStrings.class, charStrings);
        String uIstrings = Gdx.files.internal("localization/" + language + "/LagranYue-UIStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(UIStrings.class, uIstrings);
/*        String monsterStrings = Gdx.files.internal("localization/" + language + "/LagranYue-MonsterStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(MonsterStrings.class, monsterStrings);
        String potionStrings = Gdx.files.internal("localization/" + language + "/LagranYue-PotionStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(PotionStrings.class, potionStrings);
        String orbStrings = Gdx.files.internal("localization/" + language + "/LagranYue-OrbStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(OrbStrings.class, orbStrings);
        String eventStrings = Gdx.files.internal("localization/" + language + "/LagranYue-EventStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(EventStrings.class, eventStrings);
        String modStrings = Gdx.files.internal("localization/" + language + "/LagranYue-DailyModStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RunModStrings.class, modStrings);
        */

        logger.info("done editing strings");

    }

    /**
     * 负责加载关键字。
     */
    @Override
    public void receiveEditKeywords() {
        final Gson gson = new Gson();
        String language = CommonUtil.getLanguage();
        final String json = Gdx.files.internal("localization/" + language + "/LagranYue-Keywords.json").readString(String.valueOf(StandardCharsets.UTF_8));
        final Keyword[] keywords = gson.fromJson(json, Keyword[].class);
        if (keywords != null) {
            logger.info("keywords size:" + keywords.length);
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(keyword.NAMES, keyword.DESCRIPTION);
            }
        } else {
            logger.info("keywords is null");
        }
    }

    private void saveData() {
        try {
            SpireConfig config = new SpireConfig("LagranYueMod", "LagranYueSaveData", lagranYueProperties);
            config.setBool(PROP_RELIC_SHARING, contentSharing_relics);

            config.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadConfigData() {
        try {
            logger.info("LagranYueMod | Loading Config Preferences...");
            SpireConfig config = new SpireConfig("LagranYueMod", "LagranYueSaveData", lagranYueProperties);
            config.load();
            contentSharing_relics = config.getBool(PROP_RELIC_SHARING);
        } catch (Exception e) {
            e.printStackTrace();
            clearData();
        }
    }

    private void clearData() {
        saveData();
    }
}

package com.wsk;

import basemod.BaseMod;
import basemod.abstracts.CustomUnlockBundle;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.GameDictionary;
import com.megacrit.cardcrawl.localization.*;
import com.wsk.cards.arms.*;
import com.wsk.cards.attack.*;
import com.wsk.cards.power.*;
import com.wsk.cards.skill.*;
import com.wsk.characters.LagranYueCharacter;
import com.wsk.patches.AbstractCardEnum;
import com.wsk.patches.CharacterEnum;
import com.wsk.relics.*;
import com.wsk.utils.CommonUtil;

import java.nio.charset.StandardCharsets;
import java.util.TreeMap;

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

    private static final String MODNAME = "Lagran Yue";//Mod名称。
    private static final String AUTHOR = "wsk1103";//mod作者。
    private static final String DESCRIPTION = "v1.0\n Make by Sky.";//Mod描叙，随便写。
    private static final Color COLOR = CardHelper.getColor(76, 151, 226);//mod人物对应的颜色。getColor所需的三个参数分别对应颜色的三个色相R、G、U。查找色相请打开系统自带画图，编辑颜色窗口，右下角的RGU三栏。（仅以Win10的自带画图为例）

    /**
     * 卡牌背景
     */
    private static final String ATTACK_CARD = "512/bg_attack_slimebound.png";
    private static final String SKILL_CARD = "512/bg_skill_slimebound.png";
    private static final String POWER_CARD = "512/bg_power_slimebound.png";
    private static final String ENERGY_ORB = "512/card_slimebound_orb.png";
    private static final String CARD_ENERGY_ORB = "512/card_small_orb.png";

    private static final String ATTACK_CARD_PORTRAIT = "1024/bg_attack_slimebound.png";
    private static final String SKILL_CARD_PORTRAIT = "1024/bg_skill_slimebound.png";
    private static final String POWER_CARD_PORTRAIT = "1024/bg_power_slimebound.png";
    private static final String ENERGY_ORB_PORTRAIT = "1024/card_slimebound_orb.png";

    private CustomUnlockBundle unlocks0;
    private CustomUnlockBundle unlocks1;
    private CustomUnlockBundle unlocks2;
    private CustomUnlockBundle unlocks3;
    private CustomUnlockBundle unlocks4;


    public LagranYue() {
        logger.info("============================ 监听初始化事件 ============================");

        BaseMod.subscribe(this);//监听事件，确保正常注入，不可缺少。

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
//        testModDefault.setProperty(PROP_EVENT_SHARING, "FALSE");
//        testModDefault.setProperty(PROP_RELIC_SHARING, "FALSE");
//        testModDefault.setProperty(PROP_POTION_SHARING, "FALSE");
//        testModDefault.setProperty(PROP_UNLOCK_ALL, "FALSE");
//
//        loadConfigData();
        logger.info(String.format("====注入新卡片相关信息成功,%s======", AbstractCardEnum.LagranYue.toString()));
    }

    public static void initialize() {
        logger.info("=========================初始化角色Mod数据=========================");
        new LagranYue();//初始化角色mod，必备。
        logger.info("===========================角色Mod初始化成功===========================");
    }

    //ModTheSpire正常启动mod后，在主界面里多出一项Mods选项来显示启用的Mod信息。以上部分为填写被显示的信息。
    @SuppressWarnings("deprecation")
    @Override
    public void receivePostInitialize() {

        Texture badgeTexture = new Texture(CommonUtil.getResourcePath("badge.png"));
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, null);
        logger.info("Done loading badge Image and mod options");
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

    //负责注入你所编辑好的遗物。
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
        logger.info("=========================加载新的遗物内容成功=========================");
    }

    //负责注入你所编辑好的卡牌。
    @Override
    public void receiveEditCards() {
        logger.info("=========================正在加载新的卡牌内容=========================");


        BaseMod.addCard(new LifeForgingCard());

        BaseMod.addCard(new AttackAerCard());
        BaseMod.addCard(new AttackBetterCard());
        BaseMod.addCard(new AttackDevilCard());
        BaseMod.addCard(new AttackEmeraldCard());
        BaseMod.addCard(new AttackFaithCard());
        BaseMod.addCard(new AttackFireCard());
        BaseMod.addCard(new AttackFutureCard());
        BaseMod.addCard(new AttackGoldCard());
        BaseMod.addCard(new AttackIceCard());
        BaseMod.addCard(new AttackKillerCard());
        BaseMod.addCard(new AttackKingCard());
        BaseMod.addCard(new AttackLakeCard());
        BaseMod.addCard(new AttackPurpleCard());
        BaseMod.addCard(new AttackRavagedCard());
        BaseMod.addCard(new AttackRubyCard());
        BaseMod.addCard(new AttackSapphireCard());
        BaseMod.addCard(new AttackSummerCard());
        BaseMod.addCard(new AttackTopazCard());
        BaseMod.addCard(new AttackWaltzCard());
        BaseMod.addCard(new BaseAttackCard());

        BaseMod.addCard(new BasePowerCard());
        BaseMod.addCard(new PowerBloodyTempleCard());
        BaseMod.addCard(new PowerDarkTempleCard());
        BaseMod.addCard(new PowerDoubleArmsCard());
        BaseMod.addCard(new PowerDreamCard());
        BaseMod.addCard(new PowerFurnaceCard());
        BaseMod.addCard(new PowerGoldStartCard());
        BaseMod.addCard(new PowerKingTreasureCard());
        BaseMod.addCard(new PowerMaxArmsPliesCard());
        BaseMod.addCard(new PowerRemoveArmorCard());
        BaseMod.addCard(new PowerTwelveTrialsCard());
        BaseMod.addCard(new PowerWindKingEnchantmentCard());

        BaseMod.addCard(new BaseDefendCard());
        BaseMod.addCard(new BraveTemperamentCard());
        BaseMod.addCard(new DefendBetterCard());
        BaseMod.addCard(new EatTigerCard());
        BaseMod.addCard(new EmeraldRemorseCard());
        BaseMod.addCard(new FantasyCard());
        BaseMod.addCard(new ForgingCard());
        BaseMod.addCard(new GoldForgingCard());
        BaseMod.addCard(new LifeForgingCard());
        BaseMod.addCard(new MagicSkyrocketCard());
        BaseMod.addCard(new MultipleThornsCard());
        BaseMod.addCard(new OneKingCard());
        BaseMod.addCard(new PhysicalFitnessCard());
//        BaseMod.addCard(new PolyBeamCard());
        BaseMod.addCard(new PrematureBurialCard());
        BaseMod.addCard(new ProjectionCard());
        BaseMod.addCard(new PurpleNightmareCard());
        BaseMod.addCard(new RoseCard());
        BaseMod.addCard(new RubyDefendCard());
        BaseMod.addCard(new SapphirePrayerCard());


        BaseMod.addCard(new SkillBlazingSevenRingsCard());
        BaseMod.addCard(new SkillBrokenShieldCard());
        BaseMod.addCard(new SkillBrokenSwordCard());
        BaseMod.addCard(new SkillChiharaHoundCard());
        BaseMod.addCard(new SkillDeathBolgCard());
        BaseMod.addCard(new SkillExplosiveArmorCard());
        BaseMod.addCard(new SkillGaeBolgCard());
        BaseMod.addCard(new SkillGanJiangMoYeSwordCard());
        BaseMod.addCard(new SkillIncompleteArchCard());
        BaseMod.addCard(new SkillKadeboSwordCard());
        BaseMod.addCard(new SkillVictorySwordCard());
        BaseMod.addCard(new SkillWoodSpearCard());

        BaseMod.addCard(new AttackLuEnCard());
        BaseMod.addCard(new DefendLuEnCard());
        BaseMod.addCard(new ForgingLuEnCard());
        //加入卡牌格式:BaseMod.addCard(new 卡牌类名());
        //解锁卡牌格式:UnlockTracker.unlockCard("卡牌Id");

        unlocks0 = new CustomUnlockBundle(
                OneKingCard.ID, PowerKingTreasureCard.ID, AttackLakeCard.ID);

        unlocks1 = new CustomUnlockBundle(
                SkillKadeboSwordCard.ID, AttackSummerCard.ID, SkillBlazingSevenRingsCard.ID);

        unlocks3 = new CustomUnlockBundle(
                AttackFaithCard.ID, PowerFurnaceCard.ID, PowerDreamCard.ID);

        logger.info("=========================加载新的卡牌内容成功=========================");
    }

    //负责加载你编辑好的文本信息。,例如卡牌说明，遗物说明等等
    @Override
    public void receiveEditStrings() {
        logger.info("=========================正在加载遗物文本信息=========================");

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
/*        String monsterStrings = Gdx.files.internal("localization/" + language + "/Slimebound-MonsterStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(MonsterStrings.class, monsterStrings);
        String potionStrings = Gdx.files.internal("localization/" + language + "/Slimebound-PotionStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(PotionStrings.class, potionStrings);
        String orbStrings = Gdx.files.internal("localization/" + language + "/Slimebound-OrbStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(OrbStrings.class, orbStrings);
        String eventStrings = Gdx.files.internal("localization/" + language + "/Slimebound-EventStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(EventStrings.class, eventStrings);
        String modStrings = Gdx.files.internal("localization/" + language + "/Slimebound-DailyModStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RunModStrings.class, modStrings);
        String UIStrings = Gdx.files.internal("localization/" + language + "/Slimebound-UIStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(UIStrings.class, UIStrings);*/

        logger.info("done editing strings");

        logger.info("=========================加载遗物文本信息成功========================");
    }

    //负责加载关键字。
    @Override
    public void receiveEditKeywords() {
        logger.info("==========================正在注入新的关键字==========================");

        final Gson gson = new Gson();
        String language = CommonUtil.getLanguage();
        logger.info("begin editing strings");
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
        TreeMap<String, String> treeMap = GameDictionary.keywords;
        logger.info("==========================注入新的关键字成功==========================");
    }
}

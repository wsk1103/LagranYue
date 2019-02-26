package com.wsk;

import basemod.BaseMod;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.*;
import com.wsk.cards.attack.*;
import com.wsk.cards.power.BasePowerCard;
import com.wsk.cards.power.PowerDoubleArmsCard;
import com.wsk.cards.skill.*;
import com.wsk.characters.MyModCharacter;
import com.wsk.patches.AbstractCardEnum;
import com.wsk.patches.CharacterEnum;
import com.wsk.utils.CommonUtil;

import java.nio.charset.StandardCharsets;

import static basemod.DevConsole.logger;

/**
 * @author wsk1103
 * @date 2019/2/14
 * @desc 启动类
 */
@SpireInitializer
public class MyMod implements PostInitializeSubscriber,
        EditCharactersSubscriber, EditCardsSubscriber,
        EditRelicsSubscriber, EditStringsSubscriber,
        EditKeywordsSubscriber {
    //以上继承6个接口，来注入人物mod所需的全部类。其中包括：Cards(卡牌)、Power(能力)、Action(动作)、relics(遗物)、KeyWord(关键字)、Character(角色)。更多接口可详参basemod。

    private static final String MODNAME = "爱的供养";//Mod名称。
    private static final String AUTHOR = "wsk1103";//mod作者。
    private static final String DESCRIPTION = "v1.0-test\n Make by Sky.";//Mod描叙，随便写。
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

    /**
     * 配置界面说明
     */
    private static final String PROP_RELIC_SHARING = "contentSharing_relics";
    private static final String PROP_POTION_SHARING = "contentSharing_potions";
    private static final String PROP_EVENT_SHARING = "contentSharing_events";
    private static final String PROP_UNLOCK_ALL = "unlockEverything";

    private static SpireConfig modConfig = null;

    public MyMod() {
        logger.info("============================ 监听初始化事件 ============================");

        BaseMod.subscribe(this);//监听事件，确保正常注入，不可缺少。

        logger.info("============================ 监听初始化事件成功 ============================");
        logger.info("========================正在注入新卡片相关信息========================");

        BaseMod.addColor(AbstractCardEnum.MyModCard,
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
        logger.info(String.format("====注入新卡片相关信息成功,%s======", AbstractCardEnum.MyModCard.toString()));
    }

    public static void initialize() {
        logger.info("=========================初始化角色Mod数据=========================");
        new MyMod();//初始化角色mod，必备。
        logger.info("===========================角色Mod初始化成功===========================");
    }

    //ModTheSpire正常启动mod后，在主界面里多出一项Mods选项来显示启用的Mod信息。以上部分为填写被显示的信息。
    @SuppressWarnings("deprecation")
    @Override
    public void receivePostInitialize() {

//        UIStrings configStrings = CardCrawlGame.languagePack.getUIString("SkyTestConfigMenuText");
        Texture badgeTexture = new Texture(CommonUtil.getResourcePath("badge.png"));
        BaseMod.registerModBadge(badgeTexture, "slimebound-test", AUTHOR, DESCRIPTION, null);
        logger.info("Done loading badge Image and mod options");
    }

    @Override
    public void receiveEditCharacters() {
        logger.info("========================正在注入Mod人物信息========================");

        logger.info("add " + CharacterEnum.MyModCharacter.toString());
//        logger.info("add " +SlimeboundEnum.SLIMEBOUND.toString());
        //null位置对应参数：CharacterEnum.CharacterName.toString()
        //需要新建一个CharacterEnum类import后使用。代码如下：
        //@SpireEnum
        //public static AbstractPlayer.PlayerClass CharacterName; CharacterName为你的角色对应的英文名称
//        SlimeboundCharacter exampleModCharacter = new SlimeboundCharacter("Test", SlimeboundEnum.SLIMEBOUND);
        MyModCharacter exampleModCharacter = new MyModCharacter("Test", CharacterEnum.MyModCharacter);
        BaseMod.addCharacter(exampleModCharacter, CommonUtil.getResourcePath("charSelect/emptyButton.png"),
                CommonUtil.getResourcePath("charSelect/ali.png"), CharacterEnum.MyModCharacter);
//        BaseMod.addCharacter(exampleModCharacter, getResourcePath("charSelect/button.png"),
//                getResourcePath("charSelect/portrait.png"), SlimeboundEnum.SLIMEBOUND);

/*        BaseMod.addCharacter(
                null, "Mod人物名称", "CharacterName class string",
                //null位置对应参数：CharacterName.class
                //需要新建一个CharacterName类import后使用，该类负责管理人物的各类初始信息，如初始卡组、初始遗物、人物描叙等。
                //代码过长，无法展示，请详参群内的ModBaseCharacter.java-人物模板。

                null, "Mod人物名称", "角色选择界面对应的摁扭图标", "人物选择界面的背景图片",
                //null位置对应参数：AbstractCardEnum.BLACK.toString()
                //详细解释同该类第50行。

                null);
        //null位置对应参数CharacterEnum.CharacterName.toString()
        //详细解释同该类第84行。*/

        logger.info("========================注入Mod人物信息成功========================");
    }

    //负责注入你所编辑好的遗物。
    @Override
    public void receiveEditRelics() {
        logger.info("=========================正在加载新的遗物内容=========================");

        logger.info("===slimebound===这是一个测试，所以没有遗物");
//        BaseMod.addRelicToCustomPool(new 遗物类名(), AbstractCardEnum.makeBySky);
        //null位置对应参数：AbstractCardEnum.BLACK.toString()
        logger.info("=========================加载新的遗物内容成功=========================");
    }

    //负责注入你所编辑好的卡牌。
    @Override
    public void receiveEditCards() {
        logger.info("=========================正在加载新的卡牌内容=========================");

        BaseMod.addCard(new AttackBetterCard());
        BaseMod.addCard(new AttackEmeraldCard());
        BaseMod.addCard(new AttackFireCard());
        BaseMod.addCard(new AttackIceCard());
        BaseMod.addCard(new AttackPurpleCard());
        BaseMod.addCard(new AttackRubyCard());
        BaseMod.addCard(new AttackSapphireCard());
        BaseMod.addCard(new AttackSummerCard());
        BaseMod.addCard(new AttackTopazCard());
        BaseMod.addCard(new BaseAttackCard());

        BaseMod.addCard(new BasePowerCard());
        BaseMod.addCard(new PowerDoubleArmsCard());

        BaseMod.addCard(new BaseDefendCard());
        BaseMod.addCard(new DefendCard2());
        BaseMod.addCard(new PolyBeamCard());
        BaseMod.addCard(new RoseCard());
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
        //加入卡牌格式:BaseMod.addCard(new 卡牌类名());
        //解锁卡牌格式:UnlockTracker.unlockCard("卡牌Id");

        logger.info("=========================加载新的卡牌内容成功=========================");
    }
    //负责加载你编辑好的文本信息。,例如卡牌说明，遗物说明等等
    @Override
    public void receiveEditStrings() {
        logger.info("=========================正在加载遗物文本信息=========================");

//        String relicStrings = Gdx.files.internal("文本位置").readString(String.valueOf(StandardCharsets.UTF_8));
//        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
        //除遗物文本信息需要导入外，其他文本信息可在类中直接写好，也可导入，具体实现请详参其他mod。文本文件格式.json。文本编码格式UTF-8。

        String language = CommonUtil.getLanguage();
/*
        if (Settings.language == Settings.GameLanguage.ZHS) language = "zhs";
        if (Settings.language == Settings.GameLanguage.ZHT) language = "zht";
        if (Settings.language == Settings.GameLanguage.FRA) language = "fra";
        if (Settings.language == Settings.GameLanguage.KOR) language = "kor";
        if (Settings.language == Settings.GameLanguage.JPN) language = "jpn";
*/

        logger.info("begin editing strings");
        String relicStrings = Gdx.files.internal("localization/" + language + "/Slimebound-RelicStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
        String powerStrings = Gdx.files.internal("localization/" + language + "/MyMod-Powers.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);
        String cardStrings = Gdx.files.internal("localization/" + language + "/MyMod-Cards.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
        String monsterStrings = Gdx.files.internal("localization/" + language + "/Slimebound-MonsterStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(MonsterStrings.class, monsterStrings);
        String potionStrings = Gdx.files.internal("localization/" + language + "/Slimebound-PotionStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(PotionStrings.class, potionStrings);
        String orbStrings = Gdx.files.internal("localization/" + language + "/Slimebound-OrbStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(OrbStrings.class, orbStrings);
        String eventStrings = Gdx.files.internal("localization/" + language + "/Slimebound-EventStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(EventStrings.class, eventStrings);
        String modStrings = Gdx.files.internal("localization/" + language + "/Slimebound-DailyModStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RunModStrings.class, modStrings);
        String charStrings = Gdx.files.internal("localization/" + language + "/Slimebound-CharacterStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(CharacterStrings.class, charStrings);
        String UIStrings = Gdx.files.internal("localization/" + language + "/Slimebound-UIStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(UIStrings.class, UIStrings);

        logger.info("done editing strings");

        logger.info("=========================加载遗物文本信息成功========================");
    }
    //负责加载关键字。
    @Override
    public void receiveEditKeywords() {
        logger.info("==========================正在注入新的关键字==========================");

//        BaseMod.addKeyword(new String[]{"关键字", "关键字"}, "关键字描叙");
//        BaseMod.addKeyword(keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
        //文本描叙需要使用到关键字时，请将关键字格式前后加一个空格。
        //例： DESCRIPTION = "这个位置有一个 关键字 需要体现。";
//        final Gson gson = new Gson();
//        String language;
//        language = getLanguage();
//
//        logger.info("begin editing strings");
//        final String json = Gdx.files.internal("localization/" + language + "/Slimebound-KeywordStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
//
//        final com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = (com.evacipated.cardcrawl.mod.stslib.Keyword[]) gson.fromJson(json, (Class) com.evacipated.cardcrawl.mod.stslib.Keyword[].class);
//        if (keywords != null) {
//            for (final Keyword keyword : keywords) {
//                BaseMod.addKeyword(keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
//            }
//        }
        logger.info("==========================注入新的关键字成功==========================");
    }





}

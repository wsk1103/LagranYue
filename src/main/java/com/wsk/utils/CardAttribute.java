package com.wsk.utils;

/**
 * @author wsk1103
 * @date 2019/2/24
 * @desc 卡牌属性
 */
public class CardAttribute {
    /**
     * 卡牌名称
     */
    public String NAME;

    /**
     * 卡牌说明
     */
    public String DESCRIPTION;
    /**
     * 卡牌升级后的说明
     */
    public String UPGRADE_DESCRIPTION;

    /**
     * 卡牌的额外说明
     */
    public String[] EXTENDED_DESCRIPTION;

    /**
     * 卡牌类型
     * SKILL——技能，ATTACK——攻击，POWER——能力，CURSE——诅咒，STATUS——状态；
     */
    public String CARDTYPE;

    /**
     * 卡牌颜色。决定该卡牌属于哪个角色
     * BASIC——基础牌，COMMON——白卡，RARE——金卡，UNCOMMON——蓝卡,SPECIAL--特殊
     */
    public String CARDRARIRY;

    /**
     * 卡牌目标。决定你打出卡牌时指向效果
     * SELF——自己，SELF_AND_ENEMY——自己以及敌人，ALL_ENEMY——所有敌人，ENEMY——敌人；
     */
    public String CARDTARGET;

    /**
     * 卡牌图片路径
     * eg "cards/slimepunch.png"
     */
    public String IMG;

    /**
     * 卡牌费用
     */
    public int COST;

    public CardAttribute() {
    }

}

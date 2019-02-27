package com.wsk.utils;

import com.megacrit.cardcrawl.core.Settings;

/**
 * @author wsk1103
 * @date 2019/2/14
 * @desc 一句话说明
 */
public class CommonUtil {

    /**
     * 获取资源图片路径
     *
     * @param resource 图片名称
     * @return 图片路径
     */
    public static String getResourcePath(String resource) {
        return "mymod/" + resource;
    }

    public static String getLanguage() {
        String language;
        switch (Settings.language) {
            case ENG:
                language = "eng";
                break;
            default:
                language = "zhs";
        }
        return language;
    }

}

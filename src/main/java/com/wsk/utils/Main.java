package com.wsk.utils;

/**
 * @author wsk1103
 * @date 2019/7/22
 * @description 描述
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("不使用代理");
        UserService userService = new UserServiceImpl();
        userService.update();
        System.out.println("-------");

        System.out.println("使用动态代理");
        UserService target = new UserServiceImpl();
        UserService proxy = (UserService) new TranslationProxy(target).getProxyInstance();
        proxy.update();
        System.out.println("--------");

    }
}

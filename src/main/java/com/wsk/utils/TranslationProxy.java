package com.wsk.utils;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author wsk1103
 * @date 2019/7/22
 * @description 动态代理
 */
public class TranslationProxy implements MethodInterceptor {

    //被代理的对象
    private Object target;

    public TranslationProxy(Object o) {
        this.target = o;
    }

    private void start() {
        System.out.println("start translation");
    }

    private void commit() {
        System.out.println("commit translation");
    }

    public Object getProxyInstance() {
        //1.工具类
        Enhancer en = new Enhancer();
        //2.设置父类
        en.setSuperclass(target.getClass());
        //3.设置回调函数
        en.setCallback(this);
        //4.创建子类(代理对象)
        return en.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        start();
        Object result = method.invoke(target, objects);
        commit();
        return result;
    }
}

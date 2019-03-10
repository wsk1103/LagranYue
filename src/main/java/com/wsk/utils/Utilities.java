package com.wsk.utils;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.random.Random;

import java.util.ArrayList;
import java.util.function.Predicate;

/**
 * @author wsk1103
 * @date 2019/3/8
 * @description 描述
 */
public class Utilities {

    public static <T> T SafeCast(Object o, Class<T> type)
    {
        return (T)(type.isInstance(o) ? type.cast(o) : null);
    }

    public static <T> T GetRandomElement(ArrayList<T> list, Random rng)
    {
        int size = list.size();
        if (size > 0) {
            return (T)list.get(rng.random(list.size() - 1));
        }
        return null;
    }

    public static <T> T GetRandomElement(ArrayList<T> list)
    {
        int size = list.size();
        if (size > 0) {
            return (T)list.get(MathUtils.random(list.size() - 1));
        }
        return null;
    }

    public static <T> ArrayList<T> Where(ArrayList<T> list, Predicate<T> predicate)
    {
        ArrayList<T> res = new ArrayList();
        for (T t : list) {
            if (predicate.test(t)) {
                res.add(t);
            }
        }
        return res;
    }
}

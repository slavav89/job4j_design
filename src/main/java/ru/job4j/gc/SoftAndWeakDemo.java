package ru.job4j.gc;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class SoftAndWeakDemo {
    private static void referenceSoft() {
        SoftReference<List> reference = new SoftReference<>(new ArrayList<>());
        var strong = reference.get();
        System.out.println(strong != null ? "ArrayList present" : "ArrayList delete");
    }

    private static void referenceWeak() {
        WeakReference<List> reference = new WeakReference<>(new ArrayList<>());
        var strong = reference.get();
        System.out.println(strong != null ? "ArrayList present" : "ArrayList delete");
    }
}

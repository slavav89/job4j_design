package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte b = 1;
        short s = 130;
        int i = 35000;
        long l = 2247483647L;
        float f = 7.1F;
        double d = 20.3;
        boolean bool = true;
        char c = 'c';
        LOG.debug("There are 8 primitive types in java : byte = {}, short = {}, int = {}, long = {}, "
                + "float = {}, double = {}, boolean = {}, char = {}.", b, s, i, l, f, d, bool, c);
    }
}
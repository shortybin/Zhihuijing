package com.bibi.wisdom.utils;

import java.util.ArrayList;
import java.util.List;

public class TestJava {

    public static void main(String[] args) {
        List<? extends Number> list1 = new ArrayList<>();


        List<? super Number> list5 = new ArrayList<>();
        list5.add(111);
        list5.add(111);
        list5.add(111);
        list5.add(111);

        Object aa=list5.get(0);
    }
}

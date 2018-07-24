package com.belief;

import java.util.*;

public class Test {

    public static void main(String[] args) {
        List list = new ArrayList();

        for (int i = 1; i <= 5 ; i++) {
            list.add("topic"+i);
        }

        System.out.println("题目： " + list.toString());

        String[] strings = new String[]{"a", "b", "c", "d", "e"};

        System.out.println("答案： " + Arrays.toString(strings));

        Map map = new HashMap();

        for (int i = 0; i < list.size(); i++) {
            map.put(list.get(i),strings[i].equals("a")?"aaaa":strings[i].equals("b")?"bbbb":strings[i].equals("c")?"cccc":strings[i].equals("d")?"dddd":"eeee");
        }

        System.out.println("题目+答案： " + map.toString());
    }
}

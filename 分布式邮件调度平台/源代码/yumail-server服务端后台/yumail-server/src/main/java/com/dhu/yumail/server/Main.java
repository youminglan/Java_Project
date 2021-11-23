package com.dhu.yumail.server;

import org.quartz.SchedulerException;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试
 * @author Yupi Li
 * @date 19/03/15
 */
public class Main {
    public static void main(String[] args) throws SchedulerException {
        List<Integer> list = new ArrayList<>();
        list.add(2);
        list.add(1);
        list.remove(0);
        System.out.println(list.get(0));
    }
}

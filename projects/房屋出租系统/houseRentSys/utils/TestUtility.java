package com.user.houseRentSys.utils;

public class TestUtility {
    public static void main(String[] args) {
        // 只是一个测试类，使用完就可以删除了
        // 输入一个字符串，最大长度为3
        String s1 = Utility.readString(3);
        System.out.println("s1 = " + s1);

        // 输入一个字符串，最大长度为10，若直接回车，则返回默认值edu
        String s2 = Utility.readString(10, "edu");
        System.out.println("s2 = " + s2);
    }
}

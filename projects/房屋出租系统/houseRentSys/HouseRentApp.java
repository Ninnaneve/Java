package com.user.houseRentSys;

import com.user.houseRentSys.view.HouseView;

public class HouseRentApp {
    public static void main(String[] args) {
        // 创建HouseView对象，并且显示主菜单
        new HouseView().mainMenu(); // 这里的匿名对象更妙
        System.out.println("> 你退出了房屋出租系统");
//        Math.abs()
    }
}

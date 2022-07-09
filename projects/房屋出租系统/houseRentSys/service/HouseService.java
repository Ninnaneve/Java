package com.user.houseRentSys.service;

import com.user.houseRentSys.domain.House;
import com.user.houseRentSys.utils.Utility;

/**
 * 房屋出租列表：
 * 1. 相应HouseView的调用
 * 2. 完成对房屋信息的CRUD
 */

public class HouseService {
    private House[] houses; // 保存House对象
    private int houseNums = 1; // 记录当前有多少个房屋信息
    private int idCounter = 1; // 记录当前id的增长到哪个值
    // 指定数组大小（但是感觉很不灵活）
    public HouseService(int size){
        houses = new House[size];
        // 为了配合测试列表信息，在这里初始化一个House对象
        houses[0] = new House(1, "jake", "901", "海淀区", 2000, "未出租");
    }

    public House find(int findId){
        for (int i = 0; i < houseNums; i++) {
            if (findId == houses[i].getId()){
                return houses[i];
            }
        }
        return null;
    }

    public boolean del(int delId){
        int index = -1;
        for (int i = 0; i < houseNums; i++) {
            if (delId == houses[i].getId()) {
                index = i; // 找到就使用index记录i
            }
        }
        if (index == -1){ // 找不到就说明数组不存在
            return false;
        }
        // 如果找到，则将index后的数组往前移动一位，当前存在的最后一位置空
        for (int i = index; i < houseNums - 1; i++){
            houses[i] = houses[i+1];
        }
        houses[--houseNums] = null;
        return true;
    }

    public boolean add(House newHouse){
        // 判断是否还可以继续添加 => 能否自己加入数组扩容机制
        if (houseNums >= houses.length){
            System.out.println("数组已满，不能再添加了...");
            return false;
        }
        houses[houseNums++] = newHouse; // 注意：这里已经有一个测试房屋存在，因此一开始输入为1是正确的
        // 程序员需要设计一个自增长机制，然后更新newHouse的id
        newHouse.setId(++idCounter);
        return true;
    }

    // list方法，返回houses
    public House[] list(){
        return houses;
    }
}

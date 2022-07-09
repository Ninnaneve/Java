package com.user.houseRentSys.view;

import com.user.houseRentSys.domain.House;
import com.user.houseRentSys.service.HouseService;
import com.user.houseRentSys.utils.Utility;

/**
 * 1. 显示界面
 * 2. 接收用户的输入
 * 3. 调用HouseService完成对房屋信息的各种操作
 */

public class HouseView {
    private boolean loop = true; // 控制现实菜单
    private char key = ' '; // 接收用户选择
    private HouseService houseService = new HouseService(10);

    // 编写chargeHouse()，接收用户输入id，调用find方法，而不重新构造一个方法（很巧妙！）
    public void changeHouse(){
        System.out.println("-----------------修改房屋-----------------");
        System.out.print("请输入你要查找的id（-1退出）：");
        int changeId = Utility.readInt();
        if (changeId == -1){
            System.out.println("---------------放弃修改房屋---------------");
            return;
        }
        House house = houseService.find(changeId);
        if (house == null){
            System.out.println("------------编号不存在，修改失败------------");
        }
        System.out.print("姓名(" + house.getName() + ")：");
        String name = Utility.readString(8, ""); // 如果用户直接回车，表示不修改信息，默认为""
        if (!"".equals(name)){
            house.setName(name);
        }
        System.out.print("电话(" + house.getPhone() + ")：");
        String phone = Utility.readString(12, "");
        if (!"".equals(phone)){
            house.setPhone(phone);
        }
        System.out.print("地址(" + house.getAddress() + ")：");
        String address = Utility.readString(16, "");
        if (!"".equals(address)){
            house.setAddress(address);
        }
        System.out.print("租金(" + house.getRent() + ")：");
        int rent = Utility.readInt(-1);
        if (rent != -1){
            house.setRent(rent);
        }
        System.out.print("状态(" + house.getState() + ")：");
        String state = Utility.readString(3, "");
        if (!"".equals(state)){
            house.setState(state);
        }
        System.out.println("-----------------修改成功-----------------");

    }

    // 编写findHouse()，接收用户输入id，调用find方法
    public void findHouse(){
        System.out.println("-----------------查找房屋-----------------");
        System.out.print("请输入你要查找的id：");
        int findId = Utility.readInt();
        House result = houseService.find(findId);
        if (result == null){
            System.out.println("------------编号不存在，查找失败------------");
        } else{
            System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态（已出租/未出租）");
            System.out.println(result);
        }
    }

    // 编写delHouse()，接收用户输入id，调用del方法
    public void delHouse(){
        System.out.println("-----------------删除房屋-----------------");
        System.out.print("请输入待删除房屋的编号（-1退出）：");
        int delId = Utility.readInt();
        if (delId == -1){
            System.out.println("---------------放弃删除房屋---------------");
            return;
        }
        // 该方法本身就有循环判断的逻辑，必须输出Y/N
        char choice = Utility.readConfirmSelection();
        if (choice == 'Y'){
            if (houseService.del(delId)){
                System.out.println("-----------------删除成功-----------------");
            } else{
                System.out.println("-----------房屋信息不存在，删除失败-----------");
            }
        } else{
            System.out.println("---------------放弃删除房屋---------------");
        }
    }

    // 编写addHouse()，创建House对象，调用add方法
    public void addHouse(){
        System.out.println("-----------------添加房屋-----------------");
        System.out.print("姓名：");
        String name = Utility.readString(8);
        System.out.print("电话：");
        String phone = Utility.readString(12);
        System.out.print("地址：");
        String address = Utility.readString(16);
        System.out.print("月租：");
        int rent = Utility.readInt();
        System.out.print("状态（已出租/未出租）：");
        String state = Utility.readString(3);
        // 创建一个新的house对象，注意id是系统分配的，用户不能输入
        House newHouse = new House(0, name, phone, address, rent, state);
        if (houseService.add(newHouse)){
            System.out.println("-----------------添加成功-----------------");
        } else{
            System.out.println("-----------------添加失败-----------------");
        }
    }
    // 编写listHouses()，显示房屋列表
    public void listHouses(){
        System.out.println("-----------------房屋列表-----------------");
        System.out.println("编号\t\t房主\t\t电话\t\t地址\t\t月租\t\t状态（已出租/未出租）");
        House[] houses = houseService.list();
        for (int i = 0; i < houses.length; i++) {
            if (houses[i] == null){
                break;
            }
            System.out.println(houses[i]);
        }
        System.out.println("--------------房屋列表显示完毕--------------");
    }

    public void  exit(){
        char c = Utility.readConfirmSelection();
        if (c == 'Y'){
            loop = false;
        }
    }

    // 编写主菜单
    public void mainMenu(){
        do{
            System.out.println("\n---------------房屋出租系统---------------");
            System.out.println("\t\t\t1 新 增 房 源");
            System.out.println("\t\t\t2 查 找 房 屋");
            System.out.println("\t\t\t3 删 除 房 屋");
            System.out.println("\t\t\t4 修 改 房 屋 信 息");
            System.out.println("\t\t\t5 显 示 房 屋 列 表");
            System.out.println("\t\t\t6 退      出");
            System.out.println("请输入你的选择(1-6)：");
            key = Utility.readChar();
            switch (key){
                case '1':
                    addHouse();
                    break;
                case '2':
                    findHouse();
                    break;
                case '3':
                    delHouse();
                    break;
                case '4':
                    changeHouse();
                    break;
                case '5':
                    listHouses();
                    break;
                case '6':
                    exit();
                    break;
                default:
            }
        } while (loop);
    }
}

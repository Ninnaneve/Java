package com.user.smallcharge;

/*
化繁为简.
1) 先完成显示菜单，并可以选择
2) 完成零钱通明细
    思路：1. 可以把收入和消费保存到数组 2. 可以使用对象 3. 简单的话可以使用String拼接
3) 完成收益入账（完成功能驱动程序员增加新的变化和代码）
    思路：定义新的变量，用于记录入账的钱，当前余额和入账时间
4) 消费
    思路：定义新变量，保存消费的原因
5) 退出
 */

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class SmallChargeSys {
    public static void main(String[] args) {
        //1
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        String key = "";

        //2
        String details = "-------------零钱通明细-------------";

        //3
        double money = 0;
        double balance = 0;
        Date date = null; // 表示日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); // 用于日期格式化

        //4
        String note = "";


        do{
            System.out.println("\n=============零钱通菜单=============");
            System.out.println("\t\t\t1 零钱通明细");
            System.out.println("\t\t\t2 收益入账");
            System.out.println("\t\t\t3 消费");
            System.out.println("\t\t\t4 退     出");
            System.out.println("请选择（1-4）：");
            key = scanner.next();

            // 使用switch分支控制
            switch (key){
                case "1":
                    System.out.println(details);
                    break;
                case "2":
                    System.out.println("收益入账金额：");
                    money = scanner.nextDouble();
                    /*
                    7) 当收益入账和消费时，判断金额是否合理，并给出相应的提示
                        思路：找出不正确的金额条件，然后给出提示，就直接break
                     */
                    if (money <= 0){
                        System.out.println("收益金额需要大于0");
                        break;
                    }
                    balance += money;
                    // 拼接收益入账信息到details
                    date = new Date(); // 获取当前日期
                    details += "\n收益入账\t+" + money + "\t" + sdf.format(date) + "\t" + balance;
                    break;
                case "3":
                    System.out.println("消费金额：");
                    money = scanner.nextDouble();
                    if (money <= 0 || money > balance){
                        System.out.println("你的消费金额应该在0-" + balance);
                        break;
                    }
                    System.out.println("消费说明：");
                    note = scanner.next();
                    balance -= money;
                    // 拼接收益入账信息到details
                    date = new Date(); // 获取当前日期
                    details += "\n" + note + "\t-" + money + "\t" + sdf.format(date) + "\t" + balance;
                    break;
                case "4":
                    /*
                    6) 用户输入4退出时，给出提示"你确定要退出吗？y/n"，必须输入正确的的y/n，否则循环输入指令，直到输入y或者n
                        思路：
                        1. 定义一个变量choice，接收用户输入
                        2. 使用while + break，来处理接收到的输入
                        3. 退出while后，再判断choice是y还是n，就可以决定是否退出
                        建议：一段代码，完成一个小功能，尽量不要混在一起
                     */
                    String choice = "";
                    while (true){
                        System.out.println("你确定要退出吗？y/n");
                        choice = scanner.next();
                        if ("y".equals(choice) || "n".equals(choice)){
                            break;
                        }
                    }
                    // 当用户退出while后，进行判断
                    if ("y".equals(choice)){
                        loop = false;
                    }
                    break;
                default:
                    System.out.println("选择有误，请重新选择");
            }
        } while (loop);
        System.out.println("------您已退出了零钱通项目------");
    }
}

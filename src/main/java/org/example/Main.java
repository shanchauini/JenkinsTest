package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int z = scanner.nextInt();
        int[] a = new int[10]; // 使用索引1-9

        if (x == 0) {
            System.out.println("No!!!");
            return;
        }

        int ans = 0;
        for (int i = 1; i <= 1000 / x; i++) {
            int k1 = x * i;
            int k2 = y * i;
            int k3 = z * i;

            if (k2 > 1000 || k3 > 1000)
                break;

            // 重置计数数组
            for (int j = 1; j <= 9; j++) {
                a[j] = 0;
            }

            // 分解k1的各位数字
            int temp = k1;
            while (temp != 0) {
                int digit = temp % 10;
                a[digit]++;
                temp /= 10;
            }

            // 分解k2的各位数字
            temp = k2;
            while (temp != 0) {
                int digit = temp % 10;
                a[digit]++;
                temp /= 10;
            }

            // 分解k3的各位数字
            temp = k3;
            while (temp != 0) {
                int digit = temp % 10;
                a[digit]++;
                temp /= 10;
            }

            // 检查1-9是否各出现一次
            boolean valid = true;
            for (int j = 1; j <= 9; j++) {
                if (a[j] != 1) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                ans++;
                System.out.println(k1 + " " + k2 + " " + k3);
            }
        }

        if (ans == 0) {
            System.out.println("No!!!");
        }
    }
}
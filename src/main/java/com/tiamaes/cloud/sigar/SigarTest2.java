package com.tiamaes.cloud.sigar;

import org.hyperic.sigar.*;

import java.text.DecimalFormat;

/**
 * @author yangqigong
 * @version 1.0
 * @date 2020/11/20 14:57
 */
public class SigarTest2 {

    public static void main(String[] args) {
        try {
            //set sigar variable
            SigarUtil.setSystemVariable();
            Sigar sigar = new Sigar();
            CpuPerc cpu = sigar.getCpuPerc();
            System.out.println(String.valueOf(cpu.getCombined()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 打印cpu的使用率
    private void printCpuPerc(CpuPerc cpuPerc) {
        // 用户使用率
        String cpuPercUser = CpuPerc.format(cpuPerc.getUser());
        // 用户使用率
        String cpuPercSys = CpuPerc.format(cpuPerc.getSys());
        // 用户使用率
        String cpuPercWait = CpuPerc.format(cpuPerc.getWait());
        // 用户使用率
        String cpuPercNice = CpuPerc.format(cpuPerc.getNice());
        // 用户使用率
        String cpuPercIdle = CpuPerc.format(cpuPerc.getIdle());
        // 用户使用率
        String cpuPercCombined = CpuPerc.format(cpuPerc.getCombined());

        System.out.println("用户使用率:" + cpuPercUser);
        // 系统使用率
        System.out.println("系统使用率:" + cpuPercSys);
        // 当前等待率
        System.out.println("当前等待率:" + cpuPercWait);
        System.out.println("Nice :" + cpuPercNice);
        // 当前空闲率
        System.out.println("当前空闲率:" + cpuPercIdle);
        // 总的使用率
        System.out.println("总的使用率:" + cpuPercCombined);
        System.out.println("**************");
    }


    /**
     *  物理内存信息
     *  */
    public void getPhyssicalMemory() {
        DecimalFormat df = new DecimalFormat("#0.00");
        Sigar sigar = new Sigar();
        Mem mem;
        try {
            mem = sigar.getMem();
            // 内存总量
            String memTotal = df.format((float) mem.getTotal() / 1024 / 1024 / 1024) + "G";
            // 当前内存使用量
            String memUsed = df.format((float) mem.getUsed() / 1024 / 1024 / 1024) + "G";
            // 当前内存剩余量
            String memFree = df.format((float) mem.getFree() / 1024 / 1024 / 1024) + "G";

            // 系统页面文件交换区信息
            Swap swap = sigar.getSwap();
            // 交换区总量
            String swapTotal = df.format((float) swap.getTotal() / 1024 / 1024 / 1024) + "G";
            // 当前交换区使用量
            String swapUsed = df.format((float) swap.getUsed() / 1024 / 1024 / 1024) + "G";
            // 当前交换区剩余量
            String swapFree = df.format((float) swap.getFree() / 1024 / 1024 / 1024) + "G";

            // 打印信息
            System.out.println("内存总量：" + memTotal);
            System.out.println("当前内存使用量：" + memUsed);
            System.out.println("当前内存剩余量：" + memFree);
            System.out.println("交换区总量：" + swapTotal);
            System.out.println("当前交换区使用量：" + swapUsed);
            System.out.println("当前交换区剩余量：" + swapFree);

        } catch (SigarException e) {
            e.printStackTrace();
        }
    }
}

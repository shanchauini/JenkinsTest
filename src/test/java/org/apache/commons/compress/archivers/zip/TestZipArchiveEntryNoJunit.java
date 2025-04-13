package org.apache.commons.compress.archivers.zip;

import java.util.Calendar;
import java.util.Date;

public class TestZipArchiveEntryNoJunit {
    public static void main(String[] args)throws Exception{

        //创建一个新的zip目录条目
        ZipArchiveEntry entry = new ZipArchiveEntry("newdir/");

        //设置目录大小为0，并测试是否为0
        entry.setSize(0);

        if(entry.getSize()==0)
        {
            System.out.println("目录大小正确,测试通过");

        }
        else {
            System.out.println("目录大小错误，测试失败");
        }

        //设置条目最新更新时间为2025-01-01，并测试是否正确
        entry.setTime(createDate(2025,1,1).getTime());
        Date date = entry.getLastModifiedDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        if(cal.get(Calendar.YEAR)==2025)
        {
            System.out.println("日期正确，测试通过");
        }
        else {
            System.out.println("日期错误，测试失败");
        }

        //测试该条目是否为目录，覆盖缺陷方法
        if(entry.isDirectory())
        {
            System.out.println("目录判断正确，测试通过");
        }
        else
        {
            System.out.println("目录判断失败，测试失败");
        }
    }

     static Date createDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day);
        return cal.getTime();
    }
}

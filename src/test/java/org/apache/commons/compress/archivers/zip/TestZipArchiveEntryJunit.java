package org.apache.commons.compress.archivers.zip;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.util.Calendar;
import java.util.Date;

public class TestZipArchiveEntryJunit {
    private static ZipArchiveEntry Jentry;

    //实例化一个目录对象
    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        Jentry = new ZipArchiveEntry("newdir/");
    }

    //测试目录大小设置是否正确
    @Tag("test_Entry")
    @Test
    public void TestSize() {
        Jentry.setSize(0);
        assert Jentry.getSize() == 0;
    }

    //测试目录最新更新时间是否正确
    @Tag("test_Entry")
    @Test
    public void TestGetLastModified() {
        Jentry.setTime(createDate(2025,1,1).getTime());
        Date date = Jentry.getLastModifiedDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        assert cal.get(Calendar.YEAR) == 2025;
    }

    //测试目录判断是否正确
    @Tag("test_Entry")
    @Test
    public void TestIsDirectory() {
        assert Jentry.isDirectory();
    }

     private Date createDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day);
        return cal.getTime();
    }
}

package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;


public class MainTest {
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream outputCapture;

    // 测试准备：重定向输入输出
    @BeforeEach
    void setupStreams() {
        outputCapture = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputCapture));
    }

    // 测试清理：恢复原始输入输出
    @AfterEach
    void restoreStreams() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    // 测试用例1：x=0 应该立即返回错误
    @Test
    void testZeroXCase() throws Exception {
        provideInput("0 10 20\n");
        Main.main(new String[0]);
        assertOutputMatches("No!!!");
    }

    // 测试用例2：y值超过阈值应该无解
    @Test
    void testExceedingLimitCase() throws Exception {
        provideInput("1 1001 1002\n");
        Main.main(new String[0]);
        assertOutputMatches("No!!!");
    }

    // 测试用例3：验证标准正确答案
    @Test
    void testValidSolutionCase() throws Exception {
        provideInput("1 2 3\n");
        Main.main(new String[0]);

        String output = getCleanOutput();
        // 验证所有可能的正确答案
        assertTrue(output.contains("192 384 576"));
        assertTrue(output.contains("219 438 657"));
        assertTrue(output.contains("273 546 819"));
        assertTrue(output.contains("327 654 981"));
        // 验证没有错误提示
        assertFalse(output.contains("No!!!"));
    }

    @Test
    void testLoopTerminationByCondition() {
        // 选择 x=1，使得 1000/x=1000，循环会执行到 i=1001 时终止
        provideInput("1 1 1\n"); // 任何 y, z 均可，因为循环会因 i>1000 退出
        Main.main(new String[0]);

        // 验证程序未崩溃且无输出（或符合预期输出）
        String output = getCleanOutput();
        assertTrue(output.isEmpty() || output.contains("No!!!"));
    }

    // 辅助方法：模拟输入
    private void provideInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }

    // 辅助方法：获取清理后的输出
    private String getCleanOutput() {
        return outputCapture.toString()
                .replace("\r\n", "\n")  // 统一换行符
                .trim();
    }

    // 辅助方法：验证输出匹配
    private void assertOutputMatches(String expected) {
        assertEquals(expected, getCleanOutput());
    }
}
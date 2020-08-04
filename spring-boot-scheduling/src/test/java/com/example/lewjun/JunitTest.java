package com.example.lewjun;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class JunitTest {
    @Test
    public void testTimer1() throws InterruptedException {
        System.out.println("1-----> " + LocalDateTime.now());
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("2-----> " + LocalDateTime.now());
            }
        }, 2000);// 延迟两秒后执行

        System.out.println("3-----> " + LocalDateTime.now());
        // 10秒后关闭
        Thread.sleep(10000);

// out:>
//        1-----> 2020-08-04T13:41:42.066
//        3-----> 2020-08-04T13:41:42.067
//        2-----> 2020-08-04T13:41:44.068
    }

    @Test
    public void testTimer2() throws InterruptedException {
        final int[] i = {0};
        System.out.println("1-----> " + LocalDateTime.now());
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("2-----> " + LocalDateTime.now());
                System.out.println("i[0]=" + i[0]);
                if (++i[0] == 4) {
                    // 当 i = 4时，取消定时任务
                    timer.cancel();
                }
            }
        }, 2000, 1500);// 延迟2秒后执行，然后间隔1.5秒继续执行

        System.out.println("3-----> " + LocalDateTime.now());
        // 10秒后关闭
        Thread.sleep(15000);

//        1-----> 2020-08-04T14:34:29.795
//        3-----> 2020-08-04T14:34:29.810
//        2-----> 2020-08-04T14:34:31.835
//        i[0]=0
//        2-----> 2020-08-04T14:34:33.353
//        i[0]=1
//        2-----> 2020-08-04T14:34:34.853
//        i[0]=2
//        2-----> 2020-08-04T14:34:36.353
//        i[0]=3
    }

}

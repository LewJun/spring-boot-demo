package com.example.lewjun;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.example.lewjun.bean.DemoData;
import com.example.lewjun.listener.DemoDataListener;
import com.example.lewjun.util.TestFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Slf4j
public class JunitTest {
    final String fileName = TestFileUtil.getPath() + File.separator + "demo.xls";

    final File file = new File(fileName);

    @Test
    public void testSimpleReadAllSheet() {
        // 会读取所有的sheet
        EasyExcel.read(file, DemoData.class, new DemoDataListener()).doReadAll();
        log.info("-------");
    }

    @Test
    public void simpleRead() {
        // 写法2：
        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).build();
            final ReadSheet readSheet = EasyExcel.readSheet(1).build();
            excelReader.read(readSheet);
        } finally {
            if (excelReader != null) {
                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                excelReader.finish();
            }
        }
    }

    @Test
    public void write() {
        EasyExcel.write(new File(TestFileUtil.getPath() + File.separator + System.currentTimeMillis() + "_demo.xls"), DemoData.class)
                .sheet("Sheet3").doWrite(data());
    }

    private List<DemoData> data() {
        final List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final DemoData data = new DemoData();
            data.setId(i);
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(new Random().nextDouble() * i);
            list.add(data);
        }
        return list;
    }

}

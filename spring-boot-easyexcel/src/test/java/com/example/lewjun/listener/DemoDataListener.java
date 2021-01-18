package com.example.lewjun.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.lewjun.bean.DemoData;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DemoDataListener extends AnalysisEventListener<DemoData> {
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<DemoData> list = new ArrayList<>();

    @Override
    public void invoke(final DemoData demoData, final AnalysisContext analysisContext) {
        log.info("解析到一条数据 {}", demoData);
        list.add(demoData);
        if (list.size() >= BATCH_COUNT) {
            log.info("save");
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(final AnalysisContext analysisContext) {
        log.info("所有数据解析完毕");
    }
}

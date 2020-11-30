package com.example.lewjun;

import java.text.DecimalFormat;

public class NutrientContent {
    /**
     * 可食用部分
     */
    private final float eatable;

    /**
     * 为100克食部（即可食用部分）的营养素含量
     */
    private final float contentIn100;

    public NutrientContent(final float eatable, final float contentIn100) {
        this.eatable = eatable;
        this.contentIn100 = contentIn100;
    }

    /**
     * 得到实际用量对应的营养素含量
     *
     * @param weight 实际用量
     * @return 营养素含量
     */
    public float calc(final float weight) {
        return Float.parseFloat(new DecimalFormat("0.00")
                .format(weight / 100 * contentIn100 * eatable / 100)
        );
    }
}
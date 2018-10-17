package com.work.manager.util;

import java.math.BigDecimal;

/**
 * 统一的数字处理
 *
 * @author: taotao
 * @Date: 2018/5/6
 **/
public class NumberUtil {

    /**
     * 传一个浮点数，返回 保留两位小数
     * @param number
     * @return
     */
    public static Float twoDecimalDigiti(Float number){
        BigDecimal b = new BigDecimal(number);
        number = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
        return number;
    }
}

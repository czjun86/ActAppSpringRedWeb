package com.ddb.act.red.util;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

/**
 * 操作概率工具类
 * 
 * @类名 LotteryProbabilityUtil
 * @作者 JiangQT
 * @时间 2014年12月23日 上午11:39:38
 */
public class LotteryProbabilityUtil {
	public int getRandomIndex(List<BigDecimal> rates) {
		Random random = new Random();
		BigDecimal ranNum = new BigDecimal(random.nextDouble());

		// double sumRate = 0;
		BigDecimal sumRate = new BigDecimal(0.0);
		for (BigDecimal rate : rates) {
			sumRate = sumRate.add(rate);
		}
		BigDecimal minVal = new BigDecimal(0.0);
		BigDecimal maxVal = new BigDecimal(0.0);
		for (int i = 0; i < rates.size(); i++) {
			BigDecimal rangeVal = rates.get(i).divide(sumRate,
					BigDecimal.ROUND_HALF_EVEN);
			maxVal = minVal.add(rangeVal);
			boolean exp1 = false;
			if (i == 0) {
				exp1 = (minVal.compareTo(ranNum) <= 0);
			} else {
				exp1 = (minVal.compareTo(ranNum) < 0);
			}
			if (exp1 && (ranNum.compareTo(maxVal) <= 0)) {
				return i;
			}
			minVal = minVal.add(rangeVal);
		}
		throw new RuntimeException("Get Random Index Error");
	}
}

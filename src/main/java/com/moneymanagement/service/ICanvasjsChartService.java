package com.moneymanagement.service;

import java.util.List;
import java.util.Map;

public interface ICanvasjsChartService {
	List<List<Map<Object, Object>>> getCanvasjsPieChartData(String type);
	List<List<Map<Object, Object>>> getCanvasjsColumnChartData(int year);
}

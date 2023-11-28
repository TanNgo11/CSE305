package com.moneymanagement.controller.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.moneymanagement.service.ICanvasjsChartService;

@Controller
public class ChartController {
	@Autowired
	private ICanvasjsChartService canvasjsChartService;

	@RequestMapping(value = { "/chart/piechart" }, method = RequestMethod.GET)
	public ModelAndView chartPage(@RequestParam String type) {
		ModelAndView mav = new ModelAndView("web/piechart");
		List<List<Map<Object, Object>>> canvasjsDataList = canvasjsChartService.getCanvasjsPieChartData(type);
		mav.addObject("dataPointsList", canvasjsDataList);

		return mav;
	}

	@RequestMapping(value = { "/chart/columnchart/{year}" }, method = RequestMethod.GET)
	public ModelAndView chartPage(@PathVariable int year) {
		ModelAndView mav = new ModelAndView("web/columnchart");
		List<List<Map<Object, Object>>> canvasjsDataList = canvasjsChartService.getCanvasjsColumnChartData(year);
		mav.addObject("dataPointsList", canvasjsDataList);

		return mav;
	}
}

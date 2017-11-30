package com.sop.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sop.util.NoteResult;
import com.sop.service.StationService;


@Controller
@RequestMapping("/station")
public class StationController {
	@Resource
	private StationService StationService;
	
	@RequestMapping("/findAll.do")
	@ResponseBody
	public NoteResult findAllStations() throws Exception{
		NoteResult result = StationService.findAllStations();
		return result;	
	}
}

package com.sop.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sop.service.StaffService;
import com.sop.util.NoteResult;


@Controller
@RequestMapping("/staff")
public class StaffController {
	@Resource
	private StaffService staffService;
	
	@RequestMapping("/findAll.do")
	@ResponseBody
	public NoteResult findAllStaffs() throws Exception{
		NoteResult result = staffService.findAllStaffs();
		return result;	
	}
}

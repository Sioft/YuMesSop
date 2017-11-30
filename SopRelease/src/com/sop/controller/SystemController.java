package com.sop.controller;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sop.service.SystemService;
import com.sop.util.NoteResult;




@Controller
@RequestMapping("/system")
public class SystemController {
	@Resource
	private SystemService sysService;
	
	/**
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findDictType.do")  
	@ResponseBody
    public NoteResult findDictType() throws Exception {  
		NoteResult result = new NoteResult();
		result = sysService.findDictType();
		return result;	  
    }
	
	/**
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findDictData.do")  
	@ResponseBody
    public NoteResult findDictType(int dtpId) throws Exception {  
		NoteResult result = new NoteResult();
		System.out.println("findDictData参数:");
		System.out.println("dtpId:"+dtpId);
		result = sysService.findDictData(dtpId);
		return result;	  
    }
}

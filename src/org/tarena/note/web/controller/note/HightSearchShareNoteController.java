package org.tarena.note.web.controller.note;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.dao.NoteMapperDao;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.entity.SearchBean;

@Controller
@RequestMapping("/note")
public class HightSearchShareNoteController {
	@Resource
	private NoteMapperDao noteDao;
	
	@RequestMapping("/hightsearch.form")
	@ResponseBody
	public NoteResult execute(
		String title,String beginDate,String endDate){
		SearchBean bean = new SearchBean();
		bean.setTitle(title);
		if(beginDate!=null && !"".equals(beginDate)){
			long begin = Date.valueOf(beginDate).getTime();
			bean.setBeginDate(begin);
		}
		if(endDate!=null && !"".equals(endDate)){
			long end = Date.valueOf(endDate).getTime();
			bean.setEndDate(end);
		}
		
		List<Map> list = noteDao.searchShare(bean);
		NoteResult result = new NoteResult();
		result.setStatus("0");
		result.setMsg("³É¹¦");
		result.setData(list);
		return result;
	}
	
	
}

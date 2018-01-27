package org.tarena.note.web.controller.note;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.NoteService;

@Controller
@RequestMapping("/note")
public class ShareNoteController {
	@Resource
	private NoteService noteService;
	
	@RequestMapping("/share")//等价/share.扩展名
	@ResponseBody
	public NoteResult execute(String noteId){
		NoteResult result = 
			noteService.share(noteId);
		return result;
	}
	
	
}

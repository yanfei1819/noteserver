package org.tarena.note.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tarena.note.dao.NoteMapperDao;
import org.tarena.note.dao.ShareMapperDao;
import org.tarena.note.entity.Note;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.entity.Share;
import org.tarena.note.util.MessageUtil;
import org.tarena.note.util.NoteParams;

@Service
@Transactional//等价于每个方法前追加
public class NoteServiceImpl implements NoteService{

	@Resource
	private NoteMapperDao noteDao;
	@Resource
	private ShareMapperDao shareDao;
	
	public NoteResult loadNoteByBookId(String bookId) {
		NoteResult result = new NoteResult();
		if(bookId==null || "".equals(bookId)){
			result.setStatus("1");
			result.setMsg("没有数据");
			return result;
		}
		
		List<Note> list = noteDao.findByBookId(bookId);
		result.setStatus("0");
		result.setMsg("成功");
		result.setData(list);
		return result;
	}

	public NoteResult add(Note note) {
		NoteResult result = new NoteResult();
		//TODO 检测笔记是否重名
		//添加
		String id = MessageUtil.createId();
		note.setCn_note_id(id);//设置主键
		note.setCn_note_create_time(
				System.currentTimeMillis());//设置创建时间
		note.setCn_note_status_id(
				NoteParams.NORMAL_NOTE);//设置状态
		note.setCn_note_type_id(null);//设置笔记类型
		note.setCn_note_body("");//设置内容
		
		noteDao.save(note);//保存
		result.setStatus("0");
		result.setMsg("成功");
		result.setData(id);//返回新追加的笔记ID
		return result;
	}

	public NoteResult update(Note note) {
		NoteResult result = new NoteResult();
		//设置更新时间
		note.setCn_note_last_modify_time(
				System.currentTimeMillis());
		noteDao.update(note);//执行更新
		result.setStatus("0");
		result.setMsg("保存成功");
		return result;
	}

	public NoteResult loadNoteById(String noteId) {
		NoteResult result = new NoteResult();
		if(noteId==null || "".equals(noteId)){
			result.setStatus("1");
			result.setMsg("没数据");
			return result;
		}
		Note note = noteDao.findById(noteId);
		result.setStatus("0");
		result.setMsg("成功");
		result.setData(note);
		return result;
	}

	public NoteResult updateStatus(String noteId, String statusId) {
		NoteResult result = new NoteResult();
		Map<String,Object> params = 
				new HashMap<String, Object>();
		params.put("id", noteId);
		params.put("status", statusId);
		noteDao.updateStatus(params);//设置状态
		//TODO 如果是删除,要清除分享表中记录
		result.setStatus("0");
		result.setMsg("成功");
		return result;
	}

	public NoteResult share(String noteId) {
		NoteResult result = new NoteResult();
		//提取笔记信息
		Note note = noteDao.findById(noteId);
		//插入到分享笔记表
		if(note != null){
			Share share = new Share();
			String id = MessageUtil.createId();
			share.setCn_share_id(id);
//			share.setCn_note_title(note.getCn_note_title());
//			share.setCn_note_body(note.getCn_note_body());
//			share.setCn_note_id(note.getCn_note_id());
			//批量属性复制(名称一致)
			BeanUtils.copyProperties(note, share);
			shareDao.save(share);
			result.setStatus("0");
			result.setMsg("分享成功");
		}else{
			result.setStatus("1");
			result.setMsg("分享失败");
		}
		return result;
	}

	public NoteResult searchShare(String title) {
		NoteResult result = new NoteResult();
		if(title == null){
			title = "";
		}
		String param = "%"+title+"%";
		List<Share> list = 
			shareDao.findLikeTitle(param);
		result.setStatus("0");
		result.setMsg("成功");
		result.setData(list);
		return result;
	}

}

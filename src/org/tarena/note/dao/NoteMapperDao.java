package org.tarena.note.dao;

import java.util.List;
import java.util.Map;

import org.tarena.note.entity.Note;
import org.tarena.note.entity.SearchBean;
import org.tarena.note.entity.Share;

public interface NoteMapperDao {
	//动态更新--动态SQL应用
	public int dynamicUpdateNote(Note note);
	//组合查询--动态SQL应用
	public List<Map> searchShare(SearchBean bean);
	
	public Note findById(String id);
	public List<Note> findByBookId(String bookId);
	public int save(Note note);
	public int update(Note note);
	/**
	 * 更新笔记的状态
	 * @param params id:笔记本ID;status:笔记状态
	 * @return
	 */
	public int updateStatus(Map<String,Object> params);
}

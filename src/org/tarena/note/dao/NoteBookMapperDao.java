package org.tarena.note.dao;

import java.util.List;
import java.util.Map;

import org.tarena.note.entity.NoteBook;

public interface NoteBookMapperDao {
	public List<NoteBook> findByUserId(String userId);
	
	public int save(NoteBook book);
	/**
	 * ����bookName��userId����ֵ
	 * @param data
	 * @return
	 */
	public NoteBook findByName(Map<String,Object> data);
}

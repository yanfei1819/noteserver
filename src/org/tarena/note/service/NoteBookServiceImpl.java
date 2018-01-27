package org.tarena.note.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tarena.note.dao.NoteBookMapperDao;
import org.tarena.note.entity.NoteBook;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.util.MessageUtil;

@Service("bookService")
public class NoteBookServiceImpl implements NoteBookService{
	@Resource
	private NoteBookMapperDao bookDao;
	
	@Transactional
	public NoteResult loadListByUserId(String userId) {
		NoteResult result = new NoteResult();
		if(userId == null || "".equals(userId)){
			result.setStatus("1");
			result.setMsg("û���û�ID");
			result.setData(null);
			return result;
		}
		List<NoteBook> list = 
				bookDao.findByUserId(userId);
		result.setStatus("0");
		result.setMsg("�ɹ�");
		result.setData(list);
		return result;
	}

	@Transactional
	public NoteResult add(NoteBook book) {
		NoteResult result = new NoteResult();
		//���ʼǱ������Ƿ��ظ�
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("userId", book.getCn_user_id());
		params.put("bookName", book.getCn_notebook_name());
		NoteBook book_temp = 
			bookDao.findByName(params);
		if(book_temp != null){
			result.setStatus("1");
			result.setMsg("�Ѵ��ڸ����ƱʼǱ�");
			return result;
		}
		//���ظ�׷��
		String id = MessageUtil.createId();
		book.setCn_notebook_id(id);//��������ID
		Timestamp time = 
			new Timestamp(System.currentTimeMillis());
		book.setCn_notebook_createtime(time);//���ô���ʱ��
		bookDao.save(book);
		result.setStatus("0");
		result.setMsg("�ɹ�");
		result.setData(id);//���ʼǱ�ID����
		return result;
	}

}

package org.tarena.note.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tarena.note.dao.NoteBookMapperDao;
import org.tarena.note.dao.UserMapperDao;
import org.tarena.note.entity.NoteBook;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.entity.User;
import org.tarena.note.util.MessageUtil;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Resource
	private UserMapperDao userDao;
	@Resource
	private NoteBookMapperDao bookDao;
	
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)//只读事务
	public NoteResult checkLogin(
			String name, String password) {
		NoteResult result = new NoteResult();
		
		User user = userDao.findByName(name);
		if(user == null){
			result.setStatus("1");
			result.setMsg("用户名不存在");
			return result;
		}
		//将用户输入的密码加密
		String md_password = MessageUtil.md5(password);
		//然后与数据库中的密码比对
		if(!user.getCn_user_password().equals(md_password)){
			result.setStatus("2");
			result.setMsg("密码不正确");
			return result;
		}
		result.setStatus("0");
		result.setMsg("登录成功");
		//生成一个令牌号,将用户ID和令牌号一起返回给客户
		String tokenId = MessageUtil.createToken();
		Map<String,Object> data = 
				new HashMap<String, Object>();
		data.put("token", tokenId);
		data.put("userId", user.getCn_user_id());
		result.setData(data);
		return result;
	}

	/**
	 * 适用HTTP Basic Authentication模式传输 
	 */
	@Transactional(readOnly=true,rollbackFor=IOException.class)
	public NoteResult checkLogin(String base64Msg) {
		String name = "";
		String password = "";
		try{
			//对base64进行解析
			String base64_msg = base64Msg.split(" ")[1];
			//进行base64解码,获取"用户名:密码"明文
			String msg = MessageUtil.base64Decode(base64_msg);
			System.out.println("msg"+msg);
			//截取用户和密码
			name = msg.split(":")[0];
			password = msg.split(":")[1];
		}catch(Exception ex){
			NoteResult result = new NoteResult();
			result.setStatus("-1");
			result.setMsg("不合法请求");
			return result;
		}
		//调用用户名和密码检测的checkLogin方法
		return checkLogin(name,password);
	}

	
	public NoteResult regist(User user) {
		NoteResult result = new NoteResult();
		//检测用户名是否重复
		User user_tmp = userDao.findByName(
				user.getCn_user_name());
		if(user_tmp != null){
			result.setStatus("1");
			result.setMsg("用户名已占用");
			return result;
		}
		//生成主键ID
		String id = MessageUtil.createId();
		user.setCn_user_id(id);
		//密码加密
		String md5_pwd = MessageUtil.md5(
				user.getCn_user_password());
		user.setCn_user_password(md5_pwd);
		userDao.save(user);
		//模拟异常,测试事务是否起作用
		String ss = null;
		ss.length();
		//为用户创建一个"默认笔记本"
		NoteBook book = new NoteBook();
		book.setCn_notebook_name("默认笔记本");
		book.setCn_user_id(id);//设置用户ID
		String bookId = MessageUtil.createId();
		book.setCn_notebook_id(bookId);//设置笔记本ID
		Timestamp time = new Timestamp(
				System.currentTimeMillis());//设置创建时间
		book.setCn_notebook_createtime(time);
		bookDao.save(book);//生成默认笔记本
		//设置状态信息
		result.setStatus("0");
		result.setMsg("注册成功");
		return result;
	}
}

package org.tarena.note.aop.demo1;

public class SimpleServiceImpl implements SimpleService{

	public void add() {
		System.out.println("执行添加操作");
	}

	public void delete() {
		System.out.println("执行删除操作");
	}

	public void findAll() {
		System.out.println("执行查询所有记录操作");
	}

	public void findById() {
		System.out.println("执行查询某个记录操作");
	}

	public void update() {
		System.out.println("执行更新操作");
	}

}

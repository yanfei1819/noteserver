package org.tarena.note.aop.demo2;

import org.springframework.stereotype.Service;

@Service("simpleService")
public class SimpleServiceImpl implements SimpleService{

	public void add() {
		System.out.println("ִ����Ӳ���");
	}

	public void delete() {
		System.out.println("ִ��ɾ������");
	}

	public void findAll() {
		System.out.println("ִ�в�ѯ���м�¼����");
	}

	public void findById() {
		System.out.println("ִ�в�ѯĳ����¼����");
	}

	public void update() {
		System.out.println("ִ�и��²���");
	}

}

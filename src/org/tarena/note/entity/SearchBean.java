package org.tarena.note.entity;

import java.io.Serializable;

public class SearchBean implements Serializable{
	private String title;//����
	private Long beginDate;//��ʼ����
	private Long endDate;//��������
	public String getTitle() {
		if(title == null){
			title = "";
		}
		return "%"+title.trim()+"%";
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Long beginDate) {
		this.beginDate = beginDate;
	}
	public Long getEndDate() {
		return endDate;
	}
	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}
	
}

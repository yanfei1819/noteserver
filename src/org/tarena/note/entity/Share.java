package org.tarena.note.entity;

import java.io.Serializable;

public class Share implements Serializable{
	private String cn_share_id;
	private String cn_note_id;
	private String cn_note_title;
	private String cn_note_body;
	
	public String getCn_share_id() {
		return cn_share_id;
	}
	public void setCn_share_id(String cnShareId) {
		cn_share_id = cnShareId;
	}
	public String getCn_note_id() {
		return cn_note_id;
	}
	public void setCn_note_id(String cnNoteId) {
		cn_note_id = cnNoteId;
	}
	public String getCn_note_title() {
		return cn_note_title;
	}
	public void setCn_note_title(String cnNoteTitle) {
		cn_note_title = cnNoteTitle;
	}
	public String getCn_note_body() {
		return cn_note_body;
	}
	public void setCn_note_body(String cnNoteBody) {
		cn_note_body = cnNoteBody;
	}

}

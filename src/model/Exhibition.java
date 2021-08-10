package model;

import java.util.Date;

public class Exhibition {
	private int id; //���� id
	private String userNickname; //�г���? id? email? ���� ����
	private String title; //������ title
	private String description; //���� ����
	private Date start_date; //���� ������
	private Date end_date; //���� ������
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserNickname() {
		return userNickname;
	}
	
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getStart_date() {
		return start_date;
	}
	
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	
	public Date getEnd_date() {
		return end_date;
	}
	
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	
	@Override
	public String toString() {
		return "Exhibition [exhibitionId=" + id + ", userNickname=" + userNickname + ", title=" + title
				+ ", description=" + description + ", start_date=" + start_date + ", end_date=" + end_date + "]";
	}
}

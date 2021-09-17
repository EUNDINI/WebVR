package model;

public class Visit {
	
	private int visitId; // pk(�⺻Ű)
	private int userId; // user�� PK, FK
	private int exhibitionId; // exhibition�� PK, FK
	
	private String exhibitionTitle;  // exhibition�� �������� �� ���
	private String exhibitionDesc;
	
	public Visit() { }
	
	public Visit(int visitId, int userId, int exhibitionId) {
		super();
		this.visitId = visitId;
		this.userId = userId;
		this.exhibitionId = exhibitionId;
	}
	
	public Visit(int visitId, int userId, int exhibitionId, String exhibitionTitle, String exhibitionDesc) {
		super();
		this.visitId = visitId;
		this.userId = userId;
		this.exhibitionId = exhibitionId;
		this.exhibitionTitle = exhibitionTitle;
		this.exhibitionDesc = exhibitionDesc;
	}

	public int getVisitId() {
		return visitId;
	}
	public void setVisitId(int visitId) {
		this.visitId = visitId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getExhibitionId() {
		return exhibitionId;
	}
	public void setExhibitionId(int exhibitionId) {
		this.exhibitionId = exhibitionId;
	}	
	public String getExhibitionTitle() {
		return exhibitionTitle;
	}
	public void setExhibitionTitle(String exhibitionTitle) {
		this.exhibitionTitle = exhibitionTitle;
	}
	public String getExhibitionDesc() {
		return exhibitionDesc;
	}
	public void setExhibitionDesc(String exhibitionDesc) {
		this.exhibitionDesc = exhibitionDesc;
	}
}

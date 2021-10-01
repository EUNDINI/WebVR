package model;

public class Likes {
	
	private int likeId; // pk(�⺻Ű)
	private int userId; // user�� PK, FK
	private int artworkId; // artwork�� PK, FK
	
	private String artworkAddress; //address���� join �� ���
	private int exhibitionId;
	
	public Likes() { }
	
	public Likes(int likeId, int userId, int artworkId) {
		super();
		this.likeId = likeId;
		this.userId = userId;
		this.artworkId = artworkId;
	}
	public Likes(int likeId, int userId, int artworkId, String artworkAddress, int exhibitionId) {
		super();
		this.likeId = likeId;
		this.userId = userId;
		this.artworkId = artworkId;
		this.artworkAddress = artworkAddress;
		this.exhibitionId = exhibitionId;
	}
	
	public int getLikeId() {
		return likeId;
	}
	
	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getArtworkId() {
		return artworkId;
	}
	
	public void setArtworkId(int artworkId) {
		this.artworkId = artworkId;
	}

	public String getArtworkAddress() {
		return artworkAddress;
	}

	public void setArtworkAddress(String artworkAddress) {
		this.artworkAddress = artworkAddress;
	}

	public int getExhibitionId() {
		return exhibitionId;
	}

	public void setExhibitionId(int exhibitionId) {
		this.exhibitionId = exhibitionId;
	}
	
	
	
}

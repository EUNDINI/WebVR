package model;

public class User {
	private int userID;
	private String email;
	private String password;
	private String nickname;
	private String phone;
	private String favorites;
	
	// ����Ƚ�� ����Ƚ�� �����ε� �̰� ���߿� �ð����� ��������
//	private int exhbWatchCount;
//	private int exhbManageCount;
	
	public User() {}
	public User(int id, String em, String pwd, String nickname) {
		this.userID = id;
		this.email = em;
		this.password = pwd;
		this.nickname = nickname;
		
//		this.exhbWatchCount = 0;
//		this.exhbManageCount = 0;
	}
	public User(int id, String em, String pwd, String nickname, String phone, String favorites) {
		this.userID = id;
		this.email = em;
		this.password = pwd;
		this.nickname = nickname;
		this.phone = phone;
		this.favorites = favorites;
		
//		this.exhbWatchCount = 0;
//		this.exhbManageCount = 0;
	}
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getFavorites() {
		return favorites;
	}
	public void setFavorites(String favorites) {
		this.favorites = favorites;
	}
//	public int getExhbWatchCount() {
//		return exhbWatchCount;
//	}
//	public void setExhbWatchCount(int exhbWatchCount) {
//		this.exhbWatchCount = exhbWatchCount;
//	}
//	public int getExhbManageCount() {
//		return exhbManageCount;
//	}
//	public void setExhbManageCount(int exhbManageCount) {
//		this.exhbManageCount = exhbManageCount;
//	}
	
	
	//��й�ȣ �˻�
	public boolean matchPassword(String pwd) {
		if(pwd == null)
			return false;
		return this.password.equals(pwd);
	}
	
	//�̹� �ִ� �̸����̸� �̹� ������ ������ �ν�
	public boolean isSameUser(String em) {
		return email.equals(em);
	}
	
}

package entities;

public class Store {

	private int id;
	private String name;
	private String phone;
	private String email;
	private int userId;

	public Store() {
	}

	public Store(String name, String phone, String email) {
		this.name = name;
		this.phone = phone;
		this.email = email;
	}

	public Store(String name, String phone, String email, int userId) {
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Store [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + ", userId=" + userId
				+ "]";
	}

}

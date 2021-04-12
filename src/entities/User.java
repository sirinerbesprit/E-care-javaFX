package entities;

public class User {

	public enum Role {
		REPRESENTANT_PARA, REPRESENTANT_CLINIQUE, PATIENT, ADMIN
	}

	private int id;
	private String login;
	private String firstName;
	private String lastName;
	private String cin;
	private String password;
	private String role;
	private String gender;
	private String address;
	private String email;
	private String phoneNumber;
	private boolean verified;

	public static class Builder<T extends Builder<T>> {
		private int id;
		private String login;
		private String firstName;
		private String lastName;
		private String cin;
		private String password;
		private String role;
		private String gender;
		private String address;
		private String email;
		private String phoneNumber;
		private boolean verified;

		public Builder(String firstName, String lastName) {
			this.firstName = firstName;
			this.lastName = lastName;
		}

		public Builder<T> withLogin(String login) {
			this.login = login;
			return this;
		}

		public Builder<T> withId(int id) {
			this.id = id;
			return this;
		}

		public Builder<T> withCIN(String cin) {
			this.cin = cin;
			return this;
		}

		public Builder<T> withRole(String role) {
			this.role = role;
			return this;
		}

		public Builder<T> withEmail(String email) {
			this.email = email;
			return this;
		}

		public Builder<T> withPassowrd(String password) {
			this.password = password;
			return this;
		}

		public Builder<T> withGender(String gender) {
			this.gender = gender;
			return this;
		}

		public Builder<T> withAddress(String address) {
			this.address = address;
			return this;
		}

		public Builder<T> withPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}

		public Builder<T> isVerified(boolean verified) {
			this.verified = verified;
			return this;
		}

		public User build() {
			return new User(this);
		}
	}

	protected User(Builder<?> builder) {
		this.id = builder.id;
		this.login = builder.login;
		this.address = builder.address;
		this.email = builder.email;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.phoneNumber = builder.phoneNumber;
		this.verified = builder.verified;
		this.password = builder.password;
		this.cin = builder.cin;
		this.gender = builder.gender;
		this.role = builder.role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", firstName=" + firstName + ", lastName=" + lastName + ", cin="
				+ cin + ", password=" + password + ", role=" + role + ", gender=" + gender + ", address=" + address
				+ ", email=" + email + ", phoneNumber=" + phoneNumber + ", verified=" + verified + "]";
	}

}

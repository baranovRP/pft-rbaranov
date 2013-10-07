package com.baranov.pft.fw;

public class UserData implements Comparable<UserData> {

	private String id;
	private String login;
	private String password;
	private String email;

	public UserData withLogin(String login) {
		this.setLogin(login);
		return this;
	}

	public UserData withPassword(String password) {
		this.setPassword(password);
		return this;
	}

	public UserData withEmail(String email) {
		this.setEmail(email);
		return this;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserData other = (UserData) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}

	@Override
	public int compareTo(UserData other) {
		return this.getLogin().toLowerCase()
				.compareTo(other.getLogin().toLowerCase());
	}

	@Override
	public String toString() {
		return "UserData [login=" + login + ", email=" + email + "]";
	}

}

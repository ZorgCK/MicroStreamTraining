package one.microstream.microstreamtraining.entities;

public class Customer {

//	private Integer id;
	private String lastname;
	private String firstname;
//	private String mail;
//	private final LocalDateTime created = LocalDateTime.now();
	
	public String getLastname() {
		return this.lastname;
	}
	public void setLastname(final String lastname) {
		this.lastname = lastname;
	}
	public String getFirstname() {
		return this.firstname;
	}
	public void setFirstname(final String firstname) {
		this.firstname = firstname;
	}
}

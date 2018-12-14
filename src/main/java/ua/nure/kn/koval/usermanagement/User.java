package ua.nure.kn.koval.usermanagement;

import java.io.Serializable;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class User implements Serializable {
	private static final long serialVersionUID = 4188062179191390064L;
	private Long id;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;

	public User() {
		super();
		this.id = 0L;
		this.firstName = "";
		this.lastName = "";
		this.dateOfBirth = Calendar.getInstance().getTime();
	}

	public User(String firstName, String lastName, Date date) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = date;
	}

	public User(Long id, String firstName, String lastName, Date date) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this = obj) {
			return true;
		}
		if (this.getId()==null && ((User) obj).getId()==null) {
			return true;
		}
		return this.getId().equals((User) obj).getId());
			
		
	}

	@Override
	public int hashCode() {
		if (this.getId() == null) {
			return 0;
		}
		return this.getId().hashCode();
	}

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\''
				+ ", dateOfBirth=" + dateOfBirth + '}';
	}

	// get full name
	public String getFullName() {
		return new StringBuilder(getLastName()).append(", ").append(getFirstName()).toString();
	}

	// get age
	public long getAge() {
		SimpleDateFormat simdatf = new SimpleDateFormat("dd-MM-yyyy");
		DateTimeFormatter dattime = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate nowdat = LocalDate.now();
		LocalDate birth = LocalDate.parse(simdatf.format(dateOfBirth), dattime);
		Period between = Period.between(birth, nowdat);
		return between.getYears();
	}
}

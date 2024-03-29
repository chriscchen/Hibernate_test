package org.hibernate.tutorial.domain;

import java.util.HashSet;
import java.util.Set;

public class Person {
	
    public Person() {}
    private Long id;
    private int age;
    private String firstname;
    private String lastname;
    private Set<Event> events = new HashSet<Event>();
    private Set<String> emailAddresses = new HashSet<String>();

	public Set<String> getEmailAddresses() {
		return emailAddresses;
	}
	public void setEmailAddresses(Set<String> emailAddresses) {
		this.emailAddresses = emailAddresses;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Set<Event> getEvents() {
		return events;
	}
	public void setEvents(Set<Event> events) {
		this.events = events;
	} 
	
	public void addToEvent(Event event)
	{
		this.getEvents().add(event);
		event.getParticipants().add(this);
	}
	
	public void removeFromEvent(Event event)
	{
		this.getEvents().remove(event);
		event.getParticipants().remove(this);
	}

}

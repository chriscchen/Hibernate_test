package org.hibernate.tutorial;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.tutorial.domain.Event;
import org.hibernate.tutorial.domain.Person;
import org.hibernate.tutorial.util.HibernateUtil;

public class EventManager {
	public static void main(String[] args)
	{
		EventManager mgr = new EventManager();
//		if (args[0].equals("store"))
//		{
			mgr.createAndStoreEvent("My Event", new Date());
//		}
			
			List<Event> events = mgr.listEvents();
			for(Event event : events)
			{
				System.out.println("Event:" + event.getTitle() + " Time:" + event.getDate());
			}
			
			long pId = mgr.createAndStorePerson("zhang", "rui");
			long eId = mgr.createAndStoreEvent("test", new Date());
			mgr.addPersonToEvent(pId, eId);
			
			
		
		HibernateUtil.getSessionFactory().close();
		
	}
	
	private void addPersonToEvent(Long personId, Long eventId)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Person aPerson = (Person)session.load(Person.class, personId);
		Event aEvent = (Event) session.load(Event.class, eventId);
		aPerson.getEvents().add(aEvent);
		
		session.getTransaction().commit();
	}
	
	public long createAndStoreEvent(String title, Date theDate)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Event theEvent = new Event();
		theEvent.setTitle(title);
		theEvent.setDate(theDate);
		session.save(theEvent);
		session.getTransaction().commit();		
		return theEvent.getId();
	}
	
	private long createAndStorePerson(String firstname, String lastname)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Person person = new Person();
		person.setFirstname(firstname);
		person.setLastname(lastname);
		session.save(person);
		session.getTransaction().commit();
		return person.getId();
	}
	
	private List<Event> listEvents()
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		@SuppressWarnings("unchecked")
		List<Event> result = (List<Event>)session.createCriteria(Event.class).list();
		session.getTransaction().commit();
		return result;
	}
	
	private void addEmailToPerson(long personId, String emailAddress)
	{
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		Person person = (Person)session.load(Person.class, personId);
		person.getEmailAddresses().add(emailAddress);
		session.getTransaction().commit();
	}

}

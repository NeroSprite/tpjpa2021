package jpa;

import jpa.dao.AppointmentDao;
import jpa.dao.UserDao;
import jpa.dao.WorkerDao;
import jpa.domain.Appointment;
import jpa.domain.User;
import jpa.domain.Worker;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class JpaTest {

	private EntityManager manager;

	static protected Logger log = LogManager.getLogger("JpaTest");

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}
	/**

	/**
	 * @param args
	 */
	public static void main(String[] args) {


		EntityManager manager = EntityManagerHelper.getEntityManager();
		UserDao userDao = new UserDao(manager);
		AppointmentDao appointmentDao = new AppointmentDao(manager);
		User user1 = new User("Paul","antdegazzeaeazeaze.com","1234");
		userDao.addUser(user1);
		User user2 = new User("Antoine","antdegas@gmail.com","1234");
		userDao.addUser(user2);
		userDao.addUser(new User("Paul","paul@gmail.com","sdeefsdkf"));
		Worker prof1 = new Worker("Dr Raoult","raoult@bondocteur.com","Manger1PommeParJour","Marabout");
		userDao.addUser(prof1);

		//Création d'un appointment, ajout d'un organisateur et ajout d'un participant
		Appointment appointment1 = new Appointment("Réunion1",prof1);
		appointmentDao.createAppointment("Réunion1",prof1);
		appointmentDao.addParticipant(appointment1, user1);


		//Ajout d'un user sur un appointment non dispo
		appointmentDao.addParticipant(appointment1,user2);

		//Liste des Appointment de Dr Raoult
		List<Appointment> listeAppointment = appointmentDao.getAppointment(prof1);
		for(Appointment a : listeAppointment){
			log.info(a.getTitle());
		}

		manager.close();

		/*
		EntityTransaction tx = manager.getTransaction();
		JpaTest test = new JpaTest(manager);
		tx.begin();


		try {
			test.createUsers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();


		manager.close();
		EntityManagerHelper.closeEntityManagerFactory();
		//		factory.close();
		*/

	}

	private void createUsers() {
		int numOfEmployees = manager.createQuery("Select u From User u", User.class).getResultList().size();
		if (numOfEmployees == 0) {
			System.out.println("Insertion");
			manager.persist(new User("Antoine","antdegas@gmail.com","1234"));
			manager.persist(new User("Paul","paul@gmail.com","sdeefsdkf"));
			manager.persist(new Worker("Dr Raoult","RaoultLeBoss@bondocteur.com","Manger1PommeParJour","Marabout"));

		}
	}


}

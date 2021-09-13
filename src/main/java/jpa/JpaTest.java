package jpa;

import jpa.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class JpaTest {

	private EntityManager manager;

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}
	/**

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		EntityManager manager = EntityManagerHelper.getEntityManager();
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
	}

	private void createUsers() {
		int numOfEmployees = manager.createQuery("Select u From User u", User.class).getResultList().size();
		if (numOfEmployees == 0) {
			System.out.println("Insertion");
			manager.persist(new User("Antoine","antdegas@gmail.com","1234"));
			manager.persist(new User("Paul","paul@gmail.com","sdeefsdkf"));

		}
	}

	//TODO Ajouter des méthodes DAO
	//Nouvelle
	//Nouveau Worker
	//Afficher les créneaux disponible à partir d'un URL
	//Crée un nouveau apppointment
	//Réserver un apppointment
	//



}

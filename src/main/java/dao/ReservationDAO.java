package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Reservation;
import model.Vol;

public class ReservationDAO {

	/**
	  * Méthode de création de réservation
	  * @param obj
	  * @return void 
	  */
	public void saveReservation(Reservation obj) {

		EntityManager entityManager = DataBaseHelper.createEntityManager();

		DataBaseHelper.beginTx(entityManager);

		try {

			entityManager.persist(obj);

			DataBaseHelper.commitTxAndClose(entityManager);

		} catch (Exception e) {

			DataBaseHelper.rollbackTxAndClose(entityManager);

			e.printStackTrace();
		}

		entityManager.close();
	}


	/**
	  * Méthode pour touver une reservation
	  * @param numéro de vol
	  * @return list de reservation 
	  */
	public List<Reservation> findReservationByVol(String numVol) {

		EntityManager entityManager = DataBaseHelper.createEntityManager();

		TypedQuery<Vol> query = entityManager.createQuery("select v from Vol v where v.numeroVol = :numVol ",
				Vol.class);

		query.setParameter("numVol", numVol);

		Vol v = query.getSingleResult();

		List<Reservation> res = v.getReservationsClients();

		entityManager.close();

		return res;
	}

	/**
	  * Méthode pour supprimer une reservation
	  * @param num de reservation
	  * @return 
	  */
	public void removeReservationByNum(String numReservation) {

		EntityManager entityManager = DataBaseHelper.createEntityManager();

		DataBaseHelper.beginTx(entityManager);

		try {

			entityManager.remove(numReservation);

			DataBaseHelper.commitTxAndClose(entityManager);

		} catch (Exception e) {

			DataBaseHelper.rollbackTxAndClose(entityManager);

			e.printStackTrace();
		}

		entityManager.close();
	}
	
	/**
	  * Méthode pour trouver une resevation 
	  * @param nom
	  * @return list de reservation 
	  */
	public List<Reservation> findReservationByName(String n) {

		EntityManager entityManager = DataBaseHelper.createEntityManager();

		TypedQuery<Reservation> query = entityManager.createQuery("select r from Reservation r where r.nom = :n oder by r.nom ASC ",
				Reservation.class);

		query.setParameter("n", n);

		List<Reservation> res = query.getResultList();
		
		entityManager.close();

		return res;
	}
}

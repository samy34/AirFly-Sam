package dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import model.Avion;
import model.Vol;

public class AvionDAO {

	/**
	  * Méthode pour creer un vol 
	  * @param obj
	  * @return 
	  */
	public void save(Vol obj) {

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
	  * Méthode pour lister les vols 
	  * @param 
	  * @return list des vol 
	  */
	public List<Vol> findAllAvion() {

		EntityManager entityManager = DataBaseHelper.createEntityManager();

		List<Vol> l = new ArrayList<Vol>();

		TypedQuery<Vol> query = entityManager.createQuery("select v from Vol v order by dateToDepart", Vol.class);

		l = query.getResultList();

		entityManager.close();

		return l;
	}

	/**
	  * Méthode pour trouver un vol  
	  * @param numero de vol
	  * @return vol 
	  */
	public Vol findVolByNumber(String n) {

		EntityManager entityManager = DataBaseHelper.createEntityManager();

		TypedQuery<Vol> query = entityManager.createQuery("select v from Vol v where v.numeroVol = :n ", Vol.class);

		query.setParameter("n", n);

		Vol v = query.getSingleResult();

		entityManager.close();

		return v;
	}
	
	/**
	  * Méthode pour trouver des vols  
	  * @param ville de départ et d'arrivée
	  * @return Liste de vol 
	  */
	public List<Vol> findVolByTowns(String vd, String va) {

		EntityManager entityManager = DataBaseHelper.createEntityManager();

		TypedQuery<Vol> query = entityManager
				.createQuery("select v from Vol v where v.villeDeDepart = :vd and v.villeArrive =:va ", Vol.class);
		
		query.setParameter("vd", vd);
		
		query.setParameter("va", va);

		List<Vol> vols = query.getResultList();

		entityManager.close();

		return vols;
	}

}

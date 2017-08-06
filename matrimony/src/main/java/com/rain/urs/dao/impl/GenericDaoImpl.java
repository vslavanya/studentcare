package com.rain.urs.dao.impl;

import java.io.Serializable;
import java.sql.Clob;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.Clob;

import com.rain.urs.dao.GenericDao;

public abstract class GenericDaoImpl<T> implements GenericDao<T> {
	
//	@Autowired
	private SessionFactory sessionFactory;
	

	@Override
	public Serializable save(T entity) {
		return sessionFactory.getCurrentSession().save(entity);
	}

	@Override
	public Serializable saveOrUpdate(T entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
		return sessionFactory.getCurrentSession().getIdentifier(entity);
	}

	/**
	 *
	 * @param entity
	 */
	@Override
	public void merge(T entity) {

		sessionFactory.getCurrentSession().merge(entity);

	}

	@Override
	public void delete(T entity) {

		sessionFactory.getCurrentSession().delete(entity);
	}

	@Override
	public List<T> findMany(Query query) {
		List<T> t;
		t = (List<T>) query.list();
		return t;
	}

	@Override
	public T findOne(Query query) {
		T t;
		t = (T) query.uniqueResult();
		return t;
	}

	/**
	 *
	 * @param clazz
	 * @param id
	 * @return
	 */
	@Override
	public T findByID(Class clazz, Long id) {

		T t = null;
		t = (T) sessionFactory.getCurrentSession().get(clazz, id);
		return t;
	}

	/**
	 *
	 * @param clazz
	 * @return
	 */
	@Override
	public List findAll(Class clazz) {

		List T = null;
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from " + clazz.getName());
		T = query.list();

		return T;
	}

	@Override
	public void update(T entity) {

		sessionFactory.getCurrentSession().saveOrUpdate(entity);

	}

	@Override
	public String maxID(Query query) {
		String id;
		id = (String) query.uniqueResult();
		return id;
	}

	public List<T> findByCriteria(String propertyName, Object value) {
		SimpleExpression criterion = Restrictions.eq(propertyName, value);
		return findByCriteria(criterion);
	}

	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(Criterion... criterion) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				getPersistanceClass());
		for (Criterion c : criterion) {
			crit.add(c);
		}
		return crit.list();
	}

	public List<T> searchLast(Class<T> clazz, int max) {
		String hql = "from " + clazz.getSimpleName()
				+ " t order by t.lastUpdatedTimestamp desc";
		Query query = getHibernateSession().createQuery(hql);
		query.setMaxResults(max);
		return findMany(query);
	}

	@SuppressWarnings("unchecked")
	public List<T> searchMany(String hql, int limit) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(hql);
		query.setMaxResults(limit);
		return (List<T>) query.list();
	}

	public int execute(String hql) {
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(hql);
		return query.executeUpdate();
	}

	public int clearTable(String myTable) {
		String hql = String.format("delete from %s", myTable);
		return execute(hql);
	}

	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	protected Session getHibernateSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public abstract Class<T> getPersistanceClass();

	@Override
	public Clob getClob(String text) {
		if (text != null) {
			return sessionFactory.getCurrentSession().getLobHelper()
					.createClob(text);
		}
		return null;
	}
	
	@Override
	public void flushAndClearCurrentSession() {
		getHibernateSession().flush();
    	getHibernateSession().clear();
	}
}


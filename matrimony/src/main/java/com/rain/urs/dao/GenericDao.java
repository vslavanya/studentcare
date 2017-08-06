package com.rain.urs.dao;

import java.io.Serializable;
import java.sql.Clob;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.Criterion;

public interface GenericDao<T> {
	// Matches with the hibernate.jdbc.batch_size value.
	public static final int JDBC_BATCH_SIZE = 20;

	/**
     * @param entity
     * @return the generated identifier 
     */
    public  Serializable save(T entity);
    
    public  Serializable saveOrUpdate(T entity);

    public void merge(T entity);

    public void delete(T entity);

    public List<T> findMany(Query query);

    public T findOne(Query query);
    

    public List<T> findAll(Class clazz);
    
    public T findByID(Class clazz,  Long id);

    public void update(T entity);

    public String maxID(Query query);
	
    public List<T> searchLast(Class<T> clazz, int max);

    public  Class getPersistanceClass();
    
    /**
     * Returns the list of all entities based on this criteria
     *
     * @param criterion
     * @return
     */
    public List<T> findByCriteria(Criterion... criterion);

    /**
     * Returns the list of all entities based on this Criteria matching with the
     * value
     *
     * @param criterion
     * @return
     */
    public List<T> findByCriteria(String propertyName, Object value);
    
    public int clearTable(String myTable);
    public Clob getClob(String text);
	
    void flushAndClearCurrentSession();
}

package com.rain.urs.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.rain.urs.dao.AddressDao;
import com.rain.urs.dao.model.Address;

@Repository
public class AddressDaoImpl extends GenericDaoImpl<Address> implements AddressDao {

	@Override
	public List<Address> findAddress(String officeName) {
		String sql = "from Address address where address.officename like :officeName";
		Query query = getHibernateSession().createQuery(sql);
	    query.setParameter("officeName", officeName+"%");
        return findMany(query);
	}

	@Override
	public Class<Address> getPersistanceClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Address> findAddressByPinCode(String pinCode) {
		String sql = "from Address address where address.pincode like :pincode";
		Query query = getHibernateSession().createQuery(sql);
	    query.setParameter("pincode", pinCode+"%");
        return findMany(query);
	}
	

	

}

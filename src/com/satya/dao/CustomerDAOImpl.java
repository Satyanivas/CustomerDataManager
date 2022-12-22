package com.satya.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.satya.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {
		// TODO Auto-generated method stub
		Session curr=sessionFactory.getCurrentSession();
		Query<Customer> query=curr.createQuery("from Customer order by lastName",Customer.class);
		List<Customer> customers=query.getResultList();
		
		return customers;
	}

	@Override
	public void saveCustomer(Customer thecustomer) {
		// TODO Auto-generated method stub
		Session currsession=sessionFactory.getCurrentSession();
		currsession.saveOrUpdate(thecustomer);
	}

	@Override
	public Customer getCustomer(int id) {
		// TODO Auto-generated method stub
		Session currsession=sessionFactory.getCurrentSession();
		Customer customer=currsession.get(Customer.class, id);
		
		return customer;
	}

	@Override
	public void deleteCustomer(int id) {
		// TODO Auto-generated method stub
		Session currentsess=sessionFactory.getCurrentSession();
		Query query=currentsess.createQuery("delete from Customer where id=:customerId");
		query.setParameter("customerId",id);
		query.executeUpdate();
		
	}

}

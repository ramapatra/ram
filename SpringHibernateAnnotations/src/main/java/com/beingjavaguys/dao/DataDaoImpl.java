package com.beingjavaguys.dao;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.beingjavaguys.domain.Employee;

@Component
public class DataDaoImpl implements DataDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	@Transactional
	public int insertRow(Employee employee) {
		Session session = sessionFactory.getCurrentSession();
		//Transaction tx = session.beginTransaction();
		session.saveOrUpdate(employee);
		//tx.commit();
		//Serializable id = session.getIdentifier(employee);
		//session.close();
		return 1;
	}

	@Override
	public List<Employee> getList() {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		List<Employee> employeeList = session.createQuery("from Employee")
				.list();
		session.close();
		return employeeList;
	}

	@Override
	public Employee getRowById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Employee employee = (Employee) session.get(Employee.class, id);
		return employee;
	}

	@Override
	public int updateRow(Employee employee) {
		Session session = sessionFactory.getCurrentSession();
		//Transaction tx = session.beginTransaction();
		session.saveOrUpdate(employee);
		//tx.commit();
		Serializable id = session.getIdentifier(employee);
		
		return (Integer) id;
	}

	@Override
	public int deleteRow(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Employee employee = (Employee) session.load(Employee.class, id);
		session.delete(employee);
		tx.commit();
		Serializable ids = session.getIdentifier(employee);
		session.close();
		return (Integer) ids;
	}

}

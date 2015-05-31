package com.beingjavaguys.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beingjavaguys.dao.DataDao;
import com.beingjavaguys.domain.Employee;
@Service
@Transactional
public class DataServiceImpl implements DataService {
	
	@Autowired
	DataDao dataDao;

	@Override
	public int insertRow(Employee employee) {
		return dataDao.insertRow(employee);
	}

	@Override
	public List<Employee> getList() {
		return dataDao.getList();
	}

	@Override
	public Employee getRowById(int id) {
		return dataDao.getRowById(id);
	}

	@Override
	
	public int updateRow(Employee employee) {
		Employee dbEmp = getRowById(employee.getId());
		dbEmp.setEmail(employee.getEmail());
		dbEmp.setEmpid(employee.getEmpid());
		dbEmp.setFirstName(employee.getFirstName());
		dbEmp.setLastName(employee.getLastName());
		dbEmp.setPassword(employee.getPassword());
		dbEmp.setReentered(employee.getReentered());
		dbEmp.setPhone(employee.getPhone());
		
		return dataDao.updateRow(dbEmp);
	}

	@Override
	public int deleteRow(int id) {
		return dataDao.deleteRow(id);
	}

}

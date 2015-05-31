package com.beingjavaguys.services;

import java.util.List;

import javax.transaction.Transactional;

import com.beingjavaguys.domain.Employee;

public interface DataService {
	
	@Transactional
	public int insertRow(Employee employee);

	@Transactional
	public List<Employee> getList();

	public Employee getRowById(int id);

	public int updateRow(Employee employee);

	public int deleteRow(int id);

}

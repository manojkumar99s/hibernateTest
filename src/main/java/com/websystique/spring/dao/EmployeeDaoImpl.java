package com.websystique.spring.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
//import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.websystique.spring.model.Employee;



@Repository("employeeDao")
public class EmployeeDaoImpl extends AbstractDao<Integer,Employee> implements EmployeeDao{

	public void saveEmployee(Employee employee) {
		persist(employee);
	}
	@SuppressWarnings("unchecked")
	public List<Employee> findAllEmployees() {
		
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Employee> employees = (List<Employee>) criteria.list();
		
	
		return employees;
	}
	
	public void deleteEmployeeBySsn(String ssn) {
		
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssn", ssn));
		Employee employee = (Employee)crit.uniqueResult();
		delete(employee);
		
	}

	public Employee findBySsn(String ssn) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssn", ssn));
		Employee employee = (Employee)crit.uniqueResult();
		
		return employee;
	}

	public void updateEmployee(Employee employee) {
		update(employee);
		
	}

	
	
}

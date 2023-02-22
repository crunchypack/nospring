package jpa.nospring.dao;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jpa.nospring.entities.CustomerEntity;
import jpa.nospring.model.Customer;

public class CustomerDAOImpl implements CustomerDAO {

	@Override
	public Integer addCustomer(Customer customer) throws Exception {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		Integer customerId = null;
		try {
			emf = Persistence.createEntityManagerFactory("JPA_CRUD");
			em = emf.createEntityManager();
			CustomerEntity ce = new CustomerEntity();
			ce.setCustomerId(customer.getCustomerId());
			ce.setDateOfBirth(customer.getDateOfBirth());
			ce.setEmailId(customer.getEmailId());
			ce.setName(customer.getName());
			em.getTransaction().begin();
			em.persist(ce);
			em.getTransaction().commit();
			customerId = ce.getCustomerId();
		}catch (Exception e) {
			Logger logger = LogManager.getLogger(this.getClass());
			logger.error(e.getMessage(), e);
			throw e;
		}finally {
			if(em!=null && em.isOpen()){
				em.close();
			}
			if(emf!=null && emf.isOpen()){
				emf.close();
			}
		}
		return customerId;
	}
	public Customer getCustomer(Integer customerId) throws Exception {
		EntityManagerFactory emf=null;
		EntityManager em=null;
		Customer customer=null;
		try {
			emf=Persistence.createEntityManagerFactory("JPA_CRUD");
			em=emf.createEntityManager();
			CustomerEntity customerEntity = em.find(CustomerEntity.class, customerId);
			if(customerEntity!=null){
				customer=new Customer();
				customer.setCustomerId(customerEntity.getCustomerId());
				customer.setDateOfBirth(customerEntity.getDateOfBirth());
				customer.setEmailId(customerEntity.getEmailId());
				customer.setName(customerEntity.getName());
			}
		} catch (Exception e) {
			Logger logger=LogManager.getLogger(this.getClass());
			logger.error(e.getMessage(),e);
			throw e;
		}finally{
			if(em!=null && em.isOpen()){
				em.close();
			}
			if(emf!=null && emf.isOpen()){
				emf.close();
			}
		}
		return customer;
	}
	public Integer updateCustomer(Integer customerId, String emailId) throws Exception {
		EntityManagerFactory emf=null;
		EntityManager em=null;
		Integer cId=null;
		try {
			emf=Persistence.createEntityManagerFactory("JPA_CRUD");
			em=emf.createEntityManager();
			CustomerEntity customerEntity = em.find(CustomerEntity.class, customerId);
			em.getTransaction().begin();
			customerEntity.setEmailId(emailId);
			em.getTransaction().commit();
			cId=customerEntity.getCustomerId();
		} catch (Exception e) {
			Logger logger=LogManager.getLogger(this.getClass());
			logger.error(e.getMessage(),e);
			throw e;
		}finally{
			if(em!=null && em.isOpen()){
				em.close();
			}
			if(emf!=null && emf.isOpen()){
				emf.close();
			}
		}
		return cId;
	}
	public Integer deleteCustomer(Integer customerId) throws Exception {
		EntityManagerFactory emf=null;
		EntityManager em=null;
		Integer cId=null;
		try {
			emf=Persistence.createEntityManagerFactory("JPA_CRUD");
			em=emf.createEntityManager();
			CustomerEntity customerEntity = em.find(CustomerEntity.class, customerId);
			em.getTransaction().begin();
			em.remove(customerEntity);
			em.getTransaction().commit();
			cId=customerEntity.getCustomerId();
		} catch (Exception e) {
			Logger logger=LogManager.getLogger(this.getClass());
			logger.error(e.getMessage(),e);
			throw e;
		}finally{
			if(em!=null && em.isOpen()){
				em.close();
			}
			if(emf!=null && emf.isOpen()){
				emf.close();
			}
		}
		return cId;
	}

}

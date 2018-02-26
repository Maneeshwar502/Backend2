package com.DaoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.UserDao;
import com.model.User;

@Repository
public class UserDaoimpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional
	public boolean registerUser(User user) {
		// TODO Auto-generated method stub
		System.out.println("in dao layer");
		Session session=sessionFactory.getCurrentSession();
		session.save(user);//insert query
		System.out.println("inserted successfully!");
		return true;
	}
	@SuppressWarnings({ "unchecked" })
	@Transactional
	public List<User> UserList() {//select * from user;
		Criteria c=sessionFactory.openSession().createCriteria(User.class);
		List<User> l = c.list();
		return l;
	}
	@Transactional	
	public boolean isEmailvalid(String email) {
		List<User> list = UserList();
		System.out.println("isEmail valid");
		for (User user : list) {
			System.out.println(user.getEmail());
			if (user.getEmail().equals(email)){
				return false;// invalid user
			}
		}
		return true;// valid user
	}
	@Transactional
	public User login(User user) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery("from User where email=? and password=?");
		query.setString(0, user.getEmail());
		query.setString(1, user.getPassword());
		return (User)query.uniqueResult();
	}
	@Transactional
	public void update(User validuser) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		session.update(validuser);	
	}
	@Transactional
	public User getUser(int id) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.getCurrentSession();
		User user=(User)session.get(User.class,id);
		return user;
		
	}
	//@Transactional
	public void updateuser(User user) {
		// TODO Auto-generated method stub
		Session session=sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
		session.update(user);
		trans.commit();
		
	}	}
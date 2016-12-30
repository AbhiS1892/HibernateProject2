package com.abhi.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;

import com.abhi.hibernate.dto.UserDetails;

public class HibernateTest {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
//		List<UserDetails> users = null;
//		UserDetails ex = new UserDetails();
//		ex.setUserName("Ab%");
		UserDetails users = null;
		UserDetails users2 = null;

		try {
			transaction = session.beginTransaction();
//			Query query = session.getNamedQuery("UserDetails.byName");
//			query.setString(0, "Abhishek");
//			Query query = session.createQuery("from UserDetails where userId > ?");
//			query.setString(0, "0");
//			query.setFirstResult(0);
//			query.setMaxResults(2);
			
//			userNames = (List<Integer>)query.list();
//			users = (List<UserDetails>)query.list();
			
//			Example example = Example.create(ex).enableLike();
//			Criteria criteria =  session.createCriteria(UserDetails.class)
//					                    .add(example);
//					.setProjection(Projections.count("userId"));
//			criteria.add(Restrictions.eq("userName", "Abhishek"));
//			users = (List<UserDetails>)criteria.list();
//			users = (UserDetails) session.load(UserDetails.class, new Integer(1));
			Query query = session.createQuery("from UserDetails use where use.userId > 1");
			query.setCacheable(true);
			List<UserDetails> user = query.list();
			transaction.commit();
		}catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace(); 
		}finally {
			session.close();
		}
		
		
		Session session2 = sessionFactory.openSession();
		
		try {
			transaction = session2.beginTransaction();
//			users2 = (UserDetails) session2.get(UserDetails.class, new Integer(1));
			Query query2 = session2.createQuery("from UserDetails use where use.userId > 1");
			query2.setCacheable(true);
			List<UserDetails> user = query2.list();
			transaction.commit();
		}catch (Exception e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace(); 
		}finally {
			session2.close();
		}
		
//		Iterator<UserDetails> i1 = users.iterator();
//		
//		while(i1.hasNext()){
//			System.out.println(i1.next());
//		}
		
//		for(UserDetails u : users){
//			System.out.println(u);
//		}
	


	}

}

package me.feathers.online.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**Hibernate模板工具类*/
public final class HibernateTemplate {

	public static Object execute(HibernateCallback hc) {
		Object t = null;

		Session ses = null;
		Transaction tx = null;
		try {
			ses = HibernateUtil.getSession();
			tx = ses.beginTransaction();
			t = hc.doInHibernate(ses);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			HibernateUtil.close(ses);
		}
		return t;
	}
}

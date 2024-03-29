package com.baranov.pft.fw;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.baranov.pft.tests.ContactData;
import com.baranov.pft.tests.GroupData;
import com.baranov.pft.utils.SortedListOf;

public class HibernateHelper extends HelperBase {

    public HibernateHelper(ApplicationManager manager) {
	super(manager);
    }

    public List<GroupData> listGroups() {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction trans = session.beginTransaction();
	try {
	    return new SortedListOf<GroupData>((List<GroupData>) session
		    .createQuery("from GroupData").list());
	} finally {
	    trans.commit();
	}
    }

    public List<ContactData> listContacts() {
	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	Transaction trans = session.beginTransaction();
	try {
	    return new SortedListOf<ContactData>((List<ContactData>) session
		    .createQuery("from ContactData").list());
	} finally {
	    trans.commit();
	}
    }
}

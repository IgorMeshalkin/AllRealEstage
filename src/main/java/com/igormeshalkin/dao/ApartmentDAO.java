package com.igormeshalkin.dao;

import com.igormeshalkin.entity.Apartment;
import com.igormeshalkin.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ApartmentDAO {

    SessionFactory sessionFactory;

    public ApartmentDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Apartment> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Apartment", Apartment.class).getResultList();
    }

    public Apartment findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Apartment.class, id);
    }

    public void saveOrUpdate(Apartment apartment) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(apartment);
    }

    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("delete from Apartment where id = :apartmentId");
        query.setParameter("apartmentId", id);
        query.executeUpdate();
    }
}

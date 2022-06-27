package com.igormeshalkin.dao;

import com.igormeshalkin.entity.Like;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LikeDAO {
    SessionFactory sessionFactory;

    public LikeDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Like> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Like", Like.class).getResultList();
    }

    public void save(Like like) {
        Session session = sessionFactory.getCurrentSession();
        session.save(like);
    }

    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Like> query = session.createQuery("delete from Like where id = :likeId");
        query.setParameter("likeId", id);
        query.executeUpdate();
    }
}

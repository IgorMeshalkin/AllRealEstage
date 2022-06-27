package com.igormeshalkin.service;

import com.igormeshalkin.dao.ApartmentDAO;
import com.igormeshalkin.dao.LikeDAO;
import com.igormeshalkin.entity.Apartment;
import com.igormeshalkin.entity.Like;
import com.igormeshalkin.util.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LikeService {
    private final LikeDAO likeDAO;
    private final ApartmentDAO apartmentDAO;

    public LikeService(LikeDAO likeDAO, ApartmentDAO apartmentDAO) {
        this.likeDAO = likeDAO;
        this.apartmentDAO = apartmentDAO;
    }

    @Transactional
    public List<Like> findAll() {
        return likeDAO.findAll();
    }

    @Transactional
    public void like(Long apartment_id) {
        Apartment apartment = apartmentDAO.findById(apartment_id);
        Like like = apartment.getLikeByCurrentUser();

        if(like != null) {
            likeDAO.delete(like.getId());
        } else {
            Like newLike = new Like();
            LocalDateTime currentDateTime = LocalDateTime.now();
            newLike.setCreated(currentDateTime);
            newLike.setApartment(apartment);
            newLike.setUser(SecurityUtil.getCurrentUser());
            likeDAO.save(newLike);
        }
    }
}

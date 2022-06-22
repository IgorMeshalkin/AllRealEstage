package com.igormeshalkin.service;

import com.igormeshalkin.dao.ApartmentDAO;
import com.igormeshalkin.entity.Apartment;
import com.igormeshalkin.util.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApartmentService {
    ApartmentDAO apartmentDAO;

    public ApartmentService(ApartmentDAO apartmentDAO) {
        this.apartmentDAO = apartmentDAO;
    }

    @Transactional
    public List<Apartment> findAll() {
        return apartmentDAO.findAll();
    }

    @Transactional
    public Apartment findById(Long id) {
        return apartmentDAO.findById(id);
    }

    @Transactional
    public void create(Apartment apartment) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        apartment.setCreated(currentDateTime);
        apartment.setUpdated(currentDateTime);
        apartment.setUser(SecurityUtil.getCurrentUser());

        apartmentDAO.saveOrUpdate(apartment);
    }

    @Transactional
    public void update(Apartment apartment) {
        Apartment fromDb = apartmentDAO.findById(apartment.getId());
        LocalDateTime currentDateTime = LocalDateTime.now();
        fromDb.setUpdated(currentDateTime);

        fromDb.setStreet(apartment.getStreet());
        fromDb.setHouseNumber(apartment.getHouseNumber());
        fromDb.setNumberOfRooms(apartment.getNumberOfRooms());
        fromDb.setArea(apartment.getArea());
        fromDb.setFloor(apartment.getFloor());
        fromDb.setTotalFloors(apartment.getTotalFloors());
        fromDb.setBalconyAvailability(apartment.isBalconyAvailability());
        fromDb.setPrice(apartment.getPrice());

        apartmentDAO.saveOrUpdate(fromDb);
    }

    @Transactional
    public void delete(Long id) {
        apartmentDAO.delete(id);
    }
}

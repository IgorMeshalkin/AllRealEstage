package com.igormeshalkin.util;

import com.igormeshalkin.dao.UserDAO;
import com.igormeshalkin.entity.Apartment;
import com.igormeshalkin.entity.User;
import com.igormeshalkin.service.LikeService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserRatingUtil {
    private static LikeService likeService;

    public UserRatingUtil(LikeService likeService) {
        UserRatingUtil.likeService = likeService;
    }

    public static double calculateRating(User user) {
        double allLikes = likeService.findAll().size();
        double likesByThisUser = likeService.findAll()
                .stream()
                .filter(like -> like.getUser().getUsername().equals(user.getUsername()))
                .collect(Collectors.toList()).size();

        List<Apartment> apartments = user.getApartments();
        double likesToThisUser = 0;
        for(Apartment a : apartments) {
            likesToThisUser = likesToThisUser + a.getLikes().size();
        }
        return likesToThisUser / ((allLikes - likesByThisUser) /10);
    }
}

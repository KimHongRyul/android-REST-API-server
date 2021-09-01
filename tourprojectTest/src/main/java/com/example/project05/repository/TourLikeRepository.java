package com.example.project05.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.project05.model.tour.TourFav;

public interface TourLikeRepository extends JpaRepository<TourFav, Integer>{

	@Modifying
	@Query(value = "INSERT INTO tour_fav(user_id,tour_id) values(:userId,:tourId)",nativeQuery = true)
	int mLikes(int userId, int tourId);
	
	@Modifying
	@Query(value = "DELETE FROM tour_fav WHERE user_id=:userId AND tour_id=:tourId",nativeQuery = true)
	int mUnLikes(int userId, int tourId);
	
}

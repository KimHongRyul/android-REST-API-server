package com.example.project05.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.project05.model.festival.FestivalFav;

public interface FestivalLikeRepository extends JpaRepository<FestivalFav, Integer>{
	@Modifying
	@Query(value = "INSERT INTO festival_fav(user_id,festival_id) values(:userId,:festivalId)",nativeQuery = true)
	int mLikes(int userId, int festivalId);
	
	@Modifying
	@Query(value = "DELETE FROM festival_fav WHERE user_id=:userId AND festival_id=:festivalId",nativeQuery = true)
	int mUnLikes(int userId, int festivalId);
}

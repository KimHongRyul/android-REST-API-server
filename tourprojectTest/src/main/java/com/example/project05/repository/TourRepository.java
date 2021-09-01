package com.example.project05.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.project05.model.tour.Tour;

public interface TourRepository extends JpaRepository<Tour, Integer>{
	@Query(value = "SELECT * FROM tour WHERE tour_area= :area",nativeQuery = true)
	List<Tour> mArea(String area);
}

package com.example.project05.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project05.model.tour.Tour;

public interface TourRepository extends JpaRepository<Tour, Integer>{

}

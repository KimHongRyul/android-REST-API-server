package com.example.project05.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.project05.model.tour.Tour;
import com.example.project05.repository.TourRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TourService {

	private final TourRepository tourRepository;
	
	@Transactional
	public List<Tour> tourList() {
		List<Tour> tours = tourRepository.findAll();
		return tours;
	}
	@Transactional
	public Tour tourOne(int id) {
		Tour tour = tourRepository.findById(id).get();
		return tour;
	}
	@Transactional
	public List<Tour> tourArea(String area) {
		List<Tour> tours = tourRepository.mArea(area);
		return tours;
	}
	
	
}

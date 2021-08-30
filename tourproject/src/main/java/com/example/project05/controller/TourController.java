package com.example.project05.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.project05.controller.dto.CMRespDto;
import com.example.project05.model.tour.Tour;
import com.example.project05.service.FestivalService;
import com.example.project05.service.TourService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TourController {
	
	private final TourService tourService;
	
	@GetMapping("/tour/list")
	public CMRespDto<?> tourList() {
		List<Tour> tours = tourService.tourList();
		return new CMRespDto<>(1,"관광지데이터",tours);
	}
	
	@GetMapping("/tour/{id}")
	public CMRespDto<?> tourOne(@PathVariable int id) {
		Tour tour = tourService.tourOne(id);
		return new CMRespDto<>(1,"관광지상세보기",tour);
	}
	
}

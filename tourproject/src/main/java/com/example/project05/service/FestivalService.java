package com.example.project05.service;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.project05.model.festival.Festival;
import com.example.project05.repository.FestivalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FestivalService {

	private final FestivalRepository festivalRepository;
	
	@Transactional
	public List<Festival> festivalList() {
		List<Festival> fests = festivalRepository.findAll();
		return fests;
	}
	@Transactional
	public Festival festivalOne(int id) {
		Festival festival = festivalRepository.findById(id).get();
		return festival;
	}
	
}

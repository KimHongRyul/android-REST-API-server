package com.example.project05.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.project05.controller.dto.CMRespDto;
import com.example.project05.controller.dto.FestivalListDto;
import com.example.project05.model.festival.Festival;
import com.example.project05.service.FestivalService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class FestivalController {

	private final FestivalService festivalService;
	
	@GetMapping("/festival/list")
	public CMRespDto<?> festivalList() {
		List<Festival> fests = festivalService.festivalList();
		//System.out.println(fests);
		return new CMRespDto<>(1,"축제데이터",fests);
	}
	@GetMapping("/festival/{id}")
	public CMRespDto<?> festivalDetails(@PathVariable int id) {
		Festival festival = festivalService.festivalOne(id);
		return new CMRespDto<>(1,"축제상세보기",festival);
	}
	
}

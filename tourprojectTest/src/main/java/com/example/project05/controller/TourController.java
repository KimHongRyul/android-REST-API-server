package com.example.project05.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project05.config.auth.PrincipalDetails;
import com.example.project05.controller.dto.CMRespDto;
import com.example.project05.model.tour.Tour;
import com.example.project05.service.TourLikeService;
import com.example.project05.service.TourService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TourController {
	
	private final TourService tourService;
	private final TourLikeService tourLikeService;
	
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
	@GetMapping("/tour/area/{area}")
	public CMRespDto<?> tourArea(@PathVariable String area) {
		System.out.println(area);
		List<Tour> tours = tourService.tourArea(area);
		return new CMRespDto<>(1,"지역별관광지",tours);
	}
	@PostMapping("/user/tour/{tourId}/likes")
	public CMRespDto<?> likes(@PathVariable int tourId,@AuthenticationPrincipal PrincipalDetails principalDetails) {
		//System.out.println(principalDetails.getUser());
		tourLikeService.tourLikes(principalDetails.getUser().getUserId(), tourId);
		return new CMRespDto<>(1,"관광지좋아요성공",null);
	}
	@DeleteMapping("/user/tour/{tourId}/likes")
	public CMRespDto<?> unlikes(@PathVariable int tourId,@AuthenticationPrincipal PrincipalDetails principalDetails) {
		tourLikeService.tourUnLikes(principalDetails.getUser().getUserId(), tourId);
		return new CMRespDto<>(1,"관광지좋아요취소성공",null);
	}
	
}

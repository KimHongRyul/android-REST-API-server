package com.example.project05.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.project05.repository.TourLikeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TourLikeService {

	private final TourLikeRepository tourLikeRepository;
	
	@Transactional
	public void tourLikes(int userId, int tourId) {
		tourLikeRepository.mLikes(userId,tourId);
	}
	@Transactional
	public void tourUnLikes(int userId, int tourId) {
		tourLikeRepository.mUnLikes(userId,tourId);
	}
}

package com.example.project05.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.project05.repository.FestivalLikeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FestivalLikeService {
	
	private final FestivalLikeRepository festivalLikeRepository;
	
	@Transactional
	public void festivalLikes(int userId, int festivalId) {
		festivalLikeRepository.mLikes(userId,festivalId);
	}
	@Transactional
	public void festivalUnLikes(int userId, int festivalId) {
		festivalLikeRepository.mUnLikes(userId,festivalId);
	}
	
}

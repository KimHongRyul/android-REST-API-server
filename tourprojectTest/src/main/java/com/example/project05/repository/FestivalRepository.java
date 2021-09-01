package com.example.project05.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.project05.model.festival.Festival;

public interface FestivalRepository extends JpaRepository<Festival, Integer>{
	
}

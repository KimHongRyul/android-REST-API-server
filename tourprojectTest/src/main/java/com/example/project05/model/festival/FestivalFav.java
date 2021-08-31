package com.example.project05.model.festival;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.example.project05.model.user.User;

@Entity
public class FestivalFav {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int favId;
	
	@JoinColumn(name = "userId")
	@ManyToOne
	private User user;
	
	@JoinColumn(name = "festivalId")
	@ManyToOne
	private Festival festival;
	
	
}

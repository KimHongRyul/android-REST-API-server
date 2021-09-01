package com.example.project05.model.festival;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.example.project05.model.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(
		uniqueConstraints = {
				@UniqueConstraint(
						name = "festival_fav_uk",
						columnNames = {"festivalId","userId"}
				)
		}
)
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

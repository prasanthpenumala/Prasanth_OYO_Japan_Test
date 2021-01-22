package com.oyo.japan.prasanth.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * Model Class to Maintain the Player Score Information
 * @author Prasanth.Penumala
 *
 */
@Data
@Entity
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String playerName;
	private int score;
	@JsonFormat(pattern = "yyyyMMddHHmmss")
	private LocalDateTime time;
}

package com.oyo.japan.prasanth.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * This class will be used to hold the Score and Time
 * @author Prasanth.Penumala
 *
 */
@Data
@AllArgsConstructor
public class PlayerHistoryTimeScore implements Comparable<PlayerHistoryTimeScore>{
	
	private int score;
	private String time;
	
	@Override
	public int compareTo(PlayerHistoryTimeScore playerHistoryTimeScore) {
		if(this.score < playerHistoryTimeScore.score) return -1;
		else if(this.score > playerHistoryTimeScore.score) return 1;
		return 0;
	}
	

}

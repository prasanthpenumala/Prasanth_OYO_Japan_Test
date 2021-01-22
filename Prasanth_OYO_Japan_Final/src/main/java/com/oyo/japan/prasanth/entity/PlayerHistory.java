package com.oyo.japan.prasanth.entity;

import java.util.List;

import lombok.Data;

/**
 * This class will be used to maintain the Player History
 * @author Prasanth.Penumala
 *
 */
@Data
public class PlayerHistory {
	private PlayerHistoryTimeScore topScore;
	private PlayerHistoryTimeScore lowScore;
	private int avgScore;
	private List<PlayerHistoryTimeScore> allScores;

}

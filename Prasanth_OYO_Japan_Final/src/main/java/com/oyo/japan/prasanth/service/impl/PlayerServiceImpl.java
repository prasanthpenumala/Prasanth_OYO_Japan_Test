package com.oyo.japan.prasanth.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Component;

import com.oyo.japan.prasanth.entity.ClientPlayer;
import com.oyo.japan.prasanth.entity.PlayerHistory;
import com.oyo.japan.prasanth.entity.PlayerHistoryTimeScore;
import com.oyo.japan.prasanth.model.Player;
import com.oyo.japan.prasanth.repository.PlayerRepository;
import com.oyo.japan.prasanth.service.PlayerService;

/**
 * Implemented class for the Player Service
 * @author Prasanth.Penumala
 *
 */
@Component
public class PlayerServiceImpl implements PlayerService{

	private DateTimeFormatter dateTimeFormatter =  DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	
	@Autowired
	private PlayerRepository playerRepository;
	
	@Override
	public Player addPlayer(ClientPlayer clientPlayer) {
		Player player = new Player();
		player.setPlayerName(clientPlayer.getPlayer().toUpperCase());
		player.setScore(clientPlayer.getScore());
		player.setTime(getLocalDateTime(clientPlayer.getTime()));
		return playerRepository.save(player);
	}

	@Override
	public Player getPlayerByScoreId(int scoreId) {
		Optional<Player> playerData =  playerRepository.findById(scoreId);
		if(playerData.isPresent())
			return playerData.get();
		else
			return null;
	}

	@Override
	public List<Player> getPlayerByName(String playerName, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Slice<Player> playerSlice = playerRepository.findByPlayerName(playerName.toUpperCase(), pageable);
		return playerSlice.getContent();
	}

	@Override
	public List<Player> getPlayerDataAfterTime(String afterTime, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Slice<Player> playerSlice = playerRepository.findAllByTimeAfter(getLocalDateTime(afterTime), pageable);
		return playerSlice.getContent();
	}

	@Override
	public List<Player> getPlayerDataBetweenTime(String startTime, String endTime, int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Slice<Player> playerSlice = playerRepository.findAllByTimeBetween(getLocalDateTime(startTime), getLocalDateTime(endTime), pageable);
		return playerSlice.getContent();
	}

	@Override
	public void delete(int scoreId) {
		playerRepository.deleteById(scoreId);
	}

	@Override
	public PlayerHistory getPlayerHistory(String playerName) {
		List<Player> players = playerRepository.findByPlayerName(playerName.toUpperCase());
		if(players.isEmpty()) return null;
		List<PlayerHistoryTimeScore> playerHistoryList = new ArrayList<>();
		int totalScore = 0;
		for(Player player : players) {
			totalScore+=player.getScore();
			playerHistoryList.add(new PlayerHistoryTimeScore(player.getScore(), getLocalDateTimeInString(player.getTime())));
		}
		Collections.sort(playerHistoryList);
		PlayerHistory playerHistory = new PlayerHistory();
		playerHistory.setLowScore(playerHistoryList.get(0));
		playerHistory.setTopScore(playerHistoryList.get(playerHistoryList.size()-1));
		playerHistory.setAllScores(playerHistoryList);
		playerHistory.setAvgScore(totalScore/playerHistoryList.size());
		
		return playerHistory;
	}
	
	private LocalDateTime getLocalDateTime(String localDateTimeAsString)
	{
		return LocalDateTime.parse(localDateTimeAsString, dateTimeFormatter);
	}
	
	private String getLocalDateTimeInString(LocalDateTime localDateTime) {
		return localDateTime.format(dateTimeFormatter);
	}

}

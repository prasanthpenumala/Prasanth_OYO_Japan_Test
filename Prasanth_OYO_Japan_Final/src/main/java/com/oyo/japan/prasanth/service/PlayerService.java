package com.oyo.japan.prasanth.service;

import java.util.List;

import com.oyo.japan.prasanth.entity.ClientPlayer;
import com.oyo.japan.prasanth.entity.PlayerHistory;
import com.oyo.japan.prasanth.model.Player;

/**
 * This interface works a service for the Player API
 * @author Prasanth.Penumala
 *
 */
public interface PlayerService {

	public Player addPlayer(ClientPlayer player);
	public Player getPlayerByScoreId( int scoreId);
	public List<Player> getPlayerByName(String playerName, int pageNo, int pageSize);
	public List<Player> getPlayerDataAfterTime( String afterTime, int pageNo, int pageSize);
	public List<Player> getPlayerDataBetweenTime( String startTime, String endTime, int pageNo, int pageSize);
	public void delete( int scoreId);
	public PlayerHistory getPlayerHistory(String playerName);
}

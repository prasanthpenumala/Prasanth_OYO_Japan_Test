package com.oyo.japan.prasanth.resource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oyo.japan.prasanth.entity.ClientPlayer;
import com.oyo.japan.prasanth.entity.PlayerHistory;
import com.oyo.japan.prasanth.model.Player;
import com.oyo.japan.prasanth.service.PlayerService;

/**
 * This class is used a resource to provide the API's to client.
 * @author Prasanth.Penumala
 *
 */
@RestController
@RequestMapping("/player")
public class PlayerResource {

	@Autowired
	private PlayerService playerService;
	
	/**
	 * Service to Create Score into DB
	 * @param player
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Player> addPlayer(@RequestBody ClientPlayer player){
		ResponseEntity response = null;
		try{
			response = ResponseEntity.ok(playerService.addPlayer(player));
		}catch(Exception e) {
			response = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	/**
	 * Service to Get Score based on ScoreId(Unique Id)
	 * @param scoreId
	 * @return
	 */
	@GetMapping("{scoreId}")
	public ResponseEntity<Player> get(@PathVariable int scoreId){
		Player player = null;
		ResponseEntity response = null;
		try{
			player = playerService.getPlayerByScoreId(scoreId);
			if(player == null) {
				response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player Not Found with ScoreID:"+scoreId);
			}else {
				response = ResponseEntity.ok(player);
			}
		}catch(Exception e) {
			response = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return response;
	}
	
	/**
	 * Service to Delete Score based on ScoreId(Unique Id)
	 * @param scoreId
	 * @return
	 */
	@DeleteMapping("{scoreId}")
	public ResponseEntity<Player> delete(@PathVariable int scoreId){
		ResponseEntity response = null;
		try{
			response = ResponseEntity.status(HttpStatus.NO_CONTENT).body("Player Has been Successfully Deleted with ScoreID:"+scoreId);
		}catch(Exception e) {
			response = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	/**
	 * Service to Get list of Score based on the playerName
	 * @param playerName
	 * @return
	 */
	@GetMapping("name/{playerName}")
	public ResponseEntity<List<Player>> get(@PathVariable String playerName,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize){
		List<Player> players = new ArrayList<>();
		ResponseEntity response = null;
		try{
			players = playerService.getPlayerByName(playerName, pageNo, pageSize);
			if(players.isEmpty()) {
				response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player Not Found with PlayerName:"+playerName);
			}else {
				response = ResponseEntity.ok(players);
			}
		}catch(Exception e) {
			response = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return response;
	}
	
	/**
	 * Service to Get Score after Time
	 * @param afterTime
	 * @return
	 */
	@GetMapping("afterTime/{afterTime}")
	public ResponseEntity<List<Player>> getPlayersAfterTime(@PathVariable String afterTime,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize){
		List<Player> players = new ArrayList<>();
		ResponseEntity response = null;
		try{
			players = playerService.getPlayerDataAfterTime(afterTime, pageNo, pageSize);
			if(players.isEmpty()) {
				response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player Not Found after Time:"+afterTime);
			}else {
				response = ResponseEntity.ok(players);
			}
		}catch(Exception e) {
			response = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return response;
	}
	
	/**
	 * Service to Get score between Time
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@GetMapping("betweenTime/{startTime}/{endTime}")
	public ResponseEntity<List<Player>> getPlayersBetweenTime(@PathVariable String startTime, @PathVariable String endTime,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize){
		List<Player> players = new ArrayList<>();
		ResponseEntity response = null;
		try{
			players = playerService.getPlayerDataBetweenTime(startTime, endTime, pageNo, pageSize);
			if(players.isEmpty()) {
				response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player Not Found between Time:"+startTime+" and :"+endTime);
			}else {
				response = ResponseEntity.ok(players);
			}
		}catch(Exception e) {
			response = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return response;
	}
	
	
	/**
	 * Service to get Player History 
	 * @param playerName
	 * @return
	 */
	@GetMapping("playerHistory/{playerName}")
	public ResponseEntity<PlayerHistory> getPlayerHistory(@PathVariable String playerName){
		PlayerHistory playerHistory = null;
		ResponseEntity response = null;
		try {
			playerHistory = playerService.getPlayerHistory(playerName);
			if(playerHistory == null) {
				response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Player History Not Found for Player:"+playerName);
			}else {
				response = ResponseEntity.ok(playerHistory);
			}
		}catch(Exception e) {
			response = new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		return response;
	}
	
}

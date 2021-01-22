package com.oyo.japan.prasanth.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.oyo.japan.prasanth.entity.ClientPlayer;
import com.oyo.japan.prasanth.model.Player;
import com.oyo.japan.prasanth.repository.PlayerRepository;
import com.oyo.japan.prasanth.service.impl.PlayerServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class PlayerServiceTest {

	PlayerService playerService = Mockito.mock(PlayerService.class);
	
	PlayerRepository playerRepository = Mockito.mock(PlayerRepository.class);
	
	@Test
	public void testAddPlayer() {
		
	}

	@Test
	public void testGetPlayerByScoreId() {
	}

	@Test
	public void testGetPlayerByName() {
	}

	@Test
	public void testGetPlayerDataAfterTime() {
	}

	@Test
	public void testGetPlayerDataBetweenTime() {
	}

	@Test
	public void testDelete() {
	}

	@Test
	public void testGetPlayerHistory() {
	}

}

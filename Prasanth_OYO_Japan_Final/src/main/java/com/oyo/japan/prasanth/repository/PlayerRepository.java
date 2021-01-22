package com.oyo.japan.prasanth.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.oyo.japan.prasanth.model.Player;

/**
 * This class will be used to hold the DB Repository
 * @author Prasanth.Penumala
 *
 */
@Repository
public interface PlayerRepository extends CrudRepository<Player, Integer> {
	
	public Slice<Player> findByPlayerName(String playerName, Pageable pageable);
	public Slice<Player> findAllByTimeAfter(LocalDateTime time, Pageable pageable);
	public Slice<Player> findAllByTimeBetween(LocalDateTime startTime, LocalDateTime endTime, Pageable pageable);
	public List<Player> findByPlayerName(String playerName);
}

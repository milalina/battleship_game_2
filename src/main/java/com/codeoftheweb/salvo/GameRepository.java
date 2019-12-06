package com.codeoftheweb.salvo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface GameRepository extends JpaRepository<Game, Date> {
    List<Game> findByGameStart (Date gameStart);
    Game findGameById(Long id);
}
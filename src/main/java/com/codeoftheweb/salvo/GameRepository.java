package com.codeoftheweb.salvo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Set;

public interface GameRepository extends JpaRepository<Game, Date> {
    Set<Game> findByGameStart (Date gameStart);
    Game findGameById(Long id);
}
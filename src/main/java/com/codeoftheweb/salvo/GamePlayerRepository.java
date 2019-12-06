package com.codeoftheweb.salvo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface GamePlayerRepository extends JpaRepository<GamePlayer, Long> {
    Set<GamePlayer> findGamePlayerById (Long id);
}
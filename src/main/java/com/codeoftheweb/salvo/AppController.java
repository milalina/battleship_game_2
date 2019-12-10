package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api")
public class AppController {
    private GameRepository gameRepository;
    private GamePlayerRepository gamePlayerRepository;
    private SalvoRepository salvoRepository;
      private ShipRepository shipRepository;
   // private ScoreRepository scoreRepository;
    private PlayerRepository playerRepository;

    @Autowired //tells to create an instance of 'Repository' and store it in the instance variable 'repository'
    //private PlayerRepository playerRepository;
    public AppController(GameRepository gameRepository, GamePlayerRepository gamePlayerRepository,
                         ShipRepository shipRepository, SalvoRepository
            salvoRepository,  /*ScoreRepository scoreRepository,*/
                         PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.gamePlayerRepository = gamePlayerRepository;
        this.shipRepository = shipRepository;
        this.salvoRepository = salvoRepository;
       // this.scoreRepository = scoreRepository;
       // this.playerRepository = playerRepository;
    }

    @RequestMapping("/games")
    public List<Map<String, Object> >collectGameIds() {
        List <Map<String, Object>>gameDto = new ArrayList<>();
        List<Game>games=gameRepository.findAll();
        for(Game game: games){
            Map <String, Object> gameInfo = new HashMap<>();
            gameInfo.put("id", game.getId());
            gameInfo.put("created", game.getGameStart());
            gameInfo.put("gamePlayers", game.makeGamePlayerDto());
            gameDto.add(gameInfo);
        }
        return gameDto;
    }

    @RequestMapping("/game_view/{gamePlayerId}")
    public  Map<String, Object> createGameView(@PathVariable Long gamePlayerId) {
        GamePlayer gamePlayer = gamePlayerRepository.findGamePlayerById(gamePlayerId);
        Game game =gamePlayer.getGame();
        Map<String, Object> gameViewDto = new HashMap<>();
        gameViewDto.put("id", game.getId());
        gameViewDto.put("created", game.getGameStart());
        gameViewDto.put("gamePlayers", game.makeGamePlayerDto());
        gameViewDto.put("ships", gamePlayer.createShipsDto());
        return gameViewDto;
    }



}
package com.codeoftheweb.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api")
public class AppController {
    private GameRepository gameRepository;
    private GamePlayerRepository gamePlayerRepository;
   // private SalvoRepository salvoRepository;
  //  private ShipRepository shipRepository;
   // private ScoreRepository scoreRepository;
    private PlayerRepository playerRepository;

    @Autowired //tells to create an instance of 'Repository' and store it in the instance variable 'repository'
    //private PlayerRepository playerRepository;
    public AppController(GameRepository gameRepository, GamePlayerRepository gamePlayerRepository, /*SalvoRepository
            salvoRepository, ShipRepository shipRepository, ScoreRepository scoreRepository,*/
                         PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.gamePlayerRepository = gamePlayerRepository;
     //   this.shipRepository = shipRepository;
       // this.salvoRepository = salvoRepository;
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
            gameDto.add(gameInfo);
        }
        return gameDto;
    }

}
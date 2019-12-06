package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(PlayerRepository playerRepository, GameRepository gameRepository,
                                      GamePlayerRepository gamePlayerRepository) {
		return (args) -> {

			Player player1 = new Player("j.bauer@ctu.gov");
			Player player2 = new Player("c.obrian@ctu.gov");
			Player player3 = new Player("kim_bauer@gmail.com");
			Player player4 = new Player("t.almeida@ctu.gov");

            Date date1 = new Date ();
            Date date2 = new Date(System.currentTimeMillis() - 3600 * 1000);
            Date date3 = new Date(System.currentTimeMillis() - 7200 * 1000);

            Game game1 =new Game(date1);
            Game game2 =new Game(date2);
            Game game3 =new Game(date3);

            GamePlayer gamePlayer1 = new GamePlayer(player1, game1, date1);
            GamePlayer gamePlayer2 = new GamePlayer(player2, game1, date1);
            GamePlayer gamePlayer3 = new GamePlayer(player1, game2, date2);
            GamePlayer gamePlayer4 = new GamePlayer(player2, game2, date2);
            GamePlayer gamePlayer5 = new GamePlayer(player2, game3, date3);
            GamePlayer gamePlayer6 = new GamePlayer(player4, game3, date3);


			playerRepository.save(player1);
			playerRepository.save(player2);
			playerRepository.save(player3);
			playerRepository.save(player4);

			gameRepository.save(game1);
            gameRepository.save(game2);
            gameRepository.save(game3);

            gamePlayerRepository.save(gamePlayer1);
            gamePlayerRepository.save(gamePlayer2);
            gamePlayerRepository.save(gamePlayer3);
            gamePlayerRepository.save(gamePlayer4);
            gamePlayerRepository.save(gamePlayer5);
            gamePlayerRepository.save(gamePlayer6);
		};
	}
}

package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(PlayerRepository playerRepository, GameRepository gameRepository,
                                      GamePlayerRepository gamePlayerRepository, ShipRepository shipRepository,
                                      SalvoRepository salvoRepository) {
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

            List<String> shipLocations1 = Arrays.asList("H2", "H3", "H4");
            List<String> shipLocations2 = Arrays.asList("E1", "F1", "G1");
            List<String> shipLocations3 = Arrays.asList("B4", "B5");
            List<String> shipLocations4 = Arrays.asList("B5", "C5", "D5");
            List<String> shipLocations5 = Arrays.asList("F1", "F2");
            List<String> shipLocations6 = Arrays.asList("B5", "C5", "D5");
            List<String> shipLocations7 = Arrays.asList("C6", "C7");
            List<String> shipLocations8 = Arrays.asList("A2", "A3", "A4");
            List<String> shipLocations9 = Arrays.asList("G6", "H6");
            List<String> shipLocations10 = Arrays.asList("H2", "H3", "H4");
            List<String> shipLocations11 = Arrays.asList("E1", "F1", "G1");
            List<String> shipLocations12 = Arrays.asList("B4", "B5");

            Ship ship1 = new Ship("Destroyer", shipLocations1);
            Ship ship2 = new Ship("Submarine", shipLocations2);
            Ship ship3 = new Ship("Patrol Boat", shipLocations3);
            Ship ship4 = new Ship("Destroyer", shipLocations4);
            Ship ship5 = new Ship("Patrol Boat", shipLocations5);
            Ship ship6 = new Ship("Submarine", shipLocations6);
            Ship ship7 = new Ship("Patrol Boat", shipLocations7);
            Ship ship8 = new Ship("Destroyer", shipLocations8);
            Ship ship9 = new Ship("Patrol Boat", shipLocations9);
            Ship ship10 = new Ship("Destroyer", shipLocations10);
            Ship ship11 = new Ship("Submarine", shipLocations11);
            Ship ship12 = new Ship("Patrol Boat", shipLocations12);

            gamePlayer1.addShip(ship1);
            gamePlayer1.addShip(ship2);
            gamePlayer1.addShip(ship3);
            gamePlayer2.addShip(ship4);
            gamePlayer2.addShip(ship5);
            gamePlayer3.addShip(ship6);
            gamePlayer3.addShip(ship7);
            gamePlayer4.addShip(ship8);
            gamePlayer4.addShip(ship9);
            gamePlayer5.addShip(ship10);
            gamePlayer5.addShip(ship11);
            gamePlayer6.addShip(ship12);

            Date turn1 = new Date();
            Date turn2 = new Date();

            List<String> salvoLocations1=Arrays.asList("B5", "C5", "F1");
            List<String> salvoLocations2=Arrays.asList("B4", "B5", "B6");
            List<String> salvoLocations3=Arrays.asList("F2", "D5");
            List<String> salvoLocations4=Arrays.asList("E1", "H3", "A2");
            List<String> salvoLocations5=Arrays.asList("E1", "H3", "A2");

            Salvo salvo1= new Salvo(turn1, salvoLocations1);
            Salvo salvo2= new Salvo(turn1, salvoLocations2);
            Salvo salvo3= new Salvo(turn2, salvoLocations3);
            Salvo salvo4= new Salvo(turn2, salvoLocations4);
            Salvo salvo5= new Salvo(turn1, salvoLocations5);

            gamePlayer1.addSalvo(salvo1);
            gamePlayer1.addSalvo(salvo3);
            gamePlayer2.addSalvo(salvo2);
            gamePlayer2.addSalvo(salvo4);
            gamePlayer3.addSalvo(salvo5);

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

            salvoRepository.save(salvo1);
            salvoRepository.save(salvo2);
            salvoRepository.save(salvo3);
            salvoRepository.save(salvo4);
            salvoRepository.save(salvo5);

            shipRepository.save(ship1);
            shipRepository.save(ship2);
            shipRepository.save(ship3);
            shipRepository.save(ship4);
            shipRepository.save(ship5);
            shipRepository.save(ship6);
            shipRepository.save(ship7);
            shipRepository.save(ship8);
            shipRepository.save(ship9);
            shipRepository.save(ship10);
            shipRepository.save(ship11);
            shipRepository.save(ship12);


		};
	}
}

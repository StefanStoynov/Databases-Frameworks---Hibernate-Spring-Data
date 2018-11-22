package gamestore.service;

import gamestore.domain.dtos.AddGameDto;

public interface GameService {

    String addGame(AddGameDto addGameDto);
}

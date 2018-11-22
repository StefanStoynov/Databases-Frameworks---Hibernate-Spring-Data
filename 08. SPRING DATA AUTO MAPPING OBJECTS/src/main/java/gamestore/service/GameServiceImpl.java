package gamestore.service;

import gamestore.domain.dtos.AddGameDto;
import gamestore.domain.entities.Game;
import gamestore.repository.GameRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class GameServiceImpl implements GameService{
    private final GameRepository gameRepository;
    private ModelMapper modelMapper;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
        this.modelMapper = new ModelMapper();
    }


    @Override
    public String addGame(AddGameDto addGameDto) {
        StringBuilder sb = new StringBuilder();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<AddGameDto>> violations = validator.validate(addGameDto);
        if (violations.size() > 0) {
            for (ConstraintViolation<AddGameDto> violation : violations) {
                sb.append(violation).append(System.lineSeparator());
            }
        } else {

            Game entity = this.gameRepository.findByTitle(addGameDto.getTitle()).orElse(null);

            if (entity != null){
                sb.append("Game already exist");
                return sb.toString();
            }else{
                sb.append(String.format("Added %s", addGameDto.getTitle()));
                entity = this.modelMapper.map(addGameDto,Game.class);
                this.gameRepository.saveAndFlush(entity);
            }
        }
        return sb.toString().trim();
    }
}

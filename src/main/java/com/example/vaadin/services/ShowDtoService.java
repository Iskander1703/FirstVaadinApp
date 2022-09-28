package com.example.vaadin.services;

import com.example.vaadin.dto.ShowDto;
import com.example.vaadin.models.Show;
import com.example.vaadin.repositories.ShowRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShowDtoService {

    private static final Logger log = LoggerFactory.getLogger(ShowDtoService.class);

    private final ShowRepository showRepository;


    @Autowired
    public ShowDtoService(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    @Transactional
    public void saveShow(ShowDto showDto) {
        Show show = convertToShow(showDto);
        Optional<Show> optionalShow = showRepository.findById(show.getIdShow());
        if (optionalShow.isPresent()) {
            log.debug("Start edit show with id = {}", show.getIdShow());
            Show showFromRepository = optionalShow.get();
            showFromRepository.setName(show.getName());
            showFromRepository.setAgeRestriction(show.getAgeRestriction());
            showFromRepository.setCity(show.getCity());
            showRepository.save(showFromRepository);
            log.debug("Successfully edit show with id = {}", show.getIdShow());
        } else {
            log.debug("Start save new show with id = {}", show.getIdShow());
            showRepository.save(show);
            log.debug("Successfully save new show with id = {}", show.getIdShow());
        }
    }

    @Transactional
    public ShowDto findShowById(Integer id) {
        log.debug("Start find show with id = {}", id);
        Optional<Show> optionalShow = showRepository.findById(id);

        if (optionalShow.isPresent()) {
            log.debug("Successfully find show with id = {}", id);
            return convertToShowDto(optionalShow.get());
        } else {
            log.debug("Error find show with id = {}", id);
            return null;
        }
    }

    @Transactional
    public List<ShowDto> getAllShowDto() {
        log.debug("Start get all show");
        List<ShowDto> list = showRepository.findAll()
                .stream()
                .map(a -> convertToShowDto(a))
                .collect(Collectors.toList());
        log.debug("Successfully get all show");
        return list;
    }

    @Transactional
    public void deleteShowById(ShowDto showDto) {
        log.debug("Start delete show with id = {}", showDto.getShowId());
        System.out.println("Start delete show with id " + "= " + showDto.getShowId());
        showRepository.deleteById(showDto.getShowId());
        log.debug("Successfully delete show with id = {}", showDto.getShowId());
    }

    private ShowDto convertToShowDto(Show show) {
        ShowDto showDto = new ShowDto();
        showDto.setShowId(show.getIdShow());
        showDto.setCity(show.getCity());
        showDto.setName(show.getName());
        showDto.setAgeRestriction(show.getAgeRestriction());
        return showDto;
    }

    private Show convertToShow(ShowDto showDto) {
        Show show = new Show();
        show.setIdShow(showDto.getShowId());
        show.setCity(showDto.getCity());
        show.setName(showDto.getName());
        show.setAgeRestriction(showDto.getAgeRestriction());
        return show;
    }
}

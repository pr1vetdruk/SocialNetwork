package ru.privetdruk.socialnetwork.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.privetdruk.socialnetwork.domain.City;
import ru.privetdruk.socialnetwork.repository.CityRepository;

@Service
public class GeneralServiceImpl implements GeneralService {
    private final CityRepository cityRepository;

    public GeneralServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public void fillingCity(Model model) {
        if (!model.containsAttribute("citiesList"))
            model.addAttribute("citiesList", cityRepository.findAll());
    }

    @Override
    public City findCity(Short id) {
        return cityRepository.findById(id);
    }
}

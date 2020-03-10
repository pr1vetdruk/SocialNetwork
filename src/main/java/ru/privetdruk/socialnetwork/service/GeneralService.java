package ru.privetdruk.socialnetwork.service;

import org.springframework.ui.Model;
import ru.privetdruk.socialnetwork.domain.City;

public interface GeneralService {
    void fillingCity(Model model);
    City findCity(Short id);
}

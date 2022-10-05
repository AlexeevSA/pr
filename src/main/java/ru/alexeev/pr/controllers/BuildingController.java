package ru.alexeev.pr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.alexeev.pr.models.Building;
import ru.alexeev.pr.models.Floor;
import ru.alexeev.pr.repository.BuildingRepository;
import ru.alexeev.pr.repository.FloorRepository;

@Controller
public class BuildingController {

    @Autowired
    public BuildingRepository buildingRepository;
    @Autowired
    public FloorRepository floorRepository;

    @GetMapping("/building")
    public String Main(Model model){
        Iterable<Floor> floors = floorRepository.findAll();
        model.addAttribute("floor",floors);
        return "building-add";
    }

    @PostMapping("/building/add")
    public String blogPostAdd(@RequestParam String adress,
                              @RequestParam String type,
                              @RequestParam String info,
                              Model model)
    {
        Floor floor = floorRepository.findByInfo(info);
        Building building = new Building(adress, type, floor);
        buildingRepository.save(building);
        return "redirect:/building";
    }
}

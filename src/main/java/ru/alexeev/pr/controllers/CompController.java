package ru.alexeev.pr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alexeev.pr.models.Computer;
import ru.alexeev.pr.repository.CompRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/computer")
public class CompController {
    @Autowired
    private CompRepository compRepository;


    @GetMapping("/add")
    public String blogAddComp(Model model, Computer computer){
        return "blog-addComp";
    }

    @PostMapping("/add")
    public String blogPostAddComp(@Valid Computer computer, BindingResult bindingResult,
                                  Model model)
    {
        if (bindingResult.hasErrors()){
            return "blog-addComp";
        }
        compRepository.save(computer);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String blogDetailsComp(@PathVariable(value = "id") long id, Model model)
    {
        Optional<Computer> computer = compRepository.findById(id);
        ArrayList<Computer> compRes = new ArrayList<>();
        computer.ifPresent(compRes::add);
        model.addAttribute("computer", compRes);
        if(!compRepository.existsById(id))
        {
            return "redirect:/blog";
        }
//        Post post = postRepository.findById(id).orElseThrow();
//        model.addAttribute("onepost", post);
        return "blog-detailsComp";
    }

    @GetMapping("/{id}/edit")
    public String blogCompEdit(@PathVariable(value = "id") long id, Model model, Computer computer)
    {
        if(!compRepository.existsById(id))
        {
            return "redirect:/";
        }
//        Optional<Computer> computer = compRepository.findById(id);
//        ArrayList<Computer> compRes = new ArrayList<>();
//        computer.ifPresent(compRes::add);
        computer = compRepository.findById(id).orElseThrow();
        model.addAttribute("computer", computer);
        return "blog-editComp";
    }
    @PostMapping("/{id}/edit")
    public String blogCompUpdate (@PathVariable("id")long id,@Valid Computer computer,
                                  BindingResult bindingResult,
                                  Model model)
    {
        if (bindingResult.hasErrors()){
            return "blog-editComp";
        }
        compRepository.save(computer);
        return "redirect:/";
    }
    @PostMapping("/{id}/remove")
    public String blogCompDelete(@PathVariable("id")long id, Model model){
        Computer computer = compRepository.findById(id).orElseThrow();
        compRepository.delete(computer);
//        postRepository.deleteById(id);
        return "redirect:/";
    }
}

package ru.alexeev.pr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alexeev.pr.models.Keyboard;
import ru.alexeev.pr.repository.KeyBoardRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/keyboard")
public class KeyboardController {
    @Autowired
    private KeyBoardRepository keyBoardRepository;

    @GetMapping("/add")
    public String blogAddKeyboard(Model model, Keyboard keyboard){
        return "blog-addKeyboard";
    }

    @PostMapping("/add")
    public String blogPostAddKeyboard(@Valid Keyboard keyboard, BindingResult bindingResult,
                                      Model model)
    {
        if (bindingResult.hasErrors()){
            return "blog-addKeyboard";
        }
        keyBoardRepository.save(keyboard);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String blogDetailsKey(@PathVariable(value = "id") long id, Model model)
    {
        Optional<Keyboard> keyboard = keyBoardRepository.findById(id);
        ArrayList<Keyboard> keyRes = new ArrayList<>();
        keyboard.ifPresent(keyRes::add);
        model.addAttribute("keyboard", keyRes);
        if(!keyBoardRepository.existsById(id))
        {
            return "redirect:/blog";
        }
        //Post post = postRepository.findById(id).orElseThrow();
        //model.addAttribute("onepost", post);
        return "blog-detailsKeyboard";
    }
    @GetMapping("/{id}/edit")
    public String blogKeyEdit(@PathVariable(value = "id") long id, Model model, Keyboard keyboard)
    {
        if(!keyBoardRepository.existsById(id))
        {
            return "redirect:/";
        }
//        Optional<Keyboard> keyboard = keyBoardRepository.findById(id);
//        ArrayList<Keyboard> keyRes = new ArrayList<>();
//        keyboard.ifPresent(keyRes::add);
        keyboard = keyBoardRepository.findById(id).orElseThrow();
        model.addAttribute("keyboard", keyboard);
        return "blog-editKeyboard";
    }

    @PostMapping("/{id}/edit")
    public String blogKeyUpdate (@PathVariable("id")long id, @Valid Keyboard keyboard,
                                 BindingResult bindingResult,
                                 Model model)
    {
        if (bindingResult.hasErrors()){
            return "blog-editKeyboard";
        }
        keyBoardRepository.save(keyboard);
        return "redirect:/";
    }

    @PostMapping("/{id}/remove")
    public String blogKeyDelete(@PathVariable("id")long id, Model model){
        Keyboard keyboard = keyBoardRepository.findById(id).orElseThrow();
        keyBoardRepository.delete(keyboard);
//        postRepository.deleteById(id);
        return "redirect:/";
    }
}

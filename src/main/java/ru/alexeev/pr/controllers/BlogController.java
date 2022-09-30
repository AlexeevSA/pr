package ru.alexeev.pr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alexeev.pr.models.Computer;
import ru.alexeev.pr.models.Keyboard;
import ru.alexeev.pr.models.Post;
import ru.alexeev.pr.repository.CompRepository;
import ru.alexeev.pr.repository.KeyBoardRepository;
import ru.alexeev.pr.repository.PostRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BlogController  {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CompRepository compRepository;
    @Autowired
    private KeyBoardRepository keyBoardRepository;
    @GetMapping("/")
    public String blogMain(Model model)
    {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);

        Iterable<Computer> computers = compRepository.findAll();
        model.addAttribute("computer", computers);

        Iterable<Keyboard> keyboards = keyBoardRepository.findAll();
        model.addAttribute("keyboard", keyboards);

        return "blog-main";
    }

   @GetMapping("/blog/add")
    public String blogAdd(Model model, Post post)
    {
        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(//@RequestParam String title,
                              //@RequestParam String anons,
                              //@RequestParam String full_text,
                              @Valid Post post, BindingResult bindingResult,
                              Model model)
    {
//        Post post = new Post(title, anons, full_text);
        if (bindingResult.hasErrors()){
            return "blog-add";
        }
        postRepository.save(post);
        return "redirect:/";
    }

    @GetMapping("/blog/filter")
    public String blogFilter(Model model)
    {
        return "blog-filter";
    }

    @PostMapping("/blog/filter/result")
    public String blogResult(@RequestParam(required = false) String cpu ,
                             @RequestParam(required = false) String gpu,
                             @RequestParam(required = false) String name,
                             @RequestParam(required = false) Integer formPer,
                             @RequestParam (required = false) String title, Model model)
    {
        if (title != null){
            List<Post> result = postRepository.findByTitleContains(title);
            model.addAttribute("result", result);
//        List<Post> result = postRepository.findLikeTitle(title);
        }

        if(cpu != null){
            List<Computer> compResContains = compRepository.findByCpuContains(cpu);
            model.addAttribute("compResContains", compResContains);
        }
        if (gpu != null){
            List<Computer> compRes = compRepository.findByGpu(gpu);
            model.addAttribute("compRes", compRes);
        }
        if (name != null){
            List<Keyboard> keyName = keyBoardRepository.findByNameContaining(name);
            model.addAttribute("keyName", keyName);
        }
        if (formPer != null){

            List<Keyboard> keyForm = keyBoardRepository.findByFormPer(formPer);
            model.addAttribute("keyForm", keyForm);
        }
        return "blog-filter";
    }
}

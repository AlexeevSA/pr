package ru.alexeev.pr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.alexeev.pr.models.Computer;
import ru.alexeev.pr.models.Keyboard;
import ru.alexeev.pr.models.Post;
import ru.alexeev.pr.repository.CompRepository;
import ru.alexeev.pr.repository.KeyBoardRepository;
import ru.alexeev.pr.repository.PostRepository;

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
    public String blogAdd(Model model)
    {
        return "blog-add";
    }

    @GetMapping("/blog/addKeyboard")
    public String blogAddComp(Model model){
        return "blog-addComp";
    }

    @PostMapping("/blog/addKeyboard")
    public String blogPostAddComp(@RequestParam String name,
                                  @RequestParam String type,
                                  @RequestParam String switches,
                                  @RequestParam Integer formPer,
                                  @RequestParam Integer keyNums,
                                  Model model)
    {
        Keyboard keyboard = new Keyboard(name, type, switches, formPer, keyNums);
        keyBoardRepository.save(keyboard);
        return "redirect:/";
    }

    @GetMapping("/blog/addComp")
    public String blogAddKeyboard(Model model){
        return "blog-addKeyboard";
    }

    @PostMapping("/blog/addComp")
    public String blogPostAddKeyboard(@RequestParam String cpu,
                                  @RequestParam String gpu,
                                  @RequestParam String chipset,
                                  @RequestParam Integer ramMb,
                                  @RequestParam Integer romGb,
                                  Model model)
    {
        Computer comp = new Computer(cpu, gpu, chipset, ramMb, romGb);
        compRepository.save(comp);
        return "redirect:/";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title,
                              @RequestParam String anons,
                              @RequestParam String full_text, Model model)
    {
        Post post = new Post(title, anons, full_text);
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



    @GetMapping("/blog/computer/{id}")
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
        return "blog-details";
    }

    @GetMapping("/blog/keyboard/{id}")
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
//        Post post = postRepository.findById(id).orElseThrow();
//        model.addAttribute("onepost", post);
        return "blog-details";
    }

    @GetMapping("/blog/computer/{id}/edit")
    public String blogCompEdit(@PathVariable(value = "id") long id, Model model)
    {
        if(!compRepository.existsById(id))
        {
            return "redirect:/";
        }
        Optional<Computer> computer = compRepository.findById(id);
        ArrayList<Computer> compRes = new ArrayList<>();
        computer.ifPresent(compRes::add);
        model.addAttribute("computer", compRes);
        return "blog-edit";
    }
    @GetMapping("/blog/keyboard/{id}/edit")
    public String blogKeyEdit(@PathVariable(value = "id") long id, Model model)
    {
        if(!keyBoardRepository.existsById(id))
        {
            return "redirect:/";
        }
        Optional<Keyboard> keyboard = keyBoardRepository.findById(id);
        ArrayList<Keyboard> keyRes = new ArrayList<>();
        keyboard.ifPresent(keyRes::add);
        model.addAttribute("keyboard", keyRes);
        return "blog-edit";
    }

    @PostMapping("/blog/computer/{id}/edit")
    public String blogCompUpdate (@PathVariable("id")long id,
                                  @RequestParam String cpu,
                                  @RequestParam String gpu,
                                  @RequestParam String chipset,
                                  @RequestParam Integer ramMb,
                                  @RequestParam Integer romGb,
                                  Model model)
    {
        Computer computer = compRepository.findById(id).orElseThrow();
        computer.setCpu(cpu);
        computer.setGpu(gpu);
        computer.setChipset(chipset);
        computer.setRamMb(ramMb);
        computer.setRomGb(romGb);
        compRepository.save(computer);
        return "redirect:/";
    }
    @PostMapping("/blog/keyboard/{id}/edit")
    public String blogKeyUpdate (@PathVariable("id")long id,
                                  @RequestParam String name,
                                  @RequestParam String type,
                                  @RequestParam String switches,
                                  @RequestParam Integer formPer,
                                  @RequestParam Integer keyNums,
                                  Model model)
    {
        Keyboard keyboard = keyBoardRepository.findById(id).orElseThrow();
        keyboard.setName(name);
        keyboard.setType(type);
        keyboard.setSwitches(switches);
        keyboard.setFormPer(formPer);
        keyboard.setKeyNums(keyNums);
        keyBoardRepository.save(keyboard);
        return "redirect:/";
    }

    @PostMapping("/blog/computer/{id}/remove")
    public String blogCompDelete(@PathVariable("id")long id, Model model){
        Computer computer = compRepository.findById(id).orElseThrow();
        compRepository.delete(computer);
//        postRepository.deleteById(id);
        return "redirect:/";
    }
    @PostMapping("/blog/keyboard/{id}/remove")
    public String blogKeyDelete(@PathVariable("id")long id, Model model){
        Keyboard keyboard = keyBoardRepository.findById(id).orElseThrow();
        keyBoardRepository.delete(keyboard);
//        postRepository.deleteById(id);
        return "redirect:/";
    }


}

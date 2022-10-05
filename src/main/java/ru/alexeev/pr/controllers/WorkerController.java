package ru.alexeev.pr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.alexeev.pr.models.JobPost;
import ru.alexeev.pr.models.Worker;
import ru.alexeev.pr.repository.JobPostRepository;
import ru.alexeev.pr.repository.WorkerRepository;

@Controller
public class WorkerController {
    @Autowired
    private JobPostRepository jobPostRepository;
    @Autowired
    private WorkerRepository workerRepository;

    @GetMapping("/worker")
    private String Main(Model model){
        Iterable<JobPost> jobPosts = jobPostRepository.findAll();
        model.addAttribute("jobPosts", jobPosts);
        Iterable<Worker> workers = workerRepository.findAll();
        model.addAttribute("workers", workers);

        return "worker-add";
    }

    @PostMapping("/worker/add")
    public String blogPostAdd(@RequestParam String jobPost,
                              @RequestParam String worker,
                              Model model)
    {
        JobPost jobPost2 = jobPostRepository.findByName(jobPost);
        Worker worker2 = workerRepository.findByFirstname(worker);
        jobPost2.getWorker().add(worker2);
        jobPostRepository.save(jobPost2);
        return "redirect:/worker";
    }
}

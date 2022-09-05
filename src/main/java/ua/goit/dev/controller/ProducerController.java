package ua.goit.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.goit.dev.model.dao.Producer;
import ua.goit.dev.service.ProducerService;

import java.util.UUID;

@Controller
@RequestMapping("/producer")
public class ProducerController {
    @Autowired
    private ProducerService producerService;

    @GetMapping
    public String viewListOfProducers(Model model) {
        model.addAttribute("producers", producerService.getAllDto());
        return "producers";
    }


    @GetMapping("remove/{id}")
    public String delete(@PathVariable UUID id){
        producerService.deleteById(id);
        return "redirect:/producer";
    }


    @PostMapping(path = "/new")
    public String addOrUpdateProducer(@ModelAttribute("producer") Producer producer) {
        producerService.createOrUpdate(producer);
        return "redirect:/producer";
    }


    @GetMapping(path = "/new")
    public String create(Model model) {
        model.addAttribute("producer", new Producer());
        return "addFormProducer";
    }
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") UUID id, Model model) {
        Producer producer = producerService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("producer", producer);
        return "updateProducer";
    }
    @PostMapping("/update/{id}")
    public String updateProducer(@PathVariable("id") UUID id, @ModelAttribute("producer") Producer producer) {
        Producer producers = producerService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        producers.setName(producer.getName());
        producerService.createOrUpdate(producers);
        return "redirect:/producer";
    }



}

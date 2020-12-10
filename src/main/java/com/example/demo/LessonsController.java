package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/lessons")
public class LessonsController {

    private final LessonRepository repository;

    @GetMapping("/{id}")
    public Optional<Lesson> show(@PathVariable int id) {
        Long newId = new Long(id);
//        Optional<Lesson> newlesson = this.repository.findById(newId);
//        Lesson something = newlesson.get();
//        long identification = something.getId();
        return this.repository.findById(newId);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.repository.deleteById(id);
    }


    public LessonsController(LessonRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public Iterable<Lesson> all() {
        return this.repository.findAll();
    }

    @PostMapping("")
    public Lesson create(@RequestBody Lesson lesson) {
        return this.repository.save(lesson);
    }

    @PatchMapping("/{id}")
    public Lesson patch(@PathVariable Long id, @RequestBody Lesson lesson) {
        Lesson oldLesson = this.repository.findById(id).get();
        lesson.setId(oldLesson.getId());
        return this.repository.save(lesson);
    }

}

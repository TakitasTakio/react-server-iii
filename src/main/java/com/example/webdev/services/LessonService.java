package com.example.webdev.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.webdev.models.Lesson;
import com.example.webdev.models.Module;

import com.example.webdev.repositories.LessonRepository;
import com.example.webdev.repositories.ModuleRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class LessonService {
	
	@Autowired
	ModuleRepository moduleRepository;
	@Autowired
	LessonRepository lessonRepository;
	
	@PostMapping("/api/module/{moduleId}/lesson")
	public Lesson createLesson(
			@PathVariable("moduleId") int moduleId,
			@RequestBody Lesson newLesson) {
		Optional<Module> data = moduleRepository.findById(moduleId);
		if(data.isPresent()) {
			Module module = data.get();
			newLesson.setModule(module);
			return lessonRepository.save(newLesson);
		}
		return null;
	}
	@GetMapping("/api/module/{moduleId}/lesson")
	public List<Lesson> findAllLessonsForModule(
			@PathVariable("moduleId") int moduleId) {
		Optional<Module> data =
	moduleRepository.findById(moduleId);
		if(data.isPresent()) {
			Module module = data.get();
			return module.getLessons();
		}
		return null;		
	}
	
	@GetMapping("api/lesson")
	public Iterable <Lesson> findAllLessons()
	{return lessonRepository.findAll();} 
	
	
	
	@DeleteMapping("/api/lesson/{lId}")
	public void deleteLesson(
	  @PathVariable("lId")
	    int lessonId) {
		lessonRepository.deleteById(lessonId);
	}

}

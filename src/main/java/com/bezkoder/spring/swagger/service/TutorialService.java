package com.bezkoder.spring.swagger.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.bezkoder.spring.swagger.model.Tutorial;

@Service
public class TutorialService {

  private static final List<Tutorial> tutorials = new ArrayList<>();
  private static long idCounter = 0;

  // Static block to initialize the list with sample data
  static {
    tutorials.add(new Tutorial(1, "Spring Boot Basics", "Introduction à Spring Boot", true));
    tutorials.add(new Tutorial(2, "Swagger Integration", "Documentation API avec Swagger", false));
    tutorials.add(new Tutorial(3, "Spring Security", "Sécuriser une application Spring", true));
    tutorials.add(new Tutorial(4, "Docker for Java Apps", "Déploiement avec Docker", false));
  }

  public List<Tutorial> findAll() {
    return new ArrayList<>(tutorials);
  }

  public List<Tutorial> findByTitleContaining(String title) {
    return tutorials.stream()
        .filter(tutorial -> tutorial.getTitle().toLowerCase().contains(title.toLowerCase()))
        .toList();
  }

  public Tutorial findById(long id) {
    return tutorials.stream().filter(tutorial -> tutorial.getId() == id).findFirst().orElse(null);
  }

  public Tutorial save(Tutorial tutorial) {
    if (tutorial.getId() != 0) {
      for (int i = 0; i < tutorials.size(); i++) {
        if (tutorials.get(i).getId() == tutorial.getId()) {
          tutorials.set(i, tutorial);
          return tutorial;
        }
      }
    } else {
      tutorial.setId(++idCounter);
      tutorials.add(tutorial);
    }
    return tutorial;
  }

  public void deleteById(long id) {
    tutorials.removeIf(tutorial -> tutorial.getId() == id);
  }

  public void deleteAll() {
    tutorials.clear();
  }

  public List<Tutorial> findByPublished(boolean isPublished) {
    return tutorials.stream()
        .filter(tutorial -> tutorial.isPublished() == isPublished)
        .toList();
  }
}

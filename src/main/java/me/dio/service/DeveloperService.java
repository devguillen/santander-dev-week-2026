package me.dio.service;

import me.dio.domain.model.Developer;
import me.dio.domain.model.Quest;

public interface DeveloperService {
    Developer findById(Long id);
    Developer create(Developer developer);
    Developer completeQuest(Long developerId, Quest quest);
}

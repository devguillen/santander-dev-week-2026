package me.dio.service.impl;

import me.dio.controller.exception.NotFoundException;
import me.dio.domain.model.Developer;
import me.dio.domain.model.Quest;
import me.dio.domain.repository.DeveloperRepository;
import me.dio.service.DeveloperService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeveloperServiceImpl implements DeveloperService {

    private final DeveloperRepository developerRepository;

    public DeveloperServiceImpl(DeveloperRepository developerRepository) {
        this.developerRepository = developerRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Developer findById(Long id) {
        return developerRepository.findById(id).orElseThrow(() -> new NotFoundException("Developer not found"));
    }

    @Override
    @Transactional
    public Developer create(Developer developer) {
        if (developer.getId() != null && developerRepository.existsById(developer.getId())) {
            throw new IllegalArgumentException("This Developer ID already exists.");
        }
        if (developer.getLevel() == null) developer.setLevel(1);
        if (developer.getCurrentXp() == null) developer.setCurrentXp(0);
        return developerRepository.save(developer);
    }

    @Override
    @Transactional
    public Developer completeQuest(Long developerId, Quest quest) {
        Developer dev = findById(developerId);
        quest.setIsCompleted(true);
        dev.getQuests().add(quest);
        
        // Calculo de XP / Level Up
        dev.setCurrentXp(dev.getCurrentXp() + quest.getXpReward());
        if (dev.getCurrentXp() >= dev.getLevel() * 1000) {
            dev.setCurrentXp(dev.getCurrentXp() - (dev.getLevel() * 1000));
            dev.setLevel(dev.getLevel() + 1);
        }
        
        return developerRepository.save(dev);
    }
}

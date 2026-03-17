package me.dio.controller.dto;

import me.dio.domain.model.Quest;

public record QuestDto(Long id, String title, String description, Integer xpReward, Boolean isCompleted, String githubUrl) {
    public QuestDto(Quest model) {
        this(model.getId(), model.getTitle(), model.getDescription(), model.getXpReward(), model.getIsCompleted(), model.getGithubUrl());
    }

    public Quest toModel() {
        Quest model = new Quest();
        model.setId(this.id);
        model.setTitle(this.title);
        model.setDescription(this.description);
        model.setXpReward(this.xpReward);
        model.setIsCompleted(this.isCompleted);
        model.setGithubUrl(this.githubUrl);
        return model;
    }
}

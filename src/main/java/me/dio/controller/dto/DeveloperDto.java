package me.dio.controller.dto;

import me.dio.domain.model.Developer;
import java.util.List;
import java.util.stream.Collectors;

public record DeveloperDto(
        Long id,
        String name,
        String heroClass,
        Integer level,
        Integer currentXp,
        String bio,
        List<SkillDto> skills,
        List<QuestDto> quests) {

    public DeveloperDto(Developer model) {
        this(
            model.getId(),
            model.getName(),
            model.getHeroClass(),
            model.getLevel(),
            model.getCurrentXp(),
            model.getBio(),
            model.getSkills() != null ? model.getSkills().stream().map(SkillDto::new).collect(Collectors.toList()) : null,
            model.getQuests() != null ? model.getQuests().stream().map(QuestDto::new).collect(Collectors.toList()) : null
        );
    }

    public Developer toModel() {
        Developer model = new Developer();
        model.setId(this.id);
        model.setName(this.name);
        model.setHeroClass(this.heroClass);
        model.setLevel(this.level);
        model.setCurrentXp(this.currentXp);
        model.setBio(this.bio);
        if (this.skills != null) model.setSkills(this.skills.stream().map(SkillDto::toModel).collect(Collectors.toList()));
        if (this.quests != null) model.setQuests(this.quests.stream().map(QuestDto::toModel).collect(Collectors.toList()));
        return model;
    }
}

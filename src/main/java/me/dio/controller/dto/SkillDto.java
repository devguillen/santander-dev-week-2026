package me.dio.controller.dto;

import me.dio.domain.model.Skill;

public record SkillDto(Long id, String name, String type, String imageUrl) {
    public SkillDto(Skill model) {
        this(model.getId(), model.getName(), model.getType() != null ? model.getType().name() : null, model.getImageUrl());
    }

    public Skill toModel() {
        Skill model = new Skill();
        model.setId(this.id);
        model.setName(this.name);
        if (this.type != null) {
            model.setType(me.dio.domain.model.SkillType.valueOf(this.type));
        }
        model.setImageUrl(this.imageUrl);
        return model;
    }
}

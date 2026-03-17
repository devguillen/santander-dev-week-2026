package me.dio.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.dio.controller.dto.DeveloperDto;
import me.dio.controller.dto.QuestDto;
import me.dio.domain.model.Developer;
import me.dio.service.DeveloperService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/developers")
@Tag(name = "Developer Profile", description = "Endpoints to manage the gamified developer profile")
public class DeveloperController {

    private final DeveloperService developerService;

    public DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a developer by ID", description = "Retrieve a specific developer based on its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operation successful"),
            @ApiResponse(responseCode = "404", description = "Developer not found")
    })
    public ResponseEntity<DeveloperDto> findById(@PathVariable Long id) {
        Developer dev = developerService.findById(id);
        return ResponseEntity.ok(new DeveloperDto(dev));
    }

    @PostMapping
    @Operation(summary = "Create a new developer profile", description = "Initialize a new developer hero in the gamified portfolio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Developer created successfully"),
            @ApiResponse(responseCode = "422", description = "Invalid developer data provided")
    })
    public ResponseEntity<DeveloperDto> create(@RequestBody DeveloperDto developerDto) {
        Developer createdDev = developerService.create(developerDto.toModel());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdDev.getId())
                .toUri();
        return ResponseEntity.created(location).body(new DeveloperDto(createdDev));
    }

    @PostMapping("/{id}/quests")
    @Operation(summary = "Complete a quest and earn XP", description = "Add a completed quest to the developer, calculating potential level-up")
    public ResponseEntity<DeveloperDto> completeQuest(@PathVariable Long id, @RequestBody QuestDto questDto) {
        Developer updatedDev = developerService.completeQuest(id, questDto.toModel());
        return ResponseEntity.ok(new DeveloperDto(updatedDev));
    }
}

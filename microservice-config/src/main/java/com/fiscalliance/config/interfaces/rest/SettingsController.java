package com.fiscalliance.config.interfaces.rest;

import com.fiscalliance.config.domain.models.queries.GetAllSettingsQuery;
import com.fiscalliance.config.domain.models.queries.GetSettingByIdQuery;
import com.fiscalliance.config.domain.services.SettingCommandService;
import com.fiscalliance.config.domain.services.SettingQueryService;
import com.fiscalliance.config.interfaces.rest.resources.CreateSettingResource;
import com.fiscalliance.config.interfaces.rest.resources.SettingResource;
import com.fiscalliance.config.interfaces.rest.transform.CreateSettingCommandFromResourceAssembler;
import com.fiscalliance.config.interfaces.rest.transform.SettingResourceFromEntityAssembler;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/config", produces = MediaType.APPLICATION_JSON_VALUE)
public class SettingsController {

    private final SettingCommandService commandService;
    private final SettingQueryService queryService;

    public SettingsController(SettingCommandService commandService, SettingQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    public ResponseEntity<SettingResource> create(@RequestBody CreateSettingResource resource) {
        var command = CreateSettingCommandFromResourceAssembler.toCommandFromResource(resource);
        var created = commandService.handle(command);
        if (created.isEmpty()) return ResponseEntity.badRequest().build();
        var response = SettingResourceFromEntityAssembler.toResourceFromEntity(created.get());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SettingResource>> getAll() {
        var list = queryService.handle(new GetAllSettingsQuery())
                .stream()
                .map(SettingResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SettingResource> getById(@PathVariable Long id) {
        var found = queryService.handle(new GetSettingByIdQuery(id));
        return found.map(setting -> ResponseEntity.ok(
                        SettingResourceFromEntityAssembler.toResourceFromEntity(setting)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}

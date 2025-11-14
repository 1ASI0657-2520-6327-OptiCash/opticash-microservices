package com.fiscalliance.contributions.interfaces;


import com.fiscalliance.contributions.domain.models.queries.GetAllMemberContributionsQuery;
import com.fiscalliance.contributions.domain.models.queries.GetMemberContributionByIdQuery;
import com.fiscalliance.contributions.domain.services.MemberContributionCommandService;
import com.fiscalliance.contributions.domain.services.MemberContributionQueryService;
import com.fiscalliance.contributions.interfaces.rest.resources.MemberContributionResource;
import com.fiscalliance.contributions.interfaces.rest.transform.MemberContributionResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/member-contributions", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Member Contributions", description = "Available Member Contribution Endpoints")
public class MemberContributionsController {

    private final MemberContributionQueryService queryService;
    private final MemberContributionCommandService commandService;

    public MemberContributionsController(MemberContributionQueryService queryService,MemberContributionCommandService commandService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @GetMapping
    @Operation(summary = "Get all member contributions")
    public ResponseEntity<List<MemberContributionResource>> getAll() {
        var results = queryService.handle(new GetAllMemberContributionsQuery());
        if (results.isEmpty()) return ResponseEntity.notFound().build();
        var resources = results.stream()
                .map(MemberContributionResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get member contribution by ID")
    public ResponseEntity<MemberContributionResource> getById(@PathVariable Long id) {
        var query = new GetMemberContributionByIdQuery(id);
        var result = queryService.handle(query);
        if (result.isEmpty()) return ResponseEntity.notFound().build();
        var resource = MemberContributionResourceFromEntityAssembler.toResourceFromEntity(result.get());
        return ResponseEntity.ok(resource);
    }

    @PutMapping("/{id}/pay")
    @Operation(summary = "Mark a member contribution as paid")
    public ResponseEntity<MemberContributionResource> markAsPaid(@PathVariable Long id) {
        var result = commandService.handlMarkAsPaid(id);
        if (result.isEmpty()) return ResponseEntity.notFound().build();
        var resource = MemberContributionResourceFromEntityAssembler.toResourceFromEntity(result.get());
        return ResponseEntity.ok(resource);
    }




}

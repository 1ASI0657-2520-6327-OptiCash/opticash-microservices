package com.fiscalliance.households.interfaces.rest;

import com.fiscalliance.households.domain.models.queries.GetAllHouseholdMembersQuery;
import com.fiscalliance.households.domain.models.queries.GetAllMembersByHouseholdIdQuery;
import com.fiscalliance.households.domain.models.queries.GetHouseholdMemberByIdQuery;
import com.fiscalliance.households.domain.services.HouseholdMemberCommandService;
import com.fiscalliance.households.domain.services.HouseholdMemberQueryService;
import com.fiscalliance.households.interfaces.rest.resources.CreateHouseholdMemberResource;
import com.fiscalliance.households.interfaces.rest.resources.HouseholdMemberResource;
import com.fiscalliance.households.interfaces.rest.transform.CreateHouseholdMemberCommandFromResourceAssembler;
import com.fiscalliance.households.interfaces.rest.transform.HouseholdMemberResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
@RestController
@RequestMapping(value = "/api/v1/household-members", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Household Members", description = "Available Household Member Endpoints")
public class HouseholdMembersController {

    private final HouseholdMemberCommandService commandService;
    private final HouseholdMemberQueryService queryService;

    public HouseholdMembersController(HouseholdMemberCommandService commandService,
                                      HouseholdMemberQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

 @PostMapping
@Operation(summary = "Create a household member")
public ResponseEntity<HouseholdMemberResource> createHouseholdMember(@RequestBody CreateHouseholdMemberResource resource) {
    var command = CreateHouseholdMemberCommandFromResourceAssembler.toCommandFromResource(resource);
    var result = commandService.handle(command);
    if (result.isEmpty()) {
        System.out.println("❌ HouseholdMember not created. Resource: " + resource);
        return ResponseEntity.badRequest().build();
    }
    var response = HouseholdMemberResourceFromEntityAssembler.toResourceFromEntity(result.get());
    return new ResponseEntity<>(response, HttpStatus.CREATED);
}


    @GetMapping
    @Operation(summary = "Get all household members")
    public ResponseEntity<List<HouseholdMemberResource>> getAllHouseholdMembers() {
        var result = queryService.handle(new GetAllHouseholdMembersQuery());
        if (result.isEmpty()) return ResponseEntity.notFound().build();
        var resources = result.stream()
                .map(HouseholdMemberResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{memberId}")
    @Operation(summary = "Get household member by ID")
    public ResponseEntity<HouseholdMemberResource> getHouseholdMemberById(@PathVariable Long memberId) {
        var query = new GetHouseholdMemberByIdQuery(memberId);
        var result = queryService.handle(query);
        if (result.isEmpty()) return ResponseEntity.notFound().build();
        var resource = HouseholdMemberResourceFromEntityAssembler.toResourceFromEntity(result.get());
        return ResponseEntity.ok(resource);
    }

    @PutMapping("/{memberId}")
    @Operation(summary = "Update household member by ID")
    public ResponseEntity<HouseholdMemberResource> updateHouseholdMemberById(
            @PathVariable Long memberId,
            @RequestBody CreateHouseholdMemberResource resource) {
        var command = CreateHouseholdMemberCommandFromResourceAssembler.toCommandFromResource(resource);
        var updated = commandService.update(memberId, command);
        if (updated.isEmpty()) return ResponseEntity.notFound().build();
        var response = HouseholdMemberResourceFromEntityAssembler.toResourceFromEntity(updated.get());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{memberId}")
    @Operation(summary = "Delete household member by ID")
    public ResponseEntity<Void> deleteHouseholdMemberById(@PathVariable Long memberId) {
        boolean deleted = commandService.delete(memberId);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    @GetMapping("{householdId}/members")
    @Operation(summary = "Health check endpoint")
    public ResponseEntity<List<HouseholdMemberResource>> getMembersByHouseholdId(@PathVariable Long householdId) {
        var result = queryService.handle(new GetAllMembersByHouseholdIdQuery(householdId));
        if (result.isEmpty()) return ResponseEntity.notFound().build();
        var resources = result.stream()
                .map(HouseholdMemberResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }
}
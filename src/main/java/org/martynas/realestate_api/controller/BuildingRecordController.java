package org.martynas.realestate_api.controller;

import org.martynas.realestate_api.model.BuildingRecord;
import org.martynas.realestate_api.service.BuildingRecordService;
import org.martynas.realestate_api.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
public class BuildingRecordController {

    private final BuildingRecordService buildingRecordService;
    private final OwnerService ownerService;

    @Autowired
    public BuildingRecordController(BuildingRecordService buildingRecordService, OwnerService ownerService) {
        this.buildingRecordService = buildingRecordService;
        this.ownerService = ownerService;
    }

    // Get all building records by all owners
    @GetMapping("/properties")
    public Collection<BuildingRecord> getAllBuildingRecords() {
        return this.buildingRecordService.getAllOrderById();
    }

    // Get all building records by given owner id
    @GetMapping("/properties/owner/{ownerId}")
    public Collection<BuildingRecord> getAllBuildingRecordsByOwnerId(@PathVariable Long ownerId) {
        return this.buildingRecordService.getAllByOwner(this.ownerService.findById(ownerId));
    }

    // Get a building record by given id
    @GetMapping("/properties/{id}")
    public BuildingRecord getBuildingRecord(@PathVariable Long id) {
        return this.buildingRecordService.getById(id);
    }

    // Create new building record and return it or validation error or not found exception
    @PostMapping(value = "/properties")
    public BuildingRecord createBuildingRecord(@RequestBody @Valid BuildingRecord buildingRecord, BindingResult bindingResult) {

        System.err.println("bindingResult " + bindingResult);
        System.err.println("buildingRecord " + buildingRecord);

        // Persist new building record into database and return it
        return this.buildingRecordService.save(buildingRecord);
    }

    // Update building record by given id and return it or validation error or not found exception
    @PutMapping("/properties/{id}")
    public BuildingRecord replaceBuildingRecord(@PathVariable Long id, @Valid @RequestBody BuildingRecord buildingRecord, BindingResult bindingResult) {

        System.err.println("bindingResult " + bindingResult);
        System.err.println("buildingRecord " + buildingRecord);

        // Persist modified building record on database and return it
        return this.buildingRecordService.update(buildingRecord, id);
    }

    // Delete a record by given id (if one exists) or record not found exception
    @DeleteMapping("/properties/{id}")
    public void deleteBuildingRecord(@PathVariable Long id) {
        this.buildingRecordService.delete(id);
    }

}

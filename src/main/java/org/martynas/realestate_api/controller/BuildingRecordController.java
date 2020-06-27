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

    // Get all building records by given owner id or throw owner not found exception
    @GetMapping("/properties/owner/{ownerId}")
    public Collection<BuildingRecord> getAllBuildingRecordsByOwnerId(@PathVariable Long ownerId) {
        return this.buildingRecordService.getAllByOwner(this.ownerService.findById(ownerId));
    }

    // Get a building record by given id or throw record not found exception
    @GetMapping("/properties/{id}")
    public BuildingRecord getBuildingRecord(@PathVariable Long id) {
        return this.buildingRecordService.findById(id);
    }

    // Create new building record, save and return it or validation error or throw Owner not found Exception if existing owner id was passed
    @PostMapping(value = "/properties")
    public BuildingRecord createBuildingRecord(@RequestBody @Valid BuildingRecord buildingRecord, BindingResult bindingResult) {
        return this.buildingRecordService.save(buildingRecord);
    }

    // Update building record by given id and return it or validation error or throw record/owner/address not found exception if existing owner/address id was passed
    @PutMapping("/properties/{id}")
    public BuildingRecord replaceBuildingRecord(@PathVariable Long id, @Valid @RequestBody BuildingRecord buildingRecord, BindingResult bindingResult) {
        return this.buildingRecordService.update(buildingRecord, id);
    }

    // Delete a record by given id (if one exists) or record not found exception
    @DeleteMapping("/properties/{id}")
    public void deleteBuildingRecord(@PathVariable Long id) {
        this.buildingRecordService.delete(id);
    }

}

package org.martynas.realestate_api.service;

import org.martynas.realestate_api.exception.RecordNotFoundException;
import org.martynas.realestate_api.model.Address;
import org.martynas.realestate_api.model.BuildingRecord;
import org.martynas.realestate_api.model.Owner;
import org.martynas.realestate_api.repository.BuildingRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class BuildingRecordServiceImpl implements BuildingRecordService {

    public final BuildingRecordRepository buildingRecordRepository;
    public final OwnerService ownerService;
    public final AddressService addressService;


    @Autowired
    public BuildingRecordServiceImpl(BuildingRecordRepository buildingRecordRepository, OwnerService ownerService, AddressService addressService) {
        this.buildingRecordRepository = buildingRecordRepository;
        this.ownerService = ownerService;
        this.addressService = addressService;
    }

    @Override
    public BuildingRecord findById(Long id) {
        Optional<BuildingRecord> optionalRecord = buildingRecordRepository.findById(id);
        return optionalRecord.orElseThrow(() -> new RecordNotFoundException(id));
    }

    @Override
    public Collection<BuildingRecord> getAllOrderById() {
        return buildingRecordRepository.getAllByOrderById();
    }

    @Override
    public Collection<BuildingRecord> getAllByOwner(Owner owner) {
        return buildingRecordRepository.getAllByOwnerOrderById(owner);
    }

    @Override
    public BuildingRecord save(BuildingRecord buildingRecord) {

        // Ignore record id to avoid editing with POST method
        buildingRecord.setId(null);

        // Get owner id if one was passed
        Long ownerId = buildingRecord.getOwner().getId();
        // If passed owner id exists on datasource then assign new building record to existing Owner by given id or throw Owner not found Exception
        if (ownerId != null) {
            // Only existing owners can be manually set otherwise datasource sequence generator should give next available id
            buildingRecord.setOwner(ownerService.findById(ownerId));
        }

        // Persist record into datasource and return it
        return buildingRecordRepository.saveAndFlush(buildingRecord);
    }

    @Override
    public BuildingRecord update(BuildingRecord newRecord, Long id) {

        // Try to find a record by given id
        Optional<BuildingRecord> datasourceOptRecord = buildingRecordRepository.findById(id);

        // If record by id exist on datasource then modify it and return, or throw record not found exception
        if (datasourceOptRecord.isPresent()) {
            BuildingRecord currentRecord = datasourceOptRecord.get();
            // Set record Id to modify existing record to be safe
            newRecord.setId(currentRecord.getId());

            Address currentRecordAddress = currentRecord.getAddress();

            // Get owner id if one was passed
            Long newOwnerId = newRecord.getOwner().getId();
            // If passed owner id exists on datasource then assign new building record to existing Owner by given id or throw Owner not found Exception
            if (newOwnerId != null) {
                // Only existing owners can be manually set otherwise datasource sequence generator should give next available id
                newRecord.setOwner(ownerService.findById(newOwnerId));
            }

            // Get address id if one was passed
            Long newAddressId = newRecord.getAddress().getId();
            // If passed address id exists on datasource then assign new building record to existing Address by given id or throw Address not found Exception
            if (newAddressId != null) {
                // Do not violate unique constrains for address_id column
                if (newAddressId.equals(currentRecordAddress.getId())) {
                    // Only existing address can be manually set otherwise datasource sequence generator should give next available id with new data
                    newRecord.setAddress(addressService.findById(newAddressId));
                }
            }

            // Persist modified building record on datasource and return it
            return buildingRecordRepository.saveAndFlush(newRecord);
        } else {
            // Only existing records can be modified
            throw new RecordNotFoundException(id);
        }
    }

    @Override
    public void delete(Long recordId) {
        // Try to find building record by given id
        Optional<BuildingRecord> optionalRecord = buildingRecordRepository.findById(recordId);

        // If record by id exist then delete it or throw record not found exception
        if (optionalRecord.isPresent()) {
            // Delete building record from database
            buildingRecordRepository.delete(optionalRecord.get());
        } else {
            throw new RecordNotFoundException(recordId);
        }
    }
}

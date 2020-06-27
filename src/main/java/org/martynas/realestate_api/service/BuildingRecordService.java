package org.martynas.realestate_api.service;

import org.martynas.realestate_api.model.BuildingRecord;
import org.martynas.realestate_api.model.Owner;

import java.util.Collection;

public interface BuildingRecordService {

    BuildingRecord findById(Long id);

    Collection<BuildingRecord> getAllOrderById();

    Collection<BuildingRecord> getAllByOwner(Owner owner);

    BuildingRecord save(BuildingRecord buildingRecord);

    BuildingRecord update(BuildingRecord buildingRecord, Long id);

    void delete(Long recordId);
}

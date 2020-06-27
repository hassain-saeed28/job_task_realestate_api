package org.martynas.realestate_api.repository;

import org.martynas.realestate_api.model.BuildingRecord;
import org.martynas.realestate_api.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface BuildingRecordRepository extends JpaRepository<BuildingRecord, Long> {

    Optional<BuildingRecord> findById(Long id);

    Collection<BuildingRecord> getAllByOwnerOrderById(Owner owner);

    Collection<BuildingRecord> getAllByOrderById();

}

package org.martynas.realestate_api.repository;

import org.martynas.realestate_api.model.BuildingRecord;
import org.martynas.realestate_api.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface BuildingRecordRepository extends JpaRepository<BuildingRecord, Long> {

    Optional<BuildingRecord> findById(Long id);

    Collection<BuildingRecord>  findAllByOwnerOrderById(Owner owner);

    Collection<BuildingRecord>  findAllByOrderById();

}

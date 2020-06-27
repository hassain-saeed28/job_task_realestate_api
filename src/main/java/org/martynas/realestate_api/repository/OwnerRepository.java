package org.martynas.realestate_api.repository;

import org.martynas.realestate_api.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner,Long> {

    Optional<Owner> findById(Long id);

}

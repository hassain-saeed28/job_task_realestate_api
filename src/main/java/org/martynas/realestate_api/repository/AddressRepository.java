package org.martynas.realestate_api.repository;

import org.martynas.realestate_api.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address,Long> {

    Optional<Address> findById(Long id);

}

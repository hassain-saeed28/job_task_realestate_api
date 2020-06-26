package org.martynas.realestate_api.service;

import org.martynas.realestate_api.model.Address;
import org.springframework.stereotype.Service;

@Service
public interface AddressService {

    Address findById(Long id);

    void delete(Address address);

}

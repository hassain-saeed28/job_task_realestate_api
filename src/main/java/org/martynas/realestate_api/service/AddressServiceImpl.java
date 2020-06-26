package org.martynas.realestate_api.service;

import org.martynas.realestate_api.exception.AddressNotFoundException;
import org.martynas.realestate_api.model.Address;
import org.martynas.realestate_api.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address findById(Long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        return optionalAddress.orElseThrow(() -> new AddressNotFoundException(id));
    }

    @Override
    public void delete(Address address) {
        addressRepository.delete(address);
    }

}

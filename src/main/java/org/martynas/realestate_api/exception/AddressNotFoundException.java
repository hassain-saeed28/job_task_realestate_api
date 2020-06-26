package org.martynas.realestate_api.exception;

public class AddressNotFoundException extends RuntimeException{

    public AddressNotFoundException(Long id) {
        super("Could not find Address with id: " + id);
    }

}
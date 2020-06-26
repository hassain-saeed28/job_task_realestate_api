package org.martynas.realestate_api.exception;

public class OwnerNotFoundException extends RuntimeException{

    public OwnerNotFoundException(Long id) {
        super("Could not find Owner with id: " + id);
    }

}

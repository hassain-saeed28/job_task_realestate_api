package org.martynas.realestate_api.exception;

public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(Long id) {
        super("Could not find Building Record with id: " + id);
    }

}
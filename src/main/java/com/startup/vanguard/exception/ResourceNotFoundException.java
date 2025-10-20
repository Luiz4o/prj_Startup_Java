package com.startup.vanguard.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String resourceName, Long resourceId) {
        super(resourceName + " com id " + resourceId + " não encontrado.");
    }
}

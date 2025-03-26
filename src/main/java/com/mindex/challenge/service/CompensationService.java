package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;

/**
 * Service interface for handling compensation operations.
 */
public interface CompensationService {
    /**
     * Creates a new compensation record.
     *
     * @param compensation The compensation to create
     * @return The created compensation record
     */
    Compensation create(Compensation compensation);
    
    /**
     * Retrieves compensation for an employee by their ID.
     *
     * @param employeeId The ID of the employee
     * @return The compensation record for the employee
     */
    Compensation read(String employeeId);
}

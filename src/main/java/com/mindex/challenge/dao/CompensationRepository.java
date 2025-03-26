package com.mindex.challenge.dao;

import com.mindex.challenge.data.Compensation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for managing Compensation data persistence.
 */
@Repository
public interface CompensationRepository extends MongoRepository<Compensation, String> {
    /**
     * Finds compensation record for a specific employee by their employee ID.
     *
     * @param employeeId The ID of the employee
     * @return The compensation record for the employee, or null if not found
     */
    Compensation findByEmployee_EmployeeId(String employeeId);
}

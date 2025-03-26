package com.mindex.challenge.service;

import com.mindex.challenge.data.ReportingStructure;

/**
 * Service interface for handling reporting structure operations
 */
public interface ReportingStructureService {
    /**
     * Gets the reporting structure for an employee
     */
    ReportingStructure getReportingStructure(String employeeId);
}

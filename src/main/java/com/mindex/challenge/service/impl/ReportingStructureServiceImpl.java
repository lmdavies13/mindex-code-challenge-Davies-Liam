package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of ReportingStructureService that calculates total number
 * of reports for an employee
 */
@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ReportingStructure getReportingStructure(String employeeId) {
        LOG.debug("Creating reporting structure for employee id [{}]", employeeId);

        Employee employee = employeeRepository.findByEmployeeId(employeeId);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + employeeId);
        }

        int numberOfReports = calculateNumberOfReports(employee);
        return new ReportingStructure(employee, numberOfReports);
    }

    // Recursively calculates total number of direct and indirect reports
    private int calculateNumberOfReports(Employee employee) {
        if (employee.getDirectReports() == null) {
            return 0;
        }

        int totalNumReports = employee.getDirectReports().size();

        // Add indirect reports through recursion
        for(int i = 0; i < employee.getDirectReports().size(); i++) {
            Employee directReport = employee.getDirectReports().get(i);
            Employee fullDirectReport = employeeRepository.findByEmployeeId(directReport.getEmployeeId());
            totalNumReports += calculateNumberOfReports(fullDirectReport);
        }

        return totalNumReports;
    }
}

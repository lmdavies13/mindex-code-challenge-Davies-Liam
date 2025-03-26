package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of CompensationService that manages employee compensation data.
 */
@Service
public class CompensationServiceImpl implements CompensationService{
    
    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);
    
    @Autowired
    private CompensationRepository compensationRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Validates that the employee exists before creating the compensation record.
     */
    @Override
    public Compensation create(Compensation compensation){
        LOG.debug("Creating compensation [{}]", compensation);

        Employee employee = employeeRepository.findByEmployeeId(compensation.getEmployee().getEmployeeId());
        if (employee == null) {
            throw new RuntimeException("Invalid employeeId");
        }
        compensation.setEmployee(employee);

        compensationRepository.insert(compensation);
        return compensation;
    }

    /**
     * Throws an exception if no compensation record is found for the employee.
     */
    @Override
    public Compensation read(String employeeId){
        LOG.debug("Reading compensation for employee with id [{}]", employeeId);

        Compensation compensation = compensationRepository.findByEmployee_EmployeeId(employeeId);

        if (compensation == null) {
            throw new RuntimeException("No compensation found for employeeId: " + employeeId);
        }

        return compensation;
    }

}

package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Integration tests for Compensation endpoints.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String compensationUrl;
    private String compensationIdUrl;
    private String employeeUrl;

    @Before
    public void setup() {
        compensationUrl = "http://localhost:" + port + "/compensation";
        compensationIdUrl = "http://localhost:" + port + "/compensation/{id}";
        employeeUrl = "http://localhost:" + port + "/employee/{id}";
    }

    /**
     * Tests creating and reading compensation for an existing employee.
     */
    @Test
    public void testCreateReadCompensation() {
        // Test case for employee with multiple reports (John Lennon)
        String johnLennonId = "16a596ae-edd3-4847-99fe-c4518e82c86f";
        
        // Get the employee from the API
        Employee employee = restTemplate.getForEntity(employeeUrl, Employee.class, johnLennonId).getBody();
        assertNotNull(employee);
        assertEquals(johnLennonId, employee.getEmployeeId());
        
        //Create test compensation using the retrieved employee
        Compensation testCompensation = new Compensation();
        testCompensation.setEmployee(employee);
        testCompensation.setSalary(100000.00);
        testCompensation.setEffectiveDate(LocalDate.of(2024, 3, 14));

        //Test create
        Compensation createdCompensation = restTemplate.postForEntity(compensationUrl, 
            testCompensation, Compensation.class).getBody();

        assertNotNull(createdCompensation);
        assertCompensationEquivalence(testCompensation, createdCompensation);

        //Test read
        Compensation readCompensation = restTemplate.getForEntity(compensationIdUrl,
            Compensation.class, johnLennonId).getBody();

        assertNotNull(readCompensation);
        assertCompensationEquivalence(testCompensation, readCompensation);
    }

    /**
     * Tests error handling when attempting to read compensation for a non-existent employee.
     */
    @Test
    public void testReadCompensationByNonExistentEmployee() {
        String nonExistentEmployeeId = "invalid-id";
        
        try {
            restTemplate.getForEntity(compensationIdUrl,
                Compensation.class, nonExistentEmployeeId).getBody();
        } catch (RuntimeException e) {
            assertEquals("No compensation found for employeeId: " + nonExistentEmployeeId, 
                e.getMessage());
        }
    }

    /**
     * Helper method to verify that compensation objects have equivalent fields.
     */
    private static void assertCompensationEquivalence(Compensation expected, 
                                                    Compensation actual) {
        assertEquals(expected.getEmployee().getEmployeeId(), 
                    actual.getEmployee().getEmployeeId());
        assertEquals(expected.getSalary(), actual.getSalary(), 0.001);
        assertEquals(expected.getEffectiveDate(), actual.getEffectiveDate());
    }
} 
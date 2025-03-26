package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.ReportingStructure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Integration tests for ReportingStructure endpoints
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String reportingStructureUrl;

    @org.junit.Before
    public void setup() {
        reportingStructureUrl = "http://localhost:" + port + "/reportingStructure/{id}";
    }

    @Test
    public void testGetReportingStructure() {
        // Test case for employee with multiple reports (John Lennon)
        String johnLennonId = "16a596ae-edd3-4847-99fe-c4518e82c86f";
        
        ReportingStructure reportingStructure = restTemplate.getForEntity(reportingStructureUrl,
                ReportingStructure.class, johnLennonId).getBody();

        assertNotNull(reportingStructure);
        assertNotNull(reportingStructure.getEmployee());
        assertEquals(johnLennonId, reportingStructure.getEmployee().getEmployeeId());
        assertEquals(4, reportingStructure.getNumberOfReports());
    }

    @Test
    public void testGetReportingStructureNoReports() {
        // Test case for employee with no reports (Paul McCartney)
        String paulMcCartneyId = "b7839309-3348-463b-a7e3-5de1c168beb3";
        
        ReportingStructure reportingStructure = restTemplate.getForEntity(reportingStructureUrl,
                ReportingStructure.class, paulMcCartneyId).getBody();

        assertNotNull(reportingStructure);
        assertNotNull(reportingStructure.getEmployee());
        assertEquals(paulMcCartneyId, reportingStructure.getEmployee().getEmployeeId());
        assertEquals(0, reportingStructure.getNumberOfReports());
    }
}
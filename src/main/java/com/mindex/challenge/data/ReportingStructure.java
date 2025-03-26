package com.mindex.challenge.data;

/**
 * Represents an employee's reporting structure, containing the employee
 * and their total number of direct and indirect reports.
 */
public class ReportingStructure {
    private Employee employee;
    private int numberOfReports;

    /**
     * Default constructor.
     * Initializes an empty reporting structure with null employee and zero reports.
     */
    public ReportingStructure() {
        this.employee = null;
        this.numberOfReports = 0;
    }

    /**
     * Constructs a reporting structure with specified employee and number of reports.
     *
     * @param employee The employee for whom this reporting structure is created
     * @param numberOfReports The total number of employees reporting to this employee
     */
    public ReportingStructure(Employee employee, int numberOfReports) {
        this.employee = employee;
        this.numberOfReports = numberOfReports;
    }

    /**
     * Returns the employee associated with this reporting structure.
     *
     * @return The employee object
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Sets the employee for this reporting structure.
     *
     * @param employee The employee to set
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * Returns the total number of reports under this employee.
     *
     * @return The number of employees reporting to this employee
     */
    public int getNumberOfReports() {
        return numberOfReports;
    }

    /**
     * Sets the total number of reports for this employee.
     *
     * @param numberOfReports The number of reports to set
     */
    public void setNumberOfReports(int numberOfReports) {
        this.numberOfReports = numberOfReports;
    }
}

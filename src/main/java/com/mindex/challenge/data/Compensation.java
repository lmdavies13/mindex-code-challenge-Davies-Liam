package com.mindex.challenge.data;

import java.time.LocalDate;

/**
 * Represents employee compensation information, including salary and effective date.
 */
public class Compensation {
    private Employee employee;
    private double salary;
    private LocalDate effectiveDate;

    /**
     * Default constructor.
     */
    public Compensation(){
    }

    /**
     * Returns the employee associated with this compensation.
     */
    public Employee getEmployee(){
        return employee;
    }

    /**
     * Sets the employee for this compensation.
     */
    public void setEmployee(Employee employee){
        this.employee = employee;
    }

    /**
     * Returns the salary amount.
     */
    public double getSalary(){
        return salary;
    }

    /**
     * Sets the salary amount.
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * Returns the date when this compensation becomes effective.
     */
    public LocalDate getEffectiveDate(){
        return effectiveDate;
    }

    /**
     * Sets the effective date for this compensation.
     */
    public void setEffectiveDate(LocalDate effectiveDate){
        this.effectiveDate = effectiveDate;
    }
}

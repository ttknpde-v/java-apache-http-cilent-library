package com.ttknpdev.entity;

public class Employee {
    private Long id;
    private String fullname;
    private String position;
    private Double salary;

    public Employee() {
    }

    public Employee(Long id, String fullname, String position, Double salary) {
        this.id = id;
        this.fullname = fullname;
        this.position = position;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", position='" + position + '\'' +
                ", salary=" + salary +
                '}';
    }
}

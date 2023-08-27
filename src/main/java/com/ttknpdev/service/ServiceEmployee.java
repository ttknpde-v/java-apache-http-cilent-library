package com.ttknpdev.service;

import com.ttknpdev.entity.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ServiceEmployee {
    private List<Employee> data;

    public ServiceEmployee() {
        data = new ArrayList<>(
                List.of
                        (
                                new Employee(1L, "em1", "java backend", 25000.50),
                                new Employee(2L, "em2", "java backend", 26000.50),
                                new Employee(3L, "em3", "java backend", 24000.50)
                        ));
    }

    public Employee readById(int id) {
        if (id <= 0) {
            return null;
        }
        return data.get((id - 1));
    }

    public List<Employee> reads() {
        if (data.size() == 0) {
            return null;
        }
        return data;
    }

    public Boolean create (Employee employee) {
        return data.add(employee);
    }
    public Boolean update (Long id , Employee employee) {
        Boolean result = null;
        for (Employee e : data) {
            boolean check = Objects.equals(e.getId(), id);
            if (check) {
                data.removeIf(em -> em.getId().equals(id)); // delete
                employee.setId(id);
                data.add(employee);
                result = true;
            }
        }
        return result;
    }

    public Boolean delete(Long id) {
        Boolean result = null;
        for (Employee e : data) {
            boolean check = Objects.equals(e.getId(), id);
            if (check) {
                data.removeIf(em -> em.getId().equals(id)); // delete
                result = true;
            }
        }
        return result;
    }
}

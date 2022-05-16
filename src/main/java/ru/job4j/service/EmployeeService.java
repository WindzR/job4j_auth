package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.domain.Employee;
import ru.job4j.domain.Person;
import ru.job4j.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeDAO;

    public EmployeeService(EmployeeRepository employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public List<Employee> findAll() {
        return (List<Employee>) employeeDAO.findAll();
    }

    public Optional<Employee> findById(int id) {
        return employeeDAO.findById(id);
    }

    public Employee save(Employee employee) {
        employeeDAO.save(employee);
        return employee;
    }

    public Optional<Employee> addAccount(int id, Person person) {
        Optional<Employee> emp = employeeDAO.findById(id);
        if (emp.isPresent()) {
            Employee employee = emp.get();
            employee.addPerson(person);
            employeeDAO.save(employee);
            return Optional.of(employee);
        }
        return Optional.empty();
    }

    public Optional<Employee> updateAccount(int id, Person person) {
        int personId = person.getId();
        Optional<Employee> emp = employeeDAO.findById(id);
        if (emp.isPresent()) {
            Employee employee = emp.get();
            Person updatePerson = employee.getAccounts().stream()
                    .filter(pers -> pers.getId() == personId)
                    .findFirst().orElse(null);
            if (updatePerson != null) {
                updatePerson.setLogin(person.getLogin());
                updatePerson.setPassword(person.getPassword());
            } else {
                return Optional.empty();
            }
            employeeDAO.save(employee);
            return Optional.of(employee);
        }
        return Optional.empty();
    }

    public void delete(int id) {
        Employee employee = new Employee();
        employee.setId(id);
        employeeDAO.delete(employee);
    }

    public void deleteAccount(int employeeId, int accountId) {
        Optional<Employee> emp = employeeDAO.findById(employeeId);
        if (emp.isPresent()) {
            Employee employee = emp.get();
            System.out.println(employee);
            employee.getAccounts().stream()
                    .filter(pers -> pers.getId() == accountId)
                    .findFirst()
                    .ifPresent(deletePerson -> employee.getAccounts()
                            .remove(deletePerson)
                    );
            employeeDAO.save(employee);
        }
    }
}

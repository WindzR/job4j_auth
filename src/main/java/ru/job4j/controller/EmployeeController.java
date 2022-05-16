package ru.job4j.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.domain.Employee;
import ru.job4j.domain.Person;
import ru.job4j.service.EmployeeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employees;

    public EmployeeController(final EmployeeService employees) {
        this.employees = employees;
    }

    /**
     * Получение списка всех сотрудников, со всеми их аккаунтами
     */
    @GetMapping("/")
    public List<Employee> findAll() {
        return employees.findAll();
    }

    /**
     * Добавление нового аккаунта к существующему сотруднику
     */
    @PostMapping("/{employeeId}/person")
    public ResponseEntity<Employee> addAccountToEmployee(@PathVariable int employeeId,
                                                         @RequestBody Person person) {
        Optional<Employee> employee = employees.addAccount(employeeId, person);
        return new ResponseEntity<Employee>(
                employee.orElse(new Employee()),
                employee.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    /**
     * Изменение аккаунта у сотрудника
     */
    @PutMapping("/{employeeId}/person")
    public ResponseEntity<Void> updateAccountForEmployee(@PathVariable int employeeId,
                                                         @RequestBody Person person) {
        Optional<Employee> employee = employees.updateAccount(employeeId, person);
        return new ResponseEntity<Void>(
                employee.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    /**
     * Удаление аккаунта у сотрудника
     */
    @DeleteMapping("/{employeeId}/{accountId}")
    public ResponseEntity<Void> delete(@PathVariable int employeeId, @PathVariable int accountId) {
        employees.deleteAccount(employeeId, accountId);
        return ResponseEntity.ok().build();
    }
}

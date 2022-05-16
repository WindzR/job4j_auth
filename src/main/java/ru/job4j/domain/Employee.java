package ru.job4j.domain;

import javax.persistence.*;
import java.util.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String surname;

    private int inn;

    @Temporal(TemporalType.TIMESTAMP)
    private Date hireDate;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "employee_person",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private List<Person> accounts = new ArrayList<>();

    public Employee() {
    }

    public static Employee of(String surname, int inn) {
        Employee emp = new Employee();
        emp.surname = surname;
        emp.inn = inn;
        emp.hireDate = new Date();
        return emp;
    }

    public void addPerson(Person person) {
        accounts.add(person);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getInn() {
        return inn;
    }

    public void setInn(int inn) {
        this.inn = inn;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public List<Person> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Person> accounts) {
        this.accounts = accounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return id == employee.id && inn == employee.inn
                && surname.equals(employee.surname)
                && Objects.equals(hireDate, employee.hireDate)
                && Objects.equals(accounts, employee.accounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, inn, hireDate, accounts);
    }

    @Override
    public String toString() {
        return "Employee{"
                + "id=" + id
                + ", surname='" + surname + '\''
                + ", inn=" + inn
                + ", hireDate=" + hireDate
                + ", accounts=" + accounts
                + '}';
    }
}

package emp.data.service.mysql.service;

import emp.data.service.mysql.dao.Employee;
import emp.data.service.mysql.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;


    public ResponseEntity<String> addNewUser(String name, String email) {

        Employee n = new Employee();
        n.setName(name);
        n.setEmail(email);

        employeeRepository.save(n);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public List<Employee> findAll() {

        List<Employee> employeeList = null;

        employeeList = employeeRepository.findAll();

        return employeeList;

    }

    //Update operation
    public Employee updateEmail(Employee employee, Long empId) {

        Employee empDB
                = employeeRepository.findById(Math.toIntExact(empId))
                                    .get();

        if (Objects.nonNull(employee.getEmail()) && !"".equalsIgnoreCase(employee.getEmail())) {
            empDB.setEmail(employee.getEmail());
        }

        return employeeRepository.save(empDB);

    }


    public void deleteEmployeeById(Long empId) {

        employeeRepository.deleteById(Math.toIntExact(empId));

    }
}

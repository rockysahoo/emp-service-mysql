package emp.data.service.mysql.controller;

import emp.data.service.mysql.dao.Employee;
import emp.data.service.mysql.repository.EmployeeRepository;
import emp.data.service.mysql.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // This means that this class is a Rest Controller
@RequestMapping(path="/app/v1")
public class EmployeeController {

    @Autowired // This means to get the bean called empRepository, Which is auto-generated by Spring, we will use it to handle the data
    private EmployeeService employeeService;

    //http://localhost:8080/app/v1/employee/add?name=Rockyy&email=abcd@gmail.com
    @PostMapping(path="employee/add") // Map ONLY POST Requests
    public ResponseEntity<String> addNewUser (@RequestParam String name, @RequestParam String email) {
        // @RequestParam means it is a parameter from the GET or POST request

        employeeService.addNewUser(name, email);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    //http://localhost:8080/app/v1/employee/all
    @GetMapping(value = "employee/all")
    public ResponseEntity<Object> getAllUsers() {
        // This returns a JSON or XML with the users
        List<Employee> employeesList = null;

        employeesList = employeeService.findAll();

        return new ResponseEntity<>(employeesList, HttpStatus.OK);
    }

    // Update operation
    //http://localhost:8080/app/v1/employee/1
    @PutMapping("/employee/{id}")
    public Employee updateEmail(@RequestBody Employee employee,
                     @PathVariable("id") Long empId)
    {
        return employeeService.updateEmail(employee, empId);
    }

    // Delete operation
    @DeleteMapping("/employee/{id}")
    public String deleteEmployeeById(@PathVariable("id")
                                       Long departmentId)
    {
        employeeService.deleteEmployeeById(departmentId);
        return "Deleted Successfully";
    }

}

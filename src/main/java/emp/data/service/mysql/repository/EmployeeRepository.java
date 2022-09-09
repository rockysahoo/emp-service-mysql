package emp.data.service.mysql.repository;

import emp.data.service.mysql.dao.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}

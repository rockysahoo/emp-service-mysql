package emp.data.service.mysql.repository;

import emp.data.service.mysql.dao.DomainDAO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomainRepository extends JpaRepository<DomainDAO, Long> {


    DomainDAO findByName(String name);
}

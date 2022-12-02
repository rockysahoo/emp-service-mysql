package emp.data.service.mysql.repository;

import emp.data.service.mysql.dao.ApplicationArtifactDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationsRepository extends JpaRepository<ApplicationArtifactDAO, Long> {
}

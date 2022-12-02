package emp.data.service.mysql.repository;

import emp.data.service.mysql.dao.ReleaseVersionDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReleaseVersionRepository extends JpaRepository<ReleaseVersionDAO, Long> {
}

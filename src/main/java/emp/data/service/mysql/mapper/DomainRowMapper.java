package emp.data.service.mysql.mapper;

import emp.data.service.mysql.model.Domain;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DomainRowMapper implements RowMapper<Domain> {

    public Domain mapRow(ResultSet rs, int rowNum) throws SQLException{

        Domain domain = new Domain();
        domain.setId(rs.getInt("id"));
        domain.setName(rs.getString("name"));

        return domain;
    }

}

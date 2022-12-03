package emp.data.service.mysql.dao;

import emp.data.service.mysql.mapper.DomainRowMapper;
import emp.data.service.mysql.model.ApplicationArtifact;
import emp.data.service.mysql.model.DictionaryRequest;
import emp.data.service.mysql.model.Domain;
import emp.data.service.mysql.model.DomainReleaseName;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DictionaryDAO {

    private static final Logger log = LoggerFactory.getLogger(DictionaryDAO.class);

    //Insert to Domain table
    final String DOMAIN_TABLE_INSERT = "INSERT INTO domain (id, name) VALUES (:id, :name)";
    final String DOMAIN_RELEASE_TABLE_INSERT = "INSERT INTO domain_release (id, domain_id, name) VALUES (:id, :domain_id, :name)";
    final String APPLICATION_TABLE_INSERT = "INSERT INTO application_artifact (id, classifier, name, url) VALUES (:id, :classifier, :name, :url)";
    final String SELECT_DOMAIN_NAME = "SELECT * FROM domain WHERE name = ?";


    @Resource(name = "namedParameterJdbcTemplate")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void addDictionary(DictionaryRequest dictionaryRequest) {

        //domain table update
        if (dictionaryRequest.getDomain() != null) {

            //check not null otherwise take empty "" string
            String domainName = StringUtils.isNotEmpty(dictionaryRequest.getDomain().getName()) ?
                    dictionaryRequest.getDomain().getName() : "";

            //SqlParameterSource param = new MapSqlParameterSource("name", domainName);
            //namedParameterJdbcTemplate.query(DOMAIN_TABLE_INSERT, param, domainRowMapper);

            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("id", 1);
            paramMap.put("name", domainName);

            namedParameterJdbcTemplate.update(DOMAIN_TABLE_INSERT, paramMap);
        }

        //Get Domain Id
        Integer domainId = getDomainIdByName(dictionaryRequest.getDomain().getName());

        //domain_release table update
        if (dictionaryRequest.getReleaseVersion() != null && dictionaryRequest.getReleaseVersion().size() > 0) {

            Map<String, Object> paramMapForReleaseTable = new HashMap<>();
            int count = 1;
            for (DomainReleaseName domainReleaseName : dictionaryRequest.getReleaseVersion()) {

                count++;
                paramMapForReleaseTable.put("id", count);
                paramMapForReleaseTable.put("domain_id", domainId);
                //check not null otherwise take empty "" string
                paramMapForReleaseTable.put("name", StringUtils.isNotEmpty(domainReleaseName.getName()) ? domainReleaseName.getName() : "");

                namedParameterJdbcTemplate.update(DOMAIN_RELEASE_TABLE_INSERT, paramMapForReleaseTable);
            }
        }

            //application_artifact table update
            if (dictionaryRequest.getApplications() != null && dictionaryRequest.getApplications().size() > 0) {

                Map<String, Object> paramMapForApplicationTable = new HashMap<>();
                int countAgain = 1;
                for (ApplicationArtifact applicationArtifact : dictionaryRequest.getApplications()) {

                    countAgain++;
                    paramMapForApplicationTable.put("id", countAgain);
                    paramMapForApplicationTable.put("classifier", applicationArtifact.getClassifier());
                    paramMapForApplicationTable.put("name", applicationArtifact.getName());
                    paramMapForApplicationTable.put("url", applicationArtifact.getUrl());

                    namedParameterJdbcTemplate.update(APPLICATION_TABLE_INSERT, paramMapForApplicationTable);
                }


            }


        }

    private Integer getDomainIdByName(String name) {


        Domain domainDetails = jdbcTemplate.queryForObject(SELECT_DOMAIN_NAME,
                new DomainRowMapper(), new Object[]{name});

        log.debug("Domain ::" + domainDetails);
        System.out.println("Domain ::" + domainDetails);

        return domainDetails.getId();

    }
}

package emp.data.service.mysql.dao;

import emp.data.service.mysql.mapper.DomainRowMapper;
import emp.data.service.mysql.model.ApplicationArtifact;
import emp.data.service.mysql.model.DictionaryRequest;
import emp.data.service.mysql.model.DomainReleaseName;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DictionaryDAO {

    @Resource(name = "namedParameterJdbcTemplate")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public void addDictionary(DictionaryRequest dictionaryRequest) {

        //Insert to Domain table
        final String DOMAIN_TABLE_INSERT = "INSERT INTO domain (id, name) VALUES (:id, :name)";
        final String DOMAIN_RELEASE_TABLE_INSERT = "INSERT INTO domain_release (id, domain_id, name) VALUES (:id, :domain_id, :name)";
        final String APPLICATION_TABLE_INSERT = "INSERT INTO application_artifact (id, classifier, name, url) VALUES (:id, :classifier, :name, :url)";


        //domain table update
        if (dictionaryRequest.getDomain() != null) {

            String domainName = StringUtils.isNotEmpty(dictionaryRequest.getDomain().getName()) ?
                    dictionaryRequest.getDomain().getName() : "";

            //SqlParameterSource param = new MapSqlParameterSource("name", domainName);
            //namedParameterJdbcTemplate.query(DOMAIN_TABLE_INSERT, param, domainRowMapper);

            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("id", 1);
            paramMap.put("name", domainName);

            namedParameterJdbcTemplate.update(DOMAIN_TABLE_INSERT, paramMap);
        }

        //domain_release table update
        if (dictionaryRequest.getReleaseVersion() != null) {

            Map<String, Object> paramMapForReleaseTable = new HashMap<>();
            int count = 1;
            for (DomainReleaseName domainReleaseName : dictionaryRequest.getReleaseVersion()) {

                count++;
                paramMapForReleaseTable.put("id", count);
                paramMapForReleaseTable.put("domain_id", 1);
                paramMapForReleaseTable.put("name", domainReleaseName.getName());

                namedParameterJdbcTemplate.update(DOMAIN_RELEASE_TABLE_INSERT, paramMapForReleaseTable);
            }
        }

            //application_artifact table update
            if (dictionaryRequest.getApplications() != null) {

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
    }

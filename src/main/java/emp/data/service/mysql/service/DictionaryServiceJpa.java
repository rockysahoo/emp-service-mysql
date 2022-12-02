package emp.data.service.mysql.service;

import emp.data.service.mysql.dao.ApplicationArtifactDAO;
import emp.data.service.mysql.dao.DictionaryDAO;
import emp.data.service.mysql.dao.DomainDAO;
import emp.data.service.mysql.dao.ReleaseVersionDAO;
import emp.data.service.mysql.model.ApplicationArtifact;
import emp.data.service.mysql.model.DictionaryRequest;
import emp.data.service.mysql.model.DomainReleaseName;
import emp.data.service.mysql.repository.ApplicationsRepository;
import emp.data.service.mysql.repository.DomainRepository;
import emp.data.service.mysql.repository.ReleaseVersionRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DictionaryServiceJpa {



    @Autowired
    DomainRepository domainRepository;

    @Autowired
    ReleaseVersionRepository releaseVersionRepository;

    @Autowired
    ApplicationsRepository applicationsRepository;

    public void addDictionaryJpa(DictionaryRequest dictionaryRequest) {


        System.out.println("DictionaryRequest :: " + dictionaryRequest);

        //Insert to Domain table
        DomainDAO domain = new DomainDAO();

        if (dictionaryRequest.getDomain() != null){

            domain.setName(dictionaryRequest.getDomain().getName());

            domainRepository.save(domain);
        }

        //Get the domainId with domain name
        DomainDAO domainIdByName = domainRepository.findByName(dictionaryRequest.getDomain().getName());

        //Insert to  DomainRelease Table
        ReleaseVersionDAO releaseVersionDAO = new ReleaseVersionDAO();
        List<ReleaseVersionDAO> releaseVersionDAOList = new ArrayList<>();

        if (dictionaryRequest.getReleaseVersion() != null) {

            dictionaryRequest.getReleaseVersion().stream().forEach(releaseData -> {

                releaseVersionDAO.setName(releaseData.getName());
                releaseVersionDAO.setDomain_id(String.valueOf(domainIdByName.getId()));

                releaseVersionDAOList.add(releaseVersionDAO);
            });

            releaseVersionRepository.saveAll(releaseVersionDAOList);

        }

       /* for (DomainReleaseName domainReleaseName : dictionaryRequest.getReleaseVersion()){


            releaseVersionDAO.setName(domainReleaseName.getName());
            releaseVersionDAO.setDomain_id(String.valueOf(domainIdByName.getId()));

            releaseVersionRepository.save(releaseVersionDAO);

        }*/


        //Insert to ApplicationArtifact
        ApplicationArtifactDAO applicationArtifactDAO = new ApplicationArtifactDAO();
        List<ApplicationArtifactDAO> applicationArtifactDAOList = new ArrayList<>();

        if (dictionaryRequest.getApplications() != null){

            dictionaryRequest.getApplications().stream().forEach(applicationData -> {
                applicationArtifactDAO.setName(applicationData.getName());
                applicationArtifactDAO.setUrl(applicationData.getUrl());
                applicationArtifactDAO.setClassifier(applicationData.getClassifier());

                applicationArtifactDAOList.add(applicationArtifactDAO);

            });

            applicationsRepository.saveAll(applicationArtifactDAOList);
        }


    }




}

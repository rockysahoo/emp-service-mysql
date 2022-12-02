package emp.data.service.mysql.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DictionaryRequest {

    private Domain domain;
    private List<DomainReleaseName> releaseVersion;
    private List<ApplicationArtifact> applications;

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    public List<DomainReleaseName> getReleaseVersion() {
        return releaseVersion;
    }

    public void setReleaseVersion(List<DomainReleaseName> releaseVersion) {
        this.releaseVersion = releaseVersion;
    }

    public List<ApplicationArtifact> getApplications() {
        return applications;
    }

    public void setApplications(List<ApplicationArtifact> applications) {
        this.applications = applications;
    }

    @Override
    public String toString() {
        return "DictionaryRequest{" +
                "domain=" + domain +
                ", releaseVersion=" + releaseVersion +
                ", applications=" + applications +
                '}';
    }
}

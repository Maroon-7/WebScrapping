package WebScrapper.models;

public class Job {
    String companyName;
    String jobTitle;
    String jobLink;

    public Job(String companyName, String jobTitle, String jobLink) {
        this.companyName = companyName;
        this.jobTitle = jobTitle;
        this.jobLink = jobLink;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getJobLink() {
        return jobLink;
    }
}

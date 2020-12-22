package team.scholarship.bean;

/**
 * @ClassName Application
 * @Description TODO
 * @Author Brian.Z
 * @Date 2020/12/20 17:58
 */
public class Application {

    private String usr_id;

    private String scholar_year;

    private String scholar_name;

    private double usr_score;

    private String award;

    private String reason;

    private int status;

    public void setUsr_id(String usr_id) {
        this.usr_id = usr_id;
    }

    public String getUsr_id() {
        return usr_id;
    }

    public void setScholar_year(String scholar_year) {
        this.scholar_year = scholar_year;
    }

    public String getScholar_year() {
        return scholar_year;
    }

    public void setScholar_name(String scholar_name) {
        this.scholar_name = scholar_name;
    }

    public String getScholar_name() {
        return scholar_name;
    }

    public void setUsr_score(double usr_score) {
        this.usr_score = usr_score;
    }

    public double getUsr_score() {
        return usr_score;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public String getAward() {
        return award;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}

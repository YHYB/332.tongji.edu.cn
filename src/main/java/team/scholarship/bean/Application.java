package team.scholarship.bean;

import java.util.Set;

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

    private String usr_name;

    private double usr_gpa;

    private double usr_score;

    private String award;

    private String reason;

    private boolean can_adjust;

    /**
     *
     */
    private String status;

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

    public void setUsr_name(String usr_name) {
        this.usr_name = usr_name;
    }

    public String getUsr_name() {
        return usr_name;
    }

    public void setUsr_gpa(double usr_gpa) {
        this.usr_gpa = usr_gpa;
    }

    public double getUsr_gpa() {
        return usr_gpa;
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

    public void setCan_adjust(boolean can_adjust) {
        this.can_adjust = can_adjust;
    }

    public boolean getCan_adjust() {
        return can_adjust;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String  getStatus() {
        return status;
    }
}

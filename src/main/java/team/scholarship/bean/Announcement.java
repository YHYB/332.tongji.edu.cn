package team.scholarship.bean;

import java.util.Date;

/**
 * @author Kerr
 * @project TJSSE scholarship management system
 * @classname Announcement
 * @description TODO
 * @date 2020/12/20 20:39
 */

public class Announcement {
    private int id;

    private Date date;

    private String content;

    private String title;

    private int readNum;

    // get methods
    public Date getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public int getReadNum() {
        return readNum;
    }

    // set methods
    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReadNum(int readNum) {
        this.readNum = readNum;
    }
}

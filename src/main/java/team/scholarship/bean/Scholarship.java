package team.scholarship.bean;

/**
 * @author Kerr
 * @project scholarship management system
 * @classname Scholarship
 * @description TODO
 * @date 2020/12/20 20:47
 */
public class Scholarship {
    private String name;

    private String year;

    private int num;

    private int prize;

    private String limit;

    // set methods
    public void setName(String name) {
        this.name = name;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    public void setYear(String year) {
        this.year = year;
    }

    // get methods
    public int getNum() {
        return num;
    }

    public int getPrize() {
        return prize;
    }

    public String getLimit() {
        return limit;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }
}


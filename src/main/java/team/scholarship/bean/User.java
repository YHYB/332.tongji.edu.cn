package team.scholarship.bean;

/**
 * @ClassName User
 * @Description TODO
 * @Author Brian.Z
 * @Date 2020/12/20 1:36
 */
public class User {

    private String id;

    private String name;

    private String password;

    private double score;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public double getScore() {
        return score;
    }
}

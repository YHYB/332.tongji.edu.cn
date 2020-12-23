package team.scholarship.result;

public enum StatusEnum {

    SUCCESS(200,"OK"),// 成功连接
    NOT_FOUND(404,"404 not found"),//连接失败
    NO_DATA(500,"no data found"),//未查找到数据
    DUPLICATE_PK(501,"duplicate primary key"),
    WRONG_ID_OR_PWD(502,"wrong user ID or password");//插入时主码冲突

    // 状态码
    private int code;

    // 信息
    private String msg;

    StatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

package team.scholarship.result;


/**
 * @ClassName Result
 * @Description 统一返回格式
 * @Author Brian.Z
 * @Date 2020/12/21 13:04
 */
public class Result {

    // 是否执行成功
    private boolean success;

    // 状态码
    private int code;

    // 结果信息
    private String msg;

    // 具体传输的数据
    private Object data;

    public Result(boolean success, int code, String msg, Object data) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(StatusEnum statusEnum, Object data) {
        this.success = statusEnum.getCode() == 200;
        this.code = statusEnum.getCode();
        this.msg = statusEnum.getMsg();
        this.data = data;
    }

    public Result(StatusEnum statusEnum) {
        this(statusEnum, null);
    }

    public static Result SUCCESS(Object data) {
        return new Result(StatusEnum.SUCCESS, data);
    }

    public static Result SUCCESS() {
        return new Result(StatusEnum.SUCCESS);
    }

    public static Result ERROR(StatusEnum statusEnum, Object data) {
        return new Result(statusEnum, data);
    }

    public static Result ERROR(StatusEnum statusEnum) {
        return new Result(statusEnum);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}

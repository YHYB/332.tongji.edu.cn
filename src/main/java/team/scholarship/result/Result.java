package team.scholarship.result;


/**
 * @ClassName Result
 * @Description 统一返回格式
 * @Author Brian.Z
 * @Date 2020/12/21 13:04
 */
public class Result<T> {

    // 是否执行成功
    private boolean success;

    // 状态码
    private int code;

    // 结果信息
    private String msg;

    // 具体传输的数据
    private T data;

    public Result(boolean success, int code, String msg, T data) {
        this.success = success;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(StatusEnum statusEnum, T data) {
        this.success = statusEnum.getCode() == 200;
        this.code = statusEnum.getCode();
        this.msg = statusEnum.getMsg();
        this.data = data;
    }

    public Result(StatusEnum statusEnum) {
        this(statusEnum, null);
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

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}

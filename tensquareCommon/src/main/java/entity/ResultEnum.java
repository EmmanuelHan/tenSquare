package entity;

public enum ResultEnum {

    SUCCESS(        20000,"成功"),
    ERROR(          20001,"系统异常，请联系管理员"),
    LOGIN(          20002,"未登录，请登录"),
    NO_ACCESS(      20003,"没有权限访问"),
    LOGIN_ERROR(    20002,"用户名或者密码错误"),
    REMORT_ERROR(   20004,"远程调用失败"),
    REP_ERROR(      20005,"重复操作"),

    ;

    private int code;

    private String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

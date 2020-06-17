package entity;

public enum ResultEnum {

    SUCCESS(        20000,"成功"),
    ERROR(          20001,"系统异常，请联系管理员"),
    LOGIN(          20002,"未登录，请登录"),
    NO_ACCESS(      20003,"没有权限访问"),
    LOGIN_OUT(      20004,"退出登录成功"),
    LOGIN_ERROR(    20005,"用户名或者密码错误"),
    REMORT_ERROR(   20006,"远程调用失败"),
    ACCESS_WRONG(   20007,"账户名或者密码输入错误"),
    ACCESS_LOCK(    20008,"账户被锁定，请联系管理员"),
    ACCESS_PASS(    20009,"密码过期，请联系管理员"),
    ACCESS_ACCOUNT( 20010,"账户过期，请联系管理员"),
    ACCESS_FORBIDDEN(20011,"账户被禁用，请联系管理员"),
    ACCESS_ERROR(      20012,"登录失败"),


    NOT_LOGIN(      10010,"未登录"),
    LOGOUT(     10002,"注销成功"),
    ACCESS_DENIED(10003,"没有权限，请联系管理员"),
    ACCOUNT_ERROR(10004,"账户名或者密码输入错误"),
    ACCOUNT_LOCK(10005,"账户被锁定，请联系管理员"),
    ACCOUNT_PASSWOED_OVERDUE(10006,"密码过期，请联系管理员"),
    ACCOUNT_OVERDUE(10007,"账户过期，请联系管理员"),
    ACCOUNT_FORBIDDEN(10008,"账户被禁用，请联系管理员"),



    ADMIN_SAME_NAME(20801,"管理员用户名重复"),
    USER_SAME_NAME(20802,"用户名重复"),

    SAVE_CITY(20101, "相同城市名称");

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

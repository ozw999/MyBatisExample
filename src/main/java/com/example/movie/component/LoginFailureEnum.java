package com.example.movie.component;

/**
 * @Description: 登录失败异常描述
 * @Author: Ou
 * @Date: 2020/6/22
 */
public enum LoginFailureEnum {
    Locked("Locked", "账户锁定"),
    Credentials_Expired("Credentials expired", "密码过期"),
    Account_Expired("Account expired", "账户过期"),
    Disabled("Disabled", "账户被禁止"),
    Bad_Credentials("Bad credentials", "用户名或者密码错误");

    private String code;
    private String value;

    /**
     * 单例模式,构造函数是私有的,
     * 加载类时创建对象,每一个枚举即为一个对象
     *
     * @param code
     * @param value
     */
    private LoginFailureEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public static String getValue(String code){
        for (LoginFailureEnum ele : LoginFailureEnum.values()){
            if (ele.getCode().equals(code)){
                return ele.getValue();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        LoginFailureEnum[] values = LoginFailureEnum.values();
        for (LoginFailureEnum value : values) {
            System.out.println(value);
        }
        System.out.println(LoginFailureEnum.valueOf("Credentials_Expired"));
    }
}

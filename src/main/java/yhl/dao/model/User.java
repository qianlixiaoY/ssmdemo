package yhl.dao.model;

import javax.validation.constraints.Pattern;

public class User {
    private Integer id;

    @Pattern(regexp="(^[a-zA-Z0-9_-]{3,10}$)|(^[\u2E80-\u9FFF]{2,5})"
    		,message="用户名为3-10位的英文数字组合或者2-5位的中文数字")
    private String username;

    private String password;

    //在java中\d的"\"表示转义，将\d改成\\d,是java可以识别的
    @Pattern(regexp="^((1[0-5])|[1-9])?\\d$",message="年龄不合法")
    private Integer age;

    public User() {
		
	}

	public User(Integer id, String username, String password, Integer age) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.age = age;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
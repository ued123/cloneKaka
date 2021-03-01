package com.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "Kuser")
public class Kuser {

    /*
       entity에서 데이터베이스의 _를 인식 못하는 이슈가 있어서 @Column 어노테이션을 통해서 매핑해주었음
     */

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "USER_ID")
    private long uesrId;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_ORGAN")
    private String userOrgan;

    @Column(name = "USER_STATE")
    private String userState;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "PASSWORD_TYPE")
    private String passwordType;

    public long getUesrId() {
        return uesrId;
    }

    public void setUesrId(long uesrId) {
        this.uesrId = uesrId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserOrgan() {
        return userOrgan;
    }

    public void setUserOrgan(String userOrgan) {
        this.userOrgan = userOrgan;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordType() {
        return passwordType;
    }

    public void setPasswordType(String passwordType) {
        this.passwordType = passwordType;
    }
}

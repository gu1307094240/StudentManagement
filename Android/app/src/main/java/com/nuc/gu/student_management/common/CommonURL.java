package com.nuc.gu.student_management.common;



/**
 * Created by GU on 2016/10/11.
 */
public class CommonURL {

    private String ip;
    public CommonURL(String ip) {
        this.ip = ip;
    }
    public String getLoginURL(){
        return "http://"+ip+":8080/ssh/loginAndroid";
    }
    public String getGradeURL(){
        return "http://"+ip+":8080/ssh/showGrade";
    }
    public static final String loginURL = "http://"+"172.27.35.1"+":8080/ssh/loginAndroid";
    public static final String getGradeURL = "http://"+"172.27.35.1"+":8080/ssh/showGrade";
}

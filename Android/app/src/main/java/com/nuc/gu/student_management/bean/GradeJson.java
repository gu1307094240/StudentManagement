package com.nuc.gu.student_management.bean;

import java.util.List;

/**
 * Created by GU on 2016/10/11.
 */
public class GradeJson {

    /**
     * id : 1
     * snumber : 1307084309
     * cnumber : 01
     * score : 32
     */

    private List<GradeBean> grade;

    public List<GradeBean> getGrade() {
        return grade;
    }

    public void setGrade(List<GradeBean> grade) {
        this.grade = grade;
    }

    public static class GradeBean {
        private int id;
        private String snumber;
        private String cnumber;
        private String score;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSnumber() {
            return snumber;
        }

        public void setSnumber(String snumber) {
            this.snumber = snumber;
        }

        public String getCnumber() {
            return cnumber;
        }

        public void setCnumber(String cnumber) {
            this.cnumber = cnumber;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }
    }
}

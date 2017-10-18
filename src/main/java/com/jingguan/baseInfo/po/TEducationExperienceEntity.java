package com.jingguan.baseInfo.po;

import javax.persistence.*;

/**
 * Created by 陈 on 2017/10/18.
 */
@Entity
@Table(name = "t_education_experience", schema = "jg_teachers", catalog = "")
public class TEducationExperienceEntity {
    private Integer id;
    private String school;
    private String major;
    private String education;
    private Integer graduationYear;
    private Integer user_id;
    private Integer status;

    @Id
    @Column(name = "id", nullable = false, length = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "school")
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Basic
    @Column(name = "major")
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Basic
    @Column(name = "education")
    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Basic
    @Column(name = "graduationYear")
    public Integer getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(Integer graduationYear) {
        this.graduationYear = graduationYear;
    }

    @Basic
    @Column(name = "user_id")
    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TEducationExperienceEntity that = (TEducationExperienceEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (school != null ? !school.equals(that.school) : that.school != null) return false;
        if (major != null ? !major.equals(that.major) : that.major != null) return false;
        if (education != null ? !education.equals(that.education) : that.education != null) return false;
        if (graduationYear != null ? !graduationYear.equals(that.graduationYear) : that.graduationYear != null)
            return false;
        if (user_id != null ? !user_id.equals(that.user_id) : that.user_id != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (school != null ? school.hashCode() : 0);
        result = 31 * result + (major != null ? major.hashCode() : 0);
        result = 31 * result + (education != null ? education.hashCode() : 0);
        result = 31 * result + (graduationYear != null ? graduationYear.hashCode() : 0);
        result = 31 * result + (user_id != null ? user_id.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}

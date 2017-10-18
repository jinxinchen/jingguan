package com.jingguan.baseInfo.po;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by 陈 on 2017/10/15.
 */
@Entity
@Table(name = "t_teacher_baseinfo", schema = "jg_teachers", catalog = "")
public class TTeacherBaseinfoEntity {
    private Integer id;
    private Integer user_id;
    private String name;
    private String gender;
    private String birthday;
    private String identityNum;
    private String picture;
    private Timestamp createTime;
    private Integer phoneNum;
    private Integer isCar;
    private String carEvidenceSrc;
    private String major;
    private Integer isTutor;
    private String forClass;
    private String research;
    private Integer status;
    private String email;
    private String address;
    private Integer isMoreLangue;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "birthday")
    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "identityNum")
    public String getIdentityNum() {
        return identityNum;
    }

    public void setIdentityNum(String identityNum) {
        this.identityNum = identityNum;
    }

    @Basic
    @Column(name = "picture")
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "phoneNum")
    public Integer getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(Integer phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Basic
    @Column(name = "is_car")
    public Integer getIsCar() {
        return isCar;
    }

    public void setIsCar(Integer isCar) {
        this.isCar = isCar;
    }

    @Basic
    @Column(name = "car_evidence_src")
    public String getCarEvidenceSrc() {
        return carEvidenceSrc;
    }

    public void setCarEvidenceSrc(String carEvidenceSrc) {
        this.carEvidenceSrc = carEvidenceSrc;
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
    @Column(name = "is_tutor")
    public Integer getIsTutor() {
        return isTutor;
    }

    public void setIsTutor(Integer isTutor) {
        this.isTutor = isTutor;
    }

    @Basic
    @Column(name = "for_class")
    public String getForClass() {
        return forClass;
    }

    public void setForClass(String forClass) {
        this.forClass = forClass;
    }

    @Basic
    @Column(name = "research")
    public String getResearch() {
        return research;
    }

    public void setResearch(String research) {
        this.research = research;
    }

    @Basic
    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "is_more_langue")
    public Integer getIsMoreLangue() {
        return isMoreLangue;
    }

    public void setIsMoreLangue(Integer isMoreLangue) {
        this.isMoreLangue = isMoreLangue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TTeacherBaseinfoEntity that = (TTeacherBaseinfoEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (user_id != null ? !user_id.equals(that.user_id) : that.user_id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;
        if (identityNum != null ? !identityNum.equals(that.identityNum) : that.identityNum != null) return false;
        if (picture != null ? !picture.equals(that.picture) : that.picture != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (phoneNum != null ? !phoneNum.equals(that.phoneNum) : that.phoneNum != null) return false;
        if (isCar != null ? !isCar.equals(that.isCar) : that.isCar != null) return false;
        if (carEvidenceSrc != null ? !carEvidenceSrc.equals(that.carEvidenceSrc) : that.carEvidenceSrc != null)
            return false;
        if (major != null ? !major.equals(that.major) : that.major != null) return false;
        if (isTutor != null ? !isTutor.equals(that.isTutor) : that.isTutor != null) return false;
        if (forClass != null ? !forClass.equals(that.forClass) : that.forClass != null) return false;
        if (research != null ? !research.equals(that.research) : that.research != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (isMoreLangue != null ? !isMoreLangue.equals(that.isMoreLangue) : that.isMoreLangue != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (user_id != null ? user_id.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (identityNum != null ? identityNum.hashCode() : 0);
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (phoneNum != null ? phoneNum.hashCode() : 0);
        result = 31 * result + (isCar != null ? isCar.hashCode() : 0);
        result = 31 * result + (carEvidenceSrc != null ? carEvidenceSrc.hashCode() : 0);
        result = 31 * result + (major != null ? major.hashCode() : 0);
        result = 31 * result + (isTutor != null ? isTutor.hashCode() : 0);
        result = 31 * result + (forClass != null ? forClass.hashCode() : 0);
        result = 31 * result + (research != null ? research.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (isMoreLangue != null ? isMoreLangue.hashCode() : 0);
        return result;
    }
}

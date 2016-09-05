package com.example.azat.mebeltest.modelEntity;

import com.google.gson.annotations.SerializedName;

public class Profile {

    private Integer id;
    private String phone;
    private String email;
    private String name;
    private String surname;
    private String patronym;
    private String city;
    private String about;
    private Avatar avatar;
    @SerializedName("created_at")
    private Integer createdAt;
    @SerializedName("updated_at")
    private Integer updatedAt;
    private Integer status;
    private String role;
    @SerializedName("balance_start")
    private Double balanceStart;
    @SerializedName("balance_end")
    private Double balanceEnd;

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @param phone
     * The phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *
     * @return
     * The email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     * The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     *
     * @param surname
     * The surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     *
     * @return
     * The patronym
     */
    public String getPatronym() {
        return patronym;
    }

    /**
     *
     * @param patronym
     * The patronym
     */
    public void setPatronym(String patronym) {
        this.patronym = patronym;
    }

    /**
     *
     * @return
     * The city
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     * The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return
     * The about
     */
    public String getAbout() {
        return about;
    }

    /**
     *
     * @param about
     * The about
     */
    public void setAbout(String about) {
        this.about = about;
    }

    /**
     *
     * @return
     * The avatar
     */
    public Avatar getAvatar() {
        return avatar;
    }

    /**
     *
     * @param avatar
     * The avatar
     */
    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    /**
     *
     * @return
     * The createdAt
     */
    public Integer getCreatedAt() {
        return createdAt;
    }

    /**
     *
     * @param createdAt
     * The created_at
     */
    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    /**
     *
     * @return
     * The updatedAt
     */
    public Integer getUpdatedAt() {
        return updatedAt;
    }

    /**
     *
     * @param updatedAt
     * The updated_at
     */
    public void setUpdatedAt(Integer updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     *
     * @return
     * The status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The role
     */
    public String getRole() {
        return role;
    }

    /**
     *
     * @param role
     * The role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     *
     * @return
     * The balanceStart
     */
    public Double getBalanceStart() {
        return balanceStart;
    }

    /**
     *
     * @param balanceStart
     * The balance_start
     */
    public void setBalanceStart(Double balanceStart) {
        this.balanceStart = balanceStart;
    }

    /**
     *
     * @return
     * The balanceEnd
     */
    public Double getBalanceEnd() {
        return balanceEnd;
    }

    /**
     *
     * @param balanceEnd
     * The balance_end
     */
    public void setBalanceEnd(Double balanceEnd) {
        this.balanceEnd = balanceEnd;
    }

}

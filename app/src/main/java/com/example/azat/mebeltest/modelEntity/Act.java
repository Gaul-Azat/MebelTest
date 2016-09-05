package com.example.azat.mebeltest.modelEntity;

import com.google.gson.annotations.SerializedName;


public class Act {

    @SerializedName("id")
    private Integer id;
    @SerializedName("user_id")
    private Integer userId;
    @SerializedName("created_at")
    private Integer createdAt;
    @SerializedName("updated_at")
    private Integer updatedAt;
    @SerializedName("status")
    private Integer status;

    private static final int ACT_STATUS_NEW = 1;
    private static final int ACT_STATUS_DONE = 2;

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
     * The userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     *
     * @param userId
     * The user_id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
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
    public String getStatus() {
        if (status==ACT_STATUS_DONE)
            return "Отправлен";
        if (status==ACT_STATUS_NEW)
            return "Новый";
        return "Не известен";
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

}

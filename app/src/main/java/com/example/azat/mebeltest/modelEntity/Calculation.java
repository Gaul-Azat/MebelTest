package com.example.azat.mebeltest.modelEntity;

import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Calculation{
    private String id;
    @SerializedName("user_id")
    private Integer userId;
    private Integer amount;
    private Integer date;
    private Integer type;
    private String comment;
    @SerializedName("doc_num")
    private String docNum;
    @SerializedName("doc_type")
    private String docType;
    @SerializedName("order_num")
    private String orderNum;
    @SerializedName("created_at")
    private Integer createdAt;
    @SerializedName("updated_at")
    private Integer updatedAt;

    static public final int TYPE_IN=1;
    static public final int TYPE_OUT=2;

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
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
     * The amount
     */
    public Spannable getAmountToString() {
        String str="Стоимость заказа: \n"+amount+" руб.";
        Spannable text = new SpannableString(str);
        text .setSpan(new StyleSpan(Typeface.BOLD), 18, str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return text;
    }

    /**
     *
     * @param amount
     * The amount
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     *
     * @return
     * The date
     */
    public String getDateToString() {
        Date convDate=new Date(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy   HH:mm:ss");
        return "Дата: "+dateFormat.format(convDate);
    }

    /**
     *
     * @param date
     * The date
     */
    public void setDate(Integer date) {
        this.date = date;
    }

    /**
     *
     * @return
     * The type
     */
    public Integer getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     *
     * @return
     * The comment
     */
    public String getComment() {
        return comment;
    }

    /**
     *
     * @param comment
     * The comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     *
     * @return
     * The docNum
     */
    public Spannable getDocNumToString() {
        String str="Номер документа: \n"+docNum;
        Spannable text = new SpannableString(str);
        text .setSpan(new StyleSpan(Typeface.BOLD), 17, str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return text;
    }

    /**
     *
     * @param docNum
     * The doc_num
     */
    public void setDocNum(String docNum) {
        this.docNum = docNum;
    }

    /**
     *
     * @return
     * The docType
     */
    public String getDocType() {
        return docType;
    }

    /**
     *
     * @param docType
     * The doc_type
     */
    public void setDocType(String docType) {
        this.docType = docType;
    }

    /**
     *
     * @return
     * The orderNum
     */
    public String getOrderNum() {
        return orderNum;
    }

    /**
     *
     * @param orderNum
     * The order_num
     */
    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
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
}

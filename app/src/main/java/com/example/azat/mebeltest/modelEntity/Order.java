
package com.example.azat.mebeltest.modelEntity;

import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StyleSpan;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Order{

    @SerializedName("id")
    private Integer id;
    @SerializedName("user_id")
    private Integer userId;
    @SerializedName("type")
    private Integer type;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("old_description")
    private String oldDescription;
    @SerializedName("price")
    private Integer price;
    @SerializedName("old_price")
    private Integer oldPrice;
    @SerializedName("status")
    private Integer status;
    @SerializedName("old_status")
    private Integer oldStatus;
    @SerializedName("created_at")
    private Integer createdAt;
    @SerializedName("updated_at")
    private Integer updatedAt;
    @SerializedName("date")
    private Integer date;

    static public final String[] ORDER_STATUS={"","Создание заказа", "Предоплата", "Обрабатывается", "Ожидание комплектующих", "Передан на изготовление", "Готов (никто не откликается)", "Реализован", "Отгружен"};
    static public final String[] ORDER_TYPE={"", "Кухня", "Шкаф", "Фасад"};
    /**
     *
     * @return
     *     The id
     */
    public String getIdToString() {

        return "Заказ № "+id;
    }


    /**
     *
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     *     The userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     *
     * @param userId
     *     The user_id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     *
     * @return
     *     The type
     */
    public String getTypeToString() {
        return ORDER_TYPE[type];
    }

    public Integer getId() {
        return id;
    }

    public String getType() {
        return type+"";
    }

    public String getTitle() {
        return title;
    }

    public Integer getPrice() {
        return price;
    }

    public String getStatus() {
        return status+"";
    }

    public Integer getDate() {
        return date;
    }

    /**
     *
     * @param type
     *     The type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     *
     * @return
     *     The title
     */
    public String getTitleToString() {
        if (title!=null)
            return title;
        else return "Щелкни и дай имя этому заказу";
    }

    /**
     *
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     *     The description
     */
    public String getDescription() {
        return "Описание: \n"+description;
    }

    /**
     *
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     *     The oldDescription
     */
    public String getOldDescription() {
        return oldDescription;
    }

    /**
     *
     * @param oldDescription
     *     The old_description
     */
    public void setOldDescription(String oldDescription) {
        this.oldDescription = oldDescription;
    }

    /**
     *
     * @return
     *     The price
     */
    public Spannable getPriceToString() {
        String str="Стоимость: "+price+" руб.";
        Spannable text = new SpannableString(str);
        text .setSpan(new StyleSpan(Typeface.BOLD), 11, str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return text;
    }

    /**
     *
     * @param price
     *     The price
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     *
     * @return
     *     The oldPrice
     */
    public Integer getOldPrice() {
        return oldPrice;
    }

    /**
     *
     * @param oldPrice
     *     The old_price
     */
    public void setOldPrice(Integer oldPrice) {
        this.oldPrice = oldPrice;
    }

    /**
     *
     * @return
     *     The status
     */
    public String getStatusToString() {
        return ORDER_STATUS[status];
    }

    /**
     *
     * @param status
     *     The status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     *
     * @return
     *     The oldStatus
     */
    public Integer getOldStatus() {
        return oldStatus;
    }

    /**
     *
     * @param oldStatus
     *     The old_status
     */
    public void setOldStatus(Integer oldStatus) {
        this.oldStatus = oldStatus;
    }

    /**
     *
     * @return
     *     The createdAt
     */
    public Integer getCreatedAt() {
        return createdAt;
    }

    /**
     *
     * @param createdAt
     *     The created_at
     */
    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }

    /**
     *
     * @return
     *     The updatedAt
     */
    public Integer getUpdatedAt() {
        return updatedAt;
    }

    /**
     *
     * @param updatedAt
     *     The updated_at
     */
    public void setUpdatedAt(Integer updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     *
     * @return
     *     The date
     */
    public String getDateToString() {
        Date convDate=new Date(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return "Дата заказа: "+dateFormat.format(convDate);
    }

    /**
     *
     * @param date
     *     The date
     */
    public void setDate(Integer date) {
        this.date = date;
    }
}

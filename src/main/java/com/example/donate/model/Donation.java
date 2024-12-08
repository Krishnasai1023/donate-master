package com.example.donate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer donationId;
    private String itemName;
    private String itemCondition;
    private String collectionDateTime;
    private boolean isVolunteered;

    private boolean isDelivered;
    @ManyToOne()
    @JsonIgnore
    private User user;

    @ManyToOne()
    @JsonIgnore
    private User volunteeredBy;

    public Donation() {
    }

    public Integer getDonationId() {
        return donationId;
    }

    public void setDonationId(Integer donationId) {
        this.donationId = donationId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCondition() {
        return itemCondition;
    }

    public void setItemCondition(String itemCondition) {
        this.itemCondition = itemCondition;
    }

    public String getCollectionDateTime() {
        return collectionDateTime;
    }

    public void setCollectionDateTime(String collectionDateTime) {
        this.collectionDateTime = collectionDateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isVolunteered() {
        return isVolunteered;
    }

    public void setVolunteered(boolean volunteered) {
        isVolunteered = volunteered;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }

    public User getVolunteeredBy() {
        return volunteeredBy;
    }

    public void setVolunteeredBy(User volunteeredBy) {
        this.volunteeredBy = volunteeredBy;
    }
}

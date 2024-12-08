package com.example.donate.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DonationTests {

    @Test
    public void testItemName() {
        Donation donation = new Donation();
        String itemName = "Fan";
        donation.setItemName(itemName);

        assertThat(donation.getItemName()).isEqualTo(itemName);
    }

    @Test
    public void testItemCondition() {
        Donation donation = new Donation();
        String itemCondition = "1";
        donation.setItemCondition(itemCondition);

        assertThat(donation.getItemCondition()).isEqualTo(itemCondition);
    }

    @Test
    public void testCollectionDateAndTime() {
        Donation donation = new Donation();
        String dateAndTime = "12/09/2022 11:12:00";
        donation.setCollectionDateTime(dateAndTime);

        assertThat(donation.getCollectionDateTime()).isEqualTo(dateAndTime);
    }

}

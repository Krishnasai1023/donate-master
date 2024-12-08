package com.example.donate.repository;

import com.example.donate.model.Donation;
import com.example.donate.model.User;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.event.annotation.BeforeTestExecution;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)  //Will not rollback changes
public class DonationRepositoryTests {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testFirst() {
        User user = entityManager.find(User.class, 2);
        Donation donation = new Donation();
        donation.setItemName("Fan");
        donation.setItemCondition("7");
        donation.setCollectionDateTime("12/12/2022 10:30:00");
        donation.setUser(user);

        Donation savedDonation = donationRepository.save(donation);
        assertThat(savedDonation.getDonationId()).isGreaterThan(0);
    }

    @Test
    public void testListAllDonations() {
        Iterable<Donation> donations = donationRepository.findAll();
        donations.forEach(donation -> System.out.println(donation));
    }

    @Test
    public void testGetDonationById() {
        Optional<Donation> donation = donationRepository.findById(1);
        assertThat(donation).isPresent();
    }

    @Test
    public void testUpdateDonationDetails() {
        User user = entityManager.find(User.class, 1);
        Donation donation = donationRepository.findById(1).get();
        donation.setVolunteered(true);
        donation.setVolunteeredBy(user);

        Donation savedDonation = donationRepository.save(donation);
        assertThat(savedDonation.isVolunteered()).isEqualTo(true);
    }
}

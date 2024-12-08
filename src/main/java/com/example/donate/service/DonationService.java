package com.example.donate.service;

import com.example.donate.model.Donation;
import com.example.donate.model.User;
import com.example.donate.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    public void saveDonation(Donation donation) {
        donationRepository.save(donation);
    }

    public List<Donation> getVolunteeredDonations() {
        return donationRepository.getAllDonations(true);
    }

    public List<Donation> getUnVolunteeredDonations() {
        return donationRepository.getAllDonations(false);
    }

    public Donation getDonationById(Integer id) {
        Optional<Donation> donation = donationRepository.findById(id);
        if(donation.isPresent()) {
            return donation.get();
        }
        return null;
    }

    public void updateDonation(Donation donation){
        donationRepository.save(donation);
    }

    public List<Donation> getMyVolunteeredDonations(User user) {
        return donationRepository.getMyUnReportedVolunteeredDonations(user, true, false);
    }

}

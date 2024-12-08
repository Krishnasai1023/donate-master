package com.example.donate.repository;

import com.example.donate.model.Donation;
import com.example.donate.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Integer> {
    @Query("select d from Donation d where d.isVolunteered =:isVolunteered")
    public List<Donation> getAllDonations(boolean isVolunteered);
    @Query("select d from Donation d where d.volunteeredBy =:user and d.isVolunteered =:isVolunteered and d.isDelivered=:isDelivered")
    public List<Donation> getMyUnReportedVolunteeredDonations(User user, boolean isVolunteered, boolean isDelivered);
}

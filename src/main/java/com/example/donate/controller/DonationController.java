package com.example.donate.controller;

import com.example.donate.model.Donation;
import com.example.donate.model.User;
import com.example.donate.service.DonationService;
import com.example.donate.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class DonationController {
    @Autowired
    private DonationService donationService;


    @RequestMapping("/donate")
    public String makeDonation(Model model, HttpSession session) {
        User user = (User)session.getAttribute("user");
        if(user == null) {
            return "redirect:/";
        }
        model.addAttribute("title", "Make Donation - Donate");
        model.addAttribute("donation", new Donation());
        return "donate";
    }

    @RequestMapping("/save-donation")
    public String saveDonation(@ModelAttribute("donation") Donation donation, Model model, HttpSession session) {
        User user = (User)session.getAttribute("user");
        if(user == null) {
            return "redirect:/";
        }
        try {
            donation.setUser(user);
            donationService.saveDonation(donation);
            model.addAttribute("donation", new Donation());
            model.addAttribute("title", "Save Donation");
            session.setAttribute("message",new Message("Successfully Saved ","alert-success"));
            return "redirect:/donate";
        } catch(Exception e) {
            model.addAttribute("donation",donation);
            session.setAttribute("message",
                    new Message("Something went wrong!","alert-danger"));
            return "redirect:/donate";
        }
    }

    @RequestMapping("/donations")
    public String donations(Model model, HttpSession session) {
        User user = (User)session.getAttribute("user");
        if(user == null || !user.getRole().equals("AUTHORIZED")) {
            return "redirect:/";
        }
        try {
            List<Donation> donations = donationService.getUnVolunteeredDonations();
            model.addAttribute("donations", donations);
            return "donations";
        } catch(Exception e){
            session.setAttribute("message",
                    new Message("Something went wrong!","alert-danger"));
            return "redirect:/donate";
        }
    }

    @RequestMapping("/donations/volunteer/{id}")
    public String volunteerForDonation(Model model, HttpSession session, @PathVariable("id") Integer id) {
        User user = (User)session.getAttribute("user");
        if(user == null || !user.getRole().equals("AUTHORIZED")) {
            return "redirect:/";
        }
        Donation donation = donationService.getDonationById(id);
        if(donation == null) {
            return "redirect:/donate";
        } else {
            donation.setVolunteered(true);
            donation.setVolunteeredBy(user);
            donationService.updateDonation(donation);
        }
        return "redirect:/donations";
    }

    @RequestMapping("/volunteered")
    public String volunteeredItems(Model model, HttpSession session) {
        User user = (User)session.getAttribute("user");
        if(user == null || !user.getRole().equals("AUTHORIZED")) {
            return "redirect:/";
        }
        try {
            List<Donation> donations = donationService.getMyVolunteeredDonations(user);
            model.addAttribute("donations", donations);
            return "volunteered";
        } catch(Exception e){
            session.setAttribute("message",
                    new Message("Something went wrong!","alert-danger"));
            return "redirect:/donate";
        }

    }

    @RequestMapping("/volunteered/report/{id}")
    public String reportDonated(Model model, HttpSession session, @PathVariable("id")Integer id) {
        User user = (User)session.getAttribute("user");
        if(user == null || !user.getRole().equals("AUTHORIZED")) {
            return "redirect:/";
        }
        Donation donation = donationService.getDonationById(id);
        if(donation == null) {
            return "redirect:/donate";
        } else {
            donation.setDelivered(true);
            donationService.updateDonation(donation);
        }
        return "redirect:/volunteered";
    }
}

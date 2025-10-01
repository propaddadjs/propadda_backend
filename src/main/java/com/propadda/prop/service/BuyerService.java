package com.propadda.prop.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.propadda.prop.dto.PasswordUpdateRequest;
import com.propadda.prop.dto.UserResponse;
import com.propadda.prop.mappers.UserMapper;
import com.propadda.prop.model.CommercialPropertyDetails;
import com.propadda.prop.model.EnquiredListingsDetails;
import com.propadda.prop.model.FavoriteListingsDetails;
import com.propadda.prop.model.FeedbackDetails;
import com.propadda.prop.model.HelpDetails;
import com.propadda.prop.model.ResidentialPropertyDetails;
import com.propadda.prop.model.Users;
import com.propadda.prop.repo.CommercialPropertyDetailsRepo;
import com.propadda.prop.repo.EnquiredListingsDetailsRepo;
import com.propadda.prop.repo.FavoriteListingsDetailsRepo;
import com.propadda.prop.repo.FeedbackDetailsRepo;
import com.propadda.prop.repo.HelpDetailsRepo;
import com.propadda.prop.repo.ResidentialPropertyDetailsRepo;
import com.propadda.prop.repo.UsersRepo;

@Service
public class BuyerService {

    @Autowired
    private UsersRepo userRepo;
    
    @Autowired
    private CommercialPropertyDetailsRepo cRepo;

    @Autowired
    private ResidentialPropertyDetailsRepo rRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private FeedbackDetailsRepo feedbackRepo;

    @Autowired
    private HelpDetailsRepo helpRepo;

    @Autowired
    private FavoriteListingsDetailsRepo favRepo;

    @Autowired
    private EnquiredListingsDetailsRepo enqRepo;

    public Map<String,List<?>> allFavoritePropertiesByBuyer(Integer buyerId) {
        Users b = userRepo.findById(buyerId).isPresent() ? userRepo.findById(buyerId).get() : null;
        List<ResidentialPropertyDetails> rpd = new ArrayList<>();
        List<CommercialPropertyDetails> cpd = new ArrayList<>();
        if(b!=null){
            List<FavoriteListingsDetails> favs = favRepo.findByFavoritesOfBuyer(b);
            for(FavoriteListingsDetails f : favs){
                if(f.getPropertyCategory().equalsIgnoreCase("Commercial")){
                    if(cRepo.findById(f.getPropertyId()).isPresent())
                        cpd.add(cRepo.findById(f.getPropertyId()).get());
                }
                else if(f.getPropertyCategory().equalsIgnoreCase("Residential")){
                    if(rRepo.findById(f.getPropertyId()).isPresent())
                        rpd.add(rRepo.findById(f.getPropertyId()).get());
                }
            }
        Map<String,List<?>> res = new HashMap<>();
        res.put("Commercial",cpd);
        res.put("Residential", rpd);
        return res;
        }
        else{
            return null;
        }
    }

    public Map<String,List<?>> allEnquiriesByBuyer(Integer buyerId) {
        Users b = userRepo.findById(buyerId).isPresent() ? userRepo.findById(buyerId).get() : null;
        List<ResidentialPropertyDetails> rpd = new ArrayList<>();
        List<CommercialPropertyDetails> cpd = new ArrayList<>();
        if(b!=null){
            List<EnquiredListingsDetails> enqs = enqRepo.findByEnquiriesByBuyer(b);
            for(EnquiredListingsDetails e : enqs){
                if(e.getPropertyCategory().equalsIgnoreCase("Commercial")){
                    if(cRepo.findById(e.getPropertyId()).isPresent())
                        cpd.add(cRepo.findById(e.getPropertyId()).get());
                }
                else if(e.getPropertyCategory().equalsIgnoreCase("Residential")){
                    if(rRepo.findById(e.getPropertyId()).isPresent())
                        rpd.add(rRepo.findById(e.getPropertyId()).get());
                }
            }
        Map<String,List<?>> res = new HashMap<>();
        res.put("Commercial",cpd);
        res.put("Residential", rpd);
        return res;
        }
        else{
            return null;
        }
    }

    public FavoriteListingsDetails addPropertyToFavoritesForBuyer(String category, Integer listingId, Integer buyerId) {
        FavoriteListingsDetails f = new FavoriteListingsDetails();
        f.setFavoritesOfBuyer(userRepo.findById(buyerId).isPresent() ? userRepo.findById(buyerId).get() : null);
        f.setPropertyCategory(category);
        f.setPropertyId(listingId);
        return favRepo.save(f);
    }

    public EnquiredListingsDetails sendEnquiriesFromBuyer(String category, Integer listingId, Integer buyerId) {
        EnquiredListingsDetails e = new EnquiredListingsDetails();
        e.setEnquiriesByBuyer(userRepo.findById(buyerId).isPresent() ? userRepo.findById(buyerId).get() : null);
        e.setPropertyCategory(category);
        e.setPropertyId(listingId);
        return enqRepo.save(e);
    }

    public UserResponse getBuyerDetails(Integer buyerId) {
        UserResponse b = new UserResponse();
        if(userRepo.findById(buyerId).isPresent()){
            b = UserMapper.toDto(userRepo.findById(buyerId).get());
        } 
        return b;
    }

    public Map<String, Integer> getBuyerDashboardMetrics(Integer buyerId) {
        Map<String, Integer> metrics = new HashMap<>();
        metrics.put("totalFavoriteProperties",favRepo.countByFavoritesOfBuyer(userRepo.findById(buyerId).get()));
        metrics.put("totalEnquiriesMade",enqRepo.countByEnquiriesByBuyer(userRepo.findById(buyerId).get()));
        return metrics; 
    }

    public UserResponse updateBuyerPassword(Integer buyerId, PasswordUpdateRequest passwordRequest) {
        Users buyer = userRepo.findById(buyerId).isPresent() ? userRepo.findById(buyerId).get() : null;
        if(buyer!=null){
            if (!passwordRequest.getNewPassword().equals(passwordRequest.getConfirmNewPassword())) {
            throw new IllegalArgumentException("New passwords do not match.");
            }
             if (!passwordEncoder.matches(passwordRequest.getCurrentPassword(), buyer.getPassword())) {
            throw new BadCredentialsException("Invalid current password provided.");
            }
            String newHashedPassword = passwordEncoder.encode(passwordRequest.getNewPassword());
            buyer.setPassword(newHashedPassword);
            Users updatedUser = userRepo.save(buyer);
            return UserMapper.toDto(updatedUser);
        } else {
            return null;
        }
    }

    public Object addFeedbackFromBuyer(FeedbackDetails feedbackRequest, Integer buyerId) {
         Users buyer = userRepo.findById(buyerId).isPresent() ? userRepo.findById(buyerId).get() : null;
        if(buyer!=null){
        feedbackRequest.setFeedbackUser(userRepo.findById(buyerId).get());
        return feedbackRepo.save(feedbackRequest);
        }
        else {
            return null;
        }
    }

    public Object addHelpRequestFromBuyer(HelpDetails helpRequest, Integer buyerId) {
        Users buyer = userRepo.findById(buyerId).isPresent() ? userRepo.findById(buyerId).get() : null;
        if(buyer!=null){
        helpRequest.setHelpUser(userRepo.findById(buyerId).get());
        return helpRepo.save(helpRequest);
        }
        else {
            return null;
        }        
    }


}
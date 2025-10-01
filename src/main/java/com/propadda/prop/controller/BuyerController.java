package com.propadda.prop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.propadda.prop.dto.PasswordUpdateRequest;
import com.propadda.prop.model.FeedbackDetails;
import com.propadda.prop.model.HelpDetails;
import com.propadda.prop.service.BuyerService;

@RestController
@RequestMapping("/buyer")
public class BuyerController {
    
    @Autowired
    private BuyerService buyerService;

    @GetMapping("/allFavoritePropertiesByBuyer/{buyerId}")
    public ResponseEntity<?> allFavoritePropertiesByBuyer(@PathVariable Integer buyerId) {
        return ResponseEntity.ok(buyerService.allFavoritePropertiesByBuyer(buyerId));
    }

    @GetMapping("/allEnquiriesByBuyer/{buyerId}")
    public ResponseEntity<?> allEnquiriesByBuyer(@PathVariable Integer buyerId) {
        return ResponseEntity.ok(buyerService.allEnquiriesByBuyer(buyerId));
    }

    @PostMapping("/addPropertyToFavoritesForBuyer/{category}/{listingId}/{buyerId}")
    public ResponseEntity<?> addPropertyToFavoritesForBuyer(@PathVariable String category, @PathVariable Integer listingId, @PathVariable Integer buyerId) {
        return ResponseEntity.ok(buyerService.addPropertyToFavoritesForBuyer(category,listingId,buyerId));
    }

    @PostMapping("/sendEnquiriesFromBuyer/{category}/{listingId}/{buyerId}")
    public ResponseEntity<?> sendEnquiriesFromBuyer(@PathVariable String category, @PathVariable Integer listingId, @PathVariable Integer buyerId) {
        return ResponseEntity.ok(buyerService.sendEnquiriesFromBuyer(category,listingId,buyerId));
    }

    @GetMapping("/getBuyerDetails/{buyerId}")
    public ResponseEntity<?> getBuyerDetails(@PathVariable Integer buyerId) {
        return ResponseEntity.ok(buyerService.getBuyerDetails(buyerId));
    }

    @PutMapping("/updateBuyerDetails/{buyerId}")
    public ResponseEntity<?> updateBuyerDetails(@PathVariable Integer buyerId) {
        return ResponseEntity.ok(buyerService.getBuyerDetails(buyerId));
    }

    @GetMapping("/getBuyerDashboardMetrics/{buyerId}")
    public ResponseEntity<?> getBuyerDashboardMetrics(@PathVariable Integer buyerId) {
        return ResponseEntity.ok(buyerService.getBuyerDashboardMetrics(buyerId));
    }

    @PatchMapping("/updateBuyerPassword/{buyerId}")
    public ResponseEntity<?> updateBuyerPassword(@PathVariable Integer buyerId, @RequestBody PasswordUpdateRequest passwordRequest) {
        return ResponseEntity.ok(buyerService.updateBuyerPassword(buyerId,passwordRequest));
    }
    
    @PostMapping("/addFeedbackFromBuyer/{buyerId}")
    public ResponseEntity<?> addFeedbackFromBuyer(@RequestBody FeedbackDetails feedbackRequest, @PathVariable Integer buyerId) {
        return ResponseEntity.ok(buyerService.addFeedbackFromBuyer(feedbackRequest,buyerId));
    }

    @PostMapping("/addHelpRequestFromBuyer/{buyerId}")
    public ResponseEntity<?> addHelpRequestFromBuyer(@RequestBody HelpDetails helpRequest, @PathVariable Integer buyerId) {
        return ResponseEntity.ok(buyerService.addHelpRequestFromBuyer(helpRequest,buyerId));
    }

}

package com.propadda.prop.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.propadda.prop.dto.AgentUpdateRequest;
import com.propadda.prop.dto.PasswordUpdateRequest;
import com.propadda.prop.model.FeedbackDetails;
import com.propadda.prop.model.HelpDetails;
import com.propadda.prop.service.AgentService;

@CrossOrigin(
  origins = { "http://localhost:5173" },
  allowCredentials = "true",                       // string in annotation
  allowedHeaders = { "Authorization", "Content-Type", "X-Requested-With" },
  methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE, RequestMethod.OPTIONS }
)
@RestController
@RequestMapping("/agent")
public class AgentController {

    @Autowired
    private AgentService agentService;
    
    @GetMapping("/allPropertiesByAgent/{agentId}")
    public ResponseEntity<?> getAllPropertiesByAgent(@PathVariable Integer agentId) {
        if(agentService.getAllPropertiesByAgent(agentId)!=null)
        return ResponseEntity.ok(agentService.getAllPropertiesByAgent(agentId));
        else
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/propertyByIdForAgent/{agentId}/{category}/{listingId}")
    public ResponseEntity<?> propertyByIdForAgent(@PathVariable Integer agentId, @PathVariable String category, @PathVariable Integer listingId) {
        if(agentService.propertyByIdForAgent(agentId,category,listingId)!=null)
        return ResponseEntity.ok(agentService.propertyByIdForAgent(agentId,category,listingId));
        else
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/pendingApprovalPropertiesForAgent/{agentId}")
    public ResponseEntity<?> pendingApprovalPropertiesForAgent(@PathVariable Integer agentId) {
        if(agentService.pendingApprovalPropertiesForAgent(agentId)!=null)
        return ResponseEntity.ok(agentService.pendingApprovalPropertiesForAgent(agentId));
        else
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/expiredPropertiesByAgent/{agentId}")
    public ResponseEntity<?> getExpiredProperties(@PathVariable Integer agentId) {
        if(agentService.getExpiredProperties(agentId)!=null)
        return ResponseEntity.ok(agentService.getExpiredProperties(agentId));
        else
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/soldPropertiesByAgent/{agentId}")
    public ResponseEntity<?> getSoldProperties(@PathVariable Integer agentId) {
        if(agentService.getSoldProperties(agentId)!=null)
        return ResponseEntity.ok(agentService.getSoldProperties(agentId));
        else
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/getAgentDetails/{agentId}")
    public ResponseEntity<?> getAgentDetails(@PathVariable Integer agentId) {
        if(agentService.getAgentDetails(agentId)!=null)
        return ResponseEntity.ok(agentService.getAgentDetails(agentId));
        else
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/updateAgentDetails/{agentId}")
    public ResponseEntity<?> updateAgentDetails(@RequestPart AgentUpdateRequest updatedAgentDetails, @RequestPart MultipartFile profileImage, @PathVariable Integer agentId)  throws IOException {
        if(agentService.updateAgentDetails(updatedAgentDetails,profileImage,agentId)!=null)
        return ResponseEntity.ok(agentService.updateAgentDetails(updatedAgentDetails,profileImage,agentId));
        else
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/getAgentDashboardMetrics/{agentId}")
    public ResponseEntity<?> getAgentDashboardMetrics(@PathVariable Integer agentId) {
        if(agentService.getAgentDashboardMetrics(agentId)!=null)
        return ResponseEntity.ok(agentService.getAgentDashboardMetrics(agentId));
        else
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/updateAgentPassword/{agentId}")
    public ResponseEntity<?> updateAgentPassword(@PathVariable Integer agentId, @RequestBody PasswordUpdateRequest passwordRequest) {
        if(agentService.updateAgentPassword(agentId,passwordRequest)!=null)
        return ResponseEntity.ok(agentService.updateAgentPassword(agentId,passwordRequest));
        else
        return ResponseEntity.notFound().build();
    }
    
    @PostMapping("/addFeedbackFromAgent/{agentId}")
    public ResponseEntity<?> addFeedbackFromAgent(@RequestBody FeedbackDetails feedbackRequest, @PathVariable Integer agentId) {
        if(agentService.addFeedbackFromAgent(feedbackRequest,agentId)!=null)
        return ResponseEntity.ok(agentService.addFeedbackFromAgent(feedbackRequest,agentId));
        else
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/addHelpRequestFromAgent/{agentId}")
    public ResponseEntity<?> addHelpRequestFromAgent(@RequestBody HelpDetails helpRequest, @PathVariable Integer agentId) {
        if(agentService.addHelpRequestFromAgent(helpRequest,agentId)!=null)
        return ResponseEntity.ok(agentService.addHelpRequestFromAgent(helpRequest,agentId));
        else
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/allNotificationsForAgent/{agentId}")
    public ResponseEntity<?> allNotificationsForAgent(@PathVariable Integer agentId){
        if(agentService.allNotificationsForAgent(agentId)!=null)
        return ResponseEntity.ok(agentService.allNotificationsForAgent(agentId));
        else
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/newNotificationsForAgent/{agentId}")
    public ResponseEntity<?> newNotificationsForAgent(@PathVariable Integer agentId){
        if(agentService.allNotificationsForAgent(agentId)!=null)
        return ResponseEntity.ok(agentService.allNotificationsForAgent(agentId));
        else
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/getUnreadNotificationCountForAgent/{agentId}")
    public ResponseEntity<?> getUnreadNotificationCountForAgent(@PathVariable Integer agentId){
        if(agentService.getUnreadNotificationCountForAgent(agentId)!=null)
        return ResponseEntity.ok(agentService.getUnreadNotificationCountForAgent(agentId));
        else
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/markNotificationViewedForAgent/{agentId}/{notificationId}")
    public ResponseEntity<?> markNotificationViewedForAgent(@PathVariable Integer agentId, @PathVariable Integer notificationId){
        if(agentService.markNotificationViewedForAgent(agentId, notificationId)!=null)
        return ResponseEntity.ok(agentService.markNotificationViewedForAgent(agentId, notificationId));
        else
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/markAllNotificationViewedForAgent/{agentId}")
    public ResponseEntity<?> markAllNotificationViewedForAgent(@PathVariable Integer agentId){
        if(agentService.markAllNotificationViewedForAgent(agentId)!=null)
        return ResponseEntity.ok(agentService.markAllNotificationViewedForAgent(agentId));
        else
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/markPropertyAsSoldForAgent/{agentId}/{category}/{listingId}")
    public ResponseEntity<?> markPropertyAsSoldForAgent(@PathVariable Integer agentId, @PathVariable String category, @PathVariable Integer listingId){
        if(agentService.markPropertyAsSoldForAgent(agentId,category,listingId)!=null)
        return ResponseEntity.ok(agentService.markPropertyAsSoldForAgent(agentId,category,listingId));
        else
        return ResponseEntity.notFound().build();
    }
}

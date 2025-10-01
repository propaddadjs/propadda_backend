package com.propadda.prop.mappers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.propadda.prop.dto.ResidentialPropertyResponse;
import com.propadda.prop.model.ResidentialPropertyAmenities;
import com.propadda.prop.model.ResidentialPropertyDetails;

public class ResidentialPropertyMapper {

    public static ResidentialPropertyResponse toDto(ResidentialPropertyDetails entity) {
        if (entity == null) {
            return null;
        }

        ResidentialPropertyResponse dto = new ResidentialPropertyResponse();

        // 1. Map fields from ResidentialPropertyDetails
        dto.setListingId(entity.getListingId());
        dto.setCategory(entity.getCategory());
        dto.setPreference(entity.getPreference());
        dto.setPropertyType(entity.getPropertyType());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setPrice(entity.getPrice());
        dto.setMaintenance(entity.getMaintenance());
        dto.setArea(entity.getArea());
        dto.setBedrooms(entity.getBedrooms());
        dto.setBathrooms(entity.getBathrooms());
        dto.setFurnishing(entity.getFurnishing());
        dto.setFacing(entity.getFacing());
        dto.setFloor(entity.getFloor());
        dto.setAge(entity.getAge());
        dto.setAvailability(entity.getAvailability());
        dto.setReraNumber(entity.getReraNumber());
        dto.setReraVerified(entity.getReraVerified());
        dto.setState(entity.getState());
        dto.setCity(entity.getCity());
        dto.setLocality(entity.getLocality());
        dto.setAddress(entity.getAddress());
        dto.setPincode(entity.getPincode());
        dto.setNearbyPlace(entity.getNearbyPlace());
        dto.setTotalFloors(entity.getTotalFloors());
        dto.setSecurityDeposit(entity.getSecurityDeposit());
        dto.setBalconies(entity.getBalconies());
        dto.setPowerBackup(entity.getPowerBackup());
        dto.setCoveredParking(entity.getCoveredParking());
        dto.setOpenParking(entity.getOpenParking());
        dto.setAdminApproved(entity.getAdminApproved());
        dto.setExpired(entity.isExpired());
        dto.setVip(entity.isVip());
        dto.setSold(entity.getSold());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setApprovedAt(entity.getApprovedAt());

        // 2. Map Owner (Uses the dedicated OwnerMapper)
        if (entity.getResidentialOwner() != null) {
            dto.setResidentialOwner(OwnerMapper.toDto(entity.getResidentialOwner()));
        }

        // 3. Map Media (Uses the dedicated MediaMapper)
        dto.setMediaFiles(ResidentialMediaMapper.toDtoList(entity.getResidentialPropertyMediaFiles()));
        
        // 4. Map fields from ResidentialPropertyAmenities
        ResidentialPropertyAmenities amenities = entity.getAmenities();
        if (amenities != null) {
            // Property features
            dto.setCenterCooling(amenities.isCenterCooling());
            dto.setFireAlarm(amenities.isFireAlarm());
            dto.setHeating(amenities.isHeating());
            dto.setGym(amenities.isGym());
            dto.setModularKitchen(amenities.isModularKitchen());
            dto.setPool(amenities.isPool());
            dto.setElevator(amenities.isElevator());
            dto.setPetFriendly(amenities.isPetFriendly());
            dto.setStorage(amenities.isStorage());
            dto.setLaundry(amenities.isLaundry());
            dto.setDishwasher(amenities.isDishwasher());
            dto.setDryer(amenities.isDryer());
            dto.setSauna(amenities.isSauna());
            dto.setEmergencyExit(amenities.isEmergencyExit());
            dto.setWaterPurifier(amenities.isWaterPurifier());
            dto.setGasPipeline(amenities.isGasPipeline());
            dto.setPark(amenities.isPark());
            dto.setVastuCompliant(amenities.isVastuCompliant());
            dto.setRainWaterHarvesting(amenities.isRainWaterHarvesting());
            dto.setMaintenanceStaff(amenities.isMaintenanceStaff());
            
            // Other rooms
            dto.setPoojaRoom(amenities.isPoojaRoom());
            dto.setStudyRoom(amenities.isStudyRoom());
            dto.setServantRoom(amenities.isServantRoom());
            dto.setStoreRoom(amenities.isStoreRoom());
            
            // Additional property features
            dto.setHighCeilingHeight(amenities.isHighCeilingHeight());
            dto.setFalseCeilingLighting(amenities.isFalseCeilingLighting());
            dto.setInternetConnectivity(amenities.isInternetConnectivity());
            dto.setCentrallyAirConditioned(amenities.isCentrallyAirConditioned());
            dto.setSecurityFireAlarm(amenities.isSecurityFireAlarm());
            dto.setRecentlyRenovated(amenities.isRecentlyRenovated());
            dto.setPrivateGardenTerrace(amenities.isPrivateGardenTerrace());
            dto.setNaturalLight(amenities.isNaturalLight());
            dto.setAiryRooms(amenities.isAiryRooms());
            dto.setIntercomFacility(amenities.isIntercomFacility());
            dto.setSpaciousInteriors(amenities.isSpaciousInteriors());
            
            // Society/building features
            dto.setFitnessCenter(amenities.isFitnessCenter());
            dto.setSwimmingPool(amenities.isSwimmingPool());
            dto.setClubhouseCommunityCenter(amenities.isClubhouseCommunityCenter());
            dto.setSecurityPersonnel(amenities.isSecurityPersonnel());
            dto.setLifts(amenities.isLifts()); // Note: Renamed in DTO for clarity if needed, using the entity name 'lifts'
            
            // Additional features
            dto.setSeparateEntryForServantRoom(amenities.isSeparateEntryForServantRoom());
            dto.setNoOpenDrainageAround(amenities.isNoOpenDrainageAround());
            dto.setBankAttachedProperty(amenities.isBankAttachedProperty());
            dto.setLowDensitySociety(amenities.isLowDensitySociety());
            
            // Water source
            dto.setMunicipalCorporation(amenities.isMunicipalCorporation());
            dto.setBorewellTank(amenities.isBorewellTank());
            dto.setWater24x7(amenities.isWater24x7());
            
            // Overlooking
            dto.setOverlookingPool(amenities.isOverlookingPool());
            dto.setOverlookingParkGarden(amenities.isOverlookingParkGarden());
            dto.setOverlookingClub(amenities.isOverlookingClub());
            dto.setOverlookingMainRoad(amenities.isOverlookingMainRoad());
            
            // Other features
            dto.setInGatedSociety(amenities.isInGatedSociety());
            dto.setCornerProperty(amenities.isCornerProperty());
            dto.setPetFriendlySociety(amenities.isPetFriendlySociety());
            dto.setWheelchairFriendly(amenities.isWheelchairFriendly());
            
            // Location advantages
            dto.setCloseToMetroStation(amenities.isCloseToMetroStation());
            dto.setCloseToSchool(amenities.isCloseToSchool());
            dto.setCloseToHospital(amenities.isCloseToHospital());
            dto.setCloseToMarket(amenities.isCloseToMarket());
            dto.setCloseToRailwayStation(amenities.isCloseToRailwayStation());
            dto.setCloseToAirport(amenities.isCloseToAirport());
            dto.setCloseToMall(amenities.isCloseToMall());
            dto.setCloseToHighway(amenities.isCloseToHighway());
        }

        return dto;
    }

    public static List<ResidentialPropertyResponse> toDtoList(List<ResidentialPropertyDetails> entities) {
        if (entities == null) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(ResidentialPropertyMapper::toDto)
                .collect(Collectors.toList());
    }
}
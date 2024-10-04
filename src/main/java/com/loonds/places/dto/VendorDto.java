package com.loonds.places.dto;

import com.loonds.places.entity.Address;
import com.loonds.places.entity.Vendor;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Instant;

@Data
public class VendorDto {
    int id;
    String gstNumber;
    String ceoName;
    @NotNull(message = "Vendor Name is required")
    @NotEmpty(message = "Vendor Name is required")
    String vendorName;
    @NotNull(message = "Phone Number is required")
    String phoneNumber;
    String mobileNumber;
    @NotNull(message = "Number Of Employees is required")
    Integer numberOfEmployees;
    String websiteUrl;
    Address address;
    String logo;
    Boolean status;
    Instant createdDate;
    String createdBy;

    public static VendorDto of(Vendor vendor) {
        var output = new VendorDto();
        output.setId(vendor.getId());
        output.setVendorName(vendor.getVendorName());
        output.setAddress(vendor.getAddress());
        output.setCeoName(vendor.getCeoName());
        output.setGstNumber(vendor.getGstNumber());
        output.setMobileNumber(vendor.getMobileNumber());
        output.setNumberOfEmployees(vendor.getNumberOfEmployees());
        output.setPhoneNumber(vendor.getPhoneNumber());
        output.setWebsiteUrl(vendor.getWebsiteUrl());
//        toMediaUrl.apply(vendor.getLogoFileId()).ifPresent(output::setLogo);
        output.setStatus(vendor.isStatus());
        output.setCreatedDate(vendor.getCreatedDate());
        output.setCreatedBy(vendor.getCreatedBy());
        return output;
    }
}

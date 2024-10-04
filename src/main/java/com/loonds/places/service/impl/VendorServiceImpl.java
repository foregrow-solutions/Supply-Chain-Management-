package com.loonds.places.service.impl;

import com.loonds.places.dto.VendorDto;
import com.loonds.places.entity.Vendor;
import com.loonds.places.repository.VendorRepo;
import com.loonds.places.service.VendorService;
import com.loonds.places.util.Signature;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SignatureException;

@Slf4j
@RequiredArgsConstructor
@Service
public class VendorServiceImpl implements VendorService {
    private final VendorRepo vendorRepo;


    @Override
    @Transactional
    public VendorDto create(VendorDto vendorDto) throws SignatureException {
        Vendor vendor = new Vendor();
        vendor.setVendorName(vendorDto.getVendorName());
        vendor.setVendorSignature(Signature.calculateRFC2104HMAC(vendorDto.getVendorName(), vendorDto.getGstNumber()));
        vendor.setAddress(vendorDto.getAddress());
        Vendor save = vendorRepo.save(vendor);
        return VendorDto.of(vendor);
    }
}

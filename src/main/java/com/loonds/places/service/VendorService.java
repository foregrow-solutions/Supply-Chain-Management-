package com.loonds.places.service;

import com.loonds.places.dto.VendorDto;

import java.security.SignatureException;

public interface VendorService {

    VendorDto create (VendorDto vendorDto) throws SignatureException;
}

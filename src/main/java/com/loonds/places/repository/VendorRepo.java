package com.loonds.places.repository;

import com.loonds.places.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepo extends JpaRepository<Vendor, String> {
}

package com.cryptoconverter.api;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptoCompareRepository extends JpaRepository<CryptoCompareRecord, String> {

}

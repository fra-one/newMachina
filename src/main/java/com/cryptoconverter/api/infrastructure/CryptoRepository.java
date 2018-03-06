package com.cryptoconverter.api.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptoRepository extends JpaRepository<CryptoRecord, String> {

}

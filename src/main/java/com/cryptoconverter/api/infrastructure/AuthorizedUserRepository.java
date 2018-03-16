package com.cryptoconverter.api.infrastructure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorizedUserRepository extends JpaRepository<AuthorizedUser, String>{
 
}

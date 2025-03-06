package com.manage.productmanage.repository.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manage.productmanage.domain.Customer;

/**
 * 등록된 고객정보를 가져오는 Repository - Spring Data JPA활용
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

}

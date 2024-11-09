package org.baas.baascore.service;

import lombok.RequiredArgsConstructor;
import org.baas.baascore.model.Customer;
import org.baas.baascore.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    /**
     * 이름 과 폰번호로 회원 Id를 찾아오는 메서드
     * @param name
     * @param phoneNum
     * @return
     */
    public Long findCustomerByNameAndPhone(String name, String phoneNum) {
        // 이름과 전화번호로 고객을 조회
        Optional<Customer> customerOptional = customerRepository.findByNameAndPhoneNum(name, phoneNum);
        if (customerOptional.isEmpty()) {
            throw new IllegalArgumentException("해당 이름과 전화번호를 가진 고객을 찾을 수 없습니다.");
        }
        return customerOptional.get().getId();
    }

}

package com.sep.koncentratorPlacanja.service;

import com.sep.koncentratorPlacanja.model.BitCoinSecret;
import com.sep.koncentratorPlacanja.repository.BitCoinSecretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BitCoinSecretService {
    @Autowired
    BitCoinSecretRepository bitCoinSecretRepository;

    BitCoinSecret save(BitCoinSecret bitCoinSecret){
        return bitCoinSecretRepository.save(bitCoinSecret);
    }

}

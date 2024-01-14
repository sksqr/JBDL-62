package org.gfg.service;


import jakarta.transaction.Transactional;
import org.gfg.common.UserCreatedPayload;
import org.gfg.dto.UserDto;
import org.gfg.entity.User;
import org.gfg.repo.IUserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class UserService {

    private Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private static String USER_CREATED_TOPIC="USER-CREATED";

    @Autowired
    private IUserRepo userRepo;

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    @Transactional
    public Long create(final UserDto userDTO) {
        final User user = new User();
        mapToEntity(userDTO, user);
        userRepo.save(user);
        UserCreatedPayload userCreatedPayload = new UserCreatedPayload(user.getId(),user.getName(),user.getEmail(), MDC.get("requestId"));
        Future<SendResult<String ,Object>> future = kafkaTemplate.send(USER_CREATED_TOPIC,String.valueOf(user.getId()),userCreatedPayload);
        LOGGER.info("Pushed userCreatedPayload in Kafka :{}",future);
        return user.getId();
    }


    private User mapToEntity(final UserDto userDTO, final User user) {
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setKycNumber(userDTO.getKycNumber());
        return user;
    }
}

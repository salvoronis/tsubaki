package com.tsubaki.services.greed;

import com.tsubaki.converters.GreedItemToGreedResponseDto;
import com.tsubaki.dto.greed.GreedResponseDto;
import com.tsubaki.exceptions.GlobalError;
import com.tsubaki.exceptions.NoSuchUserException;
import com.tsubaki.models.GreedItemUser;
import com.tsubaki.models.User;
import com.tsubaki.repositories.GreedItemUserRepository;
import com.tsubaki.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class GreedServiceImpl implements GreedService{

    private final GreedItemUserRepository greedItemUserRepository;

    private final GreedItemToGreedResponseDto greedItemToGreedResponseDto;

    @Autowired
    public GreedServiceImpl(
            GreedItemUserRepository greedItemUserRepository,
            GreedItemToGreedResponseDto greedItemToGreedResponseDto) {
        this.greedItemUserRepository = greedItemUserRepository;
        this.greedItemToGreedResponseDto = greedItemToGreedResponseDto;
    }


    @Override
    public List<GreedResponseDto> getGreedItems(Long userId) {
        List<GreedItemUser> userOptional = greedItemUserRepository.findGreedItemUsersByUserId(userId);

        if (userOptional.isEmpty()) {
            throw new NoSuchUserException(GlobalError.NO_SUCH_USER);
        }

        List<GreedResponseDto> result = new LinkedList<>();

        userOptional
                .forEach(item -> {
                    GreedResponseDto dto = greedItemToGreedResponseDto.transform(item.getGreedItem());
                    dto.setX(item.getX());
                    dto.setY(item.getY());
                    result.add(dto);});

        return result;
    }
}

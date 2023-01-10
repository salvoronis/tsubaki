package com.tsubaki.services.greed;

import com.tsubaki.converters.GreedItemToGreedResponseDto;
import com.tsubaki.dto.greed.GreedResponseDto;
import com.tsubaki.exceptions.GlobalError;
import com.tsubaki.exceptions.NoSuchUserException;
import com.tsubaki.models.User;
import com.tsubaki.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class GreedServiceImpl implements GreedService{

    private final UserRepository userRepository;

    private final GreedItemToGreedResponseDto greedItemToGreedResponseDto;

    @Autowired
    public GreedServiceImpl(UserRepository userRepository,
                            GreedItemToGreedResponseDto greedItemToGreedResponseDto) {
        this.userRepository = userRepository;
        this.greedItemToGreedResponseDto = greedItemToGreedResponseDto;
    }


    @Override
    public List<GreedResponseDto> getGreedItems(String username) {
        Optional<User> userOptional = userRepository.findUserByUsername(username);

        if (userOptional.isEmpty()) {
            throw new NoSuchUserException(GlobalError.NO_SUCH_USER);
        }

        List<GreedResponseDto> result = new LinkedList<>();

        userOptional
                .get()
                .getUserGreedItems()
                .forEach(item -> result.add(greedItemToGreedResponseDto.transform(item)));

        return result;
    }
}

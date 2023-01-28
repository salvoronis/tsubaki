package com.tsubaki.services.toolbar;

import com.tsubaki.converters.MenuItemToToolbarResponseDto;
import com.tsubaki.dto.toolbar.ToolbarResponseDto;
import com.tsubaki.exceptions.GlobalError;
import com.tsubaki.exceptions.NoSuchUserException;
import com.tsubaki.models.MenuItem;
import com.tsubaki.models.User;
import com.tsubaki.repositories.MenuItemRepository;
import com.tsubaki.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ToolbarServiceImpl implements ToolbarService {

    private final UserRepository userRepository;

    private final MenuItemToToolbarResponseDto menuItemToToolbarResponseDto;

    @Autowired
    public ToolbarServiceImpl(
            UserRepository userRepository,
            MenuItemToToolbarResponseDto menuItemToToolbarResponseDto) {
        this.userRepository = userRepository;
        this.menuItemToToolbarResponseDto = menuItemToToolbarResponseDto;
    }

    public List<ToolbarResponseDto> getMenu(String username) {
        Optional<User> userOptional = userRepository.findUserByUsername(username);

        if (userOptional.isEmpty()) {
            throw new NoSuchUserException(GlobalError.NO_SUCH_USER);
        }

        List<ToolbarResponseDto> result = new ArrayList<>();
        Set<MenuItem> internalOwnedItems = userOptional.get().getUserMenuItems();

        internalOwnedItems
                .stream()
                .filter(item -> item.getParentMenuItem() == null)
                .forEach(item -> result.add(menuItemToToolbarResponseDto.transform(item)));

        //serve only two levels in menu
        internalOwnedItems
                .stream()
                .filter(item -> item.getParentMenuItem() != null)
                .forEach(item -> {
                    for (ToolbarResponseDto parent : result) {
                        if (parent.getId() == item.getParentMenuItem().getId()) {
                            parent.getChildren().add(menuItemToToolbarResponseDto.transform(item));
                        }
                    }
                });


        //sort result
        ToolbarResponseDto.sort(result);

        return result;
    }
}

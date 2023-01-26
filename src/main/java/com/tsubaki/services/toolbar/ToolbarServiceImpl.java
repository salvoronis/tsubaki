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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ToolbarServiceImpl implements ToolbarService {

    private final UserRepository userRepository;

    private final MenuItemToToolbarResponseDto menuItemToToolbarResponseDto;

    private final MenuItemRepository menuItemRepository;

    @Autowired
    public ToolbarServiceImpl(
            UserRepository userRepository,
            MenuItemToToolbarResponseDto menuItemToToolbarResponseDto,
            MenuItemRepository menuItemRepository) {
        this.userRepository = userRepository;
        this.menuItemToToolbarResponseDto = menuItemToToolbarResponseDto;
        this.menuItemRepository = menuItemRepository;
    }

    public List<ToolbarResponseDto> getMenu(String username) {
        Optional<User> userOptional = userRepository.findUserByUsername(username);

        if (userOptional.isEmpty()) {
            throw new NoSuchUserException(GlobalError.NO_SUCH_USER);
        }

        List<MenuItem> userItems = new ArrayList<>();
        List<ToolbarResponseDto> result = new ArrayList<>();

        userOptional
                .get()
                .getUserMenuItems()
                .stream()
                .filter(item -> !item.isDefault() && item.getParentMenuItem() == null)
                .forEach(userItems::add);

        menuItemRepository
                .findAllByIsDefaultIs(true)
                .stream()
                .filter(item -> item.getParentMenuItem() == null)
                .forEach(userItems::add);

        userItems.forEach(item -> result.add(menuItemToToolbarResponseDto.transform(item)));

        //sort result
        ToolbarResponseDto.sort(result);

        return result;
    }
}

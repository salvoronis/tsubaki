package com.tsubaki.converters;

import com.tsubaki.dto.UserDto;
import com.tsubaki.dto.toolbar.ToolbarResponseDto;
import com.tsubaki.models.MenuItem;
import org.springframework.stereotype.Component;

@Component
public class MenuItemToToolbarResponseDto implements Convertable<MenuItem, ToolbarResponseDto>{
    @Override
    public ToolbarResponseDto transform(MenuItem src) {
        return new ToolbarResponseDto(
                src.getApplication() != null ? src.getApplication().getName() : null,
                src.getApplication() != null ? src.getApplication().getRedirectionUrl() : null,
                src.getSubItems(), src.getPriority());
    }
}

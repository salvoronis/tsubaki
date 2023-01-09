package com.tsubaki.services.toolbar;

import com.tsubaki.dto.toolbar.ToolbarResponseDto;

import java.util.List;

public interface ToolbarService {
    List<ToolbarResponseDto> getMenu(String username);
}

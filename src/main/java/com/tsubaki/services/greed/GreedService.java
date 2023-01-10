package com.tsubaki.services.greed;

import com.tsubaki.dto.greed.GreedResponseDto;

import java.util.List;

public interface GreedService {
    List<GreedResponseDto> getGreedItems(String username);
}

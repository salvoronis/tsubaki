package com.tsubaki.converters;

import com.tsubaki.dto.greed.GreedResponseDto;
import com.tsubaki.models.GreedItem;
import org.springframework.stereotype.Component;

@Component
public class GreedItemToGreedResponseDto implements Convertable<GreedItem, GreedResponseDto>{

    @Override
    public GreedResponseDto transform(GreedItem src) {
        if (src.getApplication() == null) {
            return null;
        }
        return new GreedResponseDto(
                src.getApplication().getName(),
                src.getApplication().getRedirectionUrl(),
                src.getWidth(),
                src.getHeight());
    }
}

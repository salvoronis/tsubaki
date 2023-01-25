package com.tsubaki.dto.greed;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GreedResponseDto {

    private String name;

    private String redirection_url;

    private int width;

    private int height;

    private int x;

    private int y;

    public GreedResponseDto(String name, String redirection_url, int width, int height) {
        this.name = name;
        this.redirection_url = redirection_url;
        this.width = width;
        this.height = height;
    }

}

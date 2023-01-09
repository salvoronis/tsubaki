package com.tsubaki.dto.toolbar;

import com.tsubaki.models.MenuItem;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ToolbarResponseDto {

    private String name;

    private String redirection_url;

    private List<ToolbarResponseDto> children;

    public ToolbarResponseDto(String name, String redirection_url, Set<MenuItem> children) {
        this.name = name;
        this.redirection_url = redirection_url;
        this.children = childrenToToolbarResponseDto(children);
    }

    static private List<ToolbarResponseDto> childrenToToolbarResponseDto(Set<MenuItem> children) {
        if (children == null) {
            return null; //recursion stop condition
        }

        List<ToolbarResponseDto> result = new LinkedList<>();

        children.forEach(
                item -> result.add(
                        new ToolbarResponseDto(
                                item.getName(),
                                item.getUrl(),
                                item.getSubItems())));

        return result;
    }
}

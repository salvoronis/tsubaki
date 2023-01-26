package com.tsubaki.dto.toolbar;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tsubaki.models.MenuItem;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ToolbarResponseDto implements Comparable<ToolbarResponseDto> {

    private String name;

    private String redirection_url;

    private List<ToolbarResponseDto> children;

    @JsonIgnore
    private int priority;

    public ToolbarResponseDto(String name,
                              String redirection_url,
                              Set<MenuItem> children,
                              int priority) {
        this.name = name;
        this.redirection_url = redirection_url;
        this.children = childrenToToolbarResponseDto(children);
        this.priority = priority;
    }

    public ToolbarResponseDto(MenuItem src) {
        this(src.getApplication() != null ? src.getApplication().getName() : null,
                src.getApplication() != null ? src.getApplication().getRedirectionUrl() : null,
                src.getSubItems(), src.getPriority());
    }

    static private List<ToolbarResponseDto> childrenToToolbarResponseDto(Set<MenuItem> children) {
        if (children == null) {
            return null; //recursion stop condition
        }

        List<ToolbarResponseDto> result = new LinkedList<>();

        children.forEach(
                item ->
                    result.add(
                        new ToolbarResponseDto(item)));

        return result;
    }

    @Override
    public int compareTo(ToolbarResponseDto o) {
        if (this.priority == o.priority) {
            return this.getName().compareToIgnoreCase(o.getName());
        }
        return this.priority - o.priority;
    }

    public static void sort(List<ToolbarResponseDto> toolbarResponseDtoList) {
        Collections.sort(toolbarResponseDtoList);
        //sort children
        toolbarResponseDtoList.forEach(ToolbarResponseDto::sort);
    }

    private void sort() {
        if (this.children.isEmpty()) {
            return;
        }
        Collections.sort(this.children);
        for (ToolbarResponseDto i : this.children) {
            i.sort();
        }
    }
}

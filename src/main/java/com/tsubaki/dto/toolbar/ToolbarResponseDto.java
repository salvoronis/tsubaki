package com.tsubaki.dto.toolbar;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tsubaki.models.MenuItem;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
public class ToolbarResponseDto implements Comparable<ToolbarResponseDto> {

    private String name;

    @JsonIgnore
    private long id;

    private String redirection_url;

    private List<ToolbarResponseDto> children;

    @JsonIgnore
    private int priority;

    public ToolbarResponseDto(long id,
                              String name,
                              String redirection_url,
                              int priority) {
        this.id = id;
        this.name = name;
        this.redirection_url = redirection_url;
        this.priority = priority;
        children = new LinkedList<>();
    }

    public ToolbarResponseDto(MenuItem src) {
        this(src.getId(),
                src.getApplication() != null ? src.getApplication().getName() : null,
                src.getApplication() != null ? src.getApplication().getRedirectionUrl() : null,
                src.getPriority());
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

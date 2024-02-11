package ru.itmo.wp.form;

import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TagStringList {
    private List<@Pattern(regexp = "[a-zA-z]+") String> tags;

    public void setTags(String tags) {
        if (!tags.isEmpty()) {
            this.tags = Arrays.asList(tags.trim().split("\\s+"));
        } else {
            this.tags = new ArrayList<>();
        }
    }

    public List<String> getTags() {
        return tags;
    }
}

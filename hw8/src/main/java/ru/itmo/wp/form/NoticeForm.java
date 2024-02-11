package ru.itmo.wp.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NoticeForm {
    @NotNull
    @NotBlank
    @Size(min = 4, max = 255)
    private String content;

    public void setContent(String content) {
        this.content = content.trim();
    }

    public String getContent() {
        return content;
    }
}

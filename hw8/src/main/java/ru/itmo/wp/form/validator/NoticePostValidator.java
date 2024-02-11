package ru.itmo.wp.form.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.form.NoticeForm;

@Component
public class NoticePostValidator implements Validator {
    public boolean supports(Class<?> clazz) {
        return NoticeForm.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            String notice = ((NoticeForm) target).getContent();
            if (notice.isBlank()) {
                errors.rejectValue("noticeForm", "noticeForm.emptyOrOnlyWhitespaces",
                        "Empty notice text");
            } else if (notice.trim().length() < 5) {
                errors.rejectValue("noticeForm", "noticeForm.lessThanFiveSymbols",
                        "Notice must contains at least 5 symbols");
            } else if (notice.trim().length() > 65535) {
                errors.rejectValue("noticeForm", "noticeForm.moreThanAllowSymbols",
                        "Notice must contains at most 65535 symbols");
            }
        }
    }
}

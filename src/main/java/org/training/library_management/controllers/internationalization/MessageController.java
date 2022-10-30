package org.training.library_management.controllers.internationalization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Validated
public class MessageController {

    @Autowired
    private ResourceBundleMessageSource source;


    /**
     * The function returns a message from the message source, which is a property file, based on the locale of the request
     *
     * @return The message from the message source.
     */
    @GetMapping("/msg")
    public String passMessage(/*@RequestHeader(value = "Accept-Language", defaultValue = "US", required = false) String locale*/) {
//        return source.getMessage("label.msg", null, new Locale(locale));
        return source.getMessage("label.creater", null, LocaleContextHolder.getLocale());
    }

}

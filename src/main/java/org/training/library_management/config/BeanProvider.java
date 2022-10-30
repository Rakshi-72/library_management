package org.training.library_management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

@Configuration
public class BeanProvider {
    @Bean
    // Creating a bean for the locale resolver.
    public LocaleResolver getLocaleResolver() {
        AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
        resolver.setDefaultLocale(Locale.US);
        return resolver;
    }

    @Bean
    // Creating a bean for the message source.
    public ResourceBundleMessageSource getMessageSource() {
        var source = new ResourceBundleMessageSource();
        source.setBasename("message");
        return source;
    }
}

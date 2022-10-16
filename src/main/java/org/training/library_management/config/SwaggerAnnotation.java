package org.training.library_management.config;

import io.swagger.annotations.Contact;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.SwaggerDefinition;

@SwaggerDefinition(info = @Info(

                description = "My API",

                version = "V1.2.3",

                title = "The only API you'll ever need to learn about me",

                termsOfService = "share and care",

                contact = @Contact(name = "Sponge-Bob", email = "sponge-bob@swagger.io", url = "http://swagger.io"),

                license = @License(name = "Apache 2.0", url = "http://www.apache.org")), consumes = {
                                "application/json" },

                produces = { "application/json" },

                schemes = { SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS })
public interface SwaggerAnnotation {

}
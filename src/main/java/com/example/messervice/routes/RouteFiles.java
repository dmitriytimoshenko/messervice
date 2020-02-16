package com.example.messervice.routes;

import com.example.messervice.entities.Catalog;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;

@Component
public class RouteFiles extends RouteBuilder {

    @Override
    public void configure() throws Exception {


        errorHandler(deadLetterChannel("activemq:queue:errqueue")
                .retryAttemptedLogLevel(LoggingLevel.ERROR));


        from("file:C:/inputfolder?exclude=.*txt|.*xml")
                .process(exchange ->  {
                    throw new IllegalArgumentException("Endpoint is null cause files not in .xml .txt extension");
                    });

        from("file:C:/inputfolder?include=.*txt")
                .to("activemq:queue:txtQueue");

        from("file:C:/inputfolder?include=.*xml")
                .to("activemq:queue:xmlQueue");


    }
}

package com.example.messervice.routes;


import com.example.messervice.entities.CD;
import com.example.messervice.entities.Catalog;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import javax.xml.bind.JAXBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@Component
public class TakeFiles extends RouteBuilder {

    @Autowired
    DataSource dataSource;


    @Override
    public void configure() throws Exception {

        JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
        JaxbDataFormat jaxbDataFormat = new JaxbDataFormat(jaxbContext);

        from("activemq:queue:txtQueue").log(LoggingLevel.INFO, "${body}").
                to("file:C:/outputfolder/?fileName=logging.txt&charset=utf-8&fileExist=Append");

        from("activemq:queue:xmlQueue")
                .convertBodyTo(String.class)
                .unmarshal(jaxbDataFormat)
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {

                        Connection connection = dataSource.getConnection();
                        PreparedStatement ps = connection.prepareStatement(
                                "insert into headers (id, header) values (nextval('headers_id_seq'), ?);");


                        PreparedStatement ps1 = connection.prepareStatement(
                                "insert into body (id, body, header_id) values (nextval('body_id_seq'), ?, ?);");

                        PreparedStatement ps2 = connection.prepareStatement("select currval('headers_id_seq')");

                        Catalog catalog =(Catalog) exchange.getIn().getBody();
                        List<CD> cds = catalog.getCDs();


                        try {
                            for (int i = 0; i < cds.size(); i++) {

                                StringBuilder sb = new StringBuilder();
                                sb.append(cds.get(i).getArtist());
                                sb.append(", ");
                                sb.append(cds.get(i).getCompany());
                                sb.append(", ");
                                sb.append(cds.get(i).getCountry());
                                sb.append(", ");
                                sb.append(cds.get(i).getPrice());
                                sb.append(", ");
                                sb.append(cds.get(i).getYear());

                                ps.setString(1, cds.get(i).getTitle());

                                ps.executeUpdate();

                                ResultSet rs = ps2.executeQuery();
                                int nextID = 0;
                                if(rs.next()) {
                                    nextID = rs.getInt(1);
                                }

                                ps1.setString(1, sb.toString());
                                ps1.setInt(2, nextID);

                                ps1.executeUpdate();

                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).log("Rows added succesfuly");


    }
}

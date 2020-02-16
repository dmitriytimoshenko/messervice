package com.example.messervice.entities;


import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Component
@XmlRootElement(name = "CATALOG")
@XmlAccessorType(XmlAccessType.FIELD)
public class Catalog {

    @XmlElement(name = "CD")
    private List<CD> CDs = new ArrayList<>();


    public List<CD> getCDs() {
        return CDs;
    }

    public void setCDs(List<CD> CDs) {
        this.CDs = CDs;
    }
}

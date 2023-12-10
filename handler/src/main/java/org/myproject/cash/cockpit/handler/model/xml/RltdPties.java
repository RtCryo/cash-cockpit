package org.myproject.cash.cockpit.handler.model.xml;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "RltdPties")
public class RltdPties {

    @XmlElement(name = "Cdtr")
    private Cdtr cdtr;

    @XmlElement(name = "Dbtr")
    private Dbtr dbtr;

}

package org.myproject.cash.cockpit.handler.model.xml;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "RmtInf")
public class RmtInf {

    @XmlElement(name = "Ustrd")
    private List<String> infoList;

}

package org.myproject.cash.cockpit.handler.service.reader;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import org.myproject.cash.cockpit.handler.model.xml.Document;
import org.springframework.stereotype.Service;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;


@Service
public class XmlReader {

    public Document readXml(final byte[] bytes) throws JAXBException, XMLStreamException {
        XMLInputFactory xif = XMLInputFactory.newFactory();
        xif.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, Boolean.FALSE);
        xif.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, false);

        ByteArrayInputStream input = new ByteArrayInputStream(bytes);

        StreamSource source = new StreamSource(input);
        XMLStreamReader xsr = xif.createXMLStreamReader(source);

        JAXBContext context = JAXBContext.newInstance(Document.class);
        return  (Document) context.createUnmarshaller()
                .unmarshal(xsr);
    }

}

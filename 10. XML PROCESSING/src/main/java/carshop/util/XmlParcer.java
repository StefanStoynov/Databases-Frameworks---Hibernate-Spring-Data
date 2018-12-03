package carshop.util;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface XmlParcer {
    <O> O parseXml(Class<O> objectClass, String path) throws JAXBException, FileNotFoundException;

    <O> void exportToXml(O object, Class<O> objectClass, String path) throws JAXBException;
}

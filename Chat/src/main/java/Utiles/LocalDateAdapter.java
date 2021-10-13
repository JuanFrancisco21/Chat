package Utiles;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlAdapter;
/**
 * Método para guardar y cargar fechas en xml.
 * @author JF
 *
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDateTime> {
    public LocalDateTime unmarshal(String v) throws Exception {
        return LocalDateTime.parse(v);
    }

    public String marshal(LocalDateTime v) throws Exception {
        return v.toString();
    }
}


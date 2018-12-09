package alararestaurant.config;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatterAdapter extends XmlAdapter<String, Date> {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    @Override
    public Date unmarshal(final String v) throws Exception {
        return dateFormat.parse(v);
    }

    @Override
    public String marshal(final Date v) throws Exception {
        return dateFormat.format(v);
    }
}

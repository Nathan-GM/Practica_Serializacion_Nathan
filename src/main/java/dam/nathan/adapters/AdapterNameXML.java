package dam.nathan.adapters;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class AdapterNameXML extends XmlAdapter<String, String> {
    @Override
    public String unmarshal(String s) throws Exception {
        return null;
    }

    @Override
    public String marshal(String s) throws Exception {
        return null;
    }
}

package dam.nathan;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dam.nathan.adapters.AdapterNameJSON;
import dam.nathan.adapters.AdapterNameXML;
import dam.nathan.adapters.AdapterPoint2DJSON;
import dam.nathan.adapters.AdapterPoint2DXML;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;

import java.io.*;

@XmlRootElement
public class Cactus implements Serializable{
    private String name;
    private Size size;
    private Point2D coordinate;

    public Cactus() {

    }

    public Cactus(String name, Size size, Point2D coordinate) {
        this.name = name;
        this.size = size;
        this.coordinate = coordinate;
    }

    @XmlElement (name = "nombre")
    @XmlJavaTypeAdapter(AdapterNameXML.class)
    public String getName() {
        return name;
    }

    @XmlElement (name = "size")
    public Size getSize() {
        return size;
    }

    @XmlElement (name = "coordinate")
    @XmlJavaTypeAdapter(AdapterPoint2DXML.class)
    public Point2D getCoordinate() {
        return coordinate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void setCoordinate(Point2D coordinate) {
        this.coordinate = coordinate;
    }
    
    public static void save(Cactus c, String name, String ext) throws excExtension {
        String extension = ext.toLowerCase();
        switch (extension) {
            case "xml":
                saveXML(c, name);
                break;
            case "json":
                saveJSON(c,name);
                break;
            case "bin":
                saveBIN(c,name);
                break;
            default:
                throw new excExtension(extension);
        }
    }

    private static void saveBIN(Cactus c, String name) {
        FileOutputStream fos = null;
        ObjectOutputStream salida = null;
        try {
            fos = new FileOutputStream(name + ".bin");
            salida = new ObjectOutputStream(fos);
            salida.writeObject(c);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                salida.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void saveJSON(Cactus c, String name)     {
        File fichero = new File(name + ".json");

        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(Point2D.class, new AdapterPoint2DJSON());
        gb.registerTypeAdapter(String.class, new AdapterNameJSON());

        Gson gson = gb.create();

        String json = gson.toJson(c);

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fichero));
            bw.write(json);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveXML(Cactus c, String name) {
        try {
            File fichero = new File(name + ".xml");
            JAXBContext contexto = JAXBContext.newInstance(c.getClass());
            Marshaller marshaller = contexto.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(c, fichero);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    
    public static Cactus load(String name, String ext) throws excExtension{
        String extension = ext.toLowerCase();
        switch (extension) {
            case "xml":
                return loadXML(name);
            case "json":
                return loadJSON(name);
            case "bin":
                return loadBIN(name);
            default:
                throw new excExtension(extension);
        }
    }

    private static Cactus loadXML(String name) {
        Cactus c;
        try {
            JAXBContext context = JAXBContext.newInstance(Cactus.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            c = (Cactus) unmarshaller.unmarshal(new File(name + ".xml"));

            /*System.out.println(
                    "Nombre: " + c.getName() +
                            "\nTamanyo:" + c.getSize() +
                            "\nPoint2D: " + c.getCoordinate()
            );*/
            return c;
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Cactus loadJSON(String name) {
        File fichero = new File(name + ".json");
        Cactus c;
        try {
            FileReader fr = new FileReader(fichero);
            GsonBuilder gb = new GsonBuilder();

            gb.registerTypeAdapter(Point2D.class, new AdapterPoint2DJSON());
            gb.registerTypeAdapter(String.class, new AdapterNameJSON());

            Gson gson = gb.create();

            c = gson.fromJson(fr, Cactus.class);
            return c;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Cactus loadBIN(String name) {
        Cactus c;
        FileInputStream fis = null;
        ObjectInputStream entrada = null;
        try {
            fis = new FileInputStream(name + ".bin");
            entrada = new ObjectInputStream(fis);
            c = (Cactus) entrada.readObject();
            return c;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "Cactus{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", coordinate=" + coordinate +
                '}';
    }
}

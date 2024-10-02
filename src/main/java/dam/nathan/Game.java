package dam.nathan;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dam.nathan.adapters.AdapterNameJSON;
import dam.nathan.adapters.AdapterPoint2DJSON;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.*;
import java.util.Arrays;

@XmlRootElement
public class Game implements Serializable{
    private Level[] levels;

    public Game() {
    }

    public Game(Level[] levels) {
        this.levels = levels;
    }
    
    public static void save(Game g, String name, String ext) throws excExtension {
        String extension = ext.toLowerCase();
        switch (extension) {
            case "xml":
                saveXML(g, name);
                break;
            case "json":
                saveJSON(g, name);
                break;
            case "bin":
                saveBIN(g, name);
                break;
            default:
                throw new excExtension(extension);
        }
    }

    private static void saveBIN(Game g, String name) {
        FileOutputStream fos = null;
        ObjectOutputStream salida = null;
        try {
            fos = new FileOutputStream(name + ".bin");
            salida = new ObjectOutputStream(fos);
            salida.writeObject(g);
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

    private static void saveJSON(Game g, String name) {
        File fichero = new File(name + ".json");

        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(Point2D.class, new AdapterPoint2DJSON());
        gb.registerTypeAdapter(String.class, new AdapterNameJSON());
        Gson gson = gb.create();

        String json = gson.toJson(g);

        System.out.println(json);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fichero));
            bw.write(json);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveXML(Game g, String name) {
        try {
            File fichero = new File(name + ".xml");
            JAXBContext contexto = JAXBContext.newInstance(g.getClass());
            Marshaller marshaller = contexto.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(g, fichero);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static Game load(String name, String ext) throws excExtension {
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

    private static Game loadXML(String name) {
        Game g;
        try {
            JAXBContext context = JAXBContext.newInstance(Game.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            g = (Game) unmarshaller.unmarshal(new File(name + ".xml"));
            return g;
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }

    }

    private static Game loadJSON(String name) {

        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(Point2D.class, new AdapterPoint2DJSON());
        gb.registerTypeAdapter(String.class, new AdapterNameJSON());
        Gson gson = gb.create();
    try {
        Game g = gson.fromJson(new FileReader(name + ".json"), Game.class);
        return g;
    } catch (FileNotFoundException e) {
        e.printStackTrace();
        return null;
    }
    }

    private static Game loadBIN(String name) {
        Game g;
        FileInputStream fis = null;
        ObjectInputStream entrada = null;
        try {
            fis = new FileInputStream(name + ".bin");
            entrada = new ObjectInputStream(fis);
            g = (Game) entrada.readObject();
            return g;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @XmlElement(name = "Nivel")
    public Level[] getLevels() {
        return levels;
    }

    public void setLevels(Level[] levels) {
        this.levels = levels;
    }

    @Override
    public String toString() {
        return "Game{" +
                "levels=" + Arrays.toString(levels) +
                '}';
    }
}


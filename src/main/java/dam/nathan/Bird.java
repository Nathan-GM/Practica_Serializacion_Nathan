package dam.nathan;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import dam.nathan.adapters.AdapterNameJSON;
import dam.nathan.adapters.AdapterNameXML;
import dam.nathan.adapters.AdapterPoint2DJSON;
import dam.nathan.adapters.AdapterPoint2DXML;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.io.*;

@XmlRootElement
public class Bird implements Serializable{
    private String name;
    private float speed;
    private Size size;
    private Point2D coordinate;

    public Bird() {
    }

    public Bird(String name, Float speed, Size size, Point2D coordinate) {
        this.name = name;
        this.speed = speed;
        this.size = size;
        this.coordinate = coordinate;
    }


    public static void save(Bird b, String name, String ext) throws excExtension {
        String extension = ext.toLowerCase();
        switch (extension) {
            case "xml":
                saveXML(b, name);
                break;
            case "json":
                saveJSON(b,name);
                break;
            case "bin":
                saveBIN(b,name);
                break;
            default:
                throw new excExtension(extension);
        }
    }

    private static void saveBIN(Bird b, String name) {
        FileOutputStream fos = null;
        ObjectOutputStream salida = null;
        try {
            fos = new FileOutputStream(name + ".bin");
            salida = new ObjectOutputStream(fos);
            salida.writeObject(b);
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

    private static void saveJSON(Bird b, String name) {
        File fichero = new File(name + ".json");
        GsonBuilder gb = new GsonBuilder();
        gb.registerTypeAdapter(Point2D.class, new AdapterPoint2DJSON());
        gb.registerTypeAdapter(String.class, new AdapterNameJSON());
        Gson gson = gb.create();

        String json = gson.toJson(b);

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fichero));
            bw.write(json);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveXML(Bird b, String name) {
      try {
          File fichero = new File(name + ".xml");
          JAXBContext contexto = JAXBContext.newInstance(b.getClass());
          Marshaller marshaller = contexto.createMarshaller();
          marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
          marshaller.marshal(b, fichero);
      } catch (JAXBException e) {
          e.printStackTrace();
      }
    }


    public static Bird load(String name, String ext) throws excExtension {
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

    private static Bird loadXML(String name) {
        Bird b;
        try {
            JAXBContext context = JAXBContext.newInstance(Bird.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();

            b = (Bird) unmarshaller.unmarshal(new File(name + ".xml"));

            /*System.out.println(
                    "Nombre: " + b.getName() +
                            "\nTamanyo:" + b.getSize() +
                            "\nVelocidad: " + b.getSpeed() +
                            "\nPoint2D: " + b.getCoordinate()
            );*/
            return b;
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Bird loadJSON(String name) {
        File fichero = new File(name + ".json");
        Bird b;
        try {
            FileReader fr = new FileReader(fichero);
            GsonBuilder gb = new GsonBuilder();

            gb.registerTypeAdapter(Point2D.class, new AdapterPoint2DJSON());
            gb.registerTypeAdapter(String.class, new AdapterNameJSON());

            Gson gson = gb.create();

            b = gson.fromJson(fr, Bird.class);
            return b;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Página 50 Apuntes
    private static Bird loadBIN(String name) {
        Bird b;
        FileInputStream fis = null;
        ObjectInputStream entrada = null;
        try{
            fis = new FileInputStream(name + ".bin");
            entrada = new ObjectInputStream(fis);
            b = (Bird) entrada.readObject();
            //b.toString();
            return b;
        }  catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch ( ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                fis.close();
                entrada.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @XmlElement (name = "nombre")
    @XmlJavaTypeAdapter(AdapterNameXML.class)
    public String getName() {
        return name;
    }

    @XmlElement (name = "speed")
    public float getSpeed() {
        return speed;
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

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void setCoordinate(Point2D coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public String toString() {
        return "Bird{" +
                "name='" + name + '\'' +
                ", speed=" + speed +
                ", size=" + size +
                ", coordinate=" + coordinate +
                '}';
    }
}

package dam.nathan;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.Arrays;

@XmlRootElement
public class Level implements Serializable {
    private double time;
    private float speed;
    private Cactus[] cactus;
    private Bird[] birds;
    private String music;

    public Level() {
    }

    public Level(double time, float speed, Cactus[] cactus, Bird[] birds, String music) {
        this.time = time;
        this.speed = speed;
        this.cactus = cactus;
        this.birds = birds;
        this.music = music;
    }

    public double getTime() {
        return time;
    }

    public float getSpeed() {
        return speed;
    }

    //@XmlElement (name = "Cactus")
    public Cactus[] getCactus() {
        return cactus;
    }
    //@XmlElement (name = "Pajaros")
    public Bird[] getBirds() {
        return birds;
    }

    public String getMusic() {
        return music;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setCactus(Cactus[] cactus) {
        this.cactus = cactus;
    }

    public void setBirds(Bird[] birds) {
        this.birds = birds;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    @Override
    public String toString() {
        return "Level{" +
                "time=" + time +
                ", speed=" + speed +
                ", cactus=" + Arrays.toString(cactus) +
                ", birds=" + Arrays.toString(birds) +
                ", music='" + music + '\'' +
                '}';
    }
}

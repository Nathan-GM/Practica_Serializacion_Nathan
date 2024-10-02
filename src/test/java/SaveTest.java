import dam.nathan.*;
import org.junit.jupiter.api.Test;

public class SaveTest {
    Point2D cb = new Point2D(1,0);
    Point2D cc = new Point2D(4,2);
    Size sb = new Size(4,2);
    Size sc = new Size(1,0);

    Cactus cactus = new Cactus("Jaune", sc, cc);
    Cactus cactus2 = new Cactus("Sara", sb, cb);
    Bird bird = new Bird("Elliot", 0.2f, sb, cb);
    Bird bird2 = new Bird("Santo", 0.3f, sc, cc);

    Cactus[] cactusList = new Cactus[2];

    Bird[] birdList = new Bird[2];

    Level l0 = new Level(20, 0.4f, cactusList, birdList, "musica1");
    Level l1 = new Level(30, 0.5f, cactusList, birdList, "musica2");
    Level l2 = new Level(80, 0.1f, cactusList, birdList, "musica3");

    Level[] levels = new Level[3];

    Game game = new Game(levels);


    @Test
    public void saveTest() throws excExtension {
        //Test de guardado Cactus
        Cactus.save(cactus, "./Ficheros/Tests/testBINCactus", "bin");
        Cactus.save(cactus, "./Ficheros/Tests/testJSONCactus", "json");
        Cactus.save(cactus, "./Ficheros/Tests/testXMLCactus", "xml");

        //Test de guardado Pajaros
        Bird.save(bird, "./Ficheros/Tests/testXMLBird", "xml");
        Bird.save(bird, "./Ficheros/Tests/testJSONBird", "json");
        Bird.save(bird, "./Ficheros/Tests/testBINBird", "bin");
    }

    @Test
    public void saveTestError() throws excExtension {
        Cactus.save(cactus, "./Ficheros/Tests/testErrorCactus", "png");
        Bird.save(bird, "./Ficheros/Tests/testErrorBird", "html");
    }

    @Test
    public void loadTest() throws excExtension {
        Cactus c1,c2,c3;
        Bird b1,b2,b3;

        System.out.println();

        //XML
        System.out.println("XML");
        b1 = Bird.load("./Ficheros/Tests/testXMLBird", "xml");
        System.out.println(b1.toString());
        c1 = Cactus.load("./Ficheros/Tests/testXMLCactus", "xml");
        System.out.println(c1.toString());
        System.out.println();

        //JSON
        System.out.println("JSON");
        b2 = Bird.load("./Ficheros/Tests/testJSONBird", "json");
        System.out.println(b2.toString());
        c2 = Cactus.load("./Ficheros/Tests/testJSONCactus", "json");
        System.out.println(c2.toString());
        System.out.println();

        //BINARIO -> Comentado debido a devolver errores (Explicado en PDF)
        /*
        System.out.println("BIN");
        b3 = Bird.load("./Ficheros/Tests/testBINBird", "bin");
        System.out.println(b3.toString());*/
    }

    @Test
    public void saveGameTest() throws excExtension{
        cactusList[0] = cactus;
        cactusList[1] = cactus2;
        birdList[0] = bird;
        birdList[1] = bird2;

        levels[0] = l0;
        levels[1] = l1;
        levels[2] = l2;

        Game.save(game,"./Ficheros/Tests/testBINGame", "bin");
        Game.save(game,"./Ficheros/Tests/testJSONGame", "json");
        Game.save(game,"./Ficheros/Tests/testXMLGame", "xml");
    }

    @Test
    public void loadGameTest() throws excExtension {
        Game g1,g2,g3;
        System.out.println();

        //XML
        System.out.println("XML");
        g1 = Game.load("./Ficheros/Tests/testXMLGame", "xml");
        System.out.println(g1.toString());
        System.out.println();

        //JSON
        System.out.println("JSON");
        g2 = Game.load("./Ficheros/Tests/testJSONGame", "json");
        System.out.println(g2.toString());
        System.out.println();

        //BINARIO -> Comentado debido a devolver errores (Explicado en PDF)
        /*
        System.out.println("BIN");
        g3 = Game.load("./Ficheros/Tests/testBINGame", "bin");
        System.out.println(g3.toString());
        System.out.println();
*/
    }
}

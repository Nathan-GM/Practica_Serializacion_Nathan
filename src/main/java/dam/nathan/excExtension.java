package dam.nathan;

public class excExtension extends Exception{
    private String extension;

    excExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public String toString() {
        return "La extensión " + extension + " no esta permitida";
    }
}
package dam.nathan.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class AdapterNameJSON extends TypeAdapter<String> {
    @Override
    public void write(JsonWriter jsonWriter, String s) throws IOException {
        jsonWriter.nullValue();
        return;
    }

    @Override
    public String read(JsonReader jsonReader) throws IOException {
        return null;
    }
}

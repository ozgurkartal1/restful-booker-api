package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class TestDataReader {

    @SneakyThrows
    public static <T> T dataReader(String path, Class<T> valueType){
        ObjectMapper mapper = new ObjectMapper();

        FileInputStream input = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/test_data/" + path);

        return mapper.readValue(input, valueType);
    }
}

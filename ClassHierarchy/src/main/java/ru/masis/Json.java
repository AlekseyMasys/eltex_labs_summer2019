package ru.masis;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

public interface Json {
    String toJSON();
    void fromJSON(String str);
}

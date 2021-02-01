package ru.javawebinar.basejava.util;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class LocalDateJsonAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
    public static final LocalDateJsonAdapter INSTANCE = new LocalDateJsonAdapter();

    private LocalDateJsonAdapter() {
    }

    @Override
    public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (!jsonElement.isJsonNull()) {
            return LocalDate.parse(jsonElement.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE);
        }
        return null;
    }

    @Override
    public JsonElement serialize(LocalDate localDate, Type type, JsonSerializationContext jsonSerializationContext) {
        if (localDate != null) {
            return new JsonPrimitive(localDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        }
        return JsonNull.INSTANCE;
    }
}

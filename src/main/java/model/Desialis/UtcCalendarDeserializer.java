package model.Desialis;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;

import java.io.IOException;
import java.util.Calendar;
import java.util.TimeZone;

@JacksonStdImpl
public class UtcCalendarDeserializer extends DateDeserializers.CalendarDeserializer {
    TimeZone TZ_UTC = TimeZone.getTimeZone("UTC");
    @Override
    public Calendar deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        System.out.println( " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        return super.deserialize(jp, ctxt);
    }
}

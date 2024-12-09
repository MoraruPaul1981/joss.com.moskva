package businesslogic.blgeneratorjackson;


import businesslogic.SubClassWriterErros;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.cbor.CBORFactory;
import com.fasterxml.jackson.dataformat.cbor.CBORGenerator;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

@Named
@ApplicationScoped
public class GeneratorJackson {



    @Inject
    SubClassWriterErros subClassWriterErros;
    // TODO: 23.03.2023  ПАСРСИНГ  JSON JSCSON
// TODO: 23.03.2023  БИБЛИОТКЕ Jackson для созданиия И ПАРСИНГА JSON
    @Produces
    @Singleton
    @ProducedJacson
    public ObjectMapper getGeneratorJackson()   {
        ObjectMapper mapperJackson = null;
        try{
            //TODO Jacson парсинг JSON
            JsonFactory factory = new JsonFactory();
            //CBORFactory factory = new CBORFactory();
            mapperJackson = new ObjectMapper(factory);
            mapperJackson.writerWithDefaultPrettyPrinter();
            mapperJackson.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
            mapperJackson.setLocale(new Locale("ru","RU"));
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", new Locale("ru","RU"));
            mapperJackson.setDateFormat(df);
            mapperJackson.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
            mapperJackson.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            mapperJackson.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            mapperJackson.enable(SerializationFeature.FLUSH_AFTER_WRITE_VALUE);
            mapperJackson.enable(SerializationFeature.INDENT_OUTPUT);
            mapperJackson.setSerializationInclusion(JsonInclude.Include.NON_NULL);
          System.out.println( " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " mapperJackson "+mapperJackson);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println( " ERROR  class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " mapperJackson "+mapperJackson);
        }
        return  mapperJackson;

    }

    // TODO: 23.03.2023  БИБЛИОТКЕ Jackson для созданиия И ПАРСИНГА JSON
    @Produces
    @Singleton
    @ProducedJacsonTwo
    public CBORGenerator getGeneratorCBORJackson()   {
        CBORGenerator generator= null;
        try{
            //TODO Jacson парсинг JSON
            CBORFactory factory = new CBORFactory();
            factory._getBufferRecycler().allocByteBuffer(2048);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            generator=          factory.createGenerator(byteArrayOutputStream);
            System.out.println( " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " generator "+generator);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println( " ERROR  class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        }
        return  generator;

    }

}


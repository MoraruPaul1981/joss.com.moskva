package businesslogic.genertoringjson;

import businesslogic.SubClassWriterErros;
import businesslogic.blgeneratorjackson.ProducedJacson;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import dsu1glassfishatomic.workinterfaces.ProducedGeneratorJson2;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.io.ByteArrayOutputStream;
import java.util.List;
@Named
public class GeneratorJsonWriteValue  extends  GeneratorJsonAbstract {

   @Inject
   SubClassWriterErros subClassWriterErros;



     @Inject
     @ProducedJacson
      ObjectMapper getGeneratorJackson;

    @Override
    public byte[] getGeneratorJson(List<?> listОтHiberideДляГенерации, ServletContext ЛОГ) {
        byte[] БуферСозданогоJSONJackson=null;
        try {
            ObjectWriter   writer = getGeneratorJackson.writerWithDefaultPrettyPrinter();
            ByteArrayOutputStream   byteArrayOutputStream=new ByteArrayOutputStream(2048);
            writer.writeValue(byteArrayOutputStream,listОтHiberideДляГенерации);
            БуферСозданогоJSONJackson=byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();

            ЛОГ.log("\n"+" class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                    " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                    " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+ " БуферСозданогоJSONJackson " +БуферСозданогоJSONJackson.length+
                    " listОтHiberideДляГенерации" + listОтHiberideДляГенерации );
        } catch (Exception e) {
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(),"ErrorsLogs/ErrorJbossServletDSU1.txt");
        }
       return БуферСозданогоJSONJackson;
        //return super.getGeneratorJson(listОтHiberideДляГенерации, ЛОГ);
    }
}//TODO public class GeneratorJsonWriteValue




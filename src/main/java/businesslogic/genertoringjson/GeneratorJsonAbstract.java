package businesslogic.genertoringjson;

import businesslogic.SubClassWriterErros;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sun.istack.NotNull;
import dsu1glassfishatomic.workinterfaces.ProducedGeneratorJson2;
import dsu1glassfishatomic.workinterfaces.ProducedGeneratorJson3;
import dsu1glassfishatomic.workinterfaces.ProducedGeneratorJson4;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.io.ByteArrayOutputStream;
import java.util.List;


public abstract class GeneratorJsonAbstract {
    byte[] БуферСозданогоJSONJackson;
    // TODO ГЕНЕРАЦИЯ JSON ПО  НОВОМУ Jackson
    public   byte[] getGeneratorJson(@NotNull List<?> listОтHiberideДляГенерации, @NotNull ServletContext ЛОГ) {
        return БуферСозданогоJSONJackson; }

}




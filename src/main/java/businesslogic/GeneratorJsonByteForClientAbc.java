package businesslogic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.istack.NotNull;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import java.lang.module.Configuration;
import java.util.List;

public abstract class GeneratorJsonByteForClientAbc {
    protected ObjectMapper getGeneratorJacksonCbor;
    protected    SubClassWriterErros subClassWriterErros;

    public GeneratorJsonByteForClientAbc(ObjectMapper getGeneratorJacksonCbor, SubClassWriterErros subClassWriterErros) {
        this.getGeneratorJacksonCbor = getGeneratorJacksonCbor;
        this.subClassWriterErros = subClassWriterErros;
    }


    public abstract byte[]  generatingJsonWriteValueAndSequenceWriter(@NotNull List<?> listОтHiberideДляГенерации,  @NotNull ServletContext ЛОГ);
}

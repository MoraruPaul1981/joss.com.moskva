package businesslogic.bl_bluetooths.blscanners.interfaces;

import com.sun.istack.NotNull;
import org.hibernate.Session;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

public interface BinesslogicGetScannerInterface {


    byte[]   getByteScanner(@NotNull HttpServletRequest request,
                                 @NotNull ServletContext ЛОГ,
                                 @NotNull HttpServletResponse response ,
                                 @NotNull Session session);


    List<?> getReadDataScanner(ServletContext ЛОГ,
                                     Session session,
                                     String NameTable,
                                     Long VersionData,
                                     Date getBremylocal);



    byte[] generatorJsonScanner(@NotNull  List<?> ЛистДанныеОтHibenide
            , @NotNull ServletContext ЛОГ);

}

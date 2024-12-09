package businesslogic.bl_bluetooths.blgattserver.interfaces;

import com.sun.istack.NotNull;
import model.GattserversuccessEntity;
import org.hibernate.Session;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface OperationsHIbernateGattGet {




    byte[]  generatingJsontoSendtoGattClient (@NotNull ServletContext ЛОГ,
                                              @NotNull HttpServletRequest request,
                                              @NotNull HttpServletResponse response,
                                              @NotNull Session session );




    List<?> getCompleteAllmacGattServer(ServletContext ЛОГ,
                                        Session session,
                                        String NameTable,
                                        Long VersionData,
                                        Date getBremylocal);


    byte[] generatorJsonFinal(@NotNull List<?> ЛистДанныеОтHibenideGattServer,
                              @NotNull ServletContext ЛОГ);

}

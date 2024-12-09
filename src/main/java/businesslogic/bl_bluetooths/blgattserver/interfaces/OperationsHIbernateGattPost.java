package businesslogic.bl_bluetooths.blgattserver.interfaces;

import com.sun.istack.NotNull;
import model.GattserversuccessEntity;
import org.hibernate.Session;

import javax.servlet.ServletContext;
import java.math.BigDecimal;
import java.util.List;

public interface OperationsHIbernateGattPost {




    public Long persistEntityGattServer(@NotNull List<GattserversuccessEntity> getlistGattServerwtirer,
                                        @NotNull Session session,
                                        @NotNull ServletContext ЛОГ);

    Integer insertingGattServer(@NotNull   GattserversuccessEntity gattserversuccessEntity,
                                @NotNull Session session,
                                @NotNull ServletContext ЛОГ);

    Integer updatetingGattServer(@NotNull GattserversuccessEntity gattserversuccessEntity,
                                 @NotNull Session session,
                                 @NotNull ServletContext ЛОГ);

    public Long getQuertyGattServerUUD(@NotNull Session session,
                                          @NotNull ServletContext ЛОГ,@NotNull BigDecimal getUUID);


    public Long getQuertyGattServerMaxVesrion(@NotNull Session session, @NotNull ServletContext ЛОГ);
}

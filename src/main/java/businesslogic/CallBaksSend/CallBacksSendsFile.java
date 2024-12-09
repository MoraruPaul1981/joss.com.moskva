package businesslogic.CallBaksSend;

import com.sun.istack.NotNull;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

public interface CallBacksSendsFile {

    public   void successSendAndroidFileStream(@NotNull HttpServletResponse response,
                                                               @NotNull File SendFileToAndroid,
                                                               @NotNull ServletContext ЛОГ);
}

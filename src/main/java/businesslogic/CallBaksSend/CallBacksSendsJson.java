package businesslogic.CallBaksSend;

import com.sun.istack.NotNull;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

public interface CallBacksSendsJson {

    public   void successSendAndroidJsonStream(@NotNull HttpServletResponse response,
                                                               @NotNull byte[] JsonStream,
                                                               @NotNull ServletContext ЛОГ);


}

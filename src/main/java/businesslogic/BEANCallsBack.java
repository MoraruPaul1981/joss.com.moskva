package businesslogic;

import businesslogic.CallBaksSend.SuccessSendAndroidJsonStream;


import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;


@Named ("bEANCallsBack")
public class BEANCallsBack {
    @Inject
    SubClassWriterErros subClassWriterErros;


    @Inject
    SuccessSendAndroidJsonStream staringSendFileAndJsonAndroidOrBinatyStream;


    /**
     * Default constructor.
     */
    public BEANCallsBack() {
        // TODO Auto-generated constructor stub
        System.out.print("\n"+" Starting.... class "+Thread.currentThread().getStackTrace()[2].getClassName() +"\n"+
                " metod "+Thread.currentThread().getStackTrace()[2].getMethodName() +"\n"+
                " line "+  Thread.currentThread().getStackTrace()[2].getLineNumber()+"\n");
    }
    // TODO МетодКласса отправки данных андройду

    // TODO: 06.09.2023  buyte отвкет От Сервера

    // TODO МетодКласса отправки данных андройду
    public void МетодBackДанныеКлиентуByte(@NotNull  HttpServletResponse response,
                                       @NotNull byte[] ГлавныйБуферОтправкиДанныхНААндройд,
                                       @NotNull ServletContext ЛОГ)   {

        try{
            // TODO: 01.12.2023 экземпляр класс лоя отправик данных JSON 
            staringSendFileAndJsonAndroidOrBinatyStream.successSendAndroidJsonStream(response, ГлавныйБуферОтправкиДанныхНААндройд,  ЛОГ);

            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " ГлавныйБуферОтправкиДанныхНААндройд " +ГлавныйБуферОтправкиДанныхНААндройд.length);

        } catch (Exception e) {
            // TODO: 27.04.2023
            subClassWriterErros.
                    writingCurrentErrors(e,
                            Thread.currentThread().
                                    getStackTrace(), "ErrorsLogs/ErrorJbossServletDSU1.txt");
        }

    }//todo END  МетодBackДанныеКлиентуByte

    // TODO: 20.09.2023  Отправка ДАнных Клиенту СЕВРИС
    

}

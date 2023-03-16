package businesslogic;

import dsu1glassfishatomic.SubClassWriterErros;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPOutputStream;


@Named
@RequestScoped
public class BEANCallsBack {

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
    public void МетодBackДанныеКлиенту(@NotNull ServletResponse response,
                                       @NotNull StringBuffer ГлавныйБуферОтправкиДанныхНААндройд,
                                       @NotNull ServletContext ЛОГ) throws IOException, ServletException {

        if (  response.isCommitted()==false) {
        try  ( BufferedWriter БуферДанныеДляКлиента = new BufferedWriter(
                new OutputStreamWriter(new GZIPOutputStream(response.getOutputStream()), StandardCharsets.UTF_16));) {
            int ОбщийРазмерЗаписываемогоФайла = ГлавныйБуферОтправкиДанныхНААндройд.toString().toCharArray().length;
            ((HttpServletResponse) response).addHeader("stream_size", String.valueOf(ОбщийРазмерЗаписываемогоФайла));
            PrintWriter МеханизмОтправкиДанныхКлиенту = new PrintWriter(БуферДанныеДляКлиента, true);
            МеханизмОтправкиДанныхКлиенту.write(ГлавныйБуферОтправкиДанныхНААндройд.toString());
            response.flushBuffer();
           while (!response.isCommitted()) ;
           МеханизмОтправкиДанныхКлиенту.close();
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " ГлавныйБуферОтправкиДанныхНААндройд " + ГлавныйБуферОтправкиДанныхНААндройд.toString() + "  response.isCommitted() "
                    + response.isCommitted() + "   ((HttpServletResponse) response).getStatus() " +
                    ((HttpServletResponse) response).getStatus());

        } catch (IOException e) {
            new SubClassWriterErros().МетодаЗаписиОшибкиВЛог(e, ЛОГ.getServerInfo(),
                    this.getClass().getMethods().toString() + " " + this.getClass().getCanonicalName().toString() + " "
                            + this.getClass().getDeclaredMethods().toString(),
                    Thread.currentThread().getStackTrace()[2], ЛОГ, ГлавныйБуферОтправкиДанныхНААндройд.toString());
        }
        }else {
            ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                    " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                    " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"
                    + " ГлавныйБуферОтправкиДанныхНААндройд " + ГлавныйБуферОтправкиДанныхНААндройд.toString() + "  response.isCommitted() " + response.isCommitted()
                    + "   ((HttpServletResponse) response).getStatus() " +
                    ((HttpServletResponse) response).getStatus());
        }
    }

}

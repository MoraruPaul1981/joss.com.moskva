package businesslogic.GetParserDate;

import com.sun.istack.NotNull;

import javax.servlet.ServletContext;
import java.util.Date;

public interface DateParsrIn {



    Date getDateScanner(@NotNull String Bremylocal, @NotNull ServletContext ЛОГ);
}

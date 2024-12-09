package businesslogic;

import com.sun.istack.NotNull;
import model.MateriBinary;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.List;

public class DecodeByteArray_Image {
    @Inject
    SubClassWriterErros subClassWriterErros;


    void методDecodeByteImage(@NotNull ServletContext ЛОГ, @NotNull List<?> ЛистДанныеОтHibenide ){
        try{
            List<MateriBinary> mat=( List<MateriBinary>)ЛистДанныеОтHibenide;
            byte[] РИсунов=     mat.get(0).getImage();


            final BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(РИсунов));
            final BufferedImage bufferedImage2 = ImageIO.read(new ByteArrayInputStream(РИсунов));

            InputStream is = new ByteArrayInputStream(РИсунов);
            BufferedImage bi = ImageIO.read(is);

// TODO: 14.07.2023 #1
          //  ByteArrayInputStream input_stream= new ByteArrayInputStream(РИсунов);
            ByteArrayInputStream input_stream= new ByteArrayInputStream(РИсунов );
            BufferedImage final_buffered_image = ImageIO.read(input_stream);
            ImageIO.write(final_buffered_image , "jpg", new File("C:\\final_file.jpg") );
            System.out.println("Converted Successfully!");


            // TODO: 14.07.2023 #2 
            ByteArrayInputStream bais = new ByteArrayInputStream(РИсунов);
            BufferedImage image = ImageIO.read(bais);
      

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", baos);
            byte [] bytes = baos.toByteArray();
            System.out.println("Converted Successfully!");

        // TODO
        ЛОГ.log("\n" + " class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n"  + " ЛистДанныеОтHibenide " +ЛистДанныеОтHibenide);
    } catch (Exception e) {
        ЛОГ.log("\n" + " ERROR class " + Thread.currentThread().getStackTrace()[2].getClassName() + "\n" +
                " metod " + Thread.currentThread().getStackTrace()[2].getMethodName() + "\n" +
                " line " + Thread.currentThread().getStackTrace()[2].getLineNumber() + "\n");
        subClassWriterErros.
                writingCurrentErrors(e,
                        Thread.currentThread().
                                getStackTrace(), "ErrorsLogs/ErrorJbossServletDSU1.txt");
    }
    }

}

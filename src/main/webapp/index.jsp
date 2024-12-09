
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ page import="java.util.*, java.text.* ,java.net.ServerSocket ,
 java.net.UnknownHostException, java.io.BufferedReader,java.io.*,java.util.stream.*,java.nio.charset.Charset"%>
<%@ page import="javax.inject.Inject" %>
<%@ page import="businesslogic.bl_sessionfactory.InSessionFactory" %>
<%@ page import="org.hibernate.SessionFactory" %>
<%@ page import="java.nio.file.Path" %>
<%@ page import="java.nio.file.Paths" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="java.io.*" %>
<%@ page import="java.io.PrintStream" %>
<%@ page import="javax.servlet.ServletContext" %>
<%@ page import="java.util.logging.Logger" %>
<%@ page import="java.util.logging.LoggingPermission" %>
<%@ page import="java.util.logging.Level" %>
<%@ page import="java.nio.file.Files" %>


<%!
   private Logger log = Logger.getLogger(LoggingPermission.class.getName());




    String getFormattedDate() {
        String date = null;
        try {
            SimpleDateFormat   sdf = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss.SSS");
         date=  sdf.format(new Date())+"\n";
            log.log(Level.ALL, "sdf "+sdf.format(new Date())+"\n"+"date"+date);
        } catch(Exception e) {
            log.log(Level.ALL,e.getMessage().toString());
        }
        return  date;

    }

    String getTheadPool() {
        String  TheadPool = null;
        try{
          TheadPool=Thread.currentThread().getName()+"\n"+"\n"
                + " Thread.currentThread().isAlive() "+Thread.currentThread().isAlive()+"\n"+"\n"
                + " Thread.currentThread().getThreadGroup() "+Thread.currentThread().getThreadGroup()+"\n"+"\n"
                + " Thread.currentThread().getState() "+Thread.currentThread().getState()+"\n"+"\n"
                + " Thread.currentThread().isInterrupted() "+Thread.currentThread().isInterrupted()+"\n"+"\n";
        log.log(Level.ALL, "Create Stranizi Sosdannoy DSU-1  JSP --page "+TheadPool);
    } catch(Exception e) {
    log.log(Level.ALL,e.getMessage().toString());
}
        return TheadPool;

    }
    long getTheadPoolActive() {
        long TheadPool = 0;
        try{
         TheadPool=Runtime.getRuntime().freeMemory();
        log.log(Level.ALL, "getTheadPoolActive  "+TheadPool);
    } catch(Exception e) {
            log.log(Level.ALL, e.getMessage().toString());
        }
        return TheadPool;

    }
    long getActiveMemory() {
        long TheadPool = 0;
        try{
         TheadPool=Runtime.getRuntime().totalMemory();
        log.log(Level.ALL, "getActiveMemory "+TheadPool);
            } catch(Exception e) {
            log.log(Level.ALL, e.getMessage().toString());
        }
        return TheadPool;

    }

    long getActiveMaxMemory() {
        long TheadPool = 0;
        try{
       TheadPool=Runtime.getRuntime().maxMemory();
        log.log(Level.ALL, "getActiveMaxMemory "+TheadPool);
    } catch(Exception e) {
    log.log(Level.ALL, e.getMessage().toString());
}
        return TheadPool;

    }

    long getActiveUser() {
        Integer IdUser = null;
        try{
        Object IdUserПред =   Optional.ofNullable(getServletConfig().getServletContext().getAttribute("IdUser")  ).orElse("0");
         IdUser = Optional.ofNullable( IdUserПред.toString()).stream() .mapToInt(i->Integer.parseInt(i)).findFirst().orElse(0);
        log.log(Level.ALL, "getActiveUser "+IdUser);
            } catch(Exception e) {
            log.log(Level.ALL, e.getMessage().toString());
        }
        return IdUser;

    }


    StringBuffer getErrors() {
        StringBuffer stringBufferall = new StringBuffer();
        try{
            StringWriter writer = new StringWriter();
            // TODO: 10.04.2024 1 
            long targetStream =
                    Files.newBufferedReader(Paths.get("C:\\JBOSS\\EAP-7.4.0\\bin\\ErrorsLogs\\ErrorJbossServletDSU1.txt")).transferTo(writer);
            StringBuffer    stringBuffer1=      writer.getBuffer();
            log.log(Level.ALL, "stringBuffer1 "+stringBuffer1);


            // TODO: 10.04.2024  2
            long targetStream2 =
                    Files.newBufferedReader(Paths.get("C:\\JBOSS\\EAP-7.4.0\\bin\\ErrorsLogs\\ErrorJbossServletAuntification.txt")).transferTo(writer);
            StringBuffer    stringBuffer2=      writer.getBuffer();
            log.log(Level.ALL, "stringBuffer2 "+stringBuffer2);


            // TODO: 10.04.2024  3
            long targetStream3 =
                    Files.newBufferedReader(Paths.get("C:\\JBOSS\\EAP-7.4.0\\bin\\ErrorsLogs\\ErrorJbossServletRuntime.txt")).transferTo(writer);
            StringBuffer    stringBuffer3=      writer.getBuffer();
            log.log(Level.ALL, "stringBuffer2 "+stringBuffer2);

            // TODO: 10.04.2024  4
            long targetStream4 =
                    Files.newBufferedReader(Paths.get("C:\\JBOSS\\EAP-7.4.0\\bin\\ErrorsLogs\\ErrorJbossServletUpdatePO.txt")).transferTo(writer);
            log.log(Level.ALL, "stringBuffer2 "+stringBuffer2);


            writer.flush();
            // TODO: 10.04.2024 all erros
            stringBufferall
                    .append("\n")
                    .append("\n")
                    .append("\n")
                    .append("\n")
                    .append("********************************** ErrorJbossServletDSU1.txt ***************************************")
                    .append("\n")
                    .append("\n")
                    .append("\n")
                    .append("\n")
                    .append(stringBuffer1)
                    .append("\n")
                    .append("\n")
                    .append("\n")
                    .append("\n")
                    .append(" **********************************  ErrorJbossServletAuntification.txt **********************************  ")
                    .append("\n")
                    .append("\n")
                    .append("\n")
                    .append("\n")
                    .append(stringBuffer2)
                    .append("\n")
                    .append("\n")
                    .append("\n")
                    .append("\n")
                    .append("**********************************  ErrorJbossServletRuntime.txt **********************************  ")
                    .append("\n")
                    .append("\n")
                    .append("\n")
                    .append("\n")
                    .append(targetStream3)
                    .append("\n")
                    .append("\n")
                    .append("\n")
                    .append("\n")
                    .append(" **********************************  ErrorJbossServletUpdatePO.txt **********************************  ")
                    .append("\n")
                    .append("\n")
                    .append("\n")
                    .append("\n")
                    .append(targetStream4)
                    .append("\n")
                    .append("\n")
                    .append("\n")
                    .append("\n")
                    .append("\n");
            // TODO: 10.04.2024

            writer.close();

            
        } catch(Exception e) {
            log.log(Level.ALL, e.getMessage().toString());
        }
        return stringBufferall;

    }





%>
<%@ page contentType="text/html; charset=UTF-8"  %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JBOSS WITH SSL 2024 GATT server </title>
</head>
<body style="background-color:RGB(255, 192, 203);" >

<br><i>---------------------------------------------------------------------------------------------------------</i><br>
<h2>Cтраница   JSP  SOUS AVTODOR 2024 GATT server  </h2>
<h1> ООО "Союз Автодор Иваново "</h1>

<h1><i> getFormattedDate <%= getFormattedDate() %></i></h1>
<br><i>----------------------------------------------------------------------------------------------------</i><br>
<h1><i>getTheadPool <%= getTheadPool() %></i></h1>
<br><i>----------------------------------------------------------------------------------------------------</i><br>
<h1><i> getTheadPoolActive <%= getTheadPoolActive() %></i></h1>
<br><i>----------------------------------------------------------------------------------------------------</i><br>
<h1><i> getActiveMemory <%= getActiveMemory() %></i></h1>
<br><i>----------------------------------------------------------------------------------------------------</i><br>
<h1><i> getActiveUser <%=getActiveUser() %></i></h1>
<br><i>----------------------------------------------------------------------------------------------------</i><br>
<h1><i> getActiveMaxMemory <%=getActiveMaxMemory() %></i></h1>
<br><i>----------------------------------------------------------------------------------------------------</i><br>
<br><i>----------------------------------------------------------------------------------------------------</i><br>
<h1><i> SSL: <%= request.isSecure() %></i></h1>
<br><i>----------------------------------------------------------------------------------------------------</i><br>
<br><i>----------------------------------------------------------------------------------------------------</i><br>
<h1><i> Async Started: <%= request.isAsyncStarted() %></i></h1>
<br><i>----------------------------------------------------------------------------------------------------</i><br>
<br><i>----------------------------------------------------------------------------------------------------</i><br>
<h1><i> Async Supported: <%= request.isAsyncSupported() %></i></h1>
<br><i>----------------------------------------------------------------------------------------------------</i><br>
<h1><i> ТCurrent User: <%= request.getServletContext().getAttribute("IdUser") %></i></h1>
<br><i>----------------------------------------------------------------------------------------------------</i><br>
<br><i>----------------------------------------------------------------------------------------------------</i><br>
<h1><i>                                                    All ERRORS  </i></h1>
<br><i>----------------------------------------------------------------------------------------------------</i><br>
<br><i>----------------------------------------------------------------------------------------------------</i><br>
<h1><i> <%=getErrors() %></i></h1>
<br><i>----------------------------------------------------------------------------------------------------</i><br>
<h1><i> Request Context Path: <%= request.getContextPath() %></i></h1>
<h1><i> Request RequestURI: <%= request.getRequestURI() %></i></h1>
<h1><i> Request URI: <%= request.getContextPath() %></i></h1>
<h1><i> Request URL: <%= request.getRequestURL() %></i></h1>
<h1><i> Request getPathInfo: <%= request.getServletContext().getResourcePaths("") %></i></h1>
<h1><i> Request getRealPath: <%= request.getServletContext().getRealPath("/") %></i></h1>
<h1><i> Request getContextPath: <%= request.getServletContext().getContextPath() %></i></h1>

<br><i>----------------------------------------------------------------------------------------------------</i><br>
<br><i>----------------------------------------------------------------------------------------------------</i><br>
<h1><i> exists() update_android_dsu1/output-metadata.json: <%=   Paths.get("update_android_dsu1/output-metadata.json").toFile().exists() %></i></h1>
<h1><i> isFile() update_android_dsu1/output-metadata.json: <%=   Paths.get("update_android_dsu1/output-metadata.json").toFile().isFile() %></i></h1>
<h1><i> lastModified() update_android_dsu1/output-metadata.json: <%=   Paths.get("update_android_dsu1/output-metadata.json").toFile().lastModified() %></i></h1>

<br><i>----------------------------------------------------------------------------------------------------</i><br>
<h1><i> exists() update_android_dsu1/app-release.apk: <%=   Paths.get("update_android_dsu1/app-release.apk").toFile().exists() %></i></h1>
<h1><i> isFile() update_android_dsu1/app-release.apk: <%=   Paths.get("update_android_dsu1/app-release.apk").toFile().isFile() %></i></h1>
<h1><i> lastModified update_android_dsu1/app-release.apk: <%=   Paths.get("update_android_dsu1/app-release.apk").toFile().lastModified() %></i></h1>

<br><i>----------------------------------------------------------------------------------------------------</i><br>
<h1><i> isFile() add-user.properties: <%=   Paths.get("C:\\JBOSS\\EAP-7.4.0\\bin\\add-user.properties").toFile().isFile() %></i></h1>
<h1><i> exists() add-user.properties: <%=   Paths.get("C:\\JBOSS\\EAP-7.4.0\\bin\\add-user.properties").toFile().exists() %></i></h1>
<h1><i> lastModified  add-user.properties: <%=   Paths.get("C:\\JBOSS\\EAP-7.4.0\\bin\\add-user.properties").toFile().lastModified() %></i></h1>
<br><i>----------------------------------------------------------------------------------------------------</i><br>

<br><i>----------------------------------------------------------------------------------------------------</i><br>
<h1><i> MOSKVA isFile() add-user.properties: <%=   Paths.get("C:\\RedHatJboss\\EAP-7.4.0\\bin\\add-user.properties").toFile().isFile() %></i></h1>
<h1><i> MOSKVA exists() add-user.properties: <%=   Paths.get("C:\\RedHatJboss\\EAP-7.4.0\\bin\\add-user.properties").toFile().exists() %></i></h1>
<h1><i> MOSKVA lastModified add-user.properties: <%=   Paths.get("C:\\RedHatJboss\\EAP-7.4.0\\bin\\add-user.properties").toFile().lastModified() %></i></h1>
<br><i>----------------------------------------------------------------------------------------------------</i><br>


<h1><i> LOG exists() ErrorJbossServletDSU1.txt: <%=   Paths.get("ErrorsLogs/ErrorJbossServletDSU1.txt").toFile().exists() %></i></h1>
<h1><i>  LOG isFile() ErrorJbossServletDSU1.txt: <%=   Paths.get("ErrorsLogs/ErrorJbossServletDSU1.txt").toFile().isFile() %></i></h1>
<h1><i> Reads LOG lastModified ErrorJbossServletDSU1.txt: <%=Paths.get("ErrorsLogs/ErrorJbossServletDSU1.txt").toFile().lastModified()%></i></h1>


<br><i>----------------------------------------------------------------------------------------------------</i><br>



<h2><i> Адрес: Проездная ул., 18, Иваново, Ивановская обл.</i></h2>
<h2><i>     <font size="6" color="#fa8e47" face="serif">"Версия 924 Hibernate and Jakson"</font> </i></h2>
<br><i>----------------------------------------------------------------------------------------------------</i><br>

<a href="sous.jboss.download">Нажми Пинг DOWNLOAD PO  </a>
<br><i>----------------------------------------------------------------------------------------------------</i><br>
<a href="sous.jboss.runtimejboss">Нажми Пинг RUNTIME  </a>
<br><i>----------------------------------------------------------------------------------------------------</i><br>
<a href="sous.jboss.gattserver">Нажми Пинг Gatt server  </a>
<br><i>----------------------------------------------------------------------------------------------------</i><br>
<a href="sous.jboss.scanner">Нажми Пинг Scanner  </a>
<br><i>----------------------------------------------------------------------------------------------------</i><br>


<input type="hidden" name="ipaddress" value="<%=request.getRemoteAddr()%>"/>
<br><i>----------------------------------------------------------------------------------------------------</i><br>
<form action="Dsu1glassfish" method="GET"></form>
</body>
</html>
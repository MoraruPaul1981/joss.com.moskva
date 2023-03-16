
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ page import="java.util.*, java.text.* ,java.net.ServerSocket , java.net.UnknownHostException, java.io.BufferedReader,java.io.*,java.util.stream.*,java.nio.charset.Charset"%>
<%!


    String getFormattedDate()
    {



        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM HH:mm:ss.SSS");
        System.out.println( "Create Stranizi Sosdannoy DSU-1  JSP --page "+sdf.format(new Date()));
        return sdf.format(new Date());

    }





    String getSessions()
    {
        String ПолученныеОшибки=null;
        Charset cs1;
        Charset cs2;
        try {
            cs1=Charset.forName("Cp1251");
            cs2=Charset.forName("UTF-8");
            BufferedReader reader = new BufferedReader(new FileReader(new File("C:\\JBOSS\\ErrorJbossServletDSU1.txt")));
            ПолученныеОшибки=  reader.lines().collect(Collectors.joining(System.lineSeparator()));
            System.out.println( "ПолученныеОшибки   "+ПолученныеОшибки);
            if(ПолученныеОшибки==null){
                ПолученныеОшибки="DON'T ERROR SERVER";
            }else{
                ПолученныеОшибки=new String( ПолученныеОшибки.getBytes(cs1), cs2);
            }
            reader.close();
        } catch (Exception e1) {
            // TODO Автоматически созданный блок catch
            e1.printStackTrace();
        }
        return ПолученныеОшибки;

    }




%>
<%@ page contentType="text/html; charset=UTF-8"  %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here dsu-1 glassfish 4.1</title>
</head>
<body style="background-color:grey;" >

<label for="range-1">Slider:</label>
<input name="range-1" id="range-1" data-highlight="true" min="0" max="100" value="40" type="range"/>
<br><i>---------------------------------------------------------------------------------------------------------</i><br>
<h2>Добро пожаловать я страница JSP Red Hat Jboss 7 на intellij idea </h2>
<h1> ООО "Союз Автодор Иваново "</h1>

<h1><i>Сегодня <%= getFormattedDate() %></i></h1>
<h1><i>Ошибки на Сервере: <%= getSessions() %></i></h1>
<br><i>----------------------------------------------------------------------------------------------------</i><br>

<h2><i> Адрес: Проездная ул., 18, Иваново, Ивановская обл.</i></h2>
<h2><i>     <font size="6" color="#fa8e47" face="serif">"Версия 813  Hibernate and Jakson Intelli IDEA"</font> </i></h2>
<br><i>----------------------------------------------------------------------------------------------------</i><br>
<br/>
<a href="sous.jboss.tabel">Нажми Пинг dsu1.glassfish.atomic  </a>
<br><i>----------------------------------------------------------------------------------------------------</i><br>
<a href="sous.jboss.download">Нажми Пинг dsu1glassfishatomic.glassfish.download  </a>
<br><i>----------------------------------------------------------------------------------------------------</i><br>
<a href="sous.jboss.scanner">Нажми Пинг dsu1glassfishatomic.glassfish.scanner  </a>
<br><i>----------------------------------------------------------------------------------------------------</i><br>
<a href="sous.jboss.runtimejboss">Нажми Пинг dsu1glassfishatomic.glassfish.runtimejboss </a>
<br><i>----------------------------------------------------------------------------------------------------</i><br>
<a href="sous.jboss.authentication">Нажми Пинг dsu1glassfishatomic.glassfish.authentication  </a>
<br><i>----------------------------------------------------------------------------------------------------</i><br>
<input type="hidden" name="ipaddress" value="<%=request.getRemoteAddr()%>"/>
<br><i>----------------------------------------------------------------------------------------------------</i><br>
<form action="Dsu1glassfish" method="GET"></form>
</body>
</html>
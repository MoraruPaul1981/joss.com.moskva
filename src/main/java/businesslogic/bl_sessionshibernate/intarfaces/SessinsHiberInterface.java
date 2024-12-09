package businesslogic.bl_sessionshibernate.intarfaces;

import org.hibernate.Session;

public interface SessinsHiberInterface {



    Session getstartingJtaSession( );


    Session getstartingJdbcSession( );

}

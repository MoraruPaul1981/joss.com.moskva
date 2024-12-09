package businesslogic.bl_sessionshibernate.intarfaces;

import org.hibernate.Session;

public interface SessinsHiberIGattnterface {



    Session getstartingGattJtaSession( );


    Session getstartingGattJdbcSession( );

}


import 'InFuture.dart';

class getAdressJbossDebug implements InAdressJboss {
  //TODO
  @override
  String? adressJboss({required String IdUser, required String JobForServer}) {
    // TODO: implement adressJboss
    // TODO: implement adressJboss
    String?  jbossserverfinal;
    try {
      var jbossserverlink = "http://" + "192.168.3.4" + ":" + "8080" + "/"+  "jboss-1.0-SNAPSHOT/sous.jboss.runtimejboss" as String;
      var jbossserverparams  = "?" + "NameTable= "+
          "&" + "JobForServer="+JobForServer+
          "&" + "IdUser="+IdUser
          + "&" + "VersionData=0" as String;
      jbossserverfinal = jbossserverlink + jbossserverparams;
      //TODO
      print('jbossserverfinal .. $jbossserverfinal');
    } catch (e) {
      print(e);
    }
    return  jbossserverfinal;
  }
}


class getAdressJbossReliz implements InAdressJboss{
  @override
  String? adressJboss({required String IdUser, required String JobForServer}) {
    // TODO: implement adressJboss
    // TODO: implement adressJboss
    String?  jbossserverfinal;
    try {
      var jbossserverlink = "http://" + "192.168.3.4" + ":" + "8080" + "/"+  "jboss-1.0-SNAPSHOT/sous.jboss.runtimejboss" as String;
      var jbossserverparams  = "?" + "NameTable= "+
          "&" + "JobForServer="+JobForServer+
          "&" + "IdUser="+IdUser
          + "&" + "VersionData=0" as String;
      jbossserverfinal = jbossserverlink + jbossserverparams;
      //TODO
      print('jbossserverfinal .. $jbossserverfinal');
    } catch (e) {
      print(e);
    }
    return  jbossserverfinal;
  }




}


class getAdress1CDebug implements InAdress1C{
  @override
  String? adress1C({required String IdUser, required String JobForServer}) {
    // TODO: implement adress1C
// TODO: implement adressJboss
    String?  jbossserverfinal;
    try {
      var jbossserverlink = "http://" + "192.168.3.4" + ":" + "8080" + "/"+  "jboss-1.0-SNAPSHOT/sous.jboss.runtimejboss" as String;
      var jbossserverparams  = "?" + "NameTable= "+
          "&" + "JobForServer="+JobForServer+
          "&" + "IdUser="+IdUser
          + "&" + "VersionData=0" as String;
      jbossserverfinal = jbossserverlink + jbossserverparams;
      //TODO
      print('jbossserverfinal .. $jbossserverfinal');
    } catch (e) {
      print(e);
    }
    return  jbossserverfinal;
  }

}
class getAdress1C implements InAdress1C{
  @override
  String? adress1C({required String IdUser, required String JobForServer}) {
    // TODO: implement adress1C
    // TODO: implement adressJboss
    String?  jbossserverfinal;
    try {
      var jbossserverlink = "http://" + "192.168.3.4" + ":" + "8080" + "/"+  "jboss-1.0-SNAPSHOT/sous.jboss.runtimejboss" as String;
      var jbossserverparams  = "?" + "NameTable= "+
          "&" + "JobForServer="+JobForServer+
          "&" + "IdUser="+IdUser
          + "&" + "VersionData=0" as String;
      jbossserverfinal = jbossserverlink + jbossserverparams;
      //TODO
      print('jbossserverfinal .. $jbossserverfinal');
    } catch (e) {
      print(e);
    }
    return  jbossserverfinal;
  }


  }
}






import 'package:flutter/cupertino.dart';
import 'package:logger/logger.dart';

import '../../../../data/entities/Entities1CMap.dart';

abstract  class IntafaceSuccessData {

  //TODO переменные
    late AsyncSnapshot<List<Map<String, List<Entities1CMap>>>>  snapshot;
     late Logger   logger;
    late   List<Map<String, List<Entities1CMap>>>?   receiveddatafromC1;
    late BuildContext context;


  //TODO
  Widget getWidgetScaffold({required   context, required     snapshot, required    logger });
//TODO
  void firstTransformationionofincomingData({required  AsyncSnapshot<List<Map<String, List<Entities1CMap>>>> snapshot});

}






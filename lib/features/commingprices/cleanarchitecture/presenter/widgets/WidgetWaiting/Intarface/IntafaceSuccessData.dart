





import 'package:flutter/cupertino.dart';
import 'package:logger/logger.dart';

import '../../../../data/entities/Entities1CMap.dart';

abstract  class IntafaceTransformationSuccessData {

  //TODO переменные
    late AsyncSnapshot<List<Map<String, List<Entities1CMap>>>>  snapshot;
     late Logger   logger;
    late   List<Map<String, List<Entities1CMap>>>   receiveddatafromC1;
    late BuildContext context;


  //TODO  //TODO  первоя Трансформация ДАнных
  Widget getWidgetScaffold({required   context, required     snapshot, required    logger });
//TODO
  List<Map<String, List<Entities1CMap>>> firstTransformationionofincomingData({required  AsyncSnapshot<List<Map<String, List<Entities1CMap>>>> snapshot, required Logger logger});


  //TODO  //TODO  ВТОРАЯ Трансформация ДАнных
   Map<String, List<Entities1CMap>> secondConversionData({required List<Map<String, List<Entities1CMap>>> receiveddatafromC1, required Logger logger,required int index});


}






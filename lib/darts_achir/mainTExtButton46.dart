import 'package:flutter/material.dart';

void main() async{
  //TODO запускаем приложение
 // runApp(const MyApp());
    //TODO
    int a=5;
    int b=10;
    //throw ('This is an error !!!');

    print("a $a");


/*
    AllTotal getTotalPrib=GetTotalPrib(3);
    final total= await  getTotalPrib.getTotalProccering(a, b);
    print("total $total");

    AllTotal getTotalYmnojit=GetTotalYmnojit(3);
    final total2= await getTotalYmnojit.getTotalProccering(a, b);
    print("total2 $total2");

    AllTotal getTotals=GetTotals(3);
    final total3= await getTotals.getTotalProccering(a, b);


    print("total3 $total3");



    print("b $b");



}




class GetTotalPrib extends AllTotal{
  GetTotalPrib(super.second);
  @override
//TODO Умножить
  Future<int> getTotalProccering(a,b){
    late  Future<int> future;
 */
/*   try{
      future= Future.delayed(Duration(seconds:2),()=>a+b);
    } catch (error) {
      PrintingErrors printingErrors=PrintingErrors();
      printingErrors.printingError(error,'mainTextButton46.dart','getTotalProccering');
    }*//*


    return future;
  }
}



class GetTotalYmnojit extends AllTotal{
  GetTotalYmnojit(super.second);
  @override
//TODO Умножить
  Future<int> getTotalProccering(a,b){
     Future<int> future;
*/
/*    try{
      future= Future.delayed(Duration(seconds:2),()=>a*b);
    } catch (error) {
      PrintingErrors printingErrors=PrintingErrors();
      printingErrors.printingError(error,'mainTextButton46.dart','getTotalProccering');
    }*//*


    return future;
  }
}


class GetTotals implements GetTotalPrib,GetTotalYmnojit{
  @override
  late int second;

  GetTotals(this.second);

  //TODO Делить
  @override
  Future<int> getTotalProccering(a,b) {
    late Future<int> future;
  //  try {
*/
/*      future= Future.delayed(Duration(seconds:2),()=>a*b);
    } catch (error) {
      PrintingErrors printingErrors=PrintingErrors();
      printingErrors.printingError(error,'mainTextButton46.dart','getTotalProccering');
    }*//*


      return future;
    }
  }
//TODO Future
class AllTotal {
  late int second;
  AllTotal(this.second);

  //TODO Плюс
  Future<int> getTotalProccering(a,b){
     Future<int> future;
*/
/*try{
  future= Future.delayed(Duration(seconds:2),()=>a+b);
  } catch (error) {
  PrintingErrors printingErrors=PrintingErrors();
  printingErrors.printingError(error,'mainTextButton46.dart','getTotalProccering');
  }*//*


    return future;
  }
*/


}


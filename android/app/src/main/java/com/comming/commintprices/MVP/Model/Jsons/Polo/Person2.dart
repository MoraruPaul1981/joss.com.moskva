import 'InPersons.dart';


class Person2 implements InPolos{
  int? _albumId;
  int? _id;
  String? _title;
  String? _url;
  String? _thumbnailUrl;

  Person2(

      {   int?  albumId,
          int? id,
           String? title,
            String? url,
           String? thumbnailUrl}) {
try{
    this._albumId = albumId;
    this._id = id;
    this._title = title;
    this._url = url;
    this._thumbnailUrl = thumbnailUrl;
    //TODO error
  } on Exception catch (e) {
  print('e $e');
  }

  }




  @override
  Map<String, dynamic> toJson() {
    // TODO: implement toJson
    try{
    //TODO error
  } on Exception catch (e) {
  print('e $e');
  }
    throw UnimplementedError();
  }

  @override
  Map<String, dynamic> toJson2() {
    // TODO: implement toJson2
    try{
    //TODO error
  } on Exception catch (e) {
  print('e $e');
  }
    throw UnimplementedError();
  }

  @override
  Person2 fromJson(Map<String, Person2> json) {
    try{
    // TODO: implement fromJson
    _albumId = json['albumId'] as int?;
    _id = json['id'] as int?;
    _title = json['title'] as String?;
    _url = json['url'] as String?;
    _thumbnailUrl = json['thumbnailUrl'] as String?;

    //TODO error
  } on Exception catch (e) {
  print('e $e');
  }

    return  Person2(albumId: _albumId, id: _id, title: _title, url: _url, thumbnailUrl: _thumbnailUrl);
  }





/*   Person2.fromJson(Map<String, dynamic> json) {
    _albumId = json['albumId'];
    _id = json['id'];
    _title = json['title'];
    _url = json['url'];
    _thumbnailUrl = json['thumbnailUrl'];
  }*/


}

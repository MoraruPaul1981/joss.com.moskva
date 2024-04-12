import 'InPersons.dart';







class Person2 implements InPolos{
  int? _albumId;
  int? _id;
  String? _title;
  String? _url;
  String? _thumbnailUrl;

  Person2(
      { required  int  albumId,
        int? id,
        String? title,
        String? url,
        String? thumbnailUrl}) {
      this._albumId = albumId;
      this._id = id;
      this._title = title;
      this._url = url;
      this._thumbnailUrl = thumbnailUrl;

  }



  @override
  fromJson(Map<String, dynamic> json) {
    // TODO: implement fromJson
    throw UnimplementedError();
  }

  @override
  Map<String, dynamic> toJson() {
    // TODO: implement toJson
    throw UnimplementedError();
  }

  @override
  Map<String, dynamic> toJson2() {
    // TODO: implement toJson2
    throw UnimplementedError();
  }


   Person2.fromJson(Map<String, dynamic> json) {
    _albumId = json['albumId'];
    _id = json['id'];
    _title = json['title'];
    _url = json['url'];
    _thumbnailUrl = json['thumbnailUrl'];
  }


}

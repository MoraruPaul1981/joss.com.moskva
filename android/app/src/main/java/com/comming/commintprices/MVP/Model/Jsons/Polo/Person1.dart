import 'InPersons.dart';
import 'Person2.dart';







class Person1 implements InPolos{
  int? _albumId;
  int? _id;
  String? _title;
  String? _url;
  String? _thumbnailUrl;

  Person1(
      {int? albumId,
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

  int? get albumId => _albumId;
  set albumId(int? albumId) => _albumId = albumId;
  int? get id => _id;
  set id(int? id) => _id = id;
  String? get title => _title;
  set title(String? title) => _title = title;
  String? get url => _url;
  set url(String? url) => _url = url;
  String? get thumbnailUrl => _thumbnailUrl;
  set thumbnailUrl(String? thumbnailUrl) => _thumbnailUrl = thumbnailUrl;




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


  @override
  Person2 fromJson(Map<String, dynamic> json) {
    // TODO: implement fromJson
    _albumId = json['albumId'];
    _id = json['id'];
    _title = json['title'];
    _url = json['url'];
    _thumbnailUrl = json['thumbnailUrl'];

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

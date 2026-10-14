import 'dart:convert';
import 'package:http/http.dart' as http;

class FacilityService {
  static const String baseUrl = 'http://localhost:8080';

  void _checkResponse(http.Response response) {
    if (response.statusCode < 200 || response.statusCode >= 300) {
      throw Exception('API Error ${response.statusCode}: ${response.body}');
    }
  }

  Map<String, dynamic> _parseObject(http.Response response) {
    final data = jsonDecode(response.body);
    if (data is Map<String, dynamic>) return data;
    throw Exception('Unexpected API response');
  }

  Future<List<Map<String, dynamic>>> getAllFacilities() async {
    final response = await http.get(Uri.parse('$baseUrl/facility/getAll'));

    _checkResponse(response);

    final List<dynamic> data = jsonDecode(response.body);
    return data.map((item) => Map<String, dynamic>.from(item)).toList();
  }

  Future<Map<String, dynamic>> getFacilityById(int id) async {
    final response = await http.get(Uri.parse('$baseUrl/facility/getById/$id'));

    _checkResponse(response);
    return _parseObject(response);
  }

  Future<Map<String, dynamic>> addFacility({
    required String facilname,
    required String type,
    required String description,
    required String location,
    required String status,
    required int societyId,
  }) async {
    final response = await http.post(
      Uri.parse('$baseUrl/facility/add'),
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode({
        'facilname': facilname,
        'type': type,
        'description': description,
        'location': location,
        'status': status,
        'societyId': societyId,
      }),
    );

    _checkResponse(response);
    return _parseObject(response);
  }

  Future<Map<String, dynamic>> updateFacility({
    required int facilid,
    required String facilname,
    required String type,
    required String description,
    required String location,
    required String status,
    required int societyId,
  }) async {
    final response = await http.put(
      Uri.parse('$baseUrl/facility/update/$facilid'),
      headers: {'Content-Type': 'application/json'},
      body: jsonEncode({
        'facilname': facilname,
        'type': type,
        'description': description,
        'location': location,
        'status': status,
        'societyId': societyId,
      }),
    );

    _checkResponse(response);
    return _parseObject(response);
  }

  Future<void> deleteFacility(int facilid) async {
    final response = await http.delete(
      Uri.parse('$baseUrl/facility/delete/$facilid'),
    );

    _checkResponse(response);
  }
}

import 'package:flutter/material.dart';
import 'package:society_management/services/facilityservice.dart';

class FacilitiesScreen extends StatefulWidget {
  const FacilitiesScreen({super.key});

  @override
  State<FacilitiesScreen> createState() => _FacilitiesScreenState();
}

class _FacilitiesScreenState extends State<FacilitiesScreen> {
  final FacilityService _service = FacilityService();

  final _formKey = GlobalKey<FormState>();
  final _name = TextEditingController();
  final _type = TextEditingController();
  final _description = TextEditingController();
  final _location = TextEditingController();
  final _societyId = TextEditingController();
  final _search = TextEditingController();

  String _status = 'Available';
  int? _editingId;
  bool _loading = false;
  List<Map<String, dynamic>> _facilities = [];

  static const Color bg = Color(0xFF0B1120);
  static const Color surface = Color(0xFF151F32);
  static const Color blue = Color(0xFF60A5FA);
  static const Color muted = Color(0xFF94A3B8);

  @override
  void initState() {
    super.initState();
    _loadFacilities();
  }

  @override
  void dispose() {
    _name.dispose();
    _type.dispose();
    _description.dispose();
    _location.dispose();
    _societyId.dispose();
    _search.dispose();
    super.dispose();
  }

  Future<void> _loadFacilities() async {
    setState(() => _loading = true);

    try {
      final data = await _service.getAllFacilities();
      if (!mounted) return;
      setState(() => _facilities = data);
    } catch (e) {
      if (!mounted) return;
      _message('Could not load facilities: $e');
    } finally {
      if (mounted) setState(() => _loading = false);
    }
  }

  void _message(String message) {
    ScaffoldMessenger.of(
      context,
    ).showSnackBar(SnackBar(content: Text(message)));
  }

  void _clearForm() {
    _name.clear();
    _type.clear();
    _description.clear();
    _location.clear();
    _societyId.clear();

    setState(() {
      _editingId = null;
      _status = 'Available';
    });
  }

  void _editFacility(Map<String, dynamic> item) {
    _name.text = '${item['facilname'] ?? ''}';
    _type.text = '${item['type'] ?? ''}';
    _description.text = '${item['description'] ?? ''}';
    _location.text = '${item['location'] ?? ''}';
    _societyId.text = '${item['societyId'] ?? ''}';

    setState(() {
      _editingId = int.tryParse('${item['facilid']}');
      _status = '${item['status'] ?? 'Available'}';
    });

    _showForm();
  }

  Future<void> _saveFacility() async {
    if (!_formKey.currentState!.validate()) return;

    final societyId = int.tryParse(_societyId.text.trim());
    if (societyId == null || societyId <= 0) {
      _message('Enter a valid Society ID');
      return;
    }

    setState(() => _loading = true);

    try {
      if (_editingId == null) {
        await _service.addFacility(
          facilname: _name.text.trim(),
          type: _type.text.trim(),
          description: _description.text.trim(),
          location: _location.text.trim(),
          status: _status,
          societyId: societyId,
        );
        _message('Facility added successfully');
      } else {
        await _service.updateFacility(
          facilid: _editingId!,
          facilname: _name.text.trim(),
          type: _type.text.trim(),
          description: _description.text.trim(),
          location: _location.text.trim(),
          status: _status,
          societyId: societyId,
        );
        _message('Facility updated successfully');
      }

      if (!mounted) return;
      Navigator.pop(context);
      _clearForm();
      await _loadFacilities();
    } catch (e) {
      if (!mounted) return;
      _message('Save failed: $e');
    } finally {
      if (mounted) setState(() => _loading = false);
    }
  }

  Future<void> _deleteFacility(int id) async {
    final confirm = await showDialog<bool>(
      context: context,
      builder: (dialogContext) => AlertDialog(
        backgroundColor: surface,
        title: const Text(
          'Delete facility?',
          style: TextStyle(color: Colors.white),
        ),
        content: const Text(
          'This facility will be deleted.',
          style: TextStyle(color: muted),
        ),
        actions: [
          TextButton(
            onPressed: () => Navigator.pop(dialogContext, false),
            child: const Text('Cancel'),
          ),
          TextButton(
            onPressed: () => Navigator.pop(dialogContext, true),
            child: const Text(
              'Delete',
              style: TextStyle(color: Colors.redAccent),
            ),
          ),
        ],
      ),
    );

    if (confirm != true) return;

    setState(() => _loading = true);
    try {
      await _service.deleteFacility(id);
      _message('Facility deleted successfully');
      await _loadFacilities();
    } catch (e) {
      _message('Delete failed: $e');
    } finally {
      if (mounted) setState(() => _loading = false);
    }
  }

  void _showForm() {
    showModalBottomSheet(
      context: context,
      isScrollControlled: true,
      backgroundColor: surface,
      builder: (sheetContext) => StatefulBuilder(
        builder: (context, setSheetState) {
          return Padding(
            padding: EdgeInsets.fromLTRB(
              20,
              20,
              20,
              MediaQuery.of(context).viewInsets.bottom + 20,
            ),
            child: SingleChildScrollView(
              child: Form(
                key: _formKey,
                child: Column(
                  mainAxisSize: MainAxisSize.min,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      _editingId == null ? 'Add Facility' : 'Edit Facility',
                      style: const TextStyle(
                        color: Colors.white,
                        fontSize: 22,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                    const SizedBox(height: 18),
                    _field(_name, 'Facility name'),
                    _field(_type, 'Type'),
                    _field(_description, 'Description'),
                    _field(_location, 'Location'),
                    _field(_societyId, 'Society ID', number: true),
                    const SizedBox(height: 10),
                    const Text('Status', style: TextStyle(color: muted)),
                    const SizedBox(height: 8),
                    DropdownButtonFormField<String>(
                      value: _status,
                      dropdownColor: surface,
                      style: const TextStyle(color: Colors.white),
                      decoration: _decoration('Select status'),
                      items: const [
                        DropdownMenuItem(
                          value: 'Available',
                          child: Text('Available'),
                        ),
                        DropdownMenuItem(
                          value: 'Unavailable',
                          child: Text('Unavailable'),
                        ),
                        DropdownMenuItem(
                          value: 'Maintenance',
                          child: Text('Maintenance'),
                        ),
                      ],
                      onChanged: (value) {
                        if (value != null) {
                          setState(() => _status = value);
                          setSheetState(() {});
                        }
                      },
                    ),
                    const SizedBox(height: 22),
                    SizedBox(
                      width: double.infinity,
                      child: ElevatedButton(
                        onPressed: _loading ? null : _saveFacility,
                        style: ElevatedButton.styleFrom(
                          backgroundColor: blue,
                          foregroundColor: bg,
                          padding: const EdgeInsets.all(15),
                        ),
                        child: Text(
                          _editingId == null
                              ? 'Add Facility'
                              : 'Update Facility',
                        ),
                      ),
                    ),
                  ],
                ),
              ),
            ),
          );
        },
      ),
    );
  }

  InputDecoration _decoration(String label) {
    return InputDecoration(
      labelText: label,
      labelStyle: const TextStyle(color: muted),
      filled: true,
      fillColor: bg,
      border: OutlineInputBorder(borderRadius: BorderRadius.circular(12)),
      enabledBorder: OutlineInputBorder(
        borderRadius: BorderRadius.circular(12),
        borderSide: const BorderSide(color: Color(0xFF334155)),
      ),
    );
  }

  Widget _field(
    TextEditingController controller,
    String label, {
    bool number = false,
  }) {
    return Padding(
      padding: const EdgeInsets.only(bottom: 12),
      child: TextFormField(
        controller: controller,
        keyboardType: number ? TextInputType.number : TextInputType.text,
        style: const TextStyle(color: Colors.white),
        decoration: _decoration(label),
        validator: (value) {
          if (value == null || value.trim().isEmpty) {
            return '$label is required';
          }
          if (number && int.tryParse(value.trim()) == null) {
            return 'Enter a valid number';
          }
          return null;
        },
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    final filtered = _facilities.where((item) {
      final query = _search.text.toLowerCase();
      return '${item['facilname'] ?? ''}'.toLowerCase().contains(query) ||
          '${item['type'] ?? ''}'.toLowerCase().contains(query) ||
          '${item['location'] ?? ''}'.toLowerCase().contains(query);
    }).toList();

    return Scaffold(
      backgroundColor: bg,
      appBar: AppBar(
        backgroundColor: bg,
        foregroundColor: Colors.white,
        title: const Text('Facility Management'),
        actions: [
          IconButton(
            onPressed: _loadFacilities,
            icon: const Icon(Icons.refresh),
          ),
        ],
      ),
      floatingActionButton: FloatingActionButton.extended(
        onPressed: () {
          _clearForm();
          _showForm();
        },
        backgroundColor: blue,
        foregroundColor: bg,
        icon: const Icon(Icons.add),
        label: const Text('Add Facility'),
      ),
      body: Column(
        children: [
          Padding(
            padding: const EdgeInsets.all(16),
            child: TextField(
              controller: _search,
              onChanged: (_) => setState(() {}),
              style: const TextStyle(color: Colors.white),
              decoration: _decoration(
                'Search facilities',
              ).copyWith(prefixIcon: const Icon(Icons.search, color: muted)),
            ),
          ),
          Expanded(
            child: _loading && _facilities.isEmpty
                ? const Center(child: CircularProgressIndicator())
                : filtered.isEmpty
                ? const Center(
                    child: Text(
                      'No facilities found',
                      style: TextStyle(color: muted),
                    ),
                  )
                : RefreshIndicator(
                    onRefresh: _loadFacilities,
                    child: ListView.builder(
                      padding: const EdgeInsets.fromLTRB(16, 0, 16, 90),
                      itemCount: filtered.length,
                      itemBuilder: (context, index) {
                        final item = filtered[index];
                        final id = int.tryParse('${item['facilid']}') ?? 0;

                        return Card(
                          color: surface,
                          margin: const EdgeInsets.only(bottom: 12),
                          shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(16),
                          ),
                          child: Padding(
                            padding: const EdgeInsets.all(16),
                            child: Column(
                              crossAxisAlignment: CrossAxisAlignment.start,
                              children: [
                                Row(
                                  children: [
                                    const CircleAvatar(
                                      backgroundColor: Color(0xFF1E3A5F),
                                      child: Icon(Icons.apartment, color: blue),
                                    ),
                                    const SizedBox(width: 12),
                                    Expanded(
                                      child: Text(
                                        '${item['facilname'] ?? 'Unnamed facility'}',
                                        style: const TextStyle(
                                          color: Colors.white,
                                          fontSize: 17,
                                          fontWeight: FontWeight.bold,
                                        ),
                                      ),
                                    ),
                                    IconButton(
                                      onPressed: () => _editFacility(item),
                                      icon: const Icon(
                                        Icons.edit_outlined,
                                        color: blue,
                                      ),
                                    ),
                                    IconButton(
                                      onPressed: id == 0
                                          ? null
                                          : () => _deleteFacility(id),
                                      icon: const Icon(
                                        Icons.delete_outline,
                                        color: Colors.redAccent,
                                      ),
                                    ),
                                  ],
                                ),
                                const SizedBox(height: 12),
                                _detail('Facility ID', '$id'),
                                _detail('Type', '${item['type'] ?? '-'}'),
                                _detail(
                                  'Description',
                                  '${item['description'] ?? '-'}',
                                ),
                                _detail(
                                  'Location',
                                  '${item['location'] ?? '-'}',
                                ),
                                _detail(
                                  'Society ID',
                                  '${item['societyId'] ?? '-'}',
                                ),
                                const SizedBox(height: 8),
                                Chip(
                                  label: Text('${item['status'] ?? 'Unknown'}'),
                                  backgroundColor: const Color(0xFF1E3A5F),
                                  labelStyle: const TextStyle(
                                    color: Colors.white,
                                  ),
                                ),
                              ],
                            ),
                          ),
                        );
                      },
                    ),
                  ),
          ),
        ],
      ),
    );
  }

  Widget _detail(String label, String value) {
    return Padding(
      padding: const EdgeInsets.only(bottom: 7),
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          SizedBox(
            width: 105,
            child: Text(label, style: const TextStyle(color: muted)),
          ),
          Expanded(
            child: Text(value, style: const TextStyle(color: Colors.white)),
          ),
        ],
      ),
    );
  }
}

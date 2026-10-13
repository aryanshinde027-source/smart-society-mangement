import 'package:flutter/material.dart';

class ResidentsScreen extends StatefulWidget {
  const ResidentsScreen({super.key});

  @override
  State<ResidentsScreen> createState() => _ResidentsScreenState();
}

class _ResidentsScreenState extends State<ResidentsScreen> {
  final TextEditingController searchController = TextEditingController();

  final List<Map<String, String>> residents = [
    {
      'name': 'Rahul Patil',
      'flat': 'A-101',
      'role': 'Owner',
      'phone': '9876543210',
    },
    {
      'name': 'Sneha Sharma',
      'flat': 'A-102',
      'role': 'Tenant',
      'phone': '9876543211',
    },
    {
      'name': 'Amit Deshmukh',
      'flat': 'B-201',
      'role': 'Owner',
      'phone': '9876543212',
    },
    {
      'name': 'Priya Jadhav',
      'flat': 'B-202',
      'role': 'Tenant',
      'phone': '9876543213',
    },
  ];

  String searchText = '';

  @override
  void dispose() {
    searchController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    final filteredResidents = residents.where((resident) {
      final name = resident['name']!.toLowerCase();
      final flat = resident['flat']!.toLowerCase();

      return name.contains(searchText.toLowerCase()) ||
          flat.contains(searchText.toLowerCase());
    }).toList();

    return Scaffold(
      appBar: AppBar(
        title: const Text(
          'Residents',
          style: TextStyle(fontWeight: FontWeight.bold),
        ),
        backgroundColor: const Color(0xFF1E40AF),
        foregroundColor: Colors.white,
      ),
      body: Column(
        children: [
          Padding(
            padding: const EdgeInsets.all(16),
            child: TextField(
              controller: searchController,
              onChanged: (value) {
                setState(() {
                  searchText = value;
                });
              },
              decoration: InputDecoration(
                hintText: 'Search by name or flat number',
                prefixIcon: const Icon(Icons.search),
                border: OutlineInputBorder(
                  borderRadius: BorderRadius.circular(14),
                ),
              ),
            ),
          ),
          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 16),
            child: Row(
              children: [
                const Icon(Icons.people, color: Color(0xFF1E40AF)),
                const SizedBox(width: 8),
                Text(
                  'Total Residents: ${filteredResidents.length}',
                  style: const TextStyle(
                    fontSize: 16,
                    fontWeight: FontWeight.bold,
                  ),
                ),
              ],
            ),
          ),
          const SizedBox(height: 8),
          Expanded(
            child: filteredResidents.isEmpty
                ? const Center(child: Text('No residents found'))
                : ListView.builder(
                    padding: const EdgeInsets.all(12),
                    itemCount: filteredResidents.length,
                    itemBuilder: (context, index) {
                      final resident = filteredResidents[index];

                      return Card(
                        elevation: 2,
                        margin: const EdgeInsets.only(bottom: 12),
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(14),
                        ),
                        child: ListTile(
                          contentPadding: const EdgeInsets.all(12),
                          leading: CircleAvatar(
                            radius: 25,
                            backgroundColor: const Color(0xFFDBEAFE),
                            child: Text(
                              resident['name']![0],
                              style: const TextStyle(
                                color: Color(0xFF1E40AF),
                                fontWeight: FontWeight.bold,
                                fontSize: 20,
                              ),
                            ),
                          ),
                          title: Text(
                            resident['name']!,
                            style: const TextStyle(fontWeight: FontWeight.bold),
                          ),
                          subtitle: Padding(
                            padding: const EdgeInsets.only(top: 6),
                            child: Column(
                              crossAxisAlignment: CrossAxisAlignment.start,
                              children: [
                                Text('Flat: ${resident['flat']}'),
                                Text('Type: ${resident['role']}'),
                                Text('Phone: ${resident['phone']}'),
                              ],
                            ),
                          ),
                          trailing: const Icon(
                            Icons.apartment,
                            color: Color(0xFF1E40AF),
                          ),
                        ),
                      );
                    },
                  ),
          ),
        ],
      ),
    );
  }
}

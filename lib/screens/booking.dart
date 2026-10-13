import 'package:flutter/material.dart';

class BookingScreen extends StatefulWidget {
  const BookingScreen({super.key});

  @override
  State<BookingScreen> createState() => _BookingScreenState();
}

class _BookingScreenState extends State<BookingScreen> {
  final _formKey = GlobalKey<FormState>();
  final TextEditingController purposeController = TextEditingController();

  String selectedFacility = 'Clubhouse';
  String selectedSlot = '10:00 AM - 12:00 PM';
  DateTime selectedDate = DateTime.now();

  final List<Map<String, dynamic>> facilities = [
    {
      'name': 'Clubhouse',
      'icon': Icons.meeting_room_outlined,
      'description': 'Events and gatherings',
      'price': '₹500 / hour',
      'color': Colors.blue,
    },
    {
      'name': 'Gym',
      'icon': Icons.fitness_center,
      'description': 'Fitness and workout',
      'price': '₹100 / hour',
      'color': Colors.orange,
    },
    {
      'name': 'Swimming Pool',
      'icon': Icons.pool,
      'description': 'Swimming and recreation',
      'price': '₹200 / hour',
      'color': Colors.teal,
    },
    {
      'name': 'Community Hall',
      'icon': Icons.groups_outlined,
      'description': 'Meetings and functions',
      'price': '₹800 / hour',
      'color': Colors.purple,
    },
  ];

  final List<String> timeSlots = [
    '08:00 AM - 10:00 AM',
    '10:00 AM - 12:00 PM',
    '12:00 PM - 02:00 PM',
    '02:00 PM - 04:00 PM',
    '04:00 PM - 06:00 PM',
    '06:00 PM - 08:00 PM',
  ];

  final List<Map<String, String>> bookings = [];

  @override
  void dispose() {
    purposeController.dispose();
    super.dispose();
  }

  Future<void> pickDate() async {
    final date = await showDatePicker(
      context: context,
      initialDate: selectedDate,
      firstDate: DateTime.now(),
      lastDate: DateTime.now().add(const Duration(days: 180)),
    );

    if (date != null && mounted) {
      setState(() => selectedDate = date);
    }
  }

  void confirmBooking() {
    if (!_formKey.currentState!.validate()) return;

    final date =
        '${selectedDate.day}/${selectedDate.month}/${selectedDate.year}';

    setState(() {
      bookings.insert(0, {
        'facility': selectedFacility,
        'date': date,
        'slot': selectedSlot,
        'purpose': purposeController.text.trim(),
        'status': 'Pending',
      });
    });

    purposeController.clear();

    ScaffoldMessenger.of(context).showSnackBar(
      const SnackBar(
        content: Text('Booking request added successfully!'),
        backgroundColor: Colors.green,
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: const Color(0xFFF8FAFC),
      appBar: AppBar(
        title: const Text(
          'Facility Booking',
          style: TextStyle(color: Colors.white, fontWeight: FontWeight.bold),
        ),
        backgroundColor: const Color(0xFF2563EB),
        iconTheme: const IconThemeData(color: Colors.white),
      ),
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(16),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Container(
              width: double.infinity,
              padding: const EdgeInsets.all(20),
              decoration: BoxDecoration(
                gradient: const LinearGradient(
                  colors: [Color(0xFF2563EB), Color(0xFF0284C7)],
                ),
                borderRadius: BorderRadius.circular(18),
              ),
              child: const Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Icon(Icons.calendar_month, size: 42, color: Colors.white),
                  SizedBox(height: 12),
                  Text(
                    'Book a Facility',
                    style: TextStyle(
                      color: Colors.white,
                      fontSize: 24,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  SizedBox(height: 6),
                  Text(
                    'Choose a facility and your preferred time.',
                    style: TextStyle(color: Colors.white70),
                  ),
                ],
              ),
            ),

            const SizedBox(height: 24),

            const Text(
              'Available Facilities',
              style: TextStyle(fontSize: 21, fontWeight: FontWeight.bold),
            ),

            const SizedBox(height: 14),

            GridView.builder(
              shrinkWrap: true,
              physics: const NeverScrollableScrollPhysics(),
              itemCount: facilities.length,
              gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
                crossAxisCount: 2,
                crossAxisSpacing: 12,
                mainAxisSpacing: 12,
                childAspectRatio: 0.95,
              ),
              itemBuilder: (context, index) {
                final facility = facilities[index];
                final isSelected = selectedFacility == facility['name'];

                return InkWell(
                  borderRadius: BorderRadius.circular(16),
                  onTap: () {
                    setState(() {
                      selectedFacility = facility['name'] as String;
                    });
                  },
                  child: Container(
                    padding: const EdgeInsets.all(14),
                    decoration: BoxDecoration(
                      color: Colors.white,
                      borderRadius: BorderRadius.circular(16),
                      border: Border.all(
                        color: isSelected
                            ? const Color(0xFF2563EB)
                            : const Color(0xFFE2E8F0),
                        width: isSelected ? 2 : 1,
                      ),
                    ),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        CircleAvatar(
                          backgroundColor: (facility['color'] as Color)
                              .withValues(alpha: 0.12),
                          child: Icon(
                            facility['icon'] as IconData,
                            color: facility['color'] as Color,
                          ),
                        ),
                        const SizedBox(height: 10),
                        Text(
                          facility['name'] as String,
                          style: const TextStyle(
                            fontWeight: FontWeight.bold,
                            fontSize: 15,
                          ),
                        ),
                        const SizedBox(height: 4),
                        Text(
                          facility['description'] as String,
                          maxLines: 2,
                          overflow: TextOverflow.ellipsis,
                          style: const TextStyle(
                            color: Colors.grey,
                            fontSize: 12,
                          ),
                        ),
                        const SizedBox(height: 6),
                        Text(
                          facility['price'] as String,
                          style: const TextStyle(
                            color: Color(0xFF2563EB),
                            fontWeight: FontWeight.w600,
                          ),
                        ),
                        if (isSelected)
                          const Align(
                            alignment: Alignment.centerRight,
                            child: Icon(
                              Icons.check_circle,
                              color: Color(0xFF2563EB),
                              size: 20,
                            ),
                          ),
                      ],
                    ),
                  ),
                );
              },
            ),

            const SizedBox(height: 24),

            const Text(
              'Booking Details',
              style: TextStyle(fontSize: 21, fontWeight: FontWeight.bold),
            ),

            const SizedBox(height: 14),

            Card(
              color: Colors.white,
              elevation: 1,
              shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(16),
              ),
              child: Padding(
                padding: const EdgeInsets.all(16),
                child: Form(
                  key: _formKey,
                  child: Column(
                    children: [
                      InkWell(
                        onTap: pickDate,
                        child: InputDecorator(
                          decoration: InputDecoration(
                            labelText: 'Booking Date',
                            prefixIcon: const Icon(Icons.calendar_today),
                            border: OutlineInputBorder(
                              borderRadius: BorderRadius.circular(12),
                            ),
                          ),
                          child: Text(
                            '${selectedDate.day}/${selectedDate.month}/${selectedDate.year}',
                          ),
                        ),
                      ),

                      const SizedBox(height: 18),

                      DropdownButtonFormField<String>(
                        value: selectedSlot,
                        isExpanded: true,
                        decoration: InputDecoration(
                          labelText: 'Time Slot',
                          prefixIcon: const Icon(Icons.access_time),
                          border: OutlineInputBorder(
                            borderRadius: BorderRadius.circular(12),
                          ),
                        ),
                        items: timeSlots.map((slot) {
                          return DropdownMenuItem(
                            value: slot,
                            child: Text(
                              slot,
                              style: const TextStyle(fontSize: 13),
                            ),
                          );
                        }).toList(),
                        onChanged: (value) {
                          if (value != null) {
                            setState(() => selectedSlot = value);
                          }
                        },
                      ),

                      const SizedBox(height: 18),

                      TextFormField(
                        controller: purposeController,
                        maxLines: 3,
                        decoration: InputDecoration(
                          labelText: 'Booking Purpose',
                          hintText: 'Why do you need this facility?',
                          prefixIcon: const Icon(Icons.description_outlined),
                          alignLabelWithHint: true,
                          border: OutlineInputBorder(
                            borderRadius: BorderRadius.circular(12),
                          ),
                        ),
                        validator: (value) {
                          if (value == null || value.trim().isEmpty) {
                            return 'Please enter booking purpose';
                          }
                          return null;
                        },
                      ),

                      const SizedBox(height: 20),

                      SizedBox(
                        width: double.infinity,
                        child: ElevatedButton.icon(
                          onPressed: confirmBooking,
                          icon: const Icon(Icons.event_available),
                          label: const Text('Request Booking'),
                          style: ElevatedButton.styleFrom(
                            backgroundColor: const Color(0xFF2563EB),
                            foregroundColor: Colors.white,
                            padding: const EdgeInsets.all(16),
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
              ),
            ),

            const SizedBox(height: 28),

            Row(
              children: [
                const Expanded(
                  child: Text(
                    'My Bookings',
                    style: TextStyle(fontSize: 21, fontWeight: FontWeight.bold),
                  ),
                ),
                Chip(label: Text('${bookings.length}')),
              ],
            ),

            const SizedBox(height: 12),

            if (bookings.isEmpty)
              Container(
                width: double.infinity,
                padding: const EdgeInsets.all(26),
                decoration: BoxDecoration(
                  color: Colors.white,
                  borderRadius: BorderRadius.circular(16),
                ),
                child: const Column(
                  children: [
                    Icon(Icons.event_busy, size: 44, color: Colors.grey),
                    SizedBox(height: 10),
                    Text(
                      'No bookings yet',
                      style: TextStyle(fontWeight: FontWeight.w600),
                    ),
                    SizedBox(height: 6),
                    Text(
                      'Your booking requests will appear here.',
                      textAlign: TextAlign.center,
                      style: TextStyle(color: Colors.grey),
                    ),
                  ],
                ),
              )
            else
              ...bookings.map((booking) {
                return Card(
                  color: Colors.white,
                  margin: const EdgeInsets.only(bottom: 12),
                  child: ListTile(
                    leading: const CircleAvatar(
                      backgroundColor: Color(0xFFDBEAFE),
                      child: Icon(
                        Icons.calendar_month,
                        color: Color(0xFF2563EB),
                      ),
                    ),
                    title: Text(
                      booking['facility']!,
                      style: const TextStyle(fontWeight: FontWeight.bold),
                    ),
                    subtitle: Padding(
                      padding: const EdgeInsets.only(top: 6),
                      child: Text(
                        '${booking['date']} • ${booking['slot']}\n'
                        'Purpose: ${booking['purpose']}',
                      ),
                    ),
                    isThreeLine: true,
                    trailing: const Text(
                      'Pending',
                      style: TextStyle(
                        color: Colors.orange,
                        fontWeight: FontWeight.w600,
                      ),
                    ),
                  ),
                );
              }),
          ],
        ),
      ),
    );
  }
}

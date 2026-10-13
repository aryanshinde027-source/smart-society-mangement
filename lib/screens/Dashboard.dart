import 'package:flutter/material.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:society_management/screens/Complaints.dart';
import 'package:society_management/screens/Registers.dart';
import 'package:society_management/screens/booking.dart';
import 'package:society_management/screens/notice.dart';
import 'package:society_management/screens/profile.dart';
import 'package:society_management/screens/visitors.dart';

class DashboardScreen extends StatelessWidget {
  const DashboardScreen({super.key});

  @override
  Widget build(BuildContext context) {
    final user = FirebaseAuth.instance.currentUser;

    return Scaffold(
      appBar: AppBar(
        title: const Text(
          'Society Management',
          style: TextStyle(color: Colors.white, fontWeight: FontWeight.bold),
        ),
        backgroundColor: const Color(0xFF2563EB),
        actions: [
          IconButton(
            icon: const Icon(Icons.logout, color: Colors.white),
            onPressed: () async {
              await FirebaseAuth.instance.signOut();
            },
          ),
        ],
      ),
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(16),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            const Text(
              'Welcome Back!',
              style: TextStyle(fontSize: 26, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 8),
            Text(
              user?.displayName ?? user?.email ?? 'Society Resident',
              style: const TextStyle(fontSize: 16, color: Colors.grey),
            ),
            const SizedBox(height: 24),
            Container(
              width: double.infinity,
              padding: const EdgeInsets.all(20),
              decoration: BoxDecoration(
                color: const Color(0xFF2563EB),
                borderRadius: BorderRadius.circular(16),
              ),
              child: const Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Icon(Icons.apartment, color: Colors.white, size: 40),
                  SizedBox(height: 12),
                  Text(
                    'My Society',
                    style: TextStyle(
                      color: Colors.white,
                      fontSize: 22,
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                  SizedBox(height: 6),
                  Text(
                    'Manage your society activities in one place.',
                    style: TextStyle(color: Colors.white),
                  ),
                ],
              ),
            ),
            const SizedBox(height: 24),
            const Text(
              'Society Services',
              style: TextStyle(fontSize: 21, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 16),
            GridView.count(
              crossAxisCount: 2,
              crossAxisSpacing: 14,
              mainAxisSpacing: 14,
              shrinkWrap: true,
              physics: const NeverScrollableScrollPhysics(),
              children: [
                _serviceCard(
                  context,
                  Icons.report_problem_outlined,
                  'Complaints',
                  Colors.orange,
                ),
                _serviceCard(
                  context,
                  Icons.people_outline,
                  'Residents',
                  Colors.blue,
                ),
                _serviceCard(
                  context,
                  Icons.campaign_outlined,
                  'Notices',
                  Colors.purple,
                ),
                _serviceCard(
                  context,
                  Icons.meeting_room_outlined,
                  'Facility Booking',
                  Colors.green,
                ),
                _serviceCard(
                  context,
                  Icons.local_parking_outlined,
                  'Visitors',
                  Colors.teal,
                ),
                _serviceCard(
                  context,
                  Icons.person_outline,
                  'My Profile',
                  Colors.indigo,
                ),
              ],
            ),
          ],
        ),
      ),
    );
  }

  static Widget _serviceCard(
    BuildContext context,
    IconData icon,
    String title,
    Color color,
  ) {
    return Card(
      elevation: 2,
      shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(16)),
      child: InkWell(
        borderRadius: BorderRadius.circular(16),
        onTap: () {
          if (title == 'My Profile') {
            Navigator.push(
              context,
              MaterialPageRoute(builder: (context) => const ProfileScreen()),
            );
          }
          if (title == 'Visitors') {
            Navigator.push(
              context,
              MaterialPageRoute(builder: (context) => const VisitorsScreen()),
            );
          }
          if (title == 'Facility Booking') {
            Navigator.push(
              context,
              MaterialPageRoute(builder: (context) => const BookingScreen()),
            );
          }
          if (title == 'Notices') {
            Navigator.push(
              context,
              MaterialPageRoute(builder: (context) => const NoticesScreen()),
            );
          }
          if (title == 'Residents') {
            Navigator.push(
              context,
              MaterialPageRoute(builder: (context) => const ResidentsScreen()),
            );
          }
          if (title == 'Complaints') {
            Navigator.push(
              context,
              MaterialPageRoute(builder: (context) => const ComplaintsScreen()),
            );
          }
        },
        child: Padding(
          padding: const EdgeInsets.all(16),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Icon(icon, size: 40, color: color),
              const SizedBox(height: 12),
              Text(
                title,
                textAlign: TextAlign.center,
                style: const TextStyle(
                  fontSize: 16,
                  fontWeight: FontWeight.w600,
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}

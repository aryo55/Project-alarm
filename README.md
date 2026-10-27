# Project-alarm

APK alarm Android yang bisa dipairing dan disetel dari jarak jauh.

## Konsep pairing
1. Install APK di HP Android.
2. Buka aplikasi, ketuk Pairing, aplikasi menampilkan kode pairing perangkat.
3. Kode itu yang dipakai untuk mengaitkan perangkat ini dengan akun pengendali (Muse / bot) lewat chat.
4. Setelah terpairing, perintah `set alarm jam 7` dari chat diteruskan ke perangkat ini, dan aplikasi menyetel alarm lewat AlarmManager Android.

## Fitur tahap 1
- Set alarm jam tertentu (misalnya 07.00) lewat aplikasi.
- Alarm berbunyi lewat aplikasi Jam / AlarmManager, bukan sekadar notifikasi chat.
- Layar pairing dengan kode perangkat.

## Build APK
Lihat workflow GitHub Actions di `.github/workflows/build-apk.yml` (akan ditambahkan berikutnya) untuk build APK debug otomatis.

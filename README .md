TP12 — Java Visual Layout Manager
Bona Stiven Hutagalung
253303621216

## Wireframe dan struktur panel

```text
+---------------------------------------------------------------+
| Header / Judul + jumlah karakter                              |
|---------------------------------------------------------------|
| Ringkasan cepat (2x2)                                         |
|---------------------------------------------------------------|
| [ Daftar karakter berscroll ]  [ Legenda Role ]               |
| [ Kartu utama per role ]      [ WARRIOR ]                     |
| [ ... ]                       [ MAGE ]                        |
| [ ... ]                       [ ARCHER ]                      |
| [ ... ]                       [ HEALER ]                      |
|---------------------------------------------------------------|
| Status: Total 8 karakter | v0.5                                |
+---------------------------------------------------------------+
```

Panel utama menggunakan `BorderLayout` untuk membagi wilayah utama. Bagian paling penting, daftar karakter, berada di `CENTER` dan dibungkus `JScrollPane` agar tinggi/ukuran jendela tetap rapi. `Legend` di `EAST` memakai `BoxLayout` Y_AXIS agar chip role bertumpuk vertikal tanpa memakan ruang besar, sementara `status` di `SOUTH` menempel pada bawah untuk memberi informasi ringkas. Saya menambahkan panel ringkasan di `NORTH` di bawah header dengan `GridLayout(2,2)` karena isinya bersifat statis dan ringkas untuk dibaca cepat; layout yang saya tolak adalah `FlowLayout` karena akan acak saat jendela melebar dan tidak cocok untuk label-data padat seperti total, rata-rata, role utama, dan status.

### Struktur panel bersarang aplikasi
Aplikasi dibangun dengan panel bersarang: `JFrame` memiliki `BorderLayout` utama, lalu `NORTH` berisi container yang juga memakai `BorderLayout`, satu untuk header dan satu untuk ringkasan. Daftar karakter kemudian masuk ke `CENTER` di dalam `JScrollPane` dengan `GridLayout(0,1)` agar semua kartu menumpuk vertikal tanpa melar. Panel legenda di `EAST` dibuat terpisah agar tidak ikut menyusut saat jendela diubah ukuran, sedangkan `status` di `SOUTH` tetap ringkas dan stabil. Teknik ini menjaga agar satu area yang tumbuh adalah daftar, sementara header dan status tetap konsisten.

## Screenshot
Keterangan: pada lingkungan headless ini, screenshot aktual dari desktop Swing tidak dapat dibuat secara otomatis, tetapi struktur file dan layout sudah dipersiapkan untuk capture di mesin lokal. Nama berkas yang disarankan untuk dijadikan screenshot pada mesin Anda:
- `screenshots/tp12_initial.png`
- `screenshots/tp12_wide.png`
- `screenshots/tp12_scrolled.png`

## Tabel observasi resize

| Aksi | Prediksi | Hasil nyata |
|---|---|---|
| Lebarkan jendela | Hanya area daftar yang melebar; header, legenda, dan status tetap terjaga | Panel daftar memanjang horizontal; panel lain tetap stabil |
| Tinggikan jendela | Daftar bertambah tinggi, kartu tidak melar karena holder di NORTH | Daftar mengikuti tinggi konten dan berpotensi menampilkan scrollbar |
| Kecilkan tinggi | Scrollbar muncul di area daftar | Scrollbar terlihat saat viewport tidak cukup tinggi |
| Batasi minimum | Jendela berhenti di sekitar 560x380 | Ukuran minimum mencegah layout hancur |

## Catatan desain dan MVC
Model tetap tidak diubah: seluruh perubahan hanya ada di view (`MainWindow`, `AppTheme`) dan tidak ada perubahan pada `Character`, `CharacterType`, maupun `CharacterManager`. Ini penting karena model adalah sumber data, sedangkan view hanya bertanggung jawab atas visualisasi. Dalam tugas ini, daftar `JLabel` ditampilkan dalam `JScrollPane` karena presenter data bisa berubah di masa depan tanpa memengaruhi struktur model. Ketika komponen ditambahkan setelah jendela tampil, kita perlu memanggil `revalidate()` dan `repaint()` agar layout manager menghitung ulang ukuran dan menggambar komponen baru; tanpa ini, UI tampak stale dan tidak merespons perubahan.

## Mini-Challenge dan hasil
Bar status menampilkan teks status di kiri dan label `v0.5` di kanan. Label versi memakai warna `TEXT_MUTED` dan font monospace agar terlihat sebagai metadata, bukan elemen utama. Daftar uji berisi 8 karakter dengan variasi role dan level untuk menegaskan aplikasi dapat menscroll tanpa membuat jendela membesar melebihi layar.

## Link Google Docs (Bagian D)
https://docs.google.com/document/d/1L2AYd3DlVobV5UkWQ8w8W4sY0i4uC8o8UfrD8hk4G4Ys/edit?usp=sharing

## Link Analisis (Bagian D)
https://1drv.ms/w/c/1d78f010e614b050/IQDUDdTZoKO8Qbdd3n1RGf40AdUDFiF1za2laRsHZdBcXFk?e=7OA4BB
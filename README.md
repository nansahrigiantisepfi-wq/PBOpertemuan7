# Program Pengelolaan Nilai Mahasiswa (Java OOP)

Program Java berorientasi objek untuk mengelola data nilai mahasiswa. Program membaca input dari pengguna, memvalidasi data dengan **Assertion** dan **Custom Exception**, menyimpan objek dalam **ArrayList**, mengolah **String/Character**, lalu menyimpan rekapitulasi ke file teks `laporan_nilai.txt` menggunakan **File I/O**.

> Tugas: Pengganti Pertemuan 7 – Pemrograman Berorientasi Objek

## Struktur Proyek

```
.
├── Main.java                    # Kelas utama (input, statistik, tulis file)
├── Mahasiswa.java               # Kelas data + validasi + grade
├── NilaiInvalidException.java   # Custom exception
├── laporan_nilai.txt            # Dihasilkan saat program dijalankan
└── README.md
```

> Semua file berada di satu folder tanpa deklarasi `package` agar mudah dikompilasi dan dijalankan.

## Fitur

- Input data beberapa mahasiswa secara dinamis (nama, NIM, nilai) lewat `Scanner`.
- Perapian data: `trim()` dan `toUpperCase()` pada nama, `trim()` pada NIM.
- Validasi NIM hanya angka dengan `Character.isDigit()`.
- Validasi NIM tidak kosong dengan `assert !nim.isEmpty() : "NIM tidak boleh kosong!"`.
- Validasi nilai 0–100 dengan `NilaiInvalidException`.
- Konversi nilai ke grade (A/B/C/D) dan status kelulusan (`FINAL_PASSING_GRADE = 70.0`).
- Statistik kelas: rata-rata, jumlah lulus, dan jumlah tidak lulus.
- Laporan disimpan ke `laporan_nilai.txt` dengan `BufferedWriter` + `FileWriter` (try-with-resources).

## Pemetaan Materi

| Pertemuan | Materi | Implementasi |
|-----------|--------|--------------|
| 2 | Class, Constructor, Variabel, `final` | Kelas `Mahasiswa`, constructor, konstanta `FINAL_PASSING_GRADE` |
| 3 | Kondisional, Looping, Tipe Data | If-Else untuk grade, for-each/while, `double` & `boolean` vs `String` & objek |
| 4 | Exception, Assertion, Character, String | `NilaiInvalidException`, `assert`, `Character.isDigit()`, `trim()`, `toUpperCase()` |
| 5 | Array & Collection | `ArrayList<Mahasiswa>` dengan `add()`, `size()`, for-each |
| 6 | Console & File I/O | `Scanner`, `BufferedWriter`, `FileWriter`, try-with-resources |

## Aturan Penilaian

| Nilai | Grade |
|-------|-------|
| 85 – 100 | A |
| 70 – 84.99 | B |
| 55 – 69.99 | C |
| 0 – 54.99 | D |

Status **LULUS** jika nilai ≥ 70.0, selain itu **TIDAK LULUS**.

## Cara Menjalankan

Pastikan JDK sudah terpasang (`java -version` dan `javac -version`).

```bash
# 1. Kompilasi semua file
javac *.java

# 2. Jalankan dengan assertion aktif (-ea)
java -ea Main
```

> Flag `-ea` (enable assertions) wajib agar `assert` berjalan. Tanpa flag ini, `assert` dilewati, tetapi program tetap aman karena ada pengecekan cadangan untuk NIM kosong.

## Contoh Penggunaan

```
=== PROGRAM PENGELOLAAN NILAI MAHASISWA ===
Jumlah mahasiswa yang akan diinput: 2

Data mahasiswa ke-1
Nama  : budi santoso
NIM   : 12345
Nilai : 88

Data mahasiswa ke-2
Nama  : ani
NIM   : 9x1
Nilai : 67
[ERROR] NIM hanya boleh berisi angka! Ulangi input.
```

Contoh isi `laporan_nilai.txt`:

```
======================================================================
                 LAPORAN NILAI MAHASISWA
======================================================================
No   Nama                      NIM             Nilai   Grade  Status
----------------------------------------------------------------------
1    BUDI SANTOSO              12345           88.00   A      LULUS
2    ANI                       67890           67.50   C      TIDAK LULUS
======================================================================
STATISTIK KELAS
Jumlah mahasiswa : 2
Rata-rata nilai  : 77.75
Jumlah lulus     : 1
Jumlah tidak lulus: 1
Standar kelulusan: 70.0
======================================================================
```

## Penanganan Error

| Kondisi | Penanganan |
|---------|-----------|
| Nilai < 0 atau > 100 | `NilaiInvalidException` lalu input diulang |
| Nilai bukan angka | `NumberFormatException` lalu input diulang |
| NIM kosong | `assert` / `IllegalArgumentException` lalu input diulang |
| NIM mengandung huruf/simbol | `IllegalArgumentException` lalu input diulang |
| Gagal menulis file | `IOException` ditangkap dan pesan error ditampilkan |

## Author

- Nama:SEPFINANGSARIGIANTI
- NIM:L0325013
- Kelas:INFORMATIKA 3B

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pertemuan7;

/**
 *
 * @author Hype AMD
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Kelas utama: input data (Scanner), simpan di ArrayList,
 * hitung statistik, tampilkan di layar, dan simpan ke laporan_nilai.txt.
 */
public class Main {

    private static final String NAMA_FILE = "laporan_nilai.txt";

    public static void main(String[] args) {
        ArrayList<Mahasiswa> daftar = new ArrayList<>();

        try (Scanner input = new Scanner(System.in)) {
            System.out.println("=== PROGRAM PENGELOLAAN NILAI MAHASISWA ===");
            int jumlah = bacaJumlah(input);

            int ke = 1;
            while (ke <= jumlah) {
                System.out.println("\nData mahasiswa ke-" + ke);

                System.out.print("Nama  : ");
                String nama = input.nextLine();

                System.out.print("NIM   : ");
                String nim = input.nextLine();

                System.out.print("Nilai : ");
                String teksNilai = input.nextLine().trim().replace(',', '.');

                try {
                    double nilai = Double.parseDouble(teksNilai);
                    Mahasiswa m = new Mahasiswa(nama, nim, nilai);
                    daftar.add(m);
                    ke++; // hanya naik jika data berhasil disimpan
                } catch (NumberFormatException e) {
                    System.out.println("[ERROR] Nilai harus berupa angka. Ulangi input.");
                } catch (NilaiInvalidException e) {
                    System.out.println("[ERROR] " + e.getMessage() + ". Ulangi input.");
                } catch (IllegalArgumentException e) {
                    System.out.println("[ERROR] " + e.getMessage() + " Ulangi input.");
                } catch (AssertionError e) {
                    System.out.println("[ASSERT] " + e.getMessage() + " Ulangi input.");
                }
            }
        }

        if (daftar.isEmpty()) {
            System.out.println("Tidak ada data untuk diproses.");
            return;
        }

        // Statistik kelas: perulangan for-each
        double total = 0;
        int lulus = 0;
        int tidakLulus = 0;
        for (Mahasiswa m : daftar) {
            total += m.getNilai();
            if (m.isLulus()) {
                lulus++;
            } else {
                tidakLulus++;
            }
        }
        double rataRata = total / daftar.size();

        String laporan = buatLaporan(daftar, rataRata, lulus, tidakLulus);
        System.out.println("\n" + laporan);
        simpanKeFile(laporan);
    }

    /** Membaca jumlah mahasiswa; diulang sampai input valid (> 0). */
    private static int bacaJumlah(Scanner input) {
        while (true) {
            System.out.print("Jumlah mahasiswa yang akan diinput: ");
            try {
                int n = Integer.parseInt(input.nextLine().trim());
                if (n > 0) {
                    return n;
                }
                System.out.println("[ERROR] Jumlah harus lebih dari 0.");
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] Masukkan bilangan bulat.");
            }
        }
    }

    /** Menyusun teks laporan (dipakai untuk layar dan file). */
    private static String buatLaporan(ArrayList<Mahasiswa> daftar, double rataRata,
                                      int lulus, int tidakLulus) {
        StringBuilder sb = new StringBuilder();
        String garis = "=".repeat(70);

        sb.append(garis).append("\n");
        sb.append("                 LAPORAN NILAI MAHASISWA\n");
        sb.append(garis).append("\n");
        sb.append(String.format("%-4s %-25s %-15s %-7s %-6s %s%n",
                "No", "Nama", "NIM", "Nilai", "Grade", "Status"));
        sb.append("-".repeat(70)).append("\n");

        int no = 1;
        for (Mahasiswa m : daftar) {
            sb.append(String.format("%-4d %-25s %-15s %-7.2f %-6s %s%n",
                    no++, m.getNama(), m.getNim(), m.getNilai(), m.getGrade(), m.getStatus()));
        }

        sb.append(garis).append("\n");
        sb.append("STATISTIK KELAS\n");
        sb.append("Jumlah mahasiswa : ").append(daftar.size()).append("\n");
        sb.append(String.format("Rata-rata nilai  : %.2f%n", rataRata));
        sb.append("Jumlah lulus     : ").append(lulus).append("\n");
        sb.append("Jumlah tidak lulus: ").append(tidakLulus).append("\n");
        sb.append("Standar kelulusan: ").append(Mahasiswa.FINAL_PASSING_GRADE).append("\n");
        sb.append(garis);
        return sb.toString();
    }

    /** File I/O: BufferedWriter + FileWriter dengan try-with-resources. */
    private static void simpanKeFile(String laporan) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NAMA_FILE))) {
            writer.write(laporan);
            writer.newLine();
            System.out.println("\nLaporan berhasil disimpan ke " + NAMA_FILE);
        } catch (IOException e) {
            System.out.println("[ERROR] Gagal menyimpan file: " + e.getMessage());
        }
    }
}
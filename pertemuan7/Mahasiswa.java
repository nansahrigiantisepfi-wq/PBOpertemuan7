/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pertemuan7;

/**
 *
 * @author Hype AMD
 */
/**
 * Kelas data Mahasiswa.
 * Atribut: nama (String), nim (String), nilai (double).
 */
public class Mahasiswa {

    // Konstanta standar kelulusan (final + static)
    public static final double FINAL_PASSING_GRADE = 70.0;

    // Atribut (reference type: String, primitive type: double)
    private final String nama;
    private final String nim;
    private final double nilai;

    /**
     * Constructor: merapikan nama/NIM, memvalidasi, lalu menginisialisasi atribut.
     *
     * @throws NilaiInvalidException    jika nilai < 0 atau > 100
     * @throws IllegalArgumentException jika NIM mengandung karakter non-angka
     */
    public Mahasiswa(String nama, String nim, double nilai) throws NilaiInvalidException {
        // Manipulasi String: trim() menghapus spasi, toUpperCase() membuat huruf kapital
        String namaRapi = nama.trim().toUpperCase();
        String nimRapi = nim.trim();

        // Assertion: NIM tidak boleh kosong (aktifkan dengan java -ea)
        assert !nimRapi.isEmpty() : "NIM tidak boleh kosong!";

        // Pengaman tambahan jika assertion tidak diaktifkan
        if (nimRapi.isEmpty()) {
            throw new IllegalArgumentException("NIM tidak boleh kosong!");
        }

        // Validasi NIM hanya berisi angka memakai kelas Character
        for (int i = 0; i < nimRapi.length(); i++) {
            if (!Character.isDigit(nimRapi.charAt(i))) {
                throw new IllegalArgumentException("NIM hanya boleh berisi angka!");
            }
        }

        // Custom Exception: nilai harus 0 - 100
        if (nilai < 0 || nilai > 100) {
            throw new NilaiInvalidException(nilai);
        }

        this.nama = namaRapi;
        this.nim = nimRapi;
        this.nilai = nilai;
    }

    public String getNama() {
        return nama;
    }

    public String getNim() {
        return nim;
    }

    public double getNilai() {
        return nilai;
    }

    /** Memetakan nilai angka ke grade huruf dengan If-Else. */
    public String getGrade() {
        if (nilai >= 85) {
            return "A";
        } else if (nilai >= 70) {
            return "B";
        } else if (nilai >= 55) {
            return "C";
        } else {
            return "D";
        }
    }

    /** Status kelulusan berdasarkan FINAL_PASSING_GRADE (tipe primitif boolean). */
    public boolean isLulus() {
        return nilai >= FINAL_PASSING_GRADE;
    }

    public String getStatus() {
        return isLulus() ? "LULUS" : "TIDAK LULUS";
    }
}
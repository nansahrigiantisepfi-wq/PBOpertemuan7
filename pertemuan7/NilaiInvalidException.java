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
 * Custom Exception yang dilempar (throw) ketika nilai mahasiswa
 * kurang dari 0 atau lebih dari 100.
 *
 * Turunan Exception (checked exception), sehingga pemanggil
 * WAJIB menangani dengan try-catch atau mendeklarasikan throws.
 */
public class NilaiInvalidException extends Exception {

    private final double nilaiSalah;

    public NilaiInvalidException(double nilaiSalah) {
        super("Nilai tidak valid: " + nilaiSalah + " (harus antara 0 sampai 100)");
        this.nilaiSalah = nilaiSalah;
    }

    public double getNilaiSalah() {
        return nilaiSalah;
    }
}

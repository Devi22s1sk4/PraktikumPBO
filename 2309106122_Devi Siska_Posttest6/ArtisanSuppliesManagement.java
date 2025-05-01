import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;

// Interface definition
interface Inventarisasi {
    boolean checkStock();
    void restockItem(int jumlahTambahan);
}

class Kategori {
    private String namaKategori;

    public Kategori(String namaKategori) {
        this.namaKategori = namaKategori;
    }

    public String getNamaKategori() {
        return namaKategori;
    }

    public void setNamaKategori(String namaKategori) {
        this.namaKategori = namaKategori;
    }

    @Override
    public String toString() {
        return namaKategori;
    }
}

abstract class AlatLukis implements Inventarisasi {
    private String nama;
    private int stok;
    private final double harga; 
    private Kategori kategori;
    private static int totalAlatLukis = 0; // Static variable to track total items

    public AlatLukis(String nama, int stok, double harga, Kategori kategori) {
        this.nama = nama;
        this.stok = stok;
        this.harga = harga;
        this.kategori = kategori;
        totalAlatLukis++; // Increment count when new item is created
    }

    // Implementation of Inventarisasi interface
    @Override
    public boolean checkStock() {
        return stok > 0;
    }

    @Override
    public void restockItem(int jumlahTambahan) {
        if (jumlahTambahan > 0) {
            this.stok += jumlahTambahan;
            System.out.println("Stok " + nama + " berhasil ditambah sebanyak " + jumlahTambahan + ". Total stok sekarang: " + stok);
        } else {
            throw new IllegalArgumentException("Jumlah tambahan stok harus lebih dari 0");
        }
    }

    public void updateInfo(String nama) {
        this.nama = nama;
    }

    public void updateInfo(int stok) {
        if (stok >= 0) {
            this.stok = stok;
        }
    }

    public void updateInfo(String nama, int stok) {
        updateInfo(nama);
        updateInfo(stok);
    }

    public abstract String getDetailInfo();

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        if (stok >= 0) {
            this.stok = stok;
        } else {
            System.out.println("Stok tidak boleh negatif. Nilai stok tidak diubah.");
        }
    }

    public double getHarga() {
        return harga;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    // Static method to get total count of items
    public static int getTotalAlatLukis() {
        return totalAlatLukis;
    }

    // Static method to decrease count when an item is removed
    public static void decrementTotalAlatLukis() {
        totalAlatLukis--;
    }

    @Override
    public final String toString() { 
        return String.format("| %-20s | %-10d | Rp%-10.2f | %-15s | %-15s | %-15s | %-10s |", 
            nama, stok, harga, kategori, "-", "-", "Umum");
    }
}

class Cat extends AlatLukis {
    private String warna;
    private String jenisCat;

    public Cat(String nama, int stok, double harga, Kategori kategori, String warna, String jenisCat) {
        super(nama, stok, harga, kategori);
        this.warna = warna;
        this.jenisCat = jenisCat;
    }

    @Override
    public String getDetailInfo() {
        return "Warna: " + warna + ", Jenis: " + jenisCat;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public String getJenisCat() {
        return jenisCat;
    }

    public void setJenisCat(String jenisCat) {
        this.jenisCat = jenisCat;
    }

    public String getDisplayString() {
        return String.format("| %-20s | %-10d | Rp%-10.2f | %-15s | %-15s | %-15s | %-10s |", 
            getNama(), getStok(), getHarga(), getKategori(), "Warna: " + warna, "Jenis: " + jenisCat, "Cat");
    }
}

class Kuas extends AlatLukis {
    private String ukuran;
    private String bahanBulu;

    public Kuas(String nama, int stok, double harga, Kategori kategori, String ukuran, String bahanBulu) {
        super(nama, stok, harga, kategori);
        this.ukuran = ukuran;
        this.bahanBulu = bahanBulu;
    }

    @Override
    public String getDetailInfo() {
        return "Ukuran: " + ukuran + ", Bahan: " + bahanBulu;
    }

    public String getUkuran() {
        return ukuran;
    }

    public void setUkuran(String ukuran) {
        this.ukuran = ukuran;
    }

    public String getBahanBulu() {
        return bahanBulu;
    }

    public void setBahanBulu(String bahanBulu) {
        this.bahanBulu = bahanBulu;
    }

    public String getDisplayString() {
        return String.format("| %-20s | %-10d | Rp%-10.2f | %-15s | %-15s | %-15s | %-10s |", 
            getNama(), getStok(), getHarga(), getKategori(), "Ukuran: " + ukuran, "Bahan: " + bahanBulu, "Kuas");
    }
}

final class AlatLukisUmum extends AlatLukis { 
    public AlatLukisUmum(String nama, int stok, double harga, Kategori kategori) {
        super(nama, stok, harga, kategori);
    }

    @Override
    public String getDetailInfo() {
        return "Alat lukis umum";
    }
}

public class ArtisanSuppliesManagement {
    protected static ArrayList<AlatLukis> daftarAlatLukis = new ArrayList<>();
    protected static ArrayList<Kategori> daftarKategori = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeCategories();

        while (true) {
            try {
                displayMainMenu();
                int pilihan = getIntInput("Pilih menu: ");

                switch (pilihan) {
                    case 1:
                        tambahAlatLukis();
                        break;
                    case 2:
                        lihatDaftarAlatLukis();
                        break;
                    case 3:
                        updateAlatLukis();
                        break;
                    case 4:
                        hapusAlatLukis();
                        break;
                    case 5:
                        lihatDetailAlatLukis();
                        break;
                    case 6:
                        restockAlatLukis();
                        break;
                    case 7:
                        displayStatistics();
                        break;
                    case 8:
                        System.out.println("Terima kasih telah menggunakan sistem ini.");
                        System.exit(0);
                    default:
                        System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Input tidak valid. Harap masukkan angka.");
                scanner.nextLine(); // Clear the scanner buffer
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // Static method to initialize categories
    private static void initializeCategories() {
        daftarKategori.add(new Kategori("Kuas"));
        daftarKategori.add(new Kategori("Cat"));
        daftarKategori.add(new Kategori("Kanvas"));
        daftarKategori.add(new Kategori("Pensil"));
    }

    // Static method to display menu
    private static void displayMainMenu() {
        System.out.println("\n=== Sistem Manajemen Penjualan dan Stok Alat Lukis ===");
        System.out.println("1. Tambah Alat Lukis");
        System.out.println("2. Lihat Daftar Alat Lukis");
        System.out.println("3. Update Alat Lukis");
        System.out.println("4. Hapus Alat Lukis");
        System.out.println("5. Lihat Detail Alat Lukis");
        System.out.println("6. Restock Alat Lukis");
        System.out.println("7. Lihat Statistik");
        System.out.println("8. Keluar");
    }

    // Static method to handle integer input with error handling
    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        try {
            int input = scanner.nextInt();
            scanner.nextLine(); // Clear buffer
            return input;
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Clear buffer
            throw new InputMismatchException("Input harus berupa angka");
        }
    }

    // Static method to handle double input with error handling
    private static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        try {
            double input = scanner.nextDouble();
            scanner.nextLine(); // Clear buffer
            return input;
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Clear buffer
            throw new InputMismatchException("Input harus berupa angka");
        }
    }

    // Static method to handle string input
    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    // New method for restocking items (implements the Inventarisasi interface)
    public static void restockAlatLukis() {
        if (daftarAlatLukis.isEmpty()) {
            System.out.println("Daftar alat lukis kosong.");
            return;
        }

        System.out.println("\nDaftar Alat Lukis untuk Restock:");
        for (int i = 0; i < daftarAlatLukis.size(); i++) {
            AlatLukis alat = daftarAlatLukis.get(i);
            System.out.println((i + 1) + ". " + alat.getNama() + " (Stok saat ini: " + alat.getStok() + ")");
        }

        try {
            int index = getIntInput("Pilih nomor alat lukis untuk restock: ") - 1;

            if (index >= 0 && index < daftarAlatLukis.size()) {
                AlatLukis alat = daftarAlatLukis.get(index);
                int jumlahTambahan = getIntInput("Masukkan jumlah tambahan stok: ");
                
                try {
                    alat.restockItem(jumlahTambahan);
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else {
                System.out.println("Nomor tidak valid.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Input tidak valid.");
        }
    }

    // Static method to display statistics
    public static void displayStatistics() {
        System.out.println("\n=== Statistik Alat Lukis ===");
        System.out.println("Total jumlah alat lukis: " + AlatLukis.getTotalAlatLukis());
        
        int totalStok = 0;
        double totalNilaiInventaris = 0;
        int jumlahKuas = 0;
        int jumlahCat = 0;
        int jumlahUmum = 0;
        
        for (AlatLukis alat : daftarAlatLukis) {
            totalStok += alat.getStok();
            totalNilaiInventaris += (alat.getHarga() * alat.getStok());
            
            if (alat instanceof Kuas) {
                jumlahKuas++;
            } else if (alat instanceof Cat) {
                jumlahCat++;
            } else {
                jumlahUmum++;
            }
        }
        
        System.out.println("Total stok seluruh alat lukis: " + totalStok + " unit");
        System.out.printf("Total nilai inventaris: Rp %.2f\n", totalNilaiInventaris);
        System.out.println("Jumlah jenis kuas: " + jumlahKuas);
        System.out.println("Jumlah jenis cat: " + jumlahCat);
        System.out.println("Jumlah jenis alat lukis umum: " + jumlahUmum);
        
        // Display stok status
        System.out.println("\nStatus Stok Alat Lukis:");
        for (AlatLukis alat : daftarAlatLukis) {
            String status = alat.checkStock() ? "Tersedia" : "Habis";
            System.out.println("- " + alat.getNama() + ": " + status + " (Stok: " + alat.getStok() + ")");
        }
    }

    public static void tambahAlatLukis() {
        try {
            System.out.println("\nPilih jenis alat lukis:");
            System.out.println("1. Alat Lukis Umum");
            System.out.println("2. Cat");
            System.out.println("3. Kuas");
            int jenis = getIntInput("Masukkan pilihan (1-3): ");

            String nama = getStringInput("Masukkan nama alat lukis: ");
            int stok = getIntInput("Masukkan stok: ");
            double harga = getDoubleInput("Masukkan harga: ");

            if (stok < 0 || harga < 0) {
                throw new IllegalArgumentException("Stok dan harga tidak boleh negatif.");
            }

            System.out.println("\nDaftar Kategori yang Tersedia:");
            for (int i = 0; i < daftarKategori.size(); i++) {
                System.out.println((i + 1) + ". " + daftarKategori.get(i));
            }
            int pilihanKategori = getIntInput("Pilih nomor kategori atau ketik 0 untuk menambah kategori baru: ") - 1;

            Kategori kategori;
            if (pilihanKategori == -1) {
                String namaKategoriBaru = getStringInput("Masukkan nama kategori baru: ");
                kategori = new Kategori(namaKategoriBaru);
                daftarKategori.add(kategori);
                System.out.println("Kategori baru berhasil ditambahkan!");
            } else if (pilihanKategori >= 0 && pilihanKategori < daftarKategori.size()) {
                kategori = daftarKategori.get(pilihanKategori);
            } else {
                throw new IllegalArgumentException("Nomor kategori tidak valid.");
            }

            AlatLukis alatLukis;
            switch (jenis) {
                case 1:
                    alatLukis = new AlatLukisUmum(nama, stok, harga, kategori);
                    break;
                case 2:
                    String warna = getStringInput("Masukkan warna cat: ");
                    String jenisCat = getStringInput("Masukkan jenis cat (air/minyak/akrilik): ");
                    alatLukis = new Cat(nama, stok, harga, kategori, warna, jenisCat);
                    break;
                case 3:
                    String ukuran = getStringInput("Masukkan ukuran kuas: ");
                    String bahanBulu = getStringInput("Masukkan bahan bulu kuas: ");
                    alatLukis = new Kuas(nama, stok, harga, kategori, ukuran, bahanBulu);
                    break;
                default:
                    throw new IllegalArgumentException("Pilihan tidak valid.");
            }

            daftarAlatLukis.add(alatLukis);
            System.out.println("Alat lukis berhasil ditambahkan!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Error: Input tidak valid.");
            scanner.nextLine(); // Clear buffer
        }
    }

    static Kategori cariKategori(String namaKategori) {
        for (Kategori kategori : daftarKategori) {
            if (kategori.getNamaKategori().equalsIgnoreCase(namaKategori)) {
                return kategori;
            }
        }
        return null;
    }

    public static void lihatDaftarAlatLukis() {
        if (daftarAlatLukis.isEmpty()) {
            System.out.println("Daftar alat lukis kosong.");
        } else {
            String formatHeader = "| %-3s | %-20s | %-10s | %-12s | %-15s | %-20s | %-20s | %-10s |%n";
            String formatDivider = "+-----+----------------------+------------+--------------+-----------------+----------------------+----------------------+------------+%n";
            
            System.out.println("\n=== Daftar Alat Lukis ===");
            System.out.printf(formatDivider);
            System.out.printf(formatHeader, 
                "No", "Nama Alat Lukis", "Stok", "Harga", "Kategori", "Detail 1", "Detail 2", "Jenis");
            System.out.printf(formatDivider);
            
            String formatRow = "| %-3d | %-20s | %-10d | Rp%-10s | %-15s | %-20s | %-20s | %-10s |%n";
            
            int nomor = 1;
            for (AlatLukis alat : daftarAlatLukis) {
                String detail1 = "-";
                String detail2 = "-";
                String jenis = "Umum";
                
                if (alat instanceof Cat) {
                    Cat cat = (Cat) alat;
                    detail1 = "Warna: " + cat.getWarna();
                    detail2 = "Jenis: " + cat.getJenisCat();
                    jenis = "Cat";
                } else if (alat instanceof Kuas) {
                    Kuas kuas = (Kuas) alat;
                    detail1 = "Ukuran: " + kuas.getUkuran();
                    detail2 = "Bahan: " + kuas.getBahanBulu();
                    jenis = "Kuas";
                }
                
                String hargaFormatted = String.format("%,.2f", alat.getHarga()).replace(',', '.');
                
                System.out.printf(formatRow,
                    nomor++,
                    alat.getNama(),
                    alat.getStok(),
                    hargaFormatted,
                    alat.getKategori(),
                    detail1,
                    detail2,
                    jenis);
            }
            System.out.printf(formatDivider);
        }
    }

    public static void lihatDetailAlatLukis() {
        if (daftarAlatLukis.isEmpty()) {
            System.out.println("Daftar alat lukis kosong.");
            return;
        }

        try {
            System.out.println("\nDaftar Alat Lukis:");
            for (int i = 0; i < daftarAlatLukis.size(); i++) {
                System.out.println((i + 1) + ". " + daftarAlatLukis.get(i).getNama());
            }

            int index = getIntInput("Pilih nomor alat lukis untuk melihat detail: ") - 1;

            if (index >= 0 && index < daftarAlatLukis.size()) {
                AlatLukis alat = daftarAlatLukis.get(index);
                System.out.println("\n=== Detail Alat Lukis ===");
                System.out.println("Nama: " + alat.getNama());
                System.out.println("Stok: " + alat.getStok());
                System.out.println("Harga: Rp" + alat.getHarga());
                System.out.println("Kategori: " + alat.getKategori());
                System.out.println("Status stok: " + (alat.checkStock() ? "Tersedia" : "Habis"));
                System.out.println("Detail Info: " + alat.getDetailInfo());
            } else {
                System.out.println("Nomor tidak valid.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Input tidak valid.");
            scanner.nextLine(); // Clear buffer
        }
    }

    public static void updateAlatLukis() {
        if (daftarAlatLukis.isEmpty()) {
            System.out.println("Daftar alat lukis kosong.");
            return;
        }

        try {
            lihatDaftarAlatLukis();
            int index = getIntInput("Pilih nomor alat lukis yang ingin diupdate: ") - 1;

            if (index >= 0 && index < daftarAlatLukis.size()) {
                AlatLukis alatLukis = daftarAlatLukis.get(index);

                String nama = getStringInput("Masukkan nama baru (kosongkan jika tidak ingin mengubah): ");
                if (!nama.isEmpty()) {
                    alatLukis.updateInfo(nama); 
                }

                System.out.print("Masukkan stok baru (ketik -1 jika tidak ingin mengubah): ");
                int stok = scanner.nextInt();
                scanner.nextLine();
                if (stok != -1) {
                    alatLukis.updateInfo(stok); 
                }

                System.out.println("Harga tidak dapat diubah karena sudah dideklarasikan sebagai final.");

                System.out.println("\nDaftar Kategori yang Tersedia:");
                for (int i = 0; i < daftarKategori.size(); i++) {
                    System.out.println((i + 1) + ". " + daftarKategori.get(i));
                }
                int pilihanKategori = getIntInput("Pilih nomor kategori (ketik 0 jika tidak ingin mengubah): ") - 1;

                if (pilihanKategori >= 0 && pilihanKategori < daftarKategori.size()) {
                    Kategori kategori = daftarKategori.get(pilihanKategori);
                    alatLukis.setKategori(kategori);
                } else if (pilihanKategori != -1) {
                    System.out.println("Nomor kategori tidak valid. Kategori tidak diubah.");
                }

                updateSpecificProperties(alatLukis);

                System.out.println("Alat lukis berhasil diupdate!");
            } else {
                System.out.println("Nomor tidak valid.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Input tidak valid.");
            scanner.nextLine(); // Clear buffer
        }
    }

    // Helper method for updating specific properties based on type
    private static void updateSpecificProperties(AlatLukis alatLukis) {
        if (alatLukis instanceof Cat) {
            String warna = getStringInput("Masukkan warna baru (kosongkan jika tidak ingin mengubah): ");
            if (!warna.isEmpty()) {
                ((Cat) alatLukis).setWarna(warna);
            }

            String jenisCat = getStringInput("Masukkan jenis cat baru (kosongkan jika tidak ingin mengubah): ");
            if (!jenisCat.isEmpty()) {
                ((Cat) alatLukis).setJenisCat(jenisCat);
            }
        } else if (alatLukis instanceof Kuas) {
            String ukuran = getStringInput("Masukkan ukuran baru (kosongkan jika tidak ingin mengubah): ");
            if (!ukuran.isEmpty()) {
                ((Kuas) alatLukis).setUkuran(ukuran);
            }

            String bahanBulu = getStringInput("Masukkan bahan bulu baru (kosongkan jika tidak ingin mengubah): ");
            if (!bahanBulu.isEmpty()) {
                ((Kuas) alatLukis).setBahanBulu(bahanBulu);
            }
        }
    }

    public static void hapusAlatLukis() {
        if (daftarAlatLukis.isEmpty()) {
            System.out.println("Daftar alat lukis kosong.");
            return;
        }

        try {
            lihatDaftarAlatLukis();
            int index = getIntInput("Pilih nomor alat lukis yang ingin dihapus: ") - 1;

            if (index >= 0 && index < daftarAlatLukis.size()) {
                // Decrement the total count before removing
                AlatLukis.decrementTotalAlatLukis();
                daftarAlatLukis.remove(index);
                System.out.println("Alat lukis berhasil dihapus!");
            } else {
                System.out.println("Nomor tidak valid.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Input tidak valid.");
            scanner.nextLine(); // Clear buffer
        }
    }
}
import java.util.ArrayList;
import java.util.Scanner;

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

class AlatLukis {
    private String nama;
    private int stok;
    private double harga;
    private Kategori kategori; 
    public AlatLukis(String nama, int stok, double harga, Kategori kategori) {
        this.nama = nama;
        this.stok = stok;
        this.harga = harga;
        this.kategori = kategori;
    }

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
        this.stok = stok;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    @Override
    public String toString() {
        return String.format("| %-20s | %-10d | Rp%-10.2f | %-15s |", nama, stok, harga, kategori);
    }
}

public class ArtisanSuppliesManagement {
    private static ArrayList<AlatLukis> daftarAlatLukis = new ArrayList<>();
    private static ArrayList<Kategori> daftarKategori = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        daftarKategori.add(new Kategori("Kuas"));
        daftarKategori.add(new Kategori("Cat"));
        daftarKategori.add(new Kategori("Kanvas"));

        while (true) {
            System.out.println("\n=== Sistem Manajemen Penjualan dan Stok Alat Lukis ===");
            System.out.println("1. Tambah Alat Lukis");
            System.out.println("2. Lihat Daftar Alat Lukis");
            System.out.println("3. Update Alat Lukis");
            System.out.println("4. Hapus Alat Lukis");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); 

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
                    System.out.println("Terima kasih telah menggunakan sistem ini.");
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }

    private static void tambahAlatLukis() {
        System.out.print("Masukkan nama alat lukis: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan stok: ");
        int stok = scanner.nextInt();
        System.out.print("Masukkan harga: ");
        double harga = scanner.nextDouble();
        scanner.nextLine(); 

        System.out.print("Masukkan kategori alat lukis: ");
        String namaKategori = scanner.nextLine();

        Kategori kategori = cariKategori(namaKategori);
        if (kategori == null) {
            System.out.println("Kategori '" + namaKategori + "' tidak ditemukan.");
            System.out.print("Apakah Anda ingin menambahkan kategori baru? (y/n): ");
            String pilihan = scanner.nextLine();
            if (pilihan.equalsIgnoreCase("y")) {
                kategori = new Kategori(namaKategori);
                daftarKategori.add(kategori);
                System.out.println("Kategori '" + namaKategori + "' berhasil ditambahkan!");
            } else {
                System.out.println("Pilih kategori yang sudah ada:");
                for (int i = 0; i < daftarKategori.size(); i++) {
                    System.out.println((i + 1) + ". " + daftarKategori.get(i));
                }
                System.out.print("Pilih nomor kategori: ");
                int pilihanKategori = scanner.nextInt() - 1;
                scanner.nextLine(); 

                if (pilihanKategori >= 0 && pilihanKategori < daftarKategori.size()) {
                    kategori = daftarKategori.get(pilihanKategori);
                } else {
                    System.out.println("Nomor kategori tidak valid. Alat lukis tidak ditambahkan.");
                    return;
                }
            }
        }

        AlatLukis alatLukis = new AlatLukis(nama, stok, harga, kategori);
        daftarAlatLukis.add(alatLukis);
        System.out.println("Alat lukis berhasil ditambahkan!");
    }

    private static Kategori cariKategori(String namaKategori) {
        for (Kategori kategori : daftarKategori) {
            if (kategori.getNamaKategori().equalsIgnoreCase(namaKategori)) {
                return kategori;
            }
        }
        return null; 
    }

    private static void lihatDaftarAlatLukis() {
        if (daftarAlatLukis.isEmpty()) {
            System.out.println("Daftar alat lukis kosong.");
        } else {
            System.out.println("\n=== Daftar Alat Lukis ===");
            System.out.println("+----------------------+------------+--------------+-----------------+");
            System.out.println("| Nama Alat Lukis      | Stok       | Harga        | Kategori        |");
            System.out.println("+----------------------+------------+--------------+-----------------+");
            for (int i = 0; i < daftarAlatLukis.size(); i++) {
                System.out.println(daftarAlatLukis.get(i));
            }
            System.out.println("+----------------------+------------+--------------+-----------------+");
        }
    }

    private static void updateAlatLukis() {
        lihatDaftarAlatLukis();
        if (!daftarAlatLukis.isEmpty()) {
            System.out.print("Pilih nomor alat lukis yang ingin diupdate: ");
            int index = scanner.nextInt() - 1;
            scanner.nextLine(); 

            if (index >= 0 && index < daftarAlatLukis.size()) {
                System.out.print("Masukkan nama baru: ");
                String nama = scanner.nextLine();
                System.out.print("Masukkan stok baru: ");
                int stok = scanner.nextInt();
                System.out.print("Masukkan harga baru: ");
                double harga = scanner.nextDouble();
                scanner.nextLine(); 

                System.out.println("\nPilih Kategori Baru:");
                for (int i = 0; i < daftarKategori.size(); i++) {
                    System.out.println((i + 1) + ". " + daftarKategori.get(i));
                }
                System.out.print("Pilih nomor kategori: ");
                int pilihanKategori = scanner.nextInt() - 1;
                scanner.nextLine(); 

                if (pilihanKategori >= 0 && pilihanKategori < daftarKategori.size()) {
                    Kategori kategori = daftarKategori.get(pilihanKategori);
                    AlatLukis alatLukis = daftarAlatLukis.get(index);
                    alatLukis.setNama(nama);
                    alatLukis.setStok(stok);
                    alatLukis.setHarga(harga);
                    alatLukis.setKategori(kategori);
                    System.out.println("Alat lukis berhasil diupdate!");
                } else {
                    System.out.println("Nomor kategori tidak valid.");
                }
            } else {
                System.out.println("Nomor tidak valid.");
            }
        }
    }

    private static void hapusAlatLukis() {
        lihatDaftarAlatLukis();
        if (!daftarAlatLukis.isEmpty()) {
            System.out.print("Pilih nomor alat lukis yang ingin dihapus: ");
            int index = scanner.nextInt() - 1;
            scanner.nextLine();

            if (index >= 0 && index < daftarAlatLukis.size()) {
                daftarAlatLukis.remove(index);
                System.out.println("Alat lukis berhasil dihapus!");
            } else {
                System.out.println("Nomor tidak valid.");
            }
        }
    }
}
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
        if (stok >= 0) {
            this.stok = stok;
        } else {
            System.out.println("Stok tidak boleh negatif. Nilai stok tidak diubah.");
        }
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        if (harga >= 0) {
            this.harga = harga;
        } else {
            System.out.println("Harga tidak boleh negatif. Nilai harga tidak diubah.");
        }
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    @Override
    public String toString() {
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

    @Override
    public String toString() {
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

    @Override
    public String toString() {
        return String.format("| %-20s | %-10d | Rp%-10.2f | %-15s | %-15s | %-15s | %-10s |", 
            getNama(), getStok(), getHarga(), getKategori(), "Ukuran: " + ukuran, "Bahan: " + bahanBulu, "Kuas");
    }
}

public class ArtisanSuppliesManagement {
    protected static ArrayList<AlatLukis> daftarAlatLukis = new ArrayList<>();
    protected static ArrayList<Kategori> daftarKategori = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        daftarKategori.add(new Kategori("Kuas"));
        daftarKategori.add(new Kategori("Cat"));
        daftarKategori.add(new Kategori("Kanvas"));
        daftarKategori.add(new Kategori("Pensil"));

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

    public static void tambahAlatLukis() {
        System.out.println("\nPilih jenis alat lukis:");
        System.out.println("1. Alat Lukis Umum");
        System.out.println("2. Cat");
        System.out.println("3. Kuas");
        System.out.print("Masukkan pilihan (1-3): ");
        int jenis = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Masukkan nama alat lukis: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan stok: ");
        int stok = scanner.nextInt();
        System.out.print("Masukkan harga: ");
        double harga = scanner.nextDouble();
        scanner.nextLine();

        if (stok < 0 || harga < 0) {
            System.out.println("Stok dan harga tidak boleh negatif. Alat lukis tidak ditambahkan.");
            return;
        }

        System.out.println("\nDaftar Kategori yang Tersedia:");
        for (int i = 0; i < daftarKategori.size(); i++) {
            System.out.println((i + 1) + ". " + daftarKategori.get(i));
        }
        System.out.print("Pilih nomor kategori atau ketik 0 untuk menambah kategori baru: ");
        int pilihanKategori = scanner.nextInt() - 1;
        scanner.nextLine();

        Kategori kategori;
        if (pilihanKategori == -1) {
            System.out.print("Masukkan nama kategori baru: ");
            String namaKategoriBaru = scanner.nextLine();
            kategori = new Kategori(namaKategoriBaru);
            daftarKategori.add(kategori);
            System.out.println("Kategori baru berhasil ditambahkan!");
        } else if (pilihanKategori >= 0 && pilihanKategori < daftarKategori.size()) {
            kategori = daftarKategori.get(pilihanKategori);
        } else {
            System.out.println("Nomor kategori tidak valid. Alat lukis tidak ditambahkan.");
            return;
        }

        AlatLukis alatLukis;
        switch (jenis) {
            case 2:
                System.out.print("Masukkan warna cat: ");
                String warna = scanner.nextLine();
                System.out.print("Masukkan jenis cat (air/minyak/akrilik): ");
                String jenisCat = scanner.nextLine();
                alatLukis = new Cat(nama, stok, harga, kategori, warna, jenisCat);
                break;
            case 3:
                System.out.print("Masukkan ukuran kuas: ");
                String ukuran = scanner.nextLine();
                System.out.print("Masukkan bahan bulu kuas: ");
                String bahanBulu = scanner.nextLine();
                alatLukis = new Kuas(nama, stok, harga, kategori, ukuran, bahanBulu);
                break;
            default:
                alatLukis = new AlatLukis(nama, stok, harga, kategori);
        }

        daftarAlatLukis.add(alatLukis);
        System.out.println("Alat lukis berhasil ditambahkan!");
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
            System.out.println("\n=== Daftar Alat Lukis ===");
            System.out.println("+----------------------+------------+--------------+-----------------+---------------------+---------------------+------------+");
            System.out.println("| Nama Alat Lukis      | Stok       | Harga        | Kategori        | Detail 1            | Detail 2            | Jenis      |");
            System.out.println("+----------------------+------------+--------------+-----------------+---------------------+---------------------+------------+");
            for (AlatLukis alat : daftarAlatLukis) {
                System.out.println(alat);
            }
            System.out.println("+----------------------+------------+--------------+-----------------+---------------------+---------------------+------------+");
        }
    }

    public static void updateAlatLukis() {
        lihatDaftarAlatLukis();
        if (!daftarAlatLukis.isEmpty()) {
            System.out.print("Pilih nomor alat lukis yang ingin diupdate: ");
            int index = scanner.nextInt() - 1;
            scanner.nextLine();

            if (index >= 0 && index < daftarAlatLukis.size()) {
                AlatLukis alatLukis = daftarAlatLukis.get(index);

                System.out.print("Masukkan nama baru: ");
                String nama = scanner.nextLine();
                System.out.print("Masukkan stok baru: ");
                int stok = scanner.nextInt();
                System.out.print("Masukkan harga baru: ");
                double harga = scanner.nextDouble();
                scanner.nextLine();

                if (stok < 0 || harga < 0) {
                    System.out.println("Stok dan harga tidak boleh negatif. Data tidak diupdate.");
                    return;
                }

                System.out.println("\nDaftar Kategori yang Tersedia:");
                for (int i = 0; i < daftarKategori.size(); i++) {
                    System.out.println((i + 1) + ". " + daftarKategori.get(i));
                }
                System.out.print("Pilih nomor kategori: ");
                int pilihanKategori = scanner.nextInt() - 1;
                scanner.nextLine();

                if (pilihanKategori >= 0 && pilihanKategori < daftarKategori.size()) {
                    Kategori kategori = daftarKategori.get(pilihanKategori);
                    alatLukis.setNama(nama);
                    alatLukis.setStok(stok);
                    alatLukis.setHarga(harga);
                    alatLukis.setKategori(kategori);

                    if (alatLukis instanceof Cat) {
                        System.out.print("Masukkan warna baru: ");
                        String warna = scanner.nextLine();
                        System.out.print("Masukkan jenis cat baru: ");
                        String jenisCat = scanner.nextLine();
                        ((Cat) alatLukis).setWarna(warna);
                        ((Cat) alatLukis).setJenisCat(jenisCat);
                    } else if (alatLukis instanceof Kuas) {
                        System.out.print("Masukkan ukuran baru: ");
                        String ukuran = scanner.nextLine();
                        System.out.print("Masukkan bahan bulu baru: ");
                        String bahanBulu = scanner.nextLine();
                        ((Kuas) alatLukis).setUkuran(ukuran);
                        ((Kuas) alatLukis).setBahanBulu(bahanBulu);
                    }

                    System.out.println("Alat lukis berhasil diupdate!");
                } else {
                    System.out.println("Nomor kategori tidak valid.");
                }
            } else {
                System.out.println("Nomor tidak valid.");
            }
        }
    }

    public static void hapusAlatLukis() {
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
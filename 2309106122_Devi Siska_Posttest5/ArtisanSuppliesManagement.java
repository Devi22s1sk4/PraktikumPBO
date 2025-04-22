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

abstract class AlatLukis {
    private String nama;
    private int stok;
    private final double harga; 
    private Kategori kategori;

    public AlatLukis(String nama, int stok, double harga, Kategori kategori) {
        this.nama = nama;
        this.stok = stok;
        this.harga = harga;
        this.kategori = kategori;
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
            System.out.println("5. Lihat Detail Alat Lukis");
            System.out.println("6. Keluar");
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
                    lihatDetailAlatLukis();
                    break;
                case 6:
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
            case 1:
                alatLukis = new AlatLukisUmum(nama, stok, harga, kategori);
                break;
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
                System.out.println("Pilihan tidak valid. Alat lukis tidak ditambahkan.");
                return;
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

        System.out.println("\nDaftar Alat Lukis:");
        for (int i = 0; i < daftarAlatLukis.size(); i++) {
            System.out.println((i + 1) + ". " + daftarAlatLukis.get(i).getNama());
        }

        System.out.print("Pilih nomor alat lukis untuk melihat detail: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index >= 0 && index < daftarAlatLukis.size()) {
            AlatLukis alat = daftarAlatLukis.get(index);
            System.out.println("\n=== Detail Alat Lukis ===");
            System.out.println("Nama: " + alat.getNama());
            System.out.println("Stok: " + alat.getStok());
            System.out.println("Harga: Rp" + alat.getHarga());
            System.out.println("Kategori: " + alat.getKategori());
            
            System.out.println("Detail Info: " + alat.getDetailInfo());
        } else {
            System.out.println("Nomor tidak valid.");
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

                System.out.print("Masukkan nama baru (kosongkan jika tidak ingin mengubah): ");
                String nama = scanner.nextLine();
                if (!nama.isEmpty()) {
                    alatLukis.updateInfo(nama); 
                }

                System.out.print("Masukkan stok baru (ketik -1 jika tidak ingin mengubah): ");
                int stok = scanner.nextInt();
                if (stok != -1) {
                    alatLukis.updateInfo(stok); 
                }

                System.out.println("Harga tidak dapat diubah karena sudah dideklarasikan sebagai final.");

                System.out.println("\nDaftar Kategori yang Tersedia:");
                for (int i = 0; i < daftarKategori.size(); i++) {
                    System.out.println((i + 1) + ". " + daftarKategori.get(i));
                }
                System.out.print("Pilih nomor kategori (ketik 0 jika tidak ingin mengubah): ");
                int pilihanKategori = scanner.nextInt() - 1;
                scanner.nextLine();

                if (pilihanKategori >= 0 && pilihanKategori < daftarKategori.size()) {
                    Kategori kategori = daftarKategori.get(pilihanKategori);
                    alatLukis.setKategori(kategori);
                } else if (pilihanKategori != -1) {
                    System.out.println("Nomor kategori tidak valid. Kategori tidak diubah.");
                }

                if (alatLukis instanceof Cat) {
                    System.out.print("Masukkan warna baru (kosongkan jika tidak ingin mengubah): ");
                    String warna = scanner.nextLine();
                    if (!warna.isEmpty()) {
                        ((Cat) alatLukis).setWarna(warna);
                    }

                    System.out.print("Masukkan jenis cat baru (kosongkan jika tidak ingin mengubah): ");
                    String jenisCat = scanner.nextLine();
                    if (!jenisCat.isEmpty()) {
                        ((Cat) alatLukis).setJenisCat(jenisCat);
                    }
                } else if (alatLukis instanceof Kuas) {
                    System.out.print("Masukkan ukuran baru (kosongkan jika tidak ingin mengubah): ");
                    String ukuran = scanner.nextLine();
                    if (!ukuran.isEmpty()) {
                        ((Kuas) alatLukis).setUkuran(ukuran);
                    }

                    System.out.print("Masukkan bahan bulu baru (kosongkan jika tidak ingin mengubah): ");
                    String bahanBulu = scanner.nextLine();
                    if (!bahanBulu.isEmpty()) {
                        ((Kuas) alatLukis).setBahanBulu(bahanBulu);
                    }
                }

                System.out.println("Alat lukis berhasil diupdate!");
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
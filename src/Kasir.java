import java.util.Scanner;

public class Kasir {
    private Meja[] daftarMeja;
    private Menu[] daftarMenu;
    

    public Kasir() {
        daftarMeja = new Meja[10];
        for (int i = 0; i < 10; i++) {
            daftarMeja[i] = new Meja(i + 1);
        }

        daftarMenu = new Menu[5];
        daftarMenu[0] = new Menu("Nasi Goreng", 15000);
        daftarMenu[1] = new Menu("Mi Goreng", 15000);
        daftarMenu[2] = new Menu("Capcay", 20000);
        daftarMenu[3] = new Menu("Bihun Goreng", 17000);
        daftarMenu[4] = new Menu("Ayam Koloke", 25000);
    }

    public void tampilkanDaftarMeja() {
        System.out.println("Daftar meja :");
        for(int i = 0; i < 10; i++){
            System.out.print("Meja"+(i+1));
            if(daftarMeja[i].isKosong()==true){
                System.out.println(" (Kosong)");
            }else{
                System.out.println(" (Terisi oleh pelanggan "+daftarMeja[i].getNamaPelanggan()+")");
            }
        }
    }


    public void tambahPelanggan(int nomorMeja, Pelanggan pelanggan) {
        daftarMeja[nomorMeja].setPelanggan(pelanggan);
    }


    public void tambahPesanan(int nomorMeja, Menu menu) {
        daftarMeja[nomorMeja-1].setMenu(menu);
        
    }


    public void hapusPelanggan(int nomorMeja) {
        daftarMeja[nomorMeja-1].deleteAllMenu();
        daftarMeja[nomorMeja-1].setPelanggan(null);
    }

    public int hitungHargaPesanan(int nomorMeja) {
        int totalHarga = 0;
        Meja meja = daftarMeja[nomorMeja - 1];
        Pelanggan pelanggan = meja.getPelanggan();
        Menu[] menu = meja.getMenu();
        if (pelanggan != null && menu != null && menu.length > 0) {
            for (int i = 0; i < menu.length; i++) {
                if (menu[i] != null) {
                    totalHarga += menu[i].getHarga();
                }
            }
            return totalHarga;
        }
        return totalHarga;
    }

    public void tampilkanPesanan(int nomorMeja) {
        Meja meja = daftarMeja[nomorMeja - 1];
        Pelanggan pelanggan = meja.getPelanggan();
        Menu[] menu = meja.getMenu();
        if (pelanggan != null && menu != null && menu.length > 0) {
            for (int i = 0; i < menu.length; i++) {
                if (menu[i] != null) {
                    System.out.println("Meja " + nomorMeja + " - " + pelanggan.getNama() + " memesan "
                            + menu[i].getNama() + " seharga " + menu[i].getHarga());
                }
            }
        } else {
            System.out.println("Meja " + nomorMeja + " tidak memiliki pesanan");
        }
    }

    public void tampilkanDaftarMenu() {
        System.out.println("Daftar Menu:");
        System.out.println("1. Nasi Goreng - Rp15.000");
        System.out.println("2. Mi Goreng - Rp15.000");
        System.out.println("3. Capcay - Rp20.000");
        System.out.println("4. Bihun Goreng - Rp17.000");
        System.out.println("5. Ayam Koloke - Rp25.000");
        System.out.println("6. Simpan");
        System.out.println();
    }

    public void tampilkanDaftarFitur() {
        System.out.println("1. Tampilkan daftar meja");
        System.out.println("2. Tambah pelanggan");
        System.out.println("3. Tambah pesanan");
        System.out.println("4. Hapus pelanggan");
        System.out.println("5. Hitung harga pesanan");
        System.out.println("6. Tampilkan pesanan di meja");
        System.out.println("0. Keluar");
    }

    public void jalankan() {
        Scanner scanner = new Scanner(System.in);
        int pilihan = -1;
        while (pilihan != 0) {
            tampilkanDaftarFitur();
            System.out.print("Masukkan pilihan: ");
            pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                   tampilkanDaftarMeja();
                   break;
                case 2:
                    System.out.println("Masukan nomor meja: ");
                    int nomorMeja = scanner.nextInt();
                    if(daftarMeja[nomorMeja-1].getPelanggan()==null){
                        System.out.println("Nama pelanggan : ");
                        String namaPelanggan = scanner.next();
                        Pelanggan pelanggan = new Pelanggan(namaPelanggan);
                        tambahPelanggan(nomorMeja-1, pelanggan);
                    }else{
                        System.out.println("Meja telah terisi oleh "+daftarMeja[nomorMeja-1].getNamaPelanggan());
                    }
                    break;
                case 3:
                    boolean stopLoop = false;
                    System.out.print("Masukkan nomor meja: ");
                    int nomorMejaPesan = scanner.nextInt();
                    Boolean meja = daftarMeja[nomorMejaPesan - 1].isKosong();
                    scanner.nextLine();
                    if (!meja) {
                        tampilkanDaftarMenu();
                        while (!stopLoop) {
                            System.out.print("Masukkan nomor menu: ");
                            int nomorMenuPesan = scanner.nextInt();
                            scanner.nextLine();
                            switch (nomorMenuPesan) {
                                case 1:
                                    tambahPesanan(nomorMejaPesan, daftarMenu[0]);
                                    break;
                                case 2:
                                    tambahPesanan(nomorMejaPesan, daftarMenu[1]);
                                    break;
                                case 3:
                                    tambahPesanan(nomorMejaPesan, daftarMenu[2]);
                                    break;
                                case 4:
                                    tambahPesanan(nomorMejaPesan, daftarMenu[3]);
                                    break;
                                case 5:
                                    tambahPesanan(nomorMejaPesan, daftarMenu[4]);
                                    break;
                                case 6:
                                    stopLoop = true;
                                    break;
                                default:
                                    System.out.println("Nomor menu tidak valid");
                                    break;
                            }
                        }
                    } else {
                        System.out.println("Meja tidak ada pelanggan");
                    }
                    break;
                case 4:
                    System.out.print("Masukkan nomor meja: ");
                    int nomorMejaHapus = scanner.nextInt();
                        hapusPelanggan(nomorMejaHapus);
                    
                    
                    break;
                case 5:
                    System.out.print("Masukan nomor meja : ");
                    int nomorMejaTotal = scanner.nextInt();
                    
                    if(daftarMeja[nomorMejaTotal-1].getPelanggan() != null){
                        System.out.println("Total pesanan : " +hitungHargaPesanan(nomorMejaTotal));
                    }
                    break;
                case 6:
                    System.out.print("Masukan nomor meja : ");
                    int nomorMejaTampil = scanner.nextInt();
                    tampilkanPesanan(nomorMejaTampil);
                    break;

                case 0:
                    System.out.println("Terima kasih telah menggunakan aplikasi kasir restoran!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid");
                    break;
            }
            System.out.println();
        }
        scanner.close();
    }
}
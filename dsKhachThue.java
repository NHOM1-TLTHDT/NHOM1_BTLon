import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class dsKhachThue implements docdanhsach {
    private static HashMap<String, KhachThue> dsKhachThue = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public static HashMap<String, KhachThue> getDsKhachThue() {
        return dsKhachThue;
    }


    public void them() {
        System.out.print("Tên đăng nhập: ");
        String tenDangNhap = scanner.nextLine();
        String matKhau;
        while (true) {
            System.out.print("Mật khẩu: ");
            matKhau = scanner.nextLine();

            System.out.print("Xác minh mật khẩu: ");
            String xacNhanMatKhau = scanner.nextLine();

            if (matKhau.equals(xacNhanMatKhau)) {
                break;
            } else {
                System.out.println("Mật khẩu không khớp. Vui lòng nhập lại.");
            }
        }
        String idCCCD, ten, gioiTinh, sdt;
        int tuoi;
        System.out.println("Nhập thông tin khách thuê:");
        do {
            System.out.print("Số CCCD: ");
            idCCCD = scanner.nextLine();
            if(idCCCD.length()!=12){
                System.out.println("Số CCCD Không Đủ 12 Số. Vui Lòng Nhập Lại");
            }
        }
        while (idCCCD.length()!=12);
        System.out.print("Nhập Tên: ");
        ten = scanner.nextLine();
        do {
            System.out.print("Nhập Tuổi: ");
            tuoi = scanner.nextInt();
            if (tuoi<18||tuoi>=120)
                System.out.println("Tuổi Phải Từ 18->120. Nhập Lại!");
        }while (tuoi<18||tuoi>=120);
        scanner.nextLine();
        do {
            System.out.print("Giới tính (Nam, Nữ, Khác): ");
            gioiTinh = scanner.nextLine();
            if (!gioiTinh.equalsIgnoreCase("Nam") &&
                    !gioiTinh.equalsIgnoreCase("Nữ") &&
                    !gioiTinh.equalsIgnoreCase("Khác")) {
                System.out.println("Giới Tính Phải: Nam, Nữ, Hoặc Khác");
            }
        } while (!gioiTinh.equalsIgnoreCase("Nam") &&
                !gioiTinh.equalsIgnoreCase("Nữ") &&
                !gioiTinh.equalsIgnoreCase("Khác"));
        do {
            System.out.print("Số điện thoại: ");
            sdt = scanner.nextLine();
            if (!sdt.matches("0\\d{9,10}")) {
                System.out.println("Số điện thoại phải bắt đầu bằng 0 và có 10 hoặc 11 chữ số. Vui lòng nhập lại.");
            }
        } while (!sdt.matches("0\\d{9,10}"));



        TaiKhoan taiKhoan = new TaiKhoan(tenDangNhap, matKhau, "Khách thuê");
        KhachThue khachThue = new KhachThue(idCCCD, ten, tuoi, gioiTinh, sdt, taiKhoan);

        dsKhachThue.put(idCCCD, khachThue);
        System.out.println("Đã thêm khách thuê thành công.");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("khachthue.txt", true))) {
            writer.write(tenDangNhap + "," + matKhau + "," + idCCCD + "," + ten + "," + tuoi + "," + gioiTinh + "," + sdt);
            writer.newLine();
        } catch (IOException e) {System.out.println("Lỗi khi ghi vào file: " + e.getMessage());
        }
    }

    @Override
    public void docDanhSach() {
        try (BufferedReader reader = new BufferedReader(new FileReader("khachthue.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String tenDangNhap = data[0];
                String matKhau = data[1];
                String idCCCD = data[2];
                String ten = data[3];
                int tuoi = Integer.parseInt(data[4]);
                String gioiTinh = data[5];
                String sdt = data[6];

                TaiKhoan taiKhoan = new TaiKhoan(tenDangNhap, matKhau, "Chủ trọ");
                KhachThue khachThue = new KhachThue(idCCCD, ten, tuoi, gioiTinh, sdt, taiKhoan);

                dsKhachThue.put(idCCCD, khachThue);
            }

        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }
    }

    public void sua() {
        System.out.print("Nhập CCCD của khách thuê cần sửa: ");
        String idCCCD = scanner.nextLine();

        if (dsKhachThue.containsKey(idCCCD)) {
            KhachThue khachThue = dsKhachThue.get(idCCCD);

            System.out.print("Tên mới (bỏ trống nếu không đổi): ");
            String ten = scanner.nextLine();
            if (!ten.isEmpty()) khachThue.ten = ten;

            System.out.print("Tuổi mới (nhập -1 nếu không đổi): ");
            int tuoi = Integer.parseInt(scanner.nextLine());
            if (tuoi != -1) khachThue.tuoi = tuoi;

            System.out.print("Giới tính mới (bỏ trống nếu không đổi): ");
            String gioiTinh = scanner.nextLine();
            if (!gioiTinh.isEmpty()) khachThue.gioiTinh = gioiTinh;

            System.out.print("Số điện thoại mới (bỏ trống nếu không đổi): ");
            String sdt = scanner.nextLine();
            if (!sdt.isEmpty()) khachThue.sdt = sdt;

            System.out.println("Đã cập nhật thông tin khách thuê.");
        } else {
            System.out.println("Không tìm thấy khách thuê với CCCD này.");
        }
    }


    public void xoa() {
        System.out.print("Nhập CCCD của khách thuê cần xóa: ");
        String idCCCD = scanner.nextLine();

        if (dsKhachThue.containsKey(idCCCD)) {
            dsKhachThue.remove(idCCCD);
            System.out.println("Đã xóa khách thuê thành công.");
        } else {
            System.out.println("Không tìm thấy khách thuê với CCCD này.");
        }
    }


    public void timKiem() {
        System.out.print("Nhập CCCD của khách thuê cần tìm: ");
        String idCCCD = scanner.nextLine();

        if (dsKhachThue.containsKey(idCCCD)) {
            System.out.println(dsKhachThue.get(idCCCD));
        } else {
            System.out.println("Không tìm thấy khách thuê với CCCD này.");
        }
    }

    public void hienThiDanhSachKhachHang() {
        System.out.println("\n--- DANH SÁCH KHÁCH HÀNG ---");

        if (dsKhachThue.isEmpty()) {
            System.out.println("Danh sách khách hàng trống!");
            return;
        }

        for (KhachThue khachThue : dsKhachThue.values()) {
            khachThue.hienThiThongTin();
            System.out.println("----------------------------");
        }

    }
    public void dangKyThuePhong(dsChuTro dsChuTro) {
        System.out.print("Nhập CCCD của khách thuê: ");
        String idCCCD = scanner.nextLine();

        if (dsKhachThue.containsKey(idCCCD)) {
            KhachThue khachThue = dsKhachThue.get(idCCCD);

            System.out.print("Nhập mã chủ trọ: ");
            String maChuTro = scanner.nextLine();

            if (dsChuTro.getDsChuTro().containsKey(maChuTro)) {
                ChuTro chuTro = dsChuTro.getDsChuTro().get(maChuTro);
                chuTro.getDsPhongTro().hienThiDanhSachPhongTro();

                System.out.print("Nhập mã phòng muốn thuê: ");
                String soPhong = scanner.nextLine();

                if (chuTro.getDsPhongTro().getDsPhongTro().containsKey(soPhong)) {
                    PhongTro phongTro = chuTro.getDsPhongTro().getDsPhongTro().get(soPhong);

                    if (phongTro.getTrangThai()==false) {
                        System.out.println("Phòng đã được thuê. Vui lòng chọn phòng khác.");
                    } else {

                        System.out.println("Đã đăng ký thuê phòng " + soPhong + " thành công.");
                        phongTro.setTrangThai(false);

                    }

                } else {
                    System.out.println("Phòng không tồn tại.");
                }
            } else {
                System.out.println("Chủ trọ không tồn tại.");
            }
        } else {
            System.out.println("Khách thuê không tồn tại.");
        }
    }

}

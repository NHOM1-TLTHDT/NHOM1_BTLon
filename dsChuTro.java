import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class dsChuTro implements docdanhsach{


    private HashMap<String, ChuTro> dsChuTro = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);


    public HashMap<String, ChuTro> getDsChuTro() {
        return dsChuTro;
    }

    public void them() {
        String tenDangNhap;

        // Validate username
        while (true) {
            System.out.print("Tên đăng nhập: ");
            tenDangNhap = scanner.nextLine();
            if (tenDangNhap.trim().isEmpty()) {
                System.out.println("Tên đăng nhập không được để trống. Vui lòng nhập lại.");
            } else {
                break;
            }
        }
        String matKhau;
        while (true) {
            System.out.print("Mật khẩu: ");
            matKhau = scanner.nextLine();
            if (!kiemTraMatKhauManh(matKhau)) {
                System.out.println("Mật khẩu mới không đủ mạnh. Vui lòng thử lại.");
                return;
            }

            System.out.print("Xác minh mật khẩu: ");
            String xacNhanMatKhau = scanner.nextLine();

            if (matKhau.equals(xacNhanMatKhau)) {
                break;
            } else {
                System.out.println("Mật khẩu không khớp. Vui lòng nhập lại.");
            }
        }
        System.out.println("--Nhập thông tin chủ trọ:--");
        System.out.print("CCCD: ");
        String idCCCD = scanner.nextLine();
        System.out.print("Tên: ");
        String ten = scanner.nextLine();
        int tuoi = 0;
        boolean valid = false;
        while (!valid) {
            try {
                System.out.print("Nhập tuổi (18-120): ");
                tuoi = scanner.nextInt();
                scanner.nextLine();
                if (tuoi < 18 || tuoi > 120) {
                    System.out.println("Tuổi phải từ 18 đến 120. Nhập lại!");
                } else {
                    valid = true;
                }
            } catch (Exception e) {
                System.out.println("Lỗi: Vui lòng nhập tuổi chính xác!");
                scanner.nextLine(); // Clear the invalid input
            }
        }

        System.out.print("Giới tính: ");
        String gioiTinh = scanner.nextLine();
        System.out.print("Số điện thoại: ");
        String sdt = scanner.nextLine();

        TaiKhoan taiKhoan = new TaiKhoan(tenDangNhap, matKhau, "Chủ trọ");
        ChuTro chuTro = new ChuTro(idCCCD, ten, tuoi, gioiTinh, sdt, taiKhoan);

        dsChuTro.put(idCCCD, chuTro);
        System.out.println("Đã thêm chủ trọ thành công.");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("chutro.txt", true))) {
            writer.write(tenDangNhap + "," + matKhau + "," + idCCCD + "," + ten + "," + tuoi + "," + gioiTinh + "," + sdt);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi vào file: " + e.getMessage());
        }
    }
    private boolean kiemTraMatKhauManh(String matKhau) {
        // Kiểm tra độ dài tối thiểu
        if (matKhau.length() < 8) {
            return false;
        }

        // Biến kiểm tra các điều kiện
        boolean coChuHoa = false;
        boolean coChuThuong = false;
        boolean coSo = false;
        boolean coKyTuDacBiet = false;

        // Kiểm tra từng ký tự trong mật khẩu bằng for với chỉ số
        for (int i = 0; i < matKhau.length(); i++) {
            char c = matKhau.charAt(i); // Lấy ký tự tại vị trí i
            if (Character.isUpperCase(c)) coChuHoa = true;
            else if (Character.isLowerCase(c)) coChuThuong = true;
            else if (Character.isDigit(c)) coSo = true;
            else if (!Character.isLetterOrDigit(c)) coKyTuDacBiet = true;

            // Nếu tất cả điều kiện được thỏa mãn, không cần kiểm tra thêm
            if (coChuHoa && coChuThuong && coSo && coKyTuDacBiet) {
                return true;
            }
        }

        // Trả về kết quả kiểm tra
        return coChuHoa && coChuThuong && coSo && coKyTuDacBiet;
    }


    @Override
    public void docDanhSach() {
        try (BufferedReader reader = new BufferedReader(new FileReader("chutro.txt"))) {
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
                ChuTro chuTro = new ChuTro(idCCCD, ten, tuoi, gioiTinh, sdt, taiKhoan);

                dsChuTro.put(idCCCD, chuTro);
            }

        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }
    }


    public void sua() {
        System.out.print("Nhập CCCD của chủ trọ cần sửa: ");
        String idCCCD = scanner.nextLine();

        if (dsChuTro.containsKey(idCCCD)) {
            ChuTro chuTro = dsChuTro.get(idCCCD);

            System.out.print("Tên mới (bỏ trống nếu không đổi): ");
            String ten = scanner.nextLine();
            if (!ten.isEmpty()) chuTro.ten = ten;

            System.out.print("Tuổi mới (nhập -1 nếu không đổi): ");
            int tuoi = Integer.parseInt(scanner.nextLine());
            if (tuoi != -1) chuTro.tuoi = tuoi;

            System.out.print("Giới tính mới (bỏ trống nếu không đổi): ");
            String gioiTinh = scanner.nextLine();
            if (!gioiTinh.isEmpty()) chuTro.gioiTinh = gioiTinh;

            System.out.print("Số điện thoại mới (bỏ trống nếu không đổi): ");
            String sdt = scanner.nextLine();
            if (!sdt.isEmpty()) chuTro.sdt = sdt;

            System.out.println("Đã cập nhật thông tin chủ trọ.");
        } else {
            System.out.println("Không tìm thấy chủ trọ với CCCD này.");
        }
    }


    public void xoa() {
        System.out.print("Nhập CCCD của chủ trọ cần xóa: ");
        String idCCCD = scanner.nextLine();

        if (dsChuTro.containsKey(idCCCD)) {
            dsChuTro.remove(idCCCD);
            System.out.println("Đã xóa chủ trọ thành công.");
        } else {
            System.out.println("Không tìm thấy chủ trọ với CCCD này.");
        }
    }



    public void hienThiTatCaPhongTro() {
        if (dsChuTro.isEmpty()) {
            System.out.println("Không có phòng trọ nào nào trong hệ thống.");
            return;
        }

        System.out.println("\n--- DANH SÁCH TẤT CẢ PHÒNG TRỌ ---");
        for (ChuTro chuTro : dsChuTro.values()) {
            System.out.println("\nChủ trọ: " + chuTro.getTen());
            System.out.println("Danh sách phòng trọ:");

            dsPhongTro dsPhongTroChuTro = chuTro.getDsPhongTro();
            if (dsPhongTroChuTro != null) {
                dsPhongTroChuTro.hienThiDanhSachPhongTro();
            } else {
                System.out.println("Chưa có phòng trọ nào.");
            }
            System.out.println("-----------------------------");
        }
    }
    public ChuTro dangNhap(String tenDangNhap,String matKhau) {

        for (ChuTro chuTro : dsChuTro.values()) {
            TaiKhoan taiKhoan = chuTro.getTaiKhoanChu();
            if (taiKhoan != null && taiKhoan.getTenDangNhap().equals(tenDangNhap) && taiKhoan.getMatKhau().equals(matKhau)) {

                return chuTro;
            }
        }


        return null;
    }
    public void menuSauDangNhap(ChuTro chuTro) {
        boolean isLoggedIn = true;

        while (isLoggedIn) {
            System.out.println("\n--- MENU CHỨC NĂNG ---");
            System.out.println("1. Xem thông tin tài khoản");
            System.out.println("2. Sửa thông tin tài khoản");
            System.out.println("3. Xóa tài khoản");
            System.out.println("4. Quản lý phong trọ và hợp đồng! ");
            System.out.print("Chọn chức năng: ");
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    chuTro.hienThiThongTin();
                    break;

                case 2:
                    sua();
                    break;
                case 3:
                    System.out.print("Bạn có chắc muốn xóa tài khoản? (y/n): ");
                    String confirm = scanner.nextLine();
                    if (confirm.equalsIgnoreCase("y")) {
                        xoa();
                        System.out.println("Tài khoản đã được xóa.");
                        isLoggedIn = false;
                    }
                    break;

                case 4:
                    isLoggedIn = false;
                    System.out.println("Chức năng");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }
        }
    }



}

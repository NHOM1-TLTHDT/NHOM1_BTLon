import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        dsChuTro quanLyChuTro = new dsChuTro();
        dsKhachThue quanLyKhachThue = new dsKhachThue();
        Scanner scanner = new Scanner(System.in);

        int doiTuong=0;
        boolean cc=true;
        while(cc) {
            do {
                System.out.println("\n--- QUẢN LÝ NHÀ TRỌ ---");
                System.out.println("1. Chức năng Chủ trọ");
                System.out.println("2. Chức năng Khách thuê");
                System.out.println("0. Thoát");

                boolean valid = false;
                while (!valid) {
                    try {
                        System.out.print("Nhập lựa chọn: ");
                        doiTuong = scanner.nextInt();
                        scanner.nextLine();
                        valid = true;
                    } catch (Exception e) {
                        System.out.println("Lỗi: Vui lòng nhập một số nguyên hợp lệ!");
                        scanner.nextLine();
                    }
                }
                if (doiTuong == 0) {
                    System.out.println("Thoát chương trình. Tạm biệt!");
                    break;
                }
                int chon = 0;
                switch (doiTuong) {
                    case 1:
                        do {
                            System.out.println("\n--- CHỨC NĂNG CHỦ TRỌ ---");
                            System.out.println("1. Đăng ký tài khoản chủ trọ");
                            System.out.println("2. Đăng nhập");
                            System.out.println("3. Hiện thị tất cả các phòng ");


                            System.out.println("0. Thoát");
                            valid = false;
                            while (!valid) {
                                try {
                                    System.out.print("Nhập lựa chọn: ");
                                    chon = scanner.nextInt();
                                    scanner.nextLine();
                                    valid = true;
                                } catch (Exception e) {
                                    System.out.println("Lỗi: Vui lòng nhập một số nguyên hợp lệ!");
                                    scanner.nextLine();
                                }
                            }

                            switch (chon) {
                                case 1: // Thêm
                                    quanLyChuTro.them();
                                    break;

                                case 2:
                                    quanLyChuTro.docDanhSach();
                                    System.out.print("Tên đăng nhập: ");
                                    String tenDangNhap = scanner.nextLine();

                                    System.out.print("Mật khẩu: ");
                                    String matKhau = scanner.nextLine();

                                    quanLyChuTro.dangNhap(tenDangNhap, matKhau);
                                    ChuTro chuTrodn = quanLyChuTro.dangNhap(tenDangNhap, matKhau);
                                    if (chuTrodn != null) {

                                        quanLyChuTro.menuSauDangNhap(chuTrodn);
                                    }


                                    ChuTro chuTroDangNhap = null;


                                    for (ChuTro chuTro : quanLyChuTro.getDsChuTro().values()) {
                                        TaiKhoan taiKhoan = chuTro.getTaiKhoanChu();
                                        if (taiKhoan.getTenDangNhap().equals(tenDangNhap) && taiKhoan.getMatKhau().equals(matKhau)) {
                                            chuTroDangNhap = chuTro;
                                            break;
                                        }
                                    }

                                    if (chuTroDangNhap != null) {
                                        int luaChonPhongTro = 0, luaChonHopDong = 0;
                                        chuTroDangNhap.getDsPhongTro().docDanhSach(tenDangNhap);

                                        do {
                                            System.out.println("1. Phòng trọ");
                                            System.out.println("2. Hơp đồng");
                                            System.out.println("0. Đăng xuất! ");


                                            valid = false;  // Biến kiểm tra nhập liệu hợp lệ
                                            while (!valid) {
                                                try {
                                                    System.out.print("Nhập lựa chọn: ");
                                                    chon = scanner.nextInt();
                                                    scanner.nextLine();
                                                    valid = true;
                                                } catch (Exception e) {
                                                    System.out.println("Lỗi: Vui lòng nhập một số nguyên hợp lệ!");
                                                    scanner.nextLine();
                                                }
                                            }
                                            switch (chon) {
                                                case 1:
                                                    do {
                                                        System.out.println("\n--- QUẢN LÝ PHÒNG TRỌ ---");
                                                        System.out.println("1. Thêm phòng trọ");
                                                        System.out.println("2. Sửa phòng trọ");
                                                        System.out.println("3. Xóa phòng trọ");
                                                        System.out.println("4. Hiển thị danh sách phòng trọ");
                                                        System.out.println("0. Thoát");

                                                        valid = false;
                                                        while (!valid) {
                                                            try {
                                                                System.out.print("Nhập lựa chọn: ");
                                                                luaChonPhongTro = scanner.nextInt();
                                                                scanner.nextLine();
                                                                valid = true;
                                                            } catch (Exception e) {
                                                                System.out.println("Lỗi: Vui lòng nhập một số nguyên hợp lệ!");
                                                                scanner.nextLine();
                                                            }
                                                        }
                                                        switch (luaChonPhongTro) {
                                                            case 1:

                                                                if (chuTroDangNhap.getDsPhongTro() != null) {
                                                                    System.out.println("Nhập thông tin phòng trọ:");
                                                                    System.out.print("Mã phòng trọ: ");
                                                                    String soPhong = scanner.nextLine();
                                                                    chuTroDangNhap.getDsPhongTro().them(soPhong, chuTroDangNhap.idCCCD);
                                                                } else {
                                                                    System.out.println("Danh sách phòng trọ chưa được khởi tạo.");
                                                                }


                                                                break;

                                                            case 2: // Sửa phòng trọ

                                                                chuTroDangNhap.getDsPhongTro().sua();

                                                                break;
                                                            case 3:
                                                                chuTroDangNhap.getDsPhongTro().xoa();

                                                            case 4:
                                                                chuTroDangNhap.getDsPhongTro().hienThiDanhSachPhongTro();
                                                                break;
                                                            case 0:
                                                                System.out.println("Thoát quản lý phòng trọ.");
                                                                break;

                                                            default:
                                                                System.out.println("Chức năng không hợp lệ. Vui lòng thử lại!");
                                                        }
                                                    } while (luaChonPhongTro != 0);
                                                    break;
                                                case 2:
                                                    do {
                                                        System.out.println("\n--- QUẢN LÝ HỢP ĐỒNG ---");
                                                        System.out.println("1. Thêm hợp đồng");
                                                        System.out.println("2. Sửa hợp đồng");
                                                        System.out.println("3. Xóa hợp đồng");
                                                        System.out.println("4. In hợp đồng");
                                                        System.out.println("5. Hiện thị danh sách hợp đồng");
                                                        System.out.println("6. Tìm kiếm hợp đồng");
                                                        System.out.println("0. Quay lại ");


                                                        valid = false;
                                                        while (!valid) {
                                                            try {
                                                                System.out.print("Nhập lựa chọn: ");
                                                                luaChonHopDong = scanner.nextInt();
                                                                scanner.nextLine();
                                                                valid = true;
                                                            } catch (Exception e) {
                                                                System.out.println("Lỗi: Vui lòng nhập một số nguyên hợp lệ!");
                                                                scanner.nextLine();  // Đọc bỏ dòng chứa lỗi để tránh vòng lặp vô hạn
                                                            }
                                                        }
                                                        switch (luaChonHopDong) {
                                                            case 1:
                                                                chuTroDangNhap.getdSHopDong().themHopDong();
                                                                break;
                                                            case 2:
                                                                chuTroDangNhap.getdSHopDong().suaHopDong();
                                                                break;
                                                            case 3:
                                                                chuTroDangNhap.getdSHopDong().xoaHopDong();
                                                                break;
                                                            case 4:
                                                                chuTroDangNhap.getdSHopDong().inHopDong();
                                                                break;
                                                            case 5:
                                                                chuTroDangNhap.getdSHopDong().hienThiDanhSachHopDong();
                                                                break;
                                                            case 6:
                                                                chuTroDangNhap.getdSHopDong().timKiemHopDong();
                                                                break;
                                                            case 7 :
                                                                chuTroDangNhap.getdSHopDong().tinhTien();
                                                                break;

                                                            case 0:
                                                                System.out.println("Thoát quản lý phòng trọ.");
                                                                break;

                                                            default:
                                                                System.out.println("Chức năng không hợp lệ. Vui lòng thử lại!");
                                                        }


                                                    } while (luaChonHopDong != 0);
                                                case 3:
                                                    cc = true;

                                            }

                                        } while (chon != 0);
                                        cc = false;
                                    } else {
                                        System.out.println("Đăng nhập thất bại. Kiểm tra lại tên đăng nhập hoặc mật khẩu.");
                                    }


                                    break;
                                case 3:
                                    quanLyChuTro.hienThiTatCaPhongTro();
                                    break;


                                case 0:
                                    System.out.println("Thoát chức năng quản lý Chủ trọ.");
                                    break;

                                default:
                                    System.out.println("Chức năng không hợp lệ. Vui lòng thử lại!");
                            }
                        } while (chon != 0);
                        break;
                    case 2:
                        quanLyChuTro.hienThiTatCaPhongTro();
                        do {
                            System.out.println("\n--- CHỨC NĂNG KHÁCH THUÊ ---");
                            System.out.println("1. Đăng ký tài khoản khách thuê ");
                            System.out.println("2. Sửa");
                            System.out.println("3. Xóa");
                            System.out.println("4. Tìm kiếm");
                            System.out.println("5. Hiển thị thông tin ");
                            System.out.println("6. Đăng nhập tài khoản sử dụng");
                            System.out.println("0. Thoát");
                            valid = false;
                            while (!valid) {
                                try {
                                    System.out.print("Nhập lựa chọn: ");
                                    chon = scanner.nextInt();
                                    scanner.nextLine();
                                    valid = true;
                                } catch (Exception e) {
                                    System.out.println("Lỗi: Vui lòng nhập một số nguyên hợp lệ!");
                                    scanner.nextLine();
                                }
                            }
                            switch (chon) {
                                case 1:
                                    quanLyKhachThue.them();
                                    break;

                                case 2:
                                    quanLyKhachThue.sua();
                                    break;

                                case 3:
                                    quanLyKhachThue.xoa();
                                    break;

                                case 4:
                                    quanLyKhachThue.timKiem();
                                    break;

                                case 5:
                                    quanLyKhachThue.hienThiDanhSachKhachHang();
                                    break;
                                case 6:
                                    quanLyKhachThue.docDanhSach();
                                    System.out.println("Đăng nhập tài khoản:");
                                    System.out.print("Nhập tên đăng nhập: ");
                                    String tenDangNhap = scanner.nextLine();
                                    System.out.print("Nhập mật khẩu: ");
                                    String matKhau = scanner.nextLine();
//
                                    KhachThue khachThueDangNhap = null;


                                    for (KhachThue khachThue : quanLyKhachThue.getDsKhachThue().values()) {
                                        TaiKhoan taiKhoan = khachThue.getTaiKhoanKhach();
                                        if (taiKhoan.getTenDangNhap().equals(tenDangNhap) && taiKhoan.getMatKhau().equals(matKhau)) {
                                            khachThueDangNhap = khachThue;
                                            break;
                                        }
                                    }
                                    if (khachThueDangNhap != null) {
                                        System.out.println("Đăng nhập thành công!");
                                    }


                                    quanLyChuTro.hienThiTatCaPhongTro();
                                    do {
                                        System.out.println("\n--- CHỨC NĂNG KHÁCH THUÊ ---");
                                        System.out.println("1. Đăng ký thuê phòng trọ");
                                        System.out.println("2.hien thi thong tin ");
                                        System.out.println("0. Thoát!");

                                        valid = false;
                                        while (!valid) {
                                            try {
                                                System.out.print("Nhập lựa chọn: ");
                                                chon = scanner.nextInt();
                                                scanner.nextLine();
                                                valid = true;
                                            } catch (Exception e) {
                                                System.out.println("Lỗi: Vui lòng nhập một số nguyên hợp lệ!");
                                                scanner.nextLine();
                                            }
                                        }

                                        switch (chon) {
                                            case 1:
                                                quanLyKhachThue.dangKyThuePhong(quanLyChuTro);
                                                break;

                                            case 2:
                                                khachThueDangNhap.hienThiThongTin();
                                                break;

                                            case 0:
                                                System.out.println("Thoát chức năng quản lý Khách thuê.");
                                                break;

                                            default:
                                                System.out.println("Chức năng không hợp lệ. Vui lòng thử lại!");
                                        }
                                    } while (chon != 0);
                                    break;
                                case 0:
                                    System.out.println("Thoát chức năng quản lý Khách thuê.");
                                    break;

                                default:
                                    System.out.println("Chức năng không hợp lệ. Vui lòng thử lại!");
                            }
                        } while (chon != 0);
                        break;
                    default:
                        System.out.println("Chức năng không hợp lệ. Vui lòng thử lại!");

                }

            } while (doiTuong != 0);
        }

        System.out.println("Chương trình đã kết thúc.");
    }
}

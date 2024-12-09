import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        dSHopDong danhSachHopDong = new dSHopDong();
        int luaChon;

        do {
            System.out.println("\n--- QUẢN LÝ HỢP ĐỒNG ---");
            System.out.println("1. Thêm hợp đồng");
            System.out.println("2. Sửa hợp đồng");
            System.out.println("3. Xóa hợp đồng");
            System.out.println("4. Tìm kiếm hợp đồng");
            System.out.println("5. Hiển thị danh sách hợp đồng");
            System.out.println("6. In hợp đồng");
            System.out.println("7. Tính tiền nhà");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            luaChon = Integer.parseInt(scanner.nextLine());

            switch (luaChon) {
                case 1:
                    danhSachHopDong.themHopDong();
                    break;
                case 2:
                    danhSachHopDong.suaHopDong();
                    break;
                case 3:
                    danhSachHopDong.xoaHopDong();
                    break;
                case 4:
                    danhSachHopDong.timKiemHopDong();
                    break;
                case 5:
                    danhSachHopDong.hienThiDanhSachHopDong();
                    break;
                case 6:
                    danhSachHopDong.inHopDong();
                    break;
                case 7:
                    danhSachHopDong.tinhTien();
                    break;
                case 0:
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        } while (luaChon != 0);

        scanner.close();
    }
}
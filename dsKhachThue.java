import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class dsKhachThue   {
    private static HashMap<String, KhachThue> dsKhachThue = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public static HashMap<String, KhachThue> getDsKhachThue() {
        return dsKhachThue;
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


}

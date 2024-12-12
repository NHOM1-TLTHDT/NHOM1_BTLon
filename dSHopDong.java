import java.io.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class dSHopDong {
    private HashMap<Integer, HopDong> dsHopDong = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public void themHopDong() {
        System.out.println("Nhập thông tin hợp đồng:");

        // Kiểm tra mã hợp đồng
        int maHD;
        while (true) {
            System.out.print("Mã hợp đồng: ");
            String input = scanner.nextLine();
            if (input.trim().isEmpty()) {
                System.out.println("Mã hợp đồng không được để trống. Vui lòng nhập lại.");
                continue;
            }
            maHD = Integer.parseInt(input);

            // Kiểm tra xem mã hợp đồng đã tồn tại chưa
            if (dsHopDong.containsKey(maHD)) {
                System.out.println("Mã hợp đồng đã tồn tại. Vui lòng nhập mã khác.");
            } else {
                break; // Thoát khỏi vòng lặp nếu nhập hợp lệ
            }
        }

        // Kiểm tra tên khách hàng
        String tenKH;
        while (true) {
            System.out.print("Tên khách hàng: ");
            tenKH = scanner.nextLine();
            if (tenKH.trim().isEmpty()) {
                System.out.println("Tên khách hàng không được để trống. Vui lòng nhập lại.");
            } else {
                break; // Thoát khỏi vòng lặp nếu nhập hợp lệ
            }
        }

        // Kiểm tra địa chỉ
        String diaChi;
        while (true) {
            System.out.print("Địa chỉ: ");
            diaChi = scanner.nextLine();
            if (diaChi.trim().isEmpty()) {
                System.out.println("Địa chỉ không được để trống. Vui lòng nhập lại.");
            } else {
                break; // Thoát khỏi vòng lặp nếu nhập hợp lệ
            }
        }

        Date ngayBatDau = null;
        Date ngayKetThuc = null;

        // Kiểm tra ngày bắt đầu
        while (ngayBatDau == null) {
            System.out.print("Ngày bắt đầu (dd/MM/yyyy): ");
            ngayBatDau = parseDate(scanner.nextLine());
            if (ngayBatDau == null) {
                System.out.println("Ngày bắt đầu không hợp lệ. Vui lòng nhập lại.");
            }
        }

        // Kiểm tra ngày kết thúc
        while (ngayKetThuc == null) {
            System.out.print("Ngày kết thúc (dd/MM/yyyy): ");
            ngayKetThuc = parseDate(scanner.nextLine());
            if (ngayKetThuc == null) {
                System.out.println("Ngày kết thúc không hợp lệ. Vui lòng nhập lại.");
            } else if (ngayKetThuc.before(ngayBatDau)) {
                System.out.println("Ngày kết thúc phải sau ngày bắt đầu. Vui lòng nhập lại.");
                ngayKetThuc = null; // Đặt lại để yêu cầu nhập lại
            }
        }

        double tienNha = 0.0;
        // Kiểm tra tiền nhà
        while (true) {
            System.out.print("Tiền nhà mỗi tháng: ");
            try {
                tienNha = Double.parseDouble(scanner.nextLine());
                if (tienNha <= 0) {
                    System.out.println("Tiền nhà phải lớn hơn 0. Vui lòng nhập lại.");
                } else {
                    break; // Thoát khỏi vòng lặp nếu nhập hợp lệ
                }
            } catch (NumberFormatException e) {
                System.out.println("Giá trị không hợp lệ. Vui lòng nhập lại.");
            }
        }

        // Tạo hợp đồng mới với tiền nhà được nhập
        HopDong hopDong = new HopDong(maHD, tenKH, diaChi, ngayBatDau, ngayKetThuc, tienNha);
        dsHopDong.put(maHD, hopDong);
        System.out.println("Đã thêm hợp đồng thành công.");
    }
    public void suaHopDong() {
        System.out.print("Nhập mã hợp đồng cần sửa: ");
        int maHD = Integer.parseInt(scanner.nextLine());

        if (dsHopDong.containsKey(maHD)) {
            HopDong hopDong = dsHopDong.get(maHD);

            System.out.print("Tên khách hàng mới (bỏ trống nếu không đổi): ");
            String tenKH = scanner.nextLine();
            if (!tenKH.isEmpty()) hopDong.setTenKhachHang(tenKH);

            System.out.print("Địa chỉ mới (bỏ trống nếu không đổi): ");
            String diaChi = scanner.nextLine();
            if (!diaChi.isEmpty()) hopDong.setDiaChi(diaChi);

            System.out.print("Ngày bắt đầu mới (dd/MM/yyyy, bỏ trống nếu không đổi): ");
            String ngayBatDau = scanner.nextLine();
            if (!ngayBatDau.isEmpty()) hopDong.setNgayBatDau(parseDate(ngayBatDau));

            System.out.print("Ngày kết thúc mới (dd/MM/yyyy, bỏ trống nếu không đổi): ");
            String ngayKetThuc = scanner.nextLine();
            if (!ngayKetThuc.isEmpty()) hopDong.setNgayKetThuc(parseDate(ngayKetThuc));

            System.out.print("Tiền nhà mới (bỏ trống nếu không đổi): ");
            String tienNha = scanner.nextLine();
            if (!tienNha.isEmpty()) hopDong.setSoTienNha(Double.parseDouble(tienNha));

            System.out.println("Đã cập nhật thông tin hợp đồng.");
        } else {
            System.out.println("Không tìm thấy hợp đồng với mã này.");
        }
    }

    public void xoaHopDong() {
        System.out.print("Nhập mã hợp đồng cần xóa: ");
        int maHD = Integer.parseInt(scanner.nextLine());

        if (dsHopDong.containsKey(maHD)) {
            dsHopDong.remove(maHD);
            System.out.println("Đã xóa hợp đồng thành công.");
        } else {
            System.out.println("Không tìm thấy hợp đồng với mã này.");
        }
    }

    public void timKiemHopDong() {
        System.out.print("Nhập mã hợp đồng cần tìm: ");
        int maHD = Integer.parseInt(scanner.nextLine());

        if (dsHopDong.containsKey(maHD)) {
            System.out.println(dsHopDong.get(maHD));
        } else {
            System.out.println("Không tìm thấy hợp đồng với mã này.");
        }
    }

    public void hienThiDanhSachHopDong() {
        System.out.println("\n--- DANH SÁCH HỢP ĐỒNG ---");

        if (dsHopDong.isEmpty()) {
            System.out.println("Danh sách hợp đồng trống!");
            return;
        }

        for (HopDong hopDong : dsHopDong.values()) {
            System.out.println(hopDong);
            System.out.println("----------------------------");
        }
    }

    public void inHopDong() {
        System.out.print("Nhập mã hợp đồng cần in: ");
        int maHD = Integer.parseInt(scanner.nextLine());

        if (dsHopDong.containsKey(maHD)) {
            HopDong hopDong = dsHopDong.get(maHD);
            System.out.println(hopDong);


            try (BufferedWriter writer = new BufferedWriter(new FileWriter("hopdong_" + maHD + ".txt",true))) {
                writer.write(hopDong.toString());
                System.out.println("Đã xuất hợp đồng ra file hopdong_" + maHD + ".txt");
            } catch (IOException e) {
                System.out.println("Lỗi khi xuất file: " + e.getMessage());
            }
        } else {
            System.out.println("Không tìm thấy hợp đồng với mã này.");
        }
    }

    public void tinhTien() {
        System.out.print("Nhập mã hợp đồng cần tính tiền: ");
        int maHD = Integer.parseInt(scanner.nextLine());

        if (dsHopDong.containsKey(maHD)) {
            HopDong hopDong = dsHopDong.get(maHD);
            double tongTien = hopDong.tinhTongTien();

            NumberFormat formatter = NumberFormat.getInstance(new Locale("vi", "VN"));

            System.out.printf("Tổng tiền cho hợp đồng mã %d là: %s VND\n", maHD, formatter.format(tongTien));
        } else {
            System.out.println("Không tìm thấy hợp đồng với mã này.");
        }
    }

    private Date parseDate(String dateStr) {
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Ngày không hợp lệ. Vui lòng nhập lại.");
            return null;
        }
    }


}
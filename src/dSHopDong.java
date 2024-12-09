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
        System.out.print("Mã hợp đồng: ");
        int maHD = Integer.parseInt(scanner.nextLine());
        System.out.print("Tên khách hàng: ");
        String tenKH = scanner.nextLine();
        System.out.print("Địa chỉ: ");
        String diaChi = scanner.nextLine();
        System.out.print("Ngày bắt đầu (dd/MM/yyyy): ");
        Date ngayBatDau = parseDate(scanner.nextLine());
        System.out.print("Ngày kết thúc (dd/MM/yyyy): ");
        Date ngayKetThuc = parseDate(scanner.nextLine());

        // Tạo hợp đồng mới với tiền nhà mặc định
        HopDong hopDong = new HopDong(maHD, tenKH, diaChi, ngayBatDau, ngayKetThuc);
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

            // Xuất thông tin hợp đồng ra file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("hopdong_" + maHD + ".txt"))) {
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

    public void luuDanhSachHopDong(String tenFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tenFile))) {
            for (HopDong hopDong : dsHopDong.values()) {
                writer.write(hopDong.getMaHopDong() + "," + hopDong.getTenKhachHang() + "," +
                        hopDong.getDiaChi() + "," + hopDong.getNgayBatDau().getTime() + "," +
                        hopDong.getNgayKetThuc().getTime() + "\n");
            }
            System.out.println("Đã lưu danh sách hợp đồng vào file.");
        } catch (IOException e) {
            System.out.println("Lỗi khi lưu file: " + e.getMessage());
        }
    }

    public void docDanhSachHopDong(String tenFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(tenFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int maHD = Integer.parseInt(data[0]);
                String tenKH = data[1];
                String diaChi = data[2];
                Date ngayBatDau = new Date(Long.parseLong(data[3]));
                Date ngayKetThuc = new Date(Long.parseLong(data[4]));

                HopDong hopDong = new HopDong(maHD, tenKH, diaChi, ngayBatDau, ngayKetThuc);
                dsHopDong.put(maHD, hopDong);
            }
            System.out.println("Đã đọc danh sách hợp đồng từ file.");
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }
    }
}
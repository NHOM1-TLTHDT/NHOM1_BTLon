import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class dsPhongTro {
    private HashMap<String, PhongTro> dsPhongTro = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);

    public void them(String soPhong, String idCCCD) {
        String tenPhong = "";
        boolean validTenPhong = false;
        while (!validTenPhong) {
            try {
                System.out.print("Tên phòng: ");
                tenPhong = scanner.nextLine();
                if (tenPhong.trim().isEmpty()) {
                    throw new IllegalArgumentException("Tên phòng không được để trống.");
                }
                if (tenPhong.length() < 2) {
                    throw new IllegalArgumentException("Tên phòng phải có ít nhất 2 ký tự.");
                }
                validTenPhong = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        String diaChi = "";
        boolean validDiaChi = false;
        while (!validDiaChi) {
            try {
                System.out.print("Địa chỉ: ");
                diaChi = scanner.nextLine();
                if (diaChi.trim().isEmpty()) {
                    throw new IllegalArgumentException("Địa chỉ không được để trống.");
                }
                validDiaChi = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        double area = 0;
        boolean validDienTich = false;
        while (!validDienTich) {
            try {
                System.out.print("Diện tích: ");
                String dienTich = scanner.nextLine();
                area = Double.parseDouble(dienTich);
                if (area <= 0) {
                    throw new IllegalArgumentException("Diện tích phải lớn hơn 0.");
                }
                validDienTich = true;
            } catch (NumberFormatException e) {
                System.out.println("Diện tích phải là một số hợp lệ.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        double gia = 0;
        boolean validGia = false;
        while (!validGia) {
            try {
                System.out.print("Giá: ");
                gia = Double.parseDouble(scanner.nextLine());
                if (gia <= 0) {
                    throw new IllegalArgumentException("Giá phải lớn hơn 0.");
                }
                validGia = true;
            } catch (NumberFormatException e) {
                System.out.println("Giá phải là một số hợp lệ.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        PhongTro phongTro = new PhongTro(soPhong, tenPhong, diaChi, String.valueOf(area), gia, true);

        dsPhongTro.put(soPhong, phongTro);

        System.out.println("Đã thêm phòng trọ thành công.");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("phongtro.txt", true))) {
            writer.write(soPhong + "," + tenPhong + "," + diaChi + "," + area + "," + gia + "," + idCCCD);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Lỗi khi ghi vào file: " + e.getMessage());
        }
    }



    public void docDanhSach(String idChuTroHienTai) {
        try (BufferedReader reader = new BufferedReader(new FileReader("phongtro.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String soPhong = data[0];
                String tenPhong = data[1];
                String diaChi = data[2];
                String dienTich = data[3];
                double gia = Double.parseDouble(data[4]);
                String idCCCD = data[5]; // ID của chủ trọ

                // Kiểm tra nếu ID chủ trọ trong file khớp với ID chủ trọ hiện tại
                if (idCCCD.equals(idChuTroHienTai)) {
                    PhongTro phongTro = new PhongTro(soPhong, tenPhong, diaChi, dienTich, gia, true);
                    dsPhongTro.put(soPhong, phongTro);
                }
            }
            System.out.println("Đã đọc danh sách phòng trọ từ file.");
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
        }
    }

    public void sua() {
        System.out.print("Nhập mã phòng của phòng trọ cần sửa: ");
        String soPhong = scanner.nextLine();

        if (dsPhongTro.containsKey(soPhong)) {
            PhongTro phongTro = dsPhongTro.get(soPhong);

            System.out.print("Tên phòng mới (bỏ trống nếu không đổi): ");
            String tenPhong = scanner.nextLine();
            if (!tenPhong.isEmpty()) phongTro.tenPhong = tenPhong;

            System.out.print("Địa chỉ mới (bỏ trống nếu không đổi): ");
            String diaChi = scanner.nextLine();
            if (!diaChi.isEmpty()) phongTro.diaChi = diaChi;

            System.out.print("Diện tích mới (bỏ trống nếu không đổi): ");
            String dienTich = scanner.nextLine();
            if (!dienTich.isEmpty()) phongTro.dienTich = dienTich;

            System.out.print("Giá mới (nhập -1 nếu không đổi): ");
            double gia = Double.parseDouble(scanner.nextLine());
            if (gia != -1) phongTro.gia = gia;

            System.out.print("Trạng thái mới (bỏ trống nếu không đổi): ");
            String input = scanner.nextLine();

            if (!input.isEmpty()) {
                phongTro.trangThai = Boolean.parseBoolean(input);
            }


            System.out.println("Đã cập nhật thông tin phòng trọ.");
        } else {
            System.out.println("Không tìm thấy phòng trọ với mã phòng này.");
        }
    }

    public void xoa() {
        System.out.print("Nhập mã phòng của phòng trọ cần xóa: ");
        String soPhong = scanner.nextLine();

        if (dsPhongTro.containsKey(soPhong)) {
            dsPhongTro.remove(soPhong);
            System.out.println("Đã xóa phòng trọ thành công.");
        } else {
            System.out.println("Không tìm thấy phòng trọ với mã phòng này.");
        }
    }


    public void hienThiDanhSachPhongTro() {
        System.out.println("\n--- DANH SÁCH PHÒNG TRỌ ---");

        if (dsPhongTro.isEmpty()) {
            System.out.println("Danh sách phòng trọ trống!");
            return;
        }

        for (PhongTro phongTro : dsPhongTro.values()) {
            phongTro.hienThiThongTin();
            System.out.println("----------------------------");
        }
    }

    public HashMap<String, PhongTro> getDsPhongTro() {
        return dsPhongTro;
    }
}

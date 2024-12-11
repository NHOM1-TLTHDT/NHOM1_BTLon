import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DS_PhongTro dsPhongTro = new DS_PhongTro();

        int choice;
        do {
            System.out.println("\n--- QUẢN LÝ PHÒNG TRỌ ---");
            System.out.println("1. Thêm phòng trọ");
            System.out.println("2. Hiển thị danh sách phòng trọ");
            System.out.println("3. Tìm phòng trọ theo số phòng");
            System.out.println("4. Xóa phòng trọ theo số phòng");
            System.out.println("5. Cập nhật thông tin phòng trọ");
            System.out.println("6. Thoát");
            System.out.print("Chọn chức năng: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Nhập số phòng: ");
                    String soPhong = scanner.nextLine();
                    System.out.print("Nhập tên phòng: ");
                    String tenPhong = scanner.nextLine();
                    System.out.print("Nhập địa chỉ: ");
                    String diaChi = scanner.nextLine();
                    System.out.print("Nhập diện tích: ");
                    String dienTich = scanner.nextLine();
                    System.out.print("Nhập giá: ");
                    double gia = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Nhập trạng thái (Trống/Đã thuê): ");
                    String trangThai = scanner.nextLine();

                    PhongTro phongTro = new PhongTro(soPhong, tenPhong, diaChi, dienTich, gia, trangThai);
                    dsPhongTro.them(phongTro);
                }
                case 2 -> {
                    System.out.println("\n--- DANH SÁCH PHÒNG TRỌ ---");
                    dsPhongTro.hienThiDanhSach();
                }
                case 3 -> {
                    System.out.print("Nhập số phòng cần tìm: ");
                    String soPhong = scanner.nextLine();
                    PhongTro phongTro = dsPhongTro.timKiem(soPhong);
                    if (phongTro != null) {
                        System.out.println("Thông tin phòng trọ:");
                        System.out.println(phongTro);
                    } else {
                        System.out.println("Không tìm thấy phòng trọ với số phòng: " + soPhong);
                    }
                }
                case 4 -> {
                    System.out.print("Nhập số phòng cần xóa: ");
                    String soPhong = scanner.nextLine();
                    dsPhongTro.xoa(soPhong);
                }
                case 5 -> {
                    System.out.print("Nhập số phòng cần cập nhật: ");
                    String soPhong = scanner.nextLine();
                    PhongTro phongTroCu = dsPhongTro.timKiem(soPhong);
                    if (phongTroCu != null) {
                        System.out.print("Nhập tên phòng mới: ");
                        String tenPhong = scanner.nextLine();
                        System.out.print("Nhập địa chỉ mới: ");
                        String diaChi = scanner.nextLine();
                        System.out.print("Nhập diện tích mới: ");
                        String dienTich = scanner.nextLine();
                        System.out.print("Nhập giá mới: ");
                        double gia = scanner.nextDouble();
                        scanner.nextLine(); // Đọc ký tự xuống dòng
                        System.out.print("Nhập trạng thái mới (Trống/Đã thuê): ");
                        String trangThai = scanner.nextLine();

                        PhongTro phongTroMoi = new PhongTro(soPhong, tenPhong, diaChi, dienTich, gia, trangThai);
                        dsPhongTro.sua(soPhong, phongTroMoi);
                    } else {
                        System.out.println("Không tìm thấy phòng trọ với số phòng: " + soPhong);
                    }
                }
                case 6 -> System.out.println("Chương trình kết thúc. Cảm ơn đã sử dụng!");
                default -> System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        } while (choice != 6);

        scanner.close();
    }
}

import java.util.HashMap;

public class DS_PhongTro {
    private HashMap<String, PhongTro> dsPhongTro;

    public DS_PhongTro() {
        this.dsPhongTro = new HashMap<>();
    }

    public void them(PhongTro phongTro) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (phongTro == null) {
                throw new IllegalArgumentException("Phòng trọ không được null.");
            }

            if (phongTro.getSoPhong() == null || phongTro.getSoPhong().isEmpty()) {
                throw new IllegalArgumentException("Số phòng không được để trống.");
            }

            if (phongTro.getGia() <= 0) {
                throw new IllegalArgumentException("Giá phòng phải là số dương.");
            }

            if (!phongTro.getTrangThai().equals("Trống") &&
                    !phongTro.getTrangThai().equals("Đã thuê") &&
                    !phongTro.getTrangThai().equals("Đang sửa chữa")) {
                throw new IllegalArgumentException("Trạng thái phải là 'Trống', 'Đã thuê' hoặc 'Đang sửa chữa'.");
            }

            // Kiểm tra nếu phòng trọ đã tồn tại
            if (dsPhongTro.containsKey(phongTro.getSoPhong())) {
                System.out.println("Phòng trọ đã tồn tại!");
            } else {
                dsPhongTro.put(phongTro.getSoPhong(), phongTro);
                System.out.println("Thêm phòng trọ thành công!");
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Lỗi: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Lỗi không xác định: " + e.getMessage());
        }
    }

    public void sua(String soPhong, PhongTro phongTroMoi) {
        if (dsPhongTro.containsKey(soPhong)) {
            dsPhongTro.put(soPhong, phongTroMoi);
            System.out.println("Cập nhật thông tin phòng trọ thành công!");
        } else {
            System.out.println("Không tìm thấy phòng trọ với số phòng: " + soPhong);
        }
    }

    public void xoa(String soPhong) {
        if (dsPhongTro.containsKey(soPhong)) {
            dsPhongTro.remove(soPhong);
            System.out.println("Xóa phòng trọ thành công!");
        } else {
            System.out.println("Không tìm thấy phòng trọ với số phòng: " + soPhong);
        }
    }

    public PhongTro timKiem(String soPhong) {
        return dsPhongTro.getOrDefault(soPhong, null);
    }

    public void hienThiDanhSach() {
        for (PhongTro phongTro : dsPhongTro.values()) {
            phongTro.hienThiThongTin();
            System.out.println("-------------------------");
        }
    }
}


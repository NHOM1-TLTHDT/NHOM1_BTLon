public class PhongTro {
    private String soPhong;
    private String tenPhong;
    private String diaChi;
    private String dienTich;
    private double gia;
    private String trangThai;

    public PhongTro(String soPhong, String tenPhong, String diaChi, String dienTich, double gia, String trangThai) {
        this.soPhong = soPhong;
        this.tenPhong = tenPhong;
        this.diaChi = diaChi;
        this.dienTich = dienTich;
        this.gia = gia;
        this.trangThai = trangThai;
    }

    public String getSoPhong() {
        return soPhong;
    }

    public void setSoPhong(String soPhong) {
        this.soPhong = soPhong;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getDienTich() {
        return dienTich;
    }

    public void setDienTich(String dienTich) {
        this.dienTich = dienTich;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public void hienThiThongTin() {
        System.out.println("Số phòng: " + soPhong);
        System.out.println("Tên phòng: " + tenPhong);
        System.out.println("Địa chỉ: " + diaChi);
        System.out.println("Diện tích: " + dienTich);
        System.out.println("Giá: " + gia);
        System.out.println("Trạng thái: " + trangThai);
    }
    @Override
    public String toString() {
        return "PhongTro{" +
                "Số phòng='" + soPhong + '\'' +
                ", Tên phòng='" + tenPhong + '\'' +
                ", Địa chỉ='" + diaChi + '\'' +
                ", Diện tích='" + dienTich + '\'' +
                ", Giá=" + gia +
                ", Trạng thái='" + trangThai + '\'' +
                '}';
    }
}

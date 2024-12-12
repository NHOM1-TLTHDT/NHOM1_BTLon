import java.text.DecimalFormat;

public class PhongTro {
    private String soPhong;
    protected String tenPhong;
    protected String diaChi;
    protected String dienTich;
    protected double gia;
    protected boolean trangThai = true;
    DecimalFormat df = new DecimalFormat("#,###.##");

    public PhongTro(String soPhong, String tenPhong, String diaChi, String dienTich, double gia, boolean trangThai) {
        this.soPhong = soPhong;
        this.tenPhong = tenPhong;
        this.diaChi = diaChi;
        this.dienTich = dienTich;
        this.gia = gia;
        this.trangThai = trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public void hienThiThongTin() {
        System.out.printf("%-15s: %s%n", "Mã phòng", soPhong);
        System.out.printf("%-15s: %s%n", "Tên phòng", tenPhong);
        System.out.printf("%-15s: %s%n", "Địa chỉ", diaChi);
        System.out.printf("%-15s: %s m²%n", "Diện tích", dienTich);
        System.out.printf("%-15s: %s VND%n", "Giá", df.format(gia));
        System.out.printf("%-15s: %s%n", "Trạng thái", trangThai ? "Còn Trống" : "Đã Thuê");
    }

    @Override
    public String toString() {
        return "PhongTro{" +
                "Mã phòng='" + soPhong + '\'' +
                ", Tên phòng='" + tenPhong + '\'' +
                ", Địa chỉ='" + diaChi + '\'' +
                ", Diện tích='" + dienTich + '\'' +
                ", Giá=" + df.format(gia) +
                ", Trạng thái='" + (trangThai ? "Còn Trống" : "Đã Thuê") + '\'' +
                '}';
    }

    public boolean getTrangThai() {
        return trangThai;
    }
}
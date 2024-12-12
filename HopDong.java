import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HopDong implements tienNha {
    private int maHopDong;
    private String tenKhachHang;
    private String diaChi;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private double soTienNha;

    public HopDong(int maHopDong, String tenKhachHang, String diaChi, Date ngayBatDau, Date ngayKetThuc, double soTienNha) {
        this.maHopDong = maHopDong;
        this.tenKhachHang = tenKhachHang;
        this.diaChi = diaChi;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.soTienNha = soTienNha;
    }

    public int getMaHopDong() {
        return maHopDong;
    }

    public void setMaHopDong(int maHopDong) {
        this.maHopDong = maHopDong;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public double getSoTienNha() {
        return soTienNha;
    }

    public void setSoTienNha(double soTienNha) {
        this.soTienNha = soTienNha;
    }

    public double tinhTongTien() {

        if (ngayKetThuc.before(ngayBatDau)) {
            return 0;
        }


        long millis = ngayKetThuc.getTime() - ngayBatDau.getTime();
        long days = millis / (1000 * 60 * 60 * 24);


        int soThang = (int) (days / 30); // 30 ngày cho một tháng
        int soNgay = (int) (days % 30); // Số ngày còn lại


        return soThang * soTienNha + (soNgay > 0 ? soTienNha / 30 * soNgay : 0);
    }

    @Override
    public double tinhTienNha(double soTienNha) {
        return soTienNha; // Trả về số tiền nhà
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("vi", "VN"));

        return String.format("Hợp Đồng:\n" +
                        "Mã Hợp Đồng: %d\n" +
                        "Tên Khách Hàng: %s\n" +
                        "Địa Chỉ: %s\n" +
                        "Ngày Bắt Đầu: %s\n" +
                        "Ngày Kết Thúc: %s\n" +
                        "Tiền Nhà mỗi tháng: %s\n" +
                        "Tổng Tiền: %s\n",
                maHopDong, tenKhachHang, diaChi,
                sdf.format(ngayBatDau), sdf.format(ngayKetThuc),
                numberFormat.format(soTienNha),
                numberFormat.format(tinhTongTien()));
    }
}
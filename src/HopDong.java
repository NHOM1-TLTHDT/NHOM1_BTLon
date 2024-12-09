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
    private static final double TIEN_NHA_MOI_THANG = 3000000; // Tiền nhà mặc định mỗi tháng

    public HopDong(int maHopDong, String tenKhachHang, String diaChi, Date ngayBatDau, Date ngayKetThuc) {
        this.maHopDong = maHopDong;
        this.tenKhachHang = tenKhachHang;
        this.diaChi = diaChi;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
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


    public double tinhTongTien() {
        // Nếu ngày kết thúc trước ngày bắt đầu, trả về 0
        if (ngayKetThuc.before(ngayBatDau)) {
            return 0;
        }

        // Tính số tháng và số ngày
        int soThang = 0;

        // Lấy tháng và năm
        int startMonth = ngayBatDau.getMonth();
        int endMonth = ngayKetThuc.getMonth();
        int startYear = ngayBatDau.getYear();
        int endYear = ngayKetThuc.getYear();

        // Tính số tháng đầy đủ
        soThang = (endYear - startYear) * 12 + (endMonth - startMonth);

        // Xử lý ngày bắt đầu
        if (ngayBatDau.getDate() > 1) {
            soThang--; // Nếu không phải ngày đầu tháng, không tính tháng bắt đầu
        }

        // Xử lý ngày kết thúc
        if (ngayKetThuc.getDate() < 1) {
            soThang--; // Nếu không phải ngày cuối tháng, không tính tháng kết thúc
        } else if (ngayKetThuc.getDate() >= 1 && ngayKetThuc.getDate() < 30) {
            // Thêm một tháng nếu ngày kết thúc nằm trong tháng kết thúc
            soThang++;
        }

        // Đảm bảo không có tháng âm
        soThang = Math.max(0, soThang);

        return soThang * TIEN_NHA_MOI_THANG; // Tổng tiền là số tháng nhân với tiền nhà mỗi tháng
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
                numberFormat.format(TIEN_NHA_MOI_THANG),
                numberFormat.format(tinhTongTien()));
    }
}
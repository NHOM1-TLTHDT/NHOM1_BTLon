public class KhachThue extends Nguoi {
    private TaiKhoan taiKhoanKhach;
    private dsPhongTro dsPhongTro;

    public KhachThue(String idCCCD, String ten, int tuoi, String gioiTinh, String sdt, TaiKhoan taiKhoanKhach) {
        super(idCCCD, ten, tuoi, gioiTinh, sdt);
        this.taiKhoanKhach = taiKhoanKhach;
        this.dsPhongTro= dsPhongTro;
    }

    @Override
    public String getIdCCCD() {
        return super.getIdCCCD();
    }

    public dsPhongTro getDsPhongTro() {
        if (this.dsPhongTro == null) {
            this.dsPhongTro = new dsPhongTro(); // Khởi tạo nếu null
        }
        return dsPhongTro;
    }
    public TaiKhoan getTaiKhoanKhach() {
        return taiKhoanKhach;
    }

    public void setTaiKhoanKhach(TaiKhoan taiKhoanKhach) {
        this.taiKhoanKhach = taiKhoanKhach;
    }

    @Override
    public String toString() {
        return "KhachThue{" +
                "idCCCD='" + idCCCD + '\'' +
                ", ten='" + ten + '\'' +
                ", tuoi=" + tuoi +
                ", gioiTinh='" + gioiTinh + '\'' +
                ", sdt='" + sdt + '\'' +
                ", taiKhoanKhach=" + taiKhoanKhach +
                '}';
    }

    @Override
    public void hienThiThongTin() {
        if (taiKhoanKhach != null) {
            System.out.println("Tài khoản:");
            System.out.println("  Tên đăng nhập: " + taiKhoanKhach.getTenDangNhap());
            System.out.println("  Vai trò: " + taiKhoanKhach.getVaiTro());
            System.out.println("Thông tin Khách Thuê:");
            System.out.println("CCCD: " + idCCCD);
            System.out.println("Tên: " + ten);
            System.out.println("Tuổi: " + tuoi);
            System.out.println("Giới tính: " + gioiTinh);
            System.out.println("Số điện thoại: " + sdt);
        } else {
            System.out.println("Tài khoản: Chưa đăng ký");
        }
    }
}

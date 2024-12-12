public class ChuTro extends Nguoi {
    private TaiKhoan taiKhoanChu;
    private dsPhongTro dsPhongTro;
    private  dSHopDong dSHopDong;

    public ChuTro(String idCCCD, String ten, int tuoi, String gioiTinh, String sdt, TaiKhoan taiKhoanChu) {
        super(idCCCD, ten, tuoi, gioiTinh, sdt);
        this.taiKhoanChu = taiKhoanChu;
        this.dsPhongTro = new dsPhongTro();
        this.dSHopDong=new dSHopDong();
    }
    public dsPhongTro getDsPhongTro() {
        if (this.dsPhongTro == null) {
            this.dsPhongTro = new dsPhongTro();
        }
        return dsPhongTro;
    }
    public dSHopDong getdSHopDong(){
        if(this.dSHopDong==null)
            this.dSHopDong=new dSHopDong();
        return dSHopDong;

    }


    public TaiKhoan getTaiKhoanChu() {
        return taiKhoanChu;
    }

    @Override
    public String toString() {
        return "ChuTro{" +
                "idCCCD='" + idCCCD + '\'' +
                ", ten='" + ten + '\'' +
                ", tuoi=" + tuoi +
                ", gioiTinh='" + gioiTinh + '\'' +
                ", sdt='" + sdt + '\'' +
                ", taiKhoanChu=" + taiKhoanChu +
                '}';
    }

    @Override
    public String getIdCCCD() {
        return super.getIdCCCD();
    }

    @Override
    public void hienThiThongTin() {
        if (taiKhoanChu != null) {
            System.out.println("Tài khoản:");
            System.out.printf("  %-15s: %s%n", "Tên đăng nhập", taiKhoanChu.getTenDangNhap());
            System.out.printf("  %-15s: %s%n", "Vai trò", taiKhoanChu.getVaiTro());
            System.out.println("Thông tin Chủ Trọ:");
            System.out.printf("%-15s: %s%n", "CCCD", idCCCD);
            System.out.printf("%-15s: %s%n", "Tên", ten);
            System.out.printf("%-15s: %d%n", "Tuổi", tuoi);
            System.out.printf("%-15s: %s%n", "Giới tính", gioiTinh);
            System.out.printf("%-15s: %s%n", "Số điện thoại", sdt);
        } else {
            System.out.println("Tài khoản: Chưa đăng ký");
        }
    }
}

public class KhachThue extends Nguoi {


    public KhachThue(String idCCCD, String ten, int tuoi, String gioiTinh, String sdt) {
        super(idCCCD, ten, tuoi, gioiTinh, sdt);
    }

    @Override
    public String getIdCCCD() {
        return super.getIdCCCD();
    }

    @Override
    public String toString() {
        return "KhachThue{" +
                "idCCCD='" + idCCCD + '\'' +
                ", ten='" + ten + '\'' +
                ", tuoi=" + tuoi +
                ", gioiTinh='" + gioiTinh + '\'' +
                ", sdt='" + sdt + '\'' +
                '}';
    }

    @Override
    public void hienThiThongTin() {
        System.out.println("Tài khoản:");
        System.out.println("Thông tin Khách Thuê:");
        System.out.println("CCCD: " + idCCCD);
        System.out.println("Tên: " + ten);
        System.out.println("Tuổi: " + tuoi);
        System.out.println("Giới tính: " + gioiTinh);
        System.out.println("Số điện thoại: " + sdt);
        System.out.println("Tài khoản: Chưa đăng ký");
    }
}


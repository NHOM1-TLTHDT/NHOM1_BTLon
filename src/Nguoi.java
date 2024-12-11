public abstract class Nguoi {
    protected String idCCCD;
    protected String ten;
    protected int tuoi;
    protected String gioiTinh;
    protected String sdt;

    public Nguoi(String idCCCD, String ten, int tuoi, String gioiTinh, String sdt) {
        setIdCCCD(idCCCD);
        setTen(ten);
        setTuoi(tuoi);
        setGioiTinh(gioiTinh);
        setSdt(sdt);
    }

    public String getTen() {
        return ten;
    }

    public String getIdCCCD() {
        return idCCCD;
    }

    public int getTuoi() {
        return tuoi;
    }


    public void setIdCCCD(String idCCCD) {
        if (idCCCD == null || idCCCD.isEmpty() || !idCCCD.matches("\\d+")) {
            throw new IllegalArgumentException("CCCD phải là số và không được để trống.");
        }
        this.idCCCD = idCCCD;
    }

    // Ràng buộc cho tên
    public void setTen(String ten) {
        if (ten == null || ten.isEmpty()) {
            throw new IllegalArgumentException("Tên không được để trống.");
        }
        this.ten = ten;
    }

    public void setTuoi(int tuoi) {
        if (tuoi < 0) {
            throw new IllegalArgumentException("Tuổi không thể là số âm.");
        }
        this.tuoi = tuoi;
    }

    // Ràng buộc cho giới tính
    public void setGioiTinh(String gioiTinh) {
        if (gioiTinh == null || gioiTinh.isEmpty()) {
            throw new IllegalArgumentException("Giới tính không được để trống.");
        }
        this.gioiTinh = gioiTinh;
    }

    public void setSdt(String sdt) {
        if (sdt == null || sdt.isEmpty() || !sdt.matches("\\d{10}")) {
            throw new IllegalArgumentException("Số điện thoại phải là 10 chữ số.");
        }
        this.sdt = sdt;
    }

    public abstract void hienThiThongTin();
}
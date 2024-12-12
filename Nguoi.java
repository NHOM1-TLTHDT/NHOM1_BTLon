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
        this.idCCCD = idCCCD;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public abstract void hienThiThongTin();
}
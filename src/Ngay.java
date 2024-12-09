public class Ngay {
    private int ngay;
    private int thang;
    private int nam;

    public Ngay(int ngay, int thang, int nam) {
        if (isValidDate(ngay, thang, nam)) {
            this.ngay = ngay;
            this.thang = thang;
            this.nam = nam;
        } else {
            throw new IllegalArgumentException("Ngày không hợp lệ");
        }
    }

    public int getNgay() {
        return ngay;
    }

    public int getThang() {
        return thang;
    }

    public int getNam() {
        return nam;
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%d", ngay, thang, nam);
    }

    private boolean isValidDate(int ngay, int thang, int nam) {
        if (nam < 1 || thang < 1 || thang > 12 || ngay < 1) {
            return false;
        }
        int[] daysInMonth = {31, isLeapYear(nam) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return ngay <= daysInMonth[thang - 1];
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
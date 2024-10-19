public class BarCode {

    public String code;

    public BarCode(String s) {
        code = s;
    }

    @Override
    public int hashCode() {
        int b = 31;
        int mod = 115249;
        long hash = 0;
        for (int i = 0; i < code.length(); i++) {
            //System.out.print('x');
            //System.out.println(binPower(b, i, mod));
            hash += (binPower(b, i, mod) * (code.charAt(i) - '0')) % mod;
        }

        return (int)hash;
    }

    public boolean equals(BarCode a) {
        boolean eqs = true;
        if (a.code.length() != code.length()) {
            eqs = false;
        }
        for (int i = 0; i < code.length(); i++) {
            if (a.code.charAt(i) != code.charAt(i)) {
                eqs = false;
            }
        }
        return eqs;
    }

    private long binPower(int x, int y, int mod) {
        if (y == 0) {
            return 1;
        }
        else if (y == 1) {
            return x % mod;
        }
        else if (y % 2 == 1) {
            return (x * binPower(x, y-1, mod)) % mod;
        }
        else {
            long b = binPower(x, y / 2, mod);
            return (b*b) % mod;
        }
    }
}

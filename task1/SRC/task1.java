import java.lang.Math;

public class task1 {
    public static void main(String[] args) {
        try{
            for (char x : args[0].toCharArray()){
                if (args[1].indexOf(x) == -1){
                    System.out.println("usage");
                    return;
                }
            }
            if (args.length == 2) {
                System.out.println(itoBase(Integer.parseInt(args[0]), args[1]));
            } else {
                System.out.println(itoBase(args[0], args[1] , args[2]));
            }
        }catch (Exception e){
            System.out.println("usage");
        }
    }

    static String itoBase(int nb, String base) {
        StringBuilder res = new StringBuilder();
        int ns = base.length();
        while (nb >= 0) {
            res.append(base.charAt(nb % ns));
            nb /= ns;
            if (nb == 0) break;
        }
        return res.reverse().toString();
    }

    static String itoBase(String nb, String baseSrc, String baseDst) {
        StringBuilder nbsb = new StringBuilder(nb);
        nbsb.reverse();
        int DEC = 0;
        int i = 0;
        while (nbsb.length() > 0) {
            DEC += baseSrc.indexOf(nbsb.charAt(0)) * Math.pow(baseSrc.length(), i++);
            nbsb.deleteCharAt(0);
        }
        return itoBase(DEC, baseDst);
    }
}

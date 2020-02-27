import java.io.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;

public class task3 {
    public static void main(String[] args) throws FileNotFoundException {
        try{
            Instant instant = Instant.parse(args[1]);
            Date start = Date.from(instant);
            instant = Instant.parse(args[2]);
            Date end = Date.from(instant);
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
            SimpleDateFormat format = new SimpleDateFormat("y-MM-dd'Т'HH:mm:ss", Locale.ENGLISH);
            in.readLine();
            int scSuccess = 0, scFailure = 0, scWater = 0, scFailWater = 0;
            int upSuccess = 0, upFailure = 0, upWater = 0, upFailWater = 0;
            int startWater = -1;
            int barrel = Integer.parseInt(in.readLine());
            int water = Integer.parseInt(in.readLine());
            while (true){
                String s = in.readLine();
                if (s == null) break;
                String[] str = s.split(" ");
                instant = Instant.parse(str[0]);
                Date date = Date.from(instant);
                int a = Integer.parseInt(str[str.length-1].substring(0, str[str.length-1].length() - 1));
                if (date.getTime() > end.getTime()) break;
                if (date.getTime() < start.getTime()){
                    if (str[str.length-2].equals("up")){
                        if (a <= (barrel - water)){
                            water += a;
                        }
                    } else {
                        if (a <= water){
                            water -= a;
                        }
                    }
                }else{
                    if (startWater == -1) startWater = water;
                }
                if (str[str.length-2].equals("up")){
                    if (a > (barrel - water)){
                        upFailure++;
                        upFailWater += a;
                    } else {
                        water += a;
                        upWater +=a;
                        upSuccess++;
                    }
                } else {
                    if (a > water){
                        scFailure++;
                        scFailWater += a;
                    } else {
                        water -= a;
                        scWater += a;
                        scSuccess++;
                    }
                }
            }
            File outFile = new File("out.txt");
            outFile.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(outFile));
            out.write("upTry,upFailurePercent,upWater,upFailWater,scSuccess,scFailure,scWater,scFailWater,startWater,endWater\n");
            out.write(  (upSuccess +  upFailure) + "," + (upFailure * 100) / (upSuccess + upFailure) + "," + upWater + "," + upFailWater + "," +
                    (scSuccess + scFailure) + "," + (scFailure * 100) / (scSuccess + scFailure) + "," + scWater + "," + scFailWater + "," +
                    startWater + "," + water);
            out.close();
            outFile.renameTo(new File("out.cvs"));
            outFile.delete();
        }catch (Exception e){
            System.out.println("usage");
        }
    }
}

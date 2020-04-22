import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MyMain {
    public static void main(String[] args) throws ParseException {
        String[][] arr = {{"Vasya","01/01/20","05/01/20"},{"Vaq","01/01/20","04/01/20"},{"Pety","01/01/20","05/01/20"},{"Alina","01/01/20","05/01/20"}};
        System.out.println(listDatesWhenMaxGuests(arr));
    }

    static List<Date> listDatesWhenMaxGuests(String[][] arr) throws ParseException {
        List<Date> res = new ArrayList<>();
        Date begin=makeDateFromString(arr[0][1]);
        Date end=begin;
        int count=0;
        int maxCount=0;
        for (int i = 0; i < arr.length; i++) {
            Date dateBegin = makeDateFromString(arr[i][1]);
            Date dateEnd = makeDateFromString(arr[i][2]);
            if (dateBegin.before(begin)) begin=dateBegin;
            if (dateEnd.after(end)) end=dateEnd;
        }
        Date curr=begin;
            while (curr.before(end)){
                for (int i = 0; i < arr.length; i++) {
                   if ((curr.after(makeDateFromString(arr[i][1])))&&(curr.before(makeDateFromString(arr[i][2])))) {
                       count++;
                    }
                }
                if (count==maxCount){
                    res.add(curr);
                } else if (count>maxCount){
                    maxCount=count;
                    res.clear();
                    res.add(curr);
                }
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(curr);
                calendar.add(Calendar.DATE, 1);
                curr = calendar.getTime();
                count=0;
            }

        return res;
    }

    static Date makeDateFromString(String str) throws ParseException {
        DateFormat format = new SimpleDateFormat("dd/MM/yy");
        return format.parse(str);
    }
}

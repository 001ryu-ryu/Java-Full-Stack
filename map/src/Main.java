import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> marks = new HashMap<>();
        marks.put("Iftikar", 90);
        marks.put("Mamtaz", 100);

        for(Map.Entry<String, Integer> entry : marks.entrySet()) {
            System.out.print(entry.getKey() + "---------");
            System.out.println(entry.getValue());
            System.out.println("---------");
        }

        //Optional
        Optional<Integer> marksOfJintu = Optional.ofNullable(marks.get("Jintu"));
        if(marksOfJintu.isPresent()) {
            //
        } else {
            System.out.println("Bruh is not present");
        }
    }
}

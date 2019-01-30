import java.util.HashMap;
import java.util.*;

public class MapTest {
    public static void main(String args[]) {
        // putifAbsent
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }
        System.out.println("map.putifAbsent - basically adds to map if absent!");
        map.forEach((id, value) -> System.out.print(value + ","));
        System.out.println();
        System.out.println(
                "map. computerfpresent - if key value exists then perform function on the value there (aned change map)");
        map.computeIfPresent(3, (num, val) -> val + num);
        System.out.println(map.get(3)); // val33
        System.out.println("computelfpresent - basically adds to map if absent!");
        map.computeIfPresent(9, (num, val) -> null);
        System.out.println("computeIfPresent - if key not found then return false - " + map.containsKey(9));
        System.out.println(
                "map. computeIfAbsent - if key doesnt exist then perform function and add the value there (and change map");
        map.computeIfAbsent(23, num -> "val" + num);
        System.out.println("computeIfAbsent - key doesnt exist so add entry - " + map.containsKey(23));

        map.computeIfAbsent(3, num -> "bam");
        System.out.println("computerfabsent - key does exist so value is not changed - " + map.get(3));
        System.out.println("map.remove – only if value matched");
        System.out.println("remove - remove 3 if val3 (it isnt) - " + map.get(3)); // val33
        map.remove(3, "val3");
        System.out.println(map.get(3)); // val33

        System.out.println("remove - remove 3 if val33 (it is) -" + map.get(3)); // val33
        map.remove(3, "val33");
        System.out.println("remove - 3 has been removed " + map.get(3)); // null
        System.out.println("map.merge - only if key exists but still adds if doesn't exist");
        map.merge(99, "value", (value, newvalue) -> value.concat(newvalue));
        System.out.println(map.get(99)); // 'value' because 9 didn't exist so no 'value to concat
        map.merge(99, "concat", (value, newvalue) -> value.concat(newvalue));
        System.out.println(map.get(99)); // val9concat because 9 does exist now and is merged
        System.out.println(map.getOrDefault(42, "not found is the default value if not found"));
    }
}
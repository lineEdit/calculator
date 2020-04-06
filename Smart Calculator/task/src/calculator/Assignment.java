package calculator;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Assignment {
    Map<String, BigInteger> map;

    public Assignment() {
        map = new HashMap<>();
    }

    public BigInteger getValue(String key){
        return map.getOrDefault(key, null);
    }

    public Map<String, BigInteger> getMap() {
        return map;
    }

    public void setMap(Map<String, BigInteger> map) {
        this.map = map;
    }

    public void put(String key, BigInteger value) {
        if (map.containsKey(key)) {
            map.replace(key, value);
        } else {
            map.put(key, value);
        }
    }

    public void put(String key, String value) {
//        varian "a = b", contains "value"
        if (map.containsKey(value)) {
            put(key, map.get(value));
        } else {
            map.put(key, new BigInteger(value));
        }
    }

    public void put(InputLine input) {
        String[] strings = input.getContent().split("\\s*=\\s*");
        if (strings.length == 2) {
            put(strings[0], strings[1]);
        } else {
            System.out.println("Error put(String input) = " + input);
        }
    }
}

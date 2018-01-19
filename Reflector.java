import java.util.HashMap;

public class Reflector{
	private HashMap<Character, Character> map;
	
	//bidirectional mapping
	public Reflector(HashMap<Character, Character> map1){
		map = new HashMap<Character, Character>();
		for(char c : map1.keySet()){
			map.put(c, map1.get(c));
			map.put(map1.get(c), c);
		}
	}
	
	public char signal(char c){
		return map.get(c);
	}
}

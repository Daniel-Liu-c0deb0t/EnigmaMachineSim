import java.util.HashMap;
import java.util.HashSet;

public class Wheel{
	private HashMap<Character, Character> mapF, mapR;
	private int offset;
	private int constOffset;
	private HashSet<Integer> rotate;
	
	//single directional mapping
	public Wheel(HashMap<Character, Character> map1, int constOffset1, HashSet<Integer> rotate1){
		mapF = new HashMap<Character, Character>();
		mapR = new HashMap<Character, Character>();
		for(char c : map1.keySet()){
			mapF.put(c, map1.get(c));
			mapR.put(map1.get(c), c);
		}
		constOffset = constOffset1;
		rotate = rotate1;
	}
	
	//update offset, or the rotation
	//if the next wheel needs to rotate, then true is returned so the caller can update next wheel
	public boolean rotate(EnigmaMachine m){
		offset++;
		if(offset >= m.alphaSize() || rotate.contains(offset - 1)){
			offset = 0;
			return true;
		}
		return false;
	}
	
	//reset rotation
	public void reset(){
		offset = 0;
	}
	
	//get mapped letter after rotation
	public char signal(char c, boolean f, EnigmaMachine m){
		return f ? mapF.get(m.next(c, offset, constOffset)) : m.prev(mapR.get(c), offset, constOffset);
	}
}

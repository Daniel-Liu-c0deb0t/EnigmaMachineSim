import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class Generator{
	//generate single directional random mapping
	//useful for wheels
	public static HashMap<Character, Character> randMapping(long seed, HashSet<Character> alpha){
		ArrayList<Character> left = new ArrayList<Character>();
		ArrayList<Character> chars = new ArrayList<Character>();
		for(char c : alpha){
			left.add(c);
			chars.add(c);
		}
		Collections.shuffle(left, new Random(seed));
		HashMap<Character, Character> res = new HashMap<Character, Character>();
		for(int i = 0; i < chars.size(); i++){
			res.put(chars.get(i), left.get(i));
		}
		return res;
	}
	
	//generate bidirectional mapping
	//useful for plugboards and reflectors
	public static HashMap<Character, Character> randMapping2(long seed, HashSet<Character> alpha){
		ArrayList<Character> left = new ArrayList<Character>();
		for(char c : alpha){
			left.add(c);
		}
		Collections.shuffle(left, new Random(seed));
		HashMap<Character, Character> res = new HashMap<Character, Character>();
		for(int i = 0; i < left.size(); i += 2){
			res.put(left.get(i), left.get(i + 1));
		}
		return res;
	}
	
	//map each letter to itself
	public static HashMap<Character, Character> sameMapping(HashSet<Character> alpha){
		HashMap<Character, Character> res = new HashMap<Character, Character>();
		for(char c : alpha){
			res.put(c, c);
		}
		return res;
	}
	
	//generate random set of integers within the alphabet range
	public static HashSet<Integer> randSet(long seed, int length, HashSet<Character> alpha){
		HashSet<Integer> res = new HashSet<Integer>();
		Random r = new Random(seed);
		for(int i = 0; i < length; i++){
			res.add(r.nextInt(alpha.size()));
		}
		return res;
	}
	
	//get mapping from lowercase string
	public static HashMap<Character, Character> getMapping(String s){
		HashMap<Character, Character> res = new HashMap<Character, Character>();
		for(char i = 'a'; i <= 'z'; i++){
			res.put(i, s.charAt(i - 'a' + 1));
		}
		res.put('.', '.');
		return res;
	}
	
	//get mapping from uppercase string
	public static HashMap<Character, Character> getMapping2(String s){
		HashMap<Character, Character> res = new HashMap<Character, Character>();
		for(char i = 'A'; i <= 'Z'; i++){
			res.put(i, s.charAt(i - 'A' + 1));
		}
		res.put('.', '.');
		return res;
	}
}

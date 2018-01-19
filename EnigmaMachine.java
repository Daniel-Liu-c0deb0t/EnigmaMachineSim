import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class EnigmaMachine{
	private ArrayList<Wheel> w;
	private Plugboard p;
	private Reflector r;
	private ArrayList<Character> alpha;
	private HashMap<Character, Integer> alphaMap;
	
	public EnigmaMachine(Plugboard p1, Reflector r1, HashSet<Character> alpha1){
		p = p1;
		r = r1;
		w = new ArrayList<Wheel>();
		alpha = new ArrayList<Character>();
		for(char c : alpha1){
			alpha.add(c);
		}
		Collections.sort(alpha); //keeps a sorted list of all characters in the alphabet
		alphaMap = new HashMap<Character, Integer>(); //map each character to its index in the sorted list
		for(int i = 0; i < alpha.size(); i++){
			alphaMap.put(alpha.get(i), i);
		}
	}
	
	//add wheel
	public void add(Wheel w1){
		w.add(w1);
	}
	
	//reset all wheel rotations
	public void reset(){
		for(int i = 0; i < w.size(); i++){
			w.get(i).reset();
		}
	}
	
	//type a string, reset wheels right after
	public String type(String s){
		StringBuilder b = new StringBuilder();
		for(int i = 0; i < s.length(); i++){
			b.append(type(s.charAt(i)));
		}
		reset();
		return b.toString();
	}
	
	//type one character, don't reset wheel
	public char type(char c){
		boolean shouldRotate = true;
		for(int i = 0; i < w.size(); i++){
			if(shouldRotate)
				shouldRotate = w.get(i).rotate(this);
		}
		
		c = p.signal(c);
		for(int i = 0; i < w.size(); i++){
			c = w.get(i).signal(c, true, this);
		}
		c = r.signal(c);
		for(int i = w.size() - 1; i >= 0; i--){
			c = w.get(i).signal(c, false, this);
		}
		c = p.signal(c);
		
		return c;
	}
	
	public int alphaSize(){
		return alpha.size();
	}
	
	//next lexicographical letter or symbol (adds alpha.size() so the the number does not become negative)
	public char next(char c, int offset, int constOffset){
		return alpha.get((alphaMap.get(c) + alpha.size() * 2 + offset + constOffset) % alpha.size());
	}
	
	//prev lexicographical letter or symbol (adds alpha.size() so the the number does not become negative)
	public char prev(char c, int offset, int constOffset){
		return alpha.get((alphaMap.get(c) + alpha.size() * 2 - offset - constOffset) % alpha.size());
	}
}

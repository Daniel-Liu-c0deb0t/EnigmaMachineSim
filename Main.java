import java.util.HashSet;
import java.util.Random;

public class Main{
	public static void main(String[] args){
		test1();
		System.out.println();
		test2();
	}
	
	//test using predefined mappings
	private static void test2(){
		String s = "HELLO";
		
		HashSet<Character> alpha = new HashSet<Character>();
		for(char i = 'A'; i <= 'Z'; i++)
			alpha.add(i);
		alpha.add('.');
		
		EnigmaMachine m = new EnigmaMachine(new Plugboard(Generator.getMapping2(".ABCDEFGHIJKLMNOPQRSTUVWXYZ")),
				new Reflector(Generator.getMapping2(".YRUHQSLDPXNGOKMIEBFZCWVJAT")), alpha);
		HashSet<Integer> k = new HashSet<Integer>();
		k.add(17);
		m.add(new Wheel(Generator.getMapping2(".EKMFLGDQVZNTOWYHXUSPAIBRCJ"), 1, k));
		k = new HashSet<Integer>();
		k.add(5);
		m.add(new Wheel(Generator.getMapping2(".AJDKSIRUXBLHWTMCQGZNPYFVOE"), 1, k));
		k = new HashSet<Integer>();
		k.add(22);
		m.add(new Wheel(Generator.getMapping2(".BDFHJLCPRTXVZNYEIWGAKMUSQO"), 1, k));
		
		String encode = m.type(s);
		
		System.out.println(encode);
		
		String decode = m.type(encode);
		
		System.out.println(decode);
	}
	
	//test using randomly generated mappings
	//one seed is used to cause the generated mappings to be reproducible
	private static void test1(){
		String s = "Coding is fun!";
		long seed = 11;
		
		//larger alphabet to support capitalization and symbols
		HashSet<Character> alpha = new HashSet<Character>();
		for(char i = 'a'; i <= 'z'; i++)
			alpha.add(i);
		for(char i = 'A'; i <= 'Z'; i++)
			alpha.add(i);
		alpha.add('.');
		alpha.add(',');
		alpha.add('!');
		alpha.add('?');
		alpha.add(' ');
		alpha.add(':');
		
		Random r = new Random(seed);
		
		//create an instance of the enigma machine
		EnigmaMachine m = new EnigmaMachine(new Plugboard(Generator.randMapping2(r.nextLong(), alpha)),
				new Reflector(Generator.randMapping2(r.nextLong(), alpha)), alpha);
		m.add(new Wheel(Generator.randMapping(r.nextLong(), alpha), r.nextInt(alpha.size()), Generator.randSet(r.nextLong(), 2, alpha)));
		m.add(new Wheel(Generator.randMapping(r.nextLong(), alpha), r.nextInt(alpha.size()), Generator.randSet(r.nextLong(), 2, alpha)));
		m.add(new Wheel(Generator.randMapping(r.nextLong(), alpha), r.nextInt(alpha.size()), Generator.randSet(r.nextLong(), 2, alpha)));
		
		String encode = m.type(s);
		
		System.out.println("String: " + s);
		System.out.println("Encode: " + encode);
		
		String decode = m.type(encode);
		
		System.out.println("Decode: " + decode);
		System.out.println();
		
		for(int i = 0; i < s.length(); i++){
			System.out.println("Letter '" + s.charAt(i) + "' -> '" + m.type(s.charAt(i)) + "'");
		}
		m.reset();
	}
}

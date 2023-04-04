package 热学Huffman作业;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class Huffman {
	protected Huffman left;
	protected Huffman right;
	protected Huffman up;
	protected int num=0;
	protected double p;
	//空构造
	public Huffman() {
		
	}
	public Huffman(Huffman left, Huffman right, double p) {
		super();
		this.left = left;
		this.right = right;
		this.p = p;
	}
	//出加密表
	public void encrypt(HashMap<Character, String> code,String s){
		this.left.encrypt(code,s+"1");
		this.right.encrypt(code,s+"0");
	}
	//出解密表
	public void decode(HashMap<String,Character> code,String s) {
		this.left.decode(code,s+"1");
		this.right.decode(code,s+"0");
	}
	//构造编码树，返回头
	public static Huffman struct(ArrayList<Huffman> tree) {
		if(tree.size()==2) {
			tree.get(0).num=1;
			Huffman next = new Huffman(tree.get(0),tree.get(1),tree.get(0).p+tree.get(1).p);
			tree.get(0).up=tree.get(1).up=next;
			return next;
		}
		double min1=tree.get(0).p;
		int minindex=0;
		Huffman minh1,minh2;
		for(var i=0;i<tree.size();i++) {
			if(tree.get(i).p<min1) {
				minindex=i;
				min1=tree.get(minindex).p;
			}
		}
		minh1=tree.get(minindex);
		minh1.num=1;
		tree.remove(minindex);
		min1=tree.get(0).p;
		for(var i=0;i<tree.size();i++) {
			if(tree.get(i).p<min1) {
				minindex=i;
				min1=tree.get(minindex).p;
			}
		}
		minh2=tree.get(minindex);
		tree.remove(minindex);
		Huffman next = new Huffman(minh1,minh2,minh1.p+minh2.p);
		minh1.up=minh2.up=next;
		tree.add(next);
		return struct(tree);
	}
	//加密一串文字
	public static String encryptchs(String s,HashMap<Character,String> code) {
		String out="";
		for(var i=0;i<s.length();i++) {
			char a=s.charAt(i);
			out=out+code.get(a);
		}
		return out;
	}
	//解密编码
	public static String decodechs(String s,HashMap<String,Character> code) {
		String out="";
		int i=0;
		String now="";
		while(i<s.length())
		{
			while(i<s.length()) {
				now=now+s.charAt(i);
				if(code.get(now)!=null) {
					out=out+code.get(now);
					i++;
					now="";
					break;
				}
				i++;
			}
		}
		return out;
	}
	//输入字符概率对应表
	public static void input(ArrayList<Huffman> tree){
		tree.add(new Huffmanch('A',0.0683789192795197));
		tree.add(new Huffmanch('B',0.0125083388925951));
		tree.add(new Huffmanch('C',0.0233488992661775));
		tree.add(new Huffmanch('D',0.0358572381587725));
		tree.add(new Huffmanch('E',0.108405603735824));
		tree.add(new Huffmanch('F',0.0183455637091394));
		tree.add(new Huffmanch('G',0.0166777851901268));
		tree.add(new Huffmanch('H',0.0508672448298866));
		tree.add(new Huffmanch('I',0.0583722481654436));
		tree.add(new Huffmanch('J',0.00125083388925951));
		tree.add(new Huffmanch('K',0.0064209472981988));
		tree.add(new Huffmanch('L',0.0333555703802535));
		tree.add(new Huffmanch('M',0.0200133422281521));
		tree.add(new Huffmanch('N',0.0558705803869246));
		tree.add(new Huffmanch('O',0.0625416944629753));
		tree.add(new Huffmanch('P',0.0158438959306204));
		tree.add(new Huffmanch('Q',0.000792194796531021));
		tree.add(new Huffmanch('R',0.0500333555703803));
		tree.add(new Huffmanch('S',0.0525350233488993));
		tree.add(new Huffmanch('T',0.0758839226150767));
		tree.add(new Huffmanch('U',0.0233488992661775));
		tree.add(new Huffmanch('V',0.00817211474316211));
		tree.add(new Huffmanch('W',0.0200133422281521));
		tree.add(new Huffmanch('X',0.00125083388925951));
		tree.add(new Huffmanch('Y',0.0166777851901268));
		tree.add(new Huffmanch('Z',0.00061707805203469));
		tree.add(new Huffmanch(' ',0.162608405603736));
	}
	//将字符串编码转换为byte[]
	public static byte[] outstream(String str) {
		byte[] o = new byte[(str.length()+7)/8];
		int index=0;
		for(int i=0;i<str.length();i+=8) {
			if((i+8)>str.length()) {
				o[index++]=(byte)Integer.parseInt(str.substring(i), 2);
			}
			else {
				o[index++]=(byte)Integer.parseInt(str.substring(i, i+8), 2);
			}
		}
		return o;
	}
	//输出平均比特数
	public static void aver(HashMap<Character, String> code) {
		StringBuilder str = new StringBuilder();
		for(int i=0;i<10000;i++) {
			double p=Math.random();
			if(p<0.0683789192795197) str.append('A');
			else if(p<0.0808872581721148) str.append('B');
			else if(p<0.104236157438292 ) str.append('C');
			else if(p<0.140093395597065 ) str.append('D');
			else if(p<0.248498999332889 ) str.append('E');
			else if(p<0.266844563042028 ) str.append('F');
			else if(p<0.283522348232155 ) str.append('G');
			else if(p<0.334389593062042 ) str.append('H');
			else if(p<0.392761841227485 ) str.append('I');
			else if(p<0.394012675116745 ) str.append('J');
			else if(p<0.400433622414944 ) str.append('K');
			else if(p<0.433789192795197 ) str.append('L');
			else if(p<0.453802535023349 ) str.append('M');
			else if(p<0.509673115410274 ) str.append('N');
			else if(p<0.572214809873249 ) str.append('O');
			else if(p<0.588058705803869 ) str.append('P');
			else if(p<0.5888509006004 ) str.append('Q');
			else if(p<0.638884256170781 ) str.append('R');
			else if(p<0.69141927951968 ) str.append('S');
			else if(p<0.767303202134757 ) str.append('T');
			else if(p<0.790652101400934 ) str.append('U');
			else if(p<0.798824216144096 ) str.append('V');
			else if(p<0.818837558372248 ) str.append('W');
			else if(p<0.820088392261508 ) str.append('X');
			else if(p<0.836766177451635 ) str.append('Y');
			else if(p<0.837383255503669 ) str.append('Z');
			else str.append(' ');
		}
		String s = str.toString();
		String l = encryptchs(s, code);
//		byte[] sby=outstream(l);
		System.out.println("此编码对应每个字符的平均比特数为："+1.0*l.length()/s.length());
	}
	//读入源文件并将编码后内容存入文件
	public static void zip(HashMap<Character, String> code) {
		try {
			//读入原文件
			BufferedReader in = new BufferedReader(
					new InputStreamReader(
							new FileInputStream("original.txt")));
			String line;
			char ch;
			String sOut="";
			//将复杂文字转化为忽略大小写后，由大写字母和空格组成的文字。
			while((line=in.readLine())!=null) {
				for(var i=0;i<line.length();i++) {
					ch=line.charAt(i);
					if(ch>='A'&&ch<='Z');
					else if(ch>='a'&&ch<='z') ch=(char) (ch+'A'-'a');
					else ch=' ';
					sOut += ch;
				}
			}
			in.close();
			String scode = encryptchs(sOut,code);//加密
			var outold = new PrintWriter(
					new BufferedWriter(
							new OutputStreamWriter(
									new FileOutputStream("编码后文件（二进制字符储存）.txt"))));
			outold.print(scode);
			outold.close();
			byte scodeout[] = outstream(scode);
			System.out.println("加密该段文字的平均比特数为"+1.0*scode.length()/sOut.length());
			var out = new FileOutputStream("编码后文件（二进制码储存）.txt");
			out.write(scodeout);//将加密后文字输出至文件中
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//读入二进制编码文件并将解编码后内容存入文件
	private static void dezip(HashMap<String, Character> decode) {
		// TODO Auto-generated method stub
		try {
			var in = new FileInputStream("编码后文件（二进制码储存）.txt");
			byte[] code = new byte[in.available()];
			in.read(code);
			in.close();
			boolean flag=true;
			StringBuilder builder = new StringBuilder();
			for(int i=0;i<code.length;i++) {
				byte a=code[i];
				if(i==code.length-1) flag=false;
				builder.append(byteToBitString(flag,a));
			}
			String s=builder.toString();
			String o=decodechs(s, decode);
			var out = new OutputStreamWriter(
					new FileOutputStream("解码文件.txt"));
			out.write(o);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//将byte转为对应的字符串
	private static String byteToBitString(boolean flag, byte a) {
		String s;
		int p=a;
		if(flag) p|=256;//正数需要补高位，否则转换出来的string位数不足八位
		s=Integer.toBinaryString(p);
		if(flag) s=s.substring(s.length()-8);
		return s;
	}
	public static void main(String[] args) {
		ArrayList<Huffman> tree = new ArrayList<>();
		input(tree);//读入各字符概率
		Huffman head=struct(tree);//构建编码树
		HashMap<Character, String> code = new HashMap<>();
		HashMap< String,Character> decode = new HashMap<>();
		head.encrypt(code, "");//构建加密关系表
		head.decode(decode, "");//构建解密关系表
		aver(code);
		zip(code);
		dezip(decode);
	}

}
class Huffmanch extends Huffman{
	char ch;
	
	public Huffmanch(char ch,double p){
		this.ch=ch;
		this.p=p;
	}
	@Override
	public void encrypt(HashMap<Character, String> code, String s) {
		// TODO Auto-generated method stub
		code.put(ch, s);
	}
	@Override
	public void decode(HashMap<String, Character> code, String s) {
		// TODO Auto-generated method stub
		code.put(s, ch);
	}
	
}

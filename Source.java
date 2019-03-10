import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.util.HashSet;
import java.util.LinkedList;

public class Source {

    public static void main(String[] args) {
        System.out.println("Please give me two English words, and I will change the first into the second by changing one letter at a time");
        while (true) {
            System.out.println();
            System.out.println("Dictionary file name?");
            Scanner s = new Scanner(System.in);
            String fileName = s.nextLine();
            HashSet<String> set = new HashSet<String>();
            if(readFile(fileName,set)){
                System.out.println("Ending word? (or Enter to quit):");
                String begin = s.nextLine();
                System.out.println("Begining word? (or Enter to quit):");
                String end = s.nextLine();
                access(begin,end,set);
            //    s.close();
            }
        }
    }

    public static boolean readFile(String fileName, HashSet<String> set) {
        String temp = null;
        try {
            FileReader reader = new FileReader(fileName);
            BufferedReader br = new BufferedReader(reader);
            while ((temp = br.readLine()) != null) {
                set.add(temp);
           }
           br.close();
            System.out.println("Read object success!");
            return true;
        } catch (IOException e) {
            System.out.println("Unable to open that file, try again.");
            return false;
        }
    }

    public static boolean if_indic(String s, HashSet<String> set){
        return set.contains(s);
    }

    public static void printss(Stack<String> ss){
        while(ss.size() > 1)
        {
            String temp = ss.pop();
            System.out.print(temp + " -> ");
        }
        System.out.print(ss.pop());
    }

    public static String rep(String begin, int i, char c) {
        StringBuilder strB = new StringBuilder(begin);
        strB.setCharAt(i, c);
        String end = strB.toString();
        return end;
      }
    public static void access(String begin, String end, HashSet<String> set)
    {
        if(!if_indic(begin, set)){
            set.add(begin);
        }
        if(!if_indic(end, set)){
            set.add(end);
        }
        Stack<String> st = new Stack<String>();
        LinkedList<Stack<String>> ladder = new LinkedList<Stack<String>>();
        st.push(begin);
        ladder.addLast(st);
        while(ladder.size()!=0)
        {
            String s = ladder.getFirst().peek();
            for (int i = 0; i < s.length(); ++i){
                for (char c = 'a'; c <= 'z'; ++c){
                    String temp = rep(s,i,c);
                    if (if_indic(temp,set))
                    {
                        if (temp.equals(end))
                        {
                            ladder.getFirst().push(temp);
                            printss(ladder.getFirst());
                            return;
                        }
                        Stack<String> temp_sta = new Stack<String>();
                        temp_sta.addAll(ladder.getFirst());
                        temp_sta.push(temp);
                        ladder.addLast(temp_sta);
                        set.remove(temp);
                    }
                }

            }
            ladder.removeFirst();
        }
        System.out.println("No word ladder found");
    }
    
    
}
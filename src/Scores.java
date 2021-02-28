import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Set;
import java.util.TreeSet;
import java.io.*;
import java.util.Map;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.*;

// Class that takes care of the I/O of the high scores. Reads in a text file 
// with lines each having a format of (String, int). The String is the username
// and the int is the score. The read in high scores are stored in an ArrayList.
// of type singleScore. 

public class Scores {
    
    // stores high scores as an ArrayList
    private ArrayList<singleScore> scores;
    
    // makes a Scores object by reading a BufferedReader
    public Scores(Reader r) throws IOException{
        if (r == null) {
            throw new IllegalArgumentException();            
        }
        scores = new ArrayList<singleScore>();
        BufferedReader br = new BufferedReader(r);
        try {
            String line = br.readLine();
            while (line != null) {
                String trimmed = line.trim();
                int index = trimmed.indexOf(',');
                int lastIndex = trimmed.lastIndexOf(',');
                if (index == -1 || index == 0 || index == trimmed.length() - 1
                        || index != lastIndex) {
                    throw new Exception("Bad format");
                }
                String username = "";
                String score = "";
                for (int i = 0; i < index; i++) {
                    username = username + trimmed.charAt(i);
                }
                for (int j = index + 1; j < trimmed.length(); j++) {
                    score = score + trimmed.charAt(j);
                }
                username = username.trim();
                score = score.trim();
                
                int scoreNum = Integer.parseInt(score);
                
                singleScore currScore = new singleScore(scoreNum, username);
                
                scores.add(currScore);
                
                line = br.readLine();
            }
        }
        catch (IOException i) {
            throw new IOException();
        }
        catch (NullPointerException n) {
            System.out.println("Bad format");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    // make a scores object from a file
    public static Scores make (String filename) throws IOException {
        Reader r = null;
        Scores sc;
        
        try {
            r = new FileReader(filename);
            sc = new Scores(r);
        }
        catch (NullPointerException npx) {
            throw new FileNotFoundException();
        }
        catch (FileNotFoundException f) {
            throw new FileNotFoundException();
        }
        catch (IOException e) {
            throw new IOException();
        }
        finally {
            if (r != null) {
                r.close();
            }
        }
        return sc;
    }
    
    // returns a copy of the ArrayList of scores
    public ArrayList<singleScore> orderedScores() {
        ArrayList<singleScore> tm = scores;
        Collections.sort(tm);
        Collections.reverse(tm);
        return scores;
    }
    
    // function that saves a score by writing it into a text file and adding
    // it to the ArrayList
    public void saveScore(int score, String username) throws IOException{
        BufferedWriter bw = null;
        try {
            FileWriter f = new FileWriter("files/highScores.txt", true);
            bw = new BufferedWriter(f);
            bw.write(username + ", " + ("" + score));
            bw.newLine();
            singleScore newScore = new singleScore(score, username);
            scores.add(newScore);
        }
        catch (FileNotFoundException f) {
            throw new FileNotFoundException();
        }
        catch (IOException e) {
            throw new IOException();
        }
        finally {
            if (bw != null) {
                bw.close();
            }
        }
    }
    
}

// class singleScore that should only be used in the Scores class
// this class creates a singleScore object out of a String and an integer
class singleScore implements Comparable<singleScore>{
    
    private int score;
    private String name;
    
    public singleScore(int num, String name) {
        this.score = num;
        this.name = name;
    }
    
    // returns the score portion of the singleScore
    public int getScore() {
        return score;
    }
    
    // returns the name of the singleScore
    public String getName() {
        return name;
    }
    
    // set the score of the singleScore
    public void setScore(int s) {
        this.score = s;
    }
    
    // set the name of the singleScore
    public void setName(String name) {
        this.name = name;
    }
    
    // compare singleScore objects based off of their score
    @Override
    public int compareTo(singleScore second) {
        return Integer.compare(this.score, second.score);
    }
}
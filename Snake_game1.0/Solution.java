 
 package Snakegame;
 public class Solution {


    static int i=2;
    Solution(){
       i++;
       System.out.println(i);

    }

    public String owner;
    public Solution (String owner){
        this.owner= owner;
    }
    void display(){
        System.out.println("Hii I am "+owner);
    }

    static void squire(){
        System.out.println(i*i);
        
    }
    static{
        System.out.println("static meathod called");
    }

    public static void main(String[] args) {
        Solution.squire(); 
        Solution b1= new Solution();
        Solution.squire();
        Solution b2= new Solution();
        Solution.squire();
    }
}

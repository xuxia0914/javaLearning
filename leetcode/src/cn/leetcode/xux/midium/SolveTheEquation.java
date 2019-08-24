package cn.leetcode.xux.midium;

/**
 * Solve a given equation and return the value of x in the form of string "x=#value".
 * The equation contains only '+', '-' operation, the variable x and its coefficient.
 * If there is no solution for the equation, return "No solution".
 * If there are infinite solutions for the equation, return "Infinite solutions".
 * If there is exactly one solution for the equation, we ensure that the value of x is an integer.
 * Example 1:
 * Input: "x+5-3+x=6+x-2"
 * Output: "x=2"
 * Example 2:
 * Input: "x=x"
 * Output: "Infinite solutions"
 * Example 3:
 * Input: "2x=x"
 * Output: "x=0"
 * Example 4:
 * Input: "2x+3x-6x=x+2"
 * Output: "x=-1"
 * Example 5:
 * Input: "x=x+2"
 * Output: "No solution"
 */
public class SolveTheEquation {

    public String solveEquation(String equation) {
        String[] ss = equation.split("=");
        String curr;
        int a = 0;
        int b = 0;
        int start = 0;
        int i = ss[0].indexOf("+", start+1);
        int j = ss[0].indexOf("-", start+1);
        int end;
        if(i!=-1&&j!=-1) {
            end = Math.min(i, j);
        }else if(i!=-1) {
            end = i;
        }else {
            end = j;
        }
        while(end!=-1) {
            curr = ss[0].substring(start, end);
            if(curr.endsWith("x")) {
                curr = curr.substring(0, curr.length()-1);
                if(curr.equals("")||curr.equals("+")) {
                    a += 1;
                }else if(curr.equals("-")) {
                    a -= 1;
                }else {
                    a += Integer.valueOf(curr);
                }
            }else {
                b += Integer.valueOf(curr);
            }
            start = end;
            i = ss[0].indexOf("+", start+1);
            j = ss[0].indexOf("-", start+1);
            if(i!=-1&&j!=-1) {
                end = Math.min(i, j);
            }else if(i!=-1) {
                end = i;
            }else {
                end = j;
            }
        }
        curr = ss[0].substring(start);
        if(curr.endsWith("x")) {
            curr = curr.substring(0, curr.length()-1);
            if(curr.equals("")||curr.equals("+")) {
                a += 1;
            }else if(curr.equals("-")) {
                a -= 1;
            }else {
                a += Integer.valueOf(curr);
            }
        }else {
            b += Integer.valueOf(curr);
        }

        start = 0;
        i = ss[1].indexOf("+", start+1);
        j = ss[1].indexOf("-", start+1);
        if(i!=-1&&j!=-1) {
            end = Math.min(i, j);
        }else if(i!=-1) {
            end = i;
        }else {
            end = j;
        }
        while(end!=-1) {
            curr = ss[1].substring(start, end);
            if(curr.endsWith("x")) {
                curr = curr.substring(0, curr.length()-1);
                if(curr.equals("")||curr.equals("+")) {
                    a -= 1;
                }else if(curr.equals("-")) {
                    a += 1;
                }else {
                    a -= Integer.valueOf(curr);
                }
            }else {
                b -= Integer.valueOf(curr);
            }
            start = end;
            i = ss[1].indexOf("+", start+1);
            j = ss[1].indexOf("-", start+1);
            if(i!=-1&&j!=-1) {
                end = Math.min(i, j);
            }else if(i!=-1) {
                end = i;
            }else {
                end = j;
            }
        }
        curr = ss[1].substring(start);
        if(curr.endsWith("x")) {
            curr = curr.substring(0, curr.length()-1);
            if(curr.equals("")||curr.equals("+")) {
                a -= 1;
            }else if(curr.equals("-")) {
                a += 1;
            }else {
                a -= Integer.valueOf(curr);
            }
        }else {
            b -= Integer.valueOf(curr);
        }
        if(a==0) {
            if(b==0) {
                return "Infinite solutions";
            }else {
                return "No solution";
            }
        }else {
            return "x="+(-b)/a;
        }
    }

    public static void main(String[] args) {
        SolveTheEquation ste = new SolveTheEquation();
        System.out.println(ste.solveEquation("x+5-3+x=6+x-2"));
        System.out.println(ste.solveEquation("x=x"));
        System.out.println(ste.solveEquation("2x+3x-6x=x+2"));
        System.out.println(ste.solveEquation("x=x+2"));
    }

}

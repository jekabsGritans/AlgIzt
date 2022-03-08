package com.company;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Main izt = new Main();

        izt.processInfix("1+1");
        izt.processInfix("((1+2)/(4-3)+5-6)*2+4");
        izt.processInfix("((1+2)/(4-3)+5-6)C*2+4");
        izt.processInfix("1/0");
    }

    public void processInfix(String infixSt){
        infix = infixSt;
        convert();
        calculate();
        print();
    }

    public int priority(char symb){
        switch (symb) {
            case '*': return 2;
            case '/': return 2;
            case '+': return 1;
            case '-': return 1;
        }
        return -1;
    }

    public void print(){
        System.out.print("Infix form: ");
        System.out.println(infix);
        System.out.print("Valid postfix form: ");
        System.out.println(postfix);
        System.out.print("Numeric result: ");
        System.out.println(res);
        System.out.println();
    }

    public void convert(){
        Stack<Character> stack = new Stack();
        postfix="";
        for (int i=0;i<infix.length();i++){
            char c = infix.charAt(i);
            if (Character.isDigit(c)){
                postfix+=c;
            } else if (c=='(') {
                stack.push(c);
            } else if (c==')') {
                char cc;
                do {
                    cc = stack.pop();
                    if (cc!=')' && cc!='(') postfix+=cc;
                }
                while (cc != '(');
            } else if (priority(c) != -1) {
                while (!stack.empty() && priority(stack.peek()) >= priority(c))
                    postfix+=stack.pop();
                stack.push(c);
            } else {
                System.out.println("Kluda izteiksme!");
                return;
            }
        }
        while(!stack.empty())
            postfix += stack.pop();
    }

    public void applyOp(char op){
       switch (op) {
            case '+' -> numstack.push(
                    numstack.pop() + numstack.pop());
            case '-' -> numstack.push(
                    -numstack.pop() + numstack.pop());
            case '*' -> numstack.push(
                    numstack.pop() * numstack.pop());
            case '/' -> {
                if (numstack.peek() == 0)
                    System.out.println("Includes division by 0!");
                numstack.push(
                        1 / (numstack.pop() / numstack.pop()));
            }

            }
        }


    public void calculate(){
        char c;
        for (int i=0;i<postfix.length();i++) {
            c = postfix.charAt(i);
            if (Character.isDigit(c)) {
                numstack.push((double) (int) c - 48);
            } else applyOp(c);
        }
        res = numstack.pop();
    }

    Stack<Double> numstack = new Stack();
    double res;
    String infix;
    String postfix;

}

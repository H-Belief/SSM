package com.belief;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

    public static void main(String[] args) {

        String question_ids = "69,53,65,59,59,59,59,59,59,59,59,59,59,59,59,59,12";
        String answer = "A,D,E,A,A,A,A,A,A,A,A,A,A,A,A,A,C";

        List<String> idss = Arrays.asList(question_ids.split(","));
        List<String> answerss = Arrays.asList(answer.split(","));

        List<String> ids = new ArrayList<>(idss);
        List<String> answers = new ArrayList<>(answerss);

        for (int i = ids.size() - 1; i >= 0; i--) {
           if(i > 0 && ids.get(i).equals(ids.get(i-1))){
               ids.remove(i);
               answers.remove(i);
           }
        }

        System.out.println(ids.toString());
        System.out.println(answers.toString());


    }



}

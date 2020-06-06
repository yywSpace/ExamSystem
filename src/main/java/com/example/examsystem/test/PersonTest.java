package com.example.examsystem.test;

public class PersonTest {

    private int age;

    private String local;

    public PersonTest(){
        age = 30;

        local = "shanghai";
    }


    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person1 [ age=" + age + ", local=" + local + "]";
    }


    public int getAge() {
        return age;
    }



    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

}

package com.lsh.utils;

import com.lsh.domain.Student;
import com.lsh.domain.User;

public class UserHolder {
    private static final ThreadLocal<User> tl = new ThreadLocal<>();

    public static void saveUser(User user){
        tl.set(user);
    }

    public static User getUser(){
        return tl.get();
    }

    public static void removeUser(){
        tl.remove();
    }

    private static final ThreadLocal<Student> tl2 = new ThreadLocal<>();

    public static void saveStudent(Student student){
        tl2.set(student);
    }

    public static Student getStudent(){
        return tl2.get();
    }

    public static void removeStudent(){
        tl2.remove();
    }

}

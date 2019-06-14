/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Student
 */
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;


public class saverandpuller {
    public static void main(String[] args) throws IOException {
        Passwords passwords = new Passwords();
        passwords.load_all("passwords.txt");
        for(int i = 0; i < passwords.get_size(); i += 1) {
            System.out.println(passwords.get(i));
        }
        passwords.order_list_by_("name");
        for(int i = 0; i < passwords.get_size(); i += 1) {
            System.out.println(passwords.get(i));
        }
    }
}

class Passwords {
    private ArrayList<Password> password_list;
        private boolean check(String str) {
        return !str.contains("`");
    }
    
    public Passwords () {
        password_list = new ArrayList<Password>();
    }
    
    public boolean new_password(String new_website, String new_username, String new_password) {
        if (check(new_username)) {
            if (check(new_website)) {
                password_list.add(new Password(new_website + "`" + new_username + "`" + new_password));
                return true;
            }
        }
        return false;
    }

    public void save_all(String file_path) throws IOException {
        
        String file_content = "";
        BufferedWriter writer = new BufferedWriter(new FileWriter(file_path));
        boolean flag = true;
        for (Password i : password_list) {
            if (flag) {
                flag = false;
            }
            else {
                file_content += "\r\n";
            }
            file_content += i.toString();
        }
        writer.write(file_content);
        writer.close();
    }
    
    public int get_size() {
        return password_list.size();
    }
    
    public int search(String website_name) {
        for (int i = 0; i > password_list.size() ; i += 1) {
            if (password_list.get(i).get_website().toLowerCase().contains(website_name)) {
                return i;
            }
        }
        return -1;
    }
    
    public boolean load_all(String file_path) throws IOException {
        File tmp = new File(file_path);
        if (tmp.exists()) {
            String file_content = "";
            BufferedReader reader = new BufferedReader(new FileReader(file_path));
            boolean flag = true;

            int i;
            String cocmplete = "";
            while((i=reader.read())!=-1) {
                cocmplete += (char)i;
            }
            password_list = new ArrayList<Password>();
            for (String line : cocmplete.split("\r\n")) {
                password_list.add(new Password(line));
            }
            reader.close();
            return true;
        } else {
            return false;
        }
    }
    
    public void load(String name_website_password) {
        password_list.add(new Password(name_website_password));
    }
    
    public Password get(int position) {
        return password_list.get(position);
    }
    
    public void order_list_by_(String method) {
        switch (method) {
            case "website" : 
                Collections.sort(this.password_list, new sort_by_website());
            case "name" :
                Collections.sort(this.password_list, new sort_by_name());
            case "password" :
                Collections.sort(this.password_list, new sort_by_password());
        }
        Collections.reverse(this.password_list);
    }
}

class Password {
    private String name;
    private String website;
    private String password;
    
    public Password(String name_website_password) {
        String[] list = name_website_password.split("`");
        boolean flag1 = true;
        boolean flag2 = true;
        boolean flag3 = true;
        this.website = "";
        this.name = "";
        this.password = "";
        for (String i : list) {
            if (flag1 && flag2) {
                flag2 = false;
                this.website = i;
            }
            else if (flag1 && !flag2) {
                flag1 = false;
                this.name = i;
            }
            else {
                if (flag3) {
                    flag3 = false;
                }
                else {
                    this.password += "`";
                }
                this.password += i;
            }
        }
    }
    
    public String get_name() {
        return name;
    }
    public String get_website() {
        return website;
    }
    public String get_password() {
        return password;
    }
    
    public boolean set_name(String new_name) {
        if (new_name.contains("`")) {
            return false;
        }
        else {
            this.name = new_name;
            return true;
        }
    }
    
    public String toString() {
        return website + "`" + name + "`" + password;
    }
}

class sort_by_website implements Comparator<Password> {
    public int compare(Password a, Password b) {
        return a.get_website().compareTo(b.get_website());
    }
}

class sort_by_name implements Comparator<Password> {
    public int compare(Password a, Password b) {
        return a.get_name().compareTo(b.get_name());
    }
}

class sort_by_password implements Comparator<Password> {
    public int compare(Password a, Password b) {
        return a.get_password().compareTo(b.get_password());
    }
}
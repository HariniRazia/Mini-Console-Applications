import java.time.LocalDate;
import java.util.*;
class Program{
    private static Scanner sc = new Scanner(System.in);
    static int accId = 1;
    public static void main(String[] args){
        Account.acc.add(new Account(accId, "Harini", "harini@gmail.com", "48", 50000));
        accId++;
        Account.acc.add(new Account(accId, "Razia", "razia@gmail.com", "123", 30000));
        accId++;
        Account.acc.add(new Account(accId, "Saffrin", "saffrin@gmail.com", "123", 40000));
        accId++;
        Account.acc.add(new Account(accId, "Abhi", "abhi@gmail.com", "123", 50000));
        accId++;
        Account.acc.add(new Account(accId, "Ravi", "ravi@gmail.com", "123", 40000));
        accId++;

        Expense.exp.add(new Expense(User.eId, "Abhi", "Snacks", User.cDate , 200 ));
        User.eId++;

        Group.grp.add(new Group("Abhi", "Snacks", 0));
        Group.grp.add(new Group("Harini", "Snacks", 100));
        Group.grp.add(new Group("Razia", "Snacks", 100));

        Expense.nonexp.add(new Expense(User.noneId, "Ravi", "Grocery", User.cDate, 300));
        User.noneId++;

        Group.nongrp.add(new Group("Ravi", "Grocery", 100 ));
        Group.nongrp.add(new Group("Harini", "Grocery", 200));

        System.out.print("\033[H\033[2J");
        System.out.println("1. LogIn");
        System.out.println("2. SignIn");
        System.out.println("3. Exit");
        int op = sc.nextInt();
        switch(op){
            case 1:
                login();
                break;
            case 2:
                signin();
                break;
            case 3:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid input");
        }
    }

    static void signin(){
        System.out.print("\033[H\033[2J");
        System.out.println("Enter UserName:");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.println("Enter UserMail:");
        String mail = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.println("Enter Password:");
        String pass = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.println("Enter Wallet Amount:");
        int amt = sc.nextInt();
        Account.acc.add(new Account(accId,name,mail,pass,amt));
        accId++;
        System.out.println();
        System.out.println("Account Created");
        System.out.println();
        System.out.println("Press ENTER for back");
        try{
            System.in.read();
            Program.main(null);
        }
        catch (Exception e) {}

    }

    static void login(){
        System.out.print("\033[H\033[2J");
        System.out.println("Enter User Id:");
        sc.nextLine();
        String name = sc.nextLine();
        boolean n = false;
        boolean p = false;
        for(int i=0;i<Account.acc.size();i++){
            if(name.equals(Account.acc.get(i).aMail)){
            n = true;
            System.out.print("\033[H\033[2J");
            System.out.println("Enter Password:");
            String pass = sc.nextLine();
            if (pass.equals(Account.acc.get(i).aPass)) {
                p = true;
                User.cUser = Account.acc.get(i).aName;
                User.function();
                break;
            }
        }
    }
    if(!n){
        System.out.println("Invalid User ID");
        System.out.println();
    System.out.println("Press ENTER for back");
    try{
        System.in.read();
        Program.main(null);
    }
    catch(Exception e) {}
    }
    else if(!p){
        System.out.println("Invalid Password");
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            Program.main(null);
        } catch (Exception e) {
        }
    }
    }
    
}

class User{
    static private Scanner sc = new Scanner(System.in);
    static String cUser = "";
    static int eId = 1;
    static int noneId = 1;
    static ArrayList<String> history = new ArrayList<>();

    static void function(){
        System.out.print("\033[H\033[2J");
        System.out.println("1. Groups");
        System.out.println("2. Manage Wallet");
        System.out.println("3. Dues");
        System.out.println("4. history");
        System.out.println("5. Logout");
        int op = sc.nextInt();
        switch(op){
            case 1:
                groupTyp();
                break;
            case 2:
                wallet();
                break;
            case 3:
                dues();
                break;
            case 4:
                history();
                break;
            case 5:
                Program.main(null);
                break; 
            default:
                System.out.println("Invalid input");
                System.out.println();
                System.out.println("Press ENTER for back");
                try{
                    System.in.read();
                    Program.main(null);
                }
                 catch (Exception e) {}
        }
    }

    static void wallet(){
        System.out.print("\033[H\033[2J");
        for(int j=0;j<Account.acc.size();j++){
                if(Account.acc.get(j).aName.equals(cUser)){
                    System.out.println("You Current Wallet Amount: Rs." + Account.acc.get(j).aWallet);
                    System.out.println();
                    System.out.println("Do you want to Add Amount? y/n");
                    String s = sc.next().toLowerCase();
                    if(s.equals("y")){
                        System.out.println("Enter Amount: ");
                        int amt = sc.nextInt();
                        Account.acc.get(j).aWallet = Account.acc.get(j).aWallet + amt;
                        System.out.println("Amount Added to your Wallet");
                    }

                }
            }
            System.out.println();
            System.out.println("Press ENTER for back");
            try {
                System.in.read();
                function();
            } catch (Exception e) {
        }
    }


    static void dues(){
        System.out.print("\033[H\033[2J");
        System.out.println("1. View Dues");
        System.out.println("2. Pay Dues");
        System.out.println("3. Back");
        int op = sc.nextInt();
        switch (op) {
            case 1:
                view();
                break;
            case 2:
                pay();
                break;
            case 3:
                function();
                break;
            default:
                System.out.println("Invalid input");
                System.out.println();
                System.out.println("Press ENTER for back");
                try {
                    System.in.read();
                    function();
                } catch (Exception e) {
                }
        }
    }


    static void pay(){
        System.out.print("\033[H\033[2J");
        for (int i = 0; i < Expense.nonexp.size(); i++) {
            System.out.println(i + 1 + ". " + Expense.nonexp.get(i).expName);
        }
        int n = Expense.nonexp.size();
        for (int i = 0; i < Expense.exp.size(); i++) {
            System.out.println(n+ i + 1 + ". " + Expense.exp.get(i).expName);
        }
        System.out.println();
        System.out.println("Enter Expense name:");
        sc.nextLine();
        String eName = sc.nextLine();
        boolean r = false;
        if(nongroup.contains(eName)){
        for (int i = 0; i < Expense.nonexp.size(); i++) {
            if (Expense.nonexp.get(i).expName.equals(eName)) {
                r = true;
                for (int j = 0; j < Group.nongrp.size(); j++) {
                    if (Group.nongrp.get(j).expName.equals(eName) && Group.nongrp.get(j).fName.equals(cUser)) {
                        if (Group.nongrp.get(j).amt == 0) {
                            System.out.print("\033[H\033[2J");
                            System.out.println("No dues left");
                        } else {
                            System.out.print("\033[H\033[2J");
                            System.out.println("Your Due Balance : " + Group.nongrp.get(j).amt);
                            System.out.println();
                            System.out.println("Proceed to Pay? y/n");
                            String s = sc.next().toLowerCase();
                            if (s.equals("y")) {
                                System.out.println(
                                        "Enter Amount:");
                                int amt = sc.nextInt();
                                if (Group.nongrp.get(j).amt >= amt) {
                                    for (int k = 0; k < Account.acc.size(); k++) {
                                        if (Account.acc.get(k).aName.equals(cUser)) {
                                            if (amt < Account.acc.get(k).aWallet) {
                                                System.out.println("Payment done");
                                                history2(cUser, "payed", String.valueOf(amt), eName);
                                                Account.acc.get(k).aWallet = Account.acc.get(k).aWallet - amt;
                                                Group.nongrp.get(j).amt = Group.nongrp.get(j).amt - amt;
                                            }
                                            else {
                                                System.out.println("Insufficient Balance in your Wallet");
                                            }
                                        }
                                    }
                                }
                                else{
                                    System.out.println("You have entered Amount more than you Due Amount");
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    else if(group.contains(eName)){
        for(int i=0;i<Expense.exp.size();i++){
            if(Expense.exp.get(i).expName.equals(eName)){
                r = true;
                for(int j=0;j<Group.grp.size();j++){
                    if(Group.grp.get(j).expName.equals(eName) && Group.grp.get(j).fName.equals(cUser)){
                        if(Group.grp.get(j).amt == 0){
                            System.out.print("\033[H\033[2J");
                            System.out.println("No dues left");
                        }
                        else{
                            System.out.print("\033[H\033[2J");
                           System.out.println("Your Due Balance : "+ Group.grp.get(j).amt);
                           System.out.println();
                           System.out.println("Proceed to Pay? y/n");
                           String s = sc.next().toLowerCase();
                           if(s.equals("y")){
                               System.out.println(
                                   "Enter Amount:"
                               );
                               int amt = sc.nextInt();
                               if(Group.grp.get(j).amt >= amt){
                               for(int k=0;k<Account.acc.size();k++){
                                   if(Account.acc.get(k).aName.equals(cUser)){
                                       if(amt<Account.acc.get(k).aWallet){
                                           System.out.println("Payment done");
                                           history2(cUser, "payed", String.valueOf(amt), eName);
                                           Account.acc.get(k).aWallet = Account.acc.get(k).aWallet - amt;
                                           Group.grp.get(j).amt = Group.grp.get(j).amt - amt;
                                       }
                                       else{
                                           System.out.println("Insufficient Balance in your Wallet");
                                       }
                                   }
                               }
                            }
                            else {
                                System.out.println("You have entered Amount more than you Due Amount");
                            }
                           }
                        }
                    }
                }
            }
        }
    }
    if (!r) {
        System.out.println("Expense Name not found");
    } 
    System.out.println();
    System.out.println("Press ENTER for back");
    try {
        System.in.read();
        dues();
    } catch (Exception e) {
    }
    }

    static ArrayList<String> group = new ArrayList<>();
    static ArrayList<String> nongroup = new ArrayList<>();

    static void view(){
        System.out.print("\033[H\033[2J");
        System.out.printf("%-16s%-10s%-10s", "Expense Name", "Balance", "To");
        System.out.println();
        group.clear();
        nongroup.clear();
        for (int i = 0; i < Group.nongrp.size(); i++) {
            if (Group.nongrp.get(i).fName.equals(cUser)) {
                String c = "";
                for (int j = 0; j < Expense.nonexp.size(); j++) {
                    if (Group.nongrp.get(i).expName.equals(Expense.nonexp.get(j).expName)) {
                        c = Expense.nonexp.get(j).cName;
                    }
                }
                if (Group.nongrp.get(i).amt != 0) {
                    nongroup.add(Group.nongrp.get(i).expName);
                    if (c.equals(cUser)) {
                        System.out.printf("%-16s%-10s%-10s", Group.nongrp.get(i).expName, Group.nongrp.get(i).amt,
                                "Self");
                        System.out.println();
                    } else {
                        System.out.printf("%-16s%-10s%-10s", Group.nongrp.get(i).expName, Group.nongrp.get(i).amt, c);
                        System.out.println();
                    }
                }
            }
        }
        for (int i = 0; i < Group.grp.size(); i++) {
            if (Group.grp.get(i).fName.equals(cUser)) {
                String c = "";
                for (int j = 0; j < Expense.exp.size(); j++) {
                    if (Group.grp.get(i).expName.equals(Expense.exp.get(j).expName)) {
                        c = Expense.exp.get(j).cName;
                    }
                }
                if (Group.grp.get(i).amt != 0) {
                    group.add(Group.grp.get(i).expName);
                    System.out.printf("%-16s%-10s%-10s", Group.grp.get(i).expName, Group.grp.get(i).amt, c);
                    System.out.println();
                }
            }
        }
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            dues();
        } catch (Exception e) {
        }
    }

    static void groupTyp(){
        System.out.print("\033[H\033[2J");
        System.out.println("1. Group Expense");
        System.out.println("2. Non-Group Expense");
        System.out.println("3. Back");
        int op = sc.nextInt();
        switch(op){
            case 1:
                groupExp();
                break;
            case 2:
                nonGroup();
                break;
            case 3:
                function();
                break;
            default:
                System.out.println("Invalid input");
                System.out.println();
                System.out.println("Press ENTER for back");
                try{
                    System.in.read();
                    function();
                }   
                catch(Exception e) {}
        }
    }

    static void nonGroup() {
        System.out.print("\033[H\033[2J");
        System.out.println("1. Create Expense");
        System.out.println("2. Delete Expense");
        System.out.println("3. Add friends");
        System.out.println("4. Remove friends");
        System.out.println("5. Dues");
        System.out.println("6. Pay");
        System.out.println("7. Back");
        int op = sc.nextInt();
        switch (op) {
            case 1:
                createNG();
                break;
            case 2:
                dleteNG();
                break;
            case 3:
                 addFriNG();
                break;
            case 4:
                 removeFriNG();
                break;
            case 5:
                duesNG();
                break;
            case 6:
                payNG();
                break;
            case 7:
                function();
                break;
            default:
                System.out.println("Invalid input");
                System.out.println();
                System.out.println("Press ENTER for back");
                try {
                    System.in.read();
                    function();
                } catch (Exception e) {
                }
        }
    }

    static void dleteNG() {
        System.out.print("\033[H\033[2J");
        for (int i = 0; i < Expense.nonexp.size(); i++) {
            System.out.println(i + 1 + ". " + Expense.nonexp.get(i).expName);
        }
        System.out.println();
        System.out.println("Enter Expense Name:");
        sc.nextLine();
        String eName = sc.nextLine();
        boolean r = false;
        for (int i = 0; i < Expense.nonexp.size(); i++) {
            if (Expense.nonexp.get(i).expName.equals(eName)) {
                r = true;
                Expense.nonexp.remove(i);
                history1(cUser, "deleted", "Non-Group", eName);
            }
        }
        if (!r) {
            System.out.println("Expense Name not found");
        }
        for (int i = 0; i < Group.nongrp.size(); i++) {
            if (Group.nongrp.get(i).expName.equals(eName)) {
                Group.nongrp.remove(i);
            }
        }
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            nonGroup();
        } catch (Exception e) {
        }
    }

    static void createNG() {
        System.out.print("\033[H\033[2J");
        System.out.println("Enter Expense Name:");
        sc.nextLine();
        String eName = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.println("Enter Amount");
        int amt = sc.nextInt();
        System.out.print("\033[H\033[2J");
        System.out.println("Expense Added");
        Expense.nonexp.add(new Expense(noneId, cUser, eName, cDate, amt));
        noneId++;
        System.out.println();
        System.out.println("Enter Your Due:");
        int due = sc.nextInt();
        Group.nongrp.add(new Group(cUser, eName, due));
        System.out.println();
        history1(cUser, "created","Non-Group", eName);
        System.out.println("Please Add your Friend");
        try {
            System.in.read();
            nonGroup();
        } catch (Exception e) {
        }
    }

    static void addFriNG() {
        System.out.print("\033[H\033[2J");
        for (int i = 0; i < Expense.nonexp.size(); i++) {
            System.out.println(i + 1 + ". " + Expense.nonexp.get(i).expName);
        }
        System.out.println();
        System.out.println("Enter Expense Name:");
        sc.nextLine();
        String eName = sc.nextLine();
        int n = 0;
        boolean r = false;
        boolean b = false;
        for(int i=0;i<Group.nongrp.size();i++){
            if(Group.nongrp.get(i).expName.equals(eName)){
                n++;
            }
        }
        int total = 0;
        for (int i = 0; i < Expense.nonexp.size(); i++) {
            if (Expense.nonexp.get(i).expName.equals(eName)) {
                r = true;
                total = Expense.nonexp.get(i).amt;
                if(n<2){
                    System.out.print("\033[H\033[2J");
                    System.out.println("Enter Friend Name:");
                    String friend = sc.nextLine();
                    for (int k = 0; k < Account.acc.size(); k++) {
                        if (Account.acc.get(k).aName.equals(friend)) {
                            b = true;
                            Group.nongrp.add(new Group(Account.acc.get(k).aName, eName, 0));
                            history2(cUser, "added", Account.acc.get(k).aName, eName);
                            System.out.println("Friends Added to the Group");
                            solve(eName, total);
                            System.out.println();
                            System.out.println("Press ENTER for back");
                            try {
                                System.in.read();
                                nonGroup();
                            } catch (Exception e) {
                            }
                        }
                    }
                }
                else{
                    System.out.println("Only 2 / Group is allowed");
                }
            }
        }
        if (!r) {
            System.out.println("Expense Name not found");
        } else if (!b) {
            System.out.println("Friend Name not found");
        }
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            nonGroup();
        } catch (Exception e) {
        }
    }

    static void removeFriNG() {
        System.out.print("\033[H\033[2J");
        for (int i = 0; i < Expense.nonexp.size(); i++) {
            System.out.println(i + 1 + ". " + Expense.nonexp.get(i).expName);
        }
        System.out.println();
        System.out.println("Enter Expense Name:");
        sc.nextLine();
        String eName = sc.nextLine();
        boolean r = false;
        boolean b = false;
        int n = 0;
        for (int i = 0; i < Expense.nonexp.size(); i++) {
            if (Expense.nonexp.get(i).expName.equals(eName)) {
                r = true;
                System.out.print("\033[H\033[2J");
                System.out.println("Enter Friend Name:");
                String fName = sc.nextLine();
                for (int j = 0; j < Group.nongrp.size(); j++) {
                    if (Group.nongrp.get(j).fName.equals(fName)) {
                        b = true;
                        n = Group.nongrp.get(j).amt;
                        System.out.println("Friend removed");
                        Group.nongrp.remove(j);
                    }
                }
            }
        }
        for (int j = 0; j < Expense.nonexp.size(); j++) {
            if (Expense.nonexp.get(j).expName.equals(eName)) {
                Expense.nonexp.get(j).amt = Expense.nonexp.get(j).amt - n;
            }
        }
        if (!r) {
            System.out.println("Expense Name not found");
        } else if (!b) {
            System.out.println("Friend Name not found");
        }
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            nonGroup();
        } catch (Exception e) {
        }
    }

    static void duesNG() {
        System.out.print("\033[H\033[2J");
        System.out.printf("%-16s%-10s%-10s", "Expense Name", "Balance", "To");
        System.out.println();
        for (int i = 0; i < Group.nongrp.size(); i++) {
            if (Group.nongrp.get(i).fName.equals(cUser)) {
                String c = "";
                for (int j = 0; j < Expense.nonexp.size(); j++) {
                    if (Group.nongrp.get(i).expName.equals(Expense.nonexp.get(j).expName)) {
                        c = Expense.nonexp.get(j).cName;
                    }
                }
                if (Group.nongrp.get(i).amt != 0) {
                    if(c.equals(cUser)){
                        System.out.printf("%-16s%-10s%-10s", Group.nongrp.get(i).expName, Group.nongrp.get(i).amt, "Self");
                        System.out.println();
                    }
                    else{
                    System.out.printf("%-16s%-10s%-10s", Group.nongrp.get(i).expName, Group.nongrp.get(i).amt, c);
                    System.out.println();
                    }
                }
            }
        }
        System.out.println();
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            nonGroup();
        } catch (Exception e) {
        }
    }

    static void payNG() { 
        System.out.print("\033[H\033[2J");
        for (int i = 0; i < Expense.nonexp.size(); i++) {
            System.out.println(i + 1 + ". " + Expense.nonexp.get(i).expName);
        }
        System.out.println();
        System.out.println("Enter Expense Name:");
        sc.nextLine();
        String eName = sc.nextLine();
        boolean r = false;
        for (int i = 0; i < Expense.nonexp.size(); i++) {
            if (Expense.nonexp.get(i).expName.equals(eName)) {
                r = true;
                for (int j = 0; j < Group.nongrp.size(); j++) {
                    if (Group.nongrp.get(j).expName.equals(eName) && Group.nongrp.get(j).fName.equals(cUser)) {
                        if (Group.nongrp.get(j).amt == 0) {
                            System.out.print("\033[H\033[2J");
                            System.out.println("No dues left");
                        } else {
                            System.out.print("\033[H\033[2J");
                            System.out.println("Your Due Balance : " + Group.nongrp.get(j).amt);
                            System.out.println();
                            System.out.println("Proceed to Pay? y/n");
                            String s = sc.next().toLowerCase();
                            if (s.equals("y")) {
                                System.out.println(
                                        "Enter Amount:");
                                int amt = sc.nextInt();
                                if (Group.nongrp.get(j).amt >= amt) {
                                    for (int k = 0; k < Account.acc.size(); k++) {
                                        if (Account.acc.get(k).aName.equals(cUser)) {
                                            if (amt < Account.acc.get(k).aWallet) {
                                                System.out.println("Payment done");
                                                history2(cUser, "payed", String.valueOf(amt), eName);
                                                Account.acc.get(k).aWallet = Account.acc.get(k).aWallet - amt;
                                                Group.nongrp.get(j).amt = Group.nongrp.get(j).amt - amt;
                                            }
                                            else{
                                                System.out.println("Insufficient Balance in your Wallet");
                                            }
                                        }
                                    }
                                }
                                else{
                                    System.out.println("You have entered Amount more than your Due Amount");
                                }
                            }
                        }
                    }
                }
            }
        }
        if (!r) {
            System.out.println("Expense Name not found");
        } 
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            nonGroup();
        } catch (Exception e) {
        }
    }

    static void solve(String eName, int total){
        for (int i = 0; i < Group.nongrp.size(); i++) {
            if (Group.nongrp.get(i).expName.equals(eName)) {
                String c = "";
                for (int j = 0; j < Expense.nonexp.size(); j++) {
                    if (Expense.nonexp.get(j).expName.equals(eName)) {
                        c = Expense.nonexp.get(j).cName;
                    }
                }
                int amt = 0;
                if(Group.nongrp.get(i).fName.equals(c)){
                    amt = Group.nongrp.get(i).amt;
                }
                if (Group.nongrp.get(i).fName.equals(c)) {}
                else{
                    Group.nongrp.get(i).amt = total - amt;
                }
            }
        }
    }

    static void groupExp() {
        System.out.print("\033[H\033[2J");
        System.out.println("1. Create Expense");
        System.out.println("2. Delete Expense");
        System.out.println("3. Add friends");
        System.out.println("4. Remove friends");
        System.out.println("5. Dues");
        System.out.println("6. Pay");
        System.out.println("7. Track Expense");
        System.out.println("8. Back");
        int op = sc.nextInt();
        switch (op) {
            case 1:
                createG();
                break;
            case 2:
                dleteG();
                break;
            case 3:
                addFriG();
                break;
            case 4:
                removeFriG();
                break;
            case 5:
                duesG();
                break;
            case 6:
                payG();
                break;
            case 7:
                trackG();
                break;
            case 8:
                function();
                break;
            default:
                System.out.println("Invalid input");
                System.out.println();
                System.out.println("Press ENTER for back");
                try {
                    System.in.read();
                    function();
                } catch (Exception e) {
                }
        }
    }

    static void payG(){
        System.out.print("\033[H\033[2J");
        for (int i = 0; i < Expense.exp.size(); i++) {
            System.out.println(i + 1 + ". " + Expense.exp.get(i).expName);
        }
        System.out.println();
        System.out.println("Enter Expense Name:");
        sc.nextLine();
        String eName = sc.nextLine();
        boolean r = false;
        for(int i=0;i<Expense.exp.size();i++){
            if(Expense.exp.get(i).expName.equals(eName)){
                r = true;
                for(int j=0;j<Group.grp.size();j++){
                    if(Group.grp.get(j).expName.equals(eName) && Group.grp.get(j).fName.equals(cUser)){
                        if(Group.grp.get(j).amt == 0){
                            System.out.print("\033[H\033[2J");
                            System.out.println("No dues left");
                        }
                        else{
                            System.out.print("\033[H\033[2J");
                           System.out.println("Your Due Balance : "+ Group.grp.get(j).amt);
                           System.out.println();
                           System.out.println("Proceed to Pay? y/n");
                           String s = sc.next().toLowerCase();
                           if(s.equals("y")){
                               System.out.println(
                                   "Enter Amount:"
                               );
                               int amt = sc.nextInt();
                               if(Group.grp.get(j).amt >= amt){
                               for(int k=0;k<Account.acc.size();k++){
                                   if(Account.acc.get(k).aName.equals(cUser)){
                                       if(amt<Account.acc.get(k).aWallet){
                                           System.out.println("Payment done");
                                           history2(cUser, "payed", String.valueOf(amt), eName);
                                           Account.acc.get(k).aWallet = Account.acc.get(k).aWallet - amt;
                                           Group.grp.get(j).amt = Group.grp.get(j).amt - amt;
                                       }
                                       else{
                                           System.out.println("Insufficient Balance in you Wallet");
                                       }
                                   }
                               }
                            }
                            else{
                                System.out.println("You have entered Amount more than your Due Amount");
                            }
                           }
                        }
                    }
                }
            }
        }
        if (!r) {
            System.out.println("Expense Name not found");
        }
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            groupExp();
        } catch (Exception e) {
        }
    }

    static void trackG(){
        System.out.print("\033[H\033[2J");
        for(int i=0;i<Expense.exp.size();i++){
            System.out.println(i+1+ ". " + Expense.exp.get(i).expName );
        }
        System.out.println();
        System.out.println("Enter Expense Name:");
        sc.nextLine();
        boolean r = false;
        String eName = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.printf("%-15s%-15s", "FriendName", "Balance");
        System.out.println();
        for(int i=0;i<Expense.exp.size();i++){
            if(Expense.exp.get(i).expName.equals(eName)){
                r = true;
                String a = "";
                a = Expense.exp.get(i).cName;
                for(int j =0;j<Group.grp.size();j++){
                    if(Group.grp.get(j).expName.equals(eName)){
                        if(Group.grp.get(j).fName.equals(a)){}
                        else{
                            System.out.printf("%-15s%-15s", Group.grp.get(j).fName , Group.grp.get(j).amt);
                            System.out.println();
                        }
                    }
                }
            }
        }

        if (!r) {
            System.out.println("Expense Name not found");
        } 

        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            groupExp();
        } catch (Exception e) {
        }
    }

    static void duesG(){
        System.out.print("\033[H\033[2J");
        System.out.printf("%-16s%-10s%-10s","Expense Name","Balance","To");
        System.out.println();
        for(int i=0;i<Group.grp.size();i++){
            if(Group.grp.get(i).fName.equals(cUser)){
                String c = "";
                for (int j = 0; j < Expense.exp.size(); j++) {
                    if(Group.grp.get(i).expName.equals(Expense.exp.get(j).expName)){
                        c = Expense.exp.get(j).cName;
                    }
                }
                if(Group.grp.get(i).amt != 0){
                System.out.printf("%-16s%-10s%-10s", Group.grp.get(i).expName, Group.grp.get(i).amt,c);
                System.out.println();
                }
            }
        }
        System.out.println();
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            groupExp();
        } catch (Exception e) {
        }
    }

    static void removeFriG(){
        System.out.print("\033[H\033[2J");
        for (int i = 0; i < Expense.exp.size(); i++) {
            System.out.println(i + 1 + ". " + Expense.exp.get(i).expName);
        }
        System.out.println();
        System.out.println("Enter Expense Name:");
        sc.nextLine();
        String eName = sc.nextLine();
        int n = 0;
        boolean r = false;
        boolean b = false;
        for (int i = 0; i < Expense.exp.size(); i++) {
            if (Expense.exp.get(i).expName.equals(eName)) {
                r = true;
                System.out.print("\033[H\033[2J");
                System.out.println("Enter Friend Name:");
                String fName = sc.nextLine();
                for(int j=0;j<Group.grp.size();j++){
                    if(Group.grp.get(j).fName.equals(fName)){
                        b = true;
                        n = Group.grp.get(j).amt;
                        System.out.println("Friend removed");
                        Group.grp.remove(j);
                    }
                }
            }
        }

        if (!r) {
            System.out.println("Expense Name not found");
        } else if (!b) {
            System.out.println("Friend Name not found");
        }

        for (int j = 0; j < Expense.exp.size(); j++) {
            if (Expense.exp.get(j).expName.equals(eName)) {
                Expense.exp.get(j).amt = Expense.exp.get(j).amt - n;
            }
        }
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            groupExp();
        } catch (Exception e) {
        }
    }

    static void dleteG(){
        System.out.print("\033[H\033[2J");
        for (int i = 0; i < Expense.exp.size(); i++) {
            System.out.println(i + 1 + ". " + Expense.exp.get(i).expName);
        }
        System.out.println();
        System.out.println("Enter Expense Name:");
        sc.nextLine();
        boolean r = false;
        String eName = sc.nextLine();
        for(int i=0;i<Expense.exp.size();i++){
            if(Expense.exp.get(i).expName.equals(eName)){
                r = true;
                Expense.exp.remove(i);
                history1(cUser, "deleted", "Group", eName);
            }
        }
        if (!r) {
            System.out.println("Expense Name not found");
        } 
        for(int i=0;i<Group.grp.size();i++){
            if(Group.grp.get(i).expName.equals(eName)){
                Group.grp.remove(i);
            }
        }
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            groupExp();
        } catch (Exception e) {
        }
    }

    static LocalDate cDate = LocalDate.now();

    static void createG(){
        System.out.print("\033[H\033[2J");
        System.out.println("Enter Expense Name:");
        sc.nextLine();
        String eName = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.println("Enter Amount");
        int amt = sc.nextInt();
        System.out.print("\033[H\033[2J");
        System.out.println("Expense Added");
        Expense.exp.add(new Expense(eId, cUser, eName, cDate, amt));
        eId++;
        Group.grp.add(new Group(cUser, eName, 0));
        System.out.println();
        history1(cUser, "created", "Group", eName);
        System.out.println("Please Add your Friends");
        try{
            System.in.read();
            groupExp();
        }
        catch(Exception e) {}

    }

    static void addFriG(){
        System.out.print("\033[H\033[2J");
        for (int i = 0; i < Expense.exp.size(); i++) {
            System.out.println(i + 1 + ". " + Expense.exp.get(i).expName);
        }
        System.out.println();
        System.out.println("Enter Expense Name:");
        sc.nextLine();
        boolean r = false;
        boolean b = false;
        String eName = sc.nextLine();
        for(int i=0;i<Expense.exp.size();i++){
            if(Expense.exp.get(i).expName.equals(eName)){
                r = true;
                System.out.print("\033[H\033[2J");
                    System.out.println("Enter Friend Name:");
                    String friend = sc.nextLine();
                    for(int k=0;k<Account.acc.size();k++){
                        if(Account.acc.get(k).aName.equals(friend)){
                            b = true;
                            Group.grp.add(new Group(Account.acc.get(k).aName, eName, 0));
                            history2(cUser, "added", Account.acc.get(k).aName, eName);
                        }
                    }
                System.out.println("Friends Added to the Group");
            }
        }
        if(!r){
            System.out.println("Expense Name not found");
        }else if(!b){
            System.out.println("Friend Name not found");
        }
        int n = 0;
        for(int i = 0;i<Group.grp.size();i++){
            if(Group.grp.get(i).expName.equals(eName)){
                n++;
            }
        }
        n = n-1;
        String c = "";
        for (int j = 0; j < Expense.exp.size(); j++) {
            if (eName.equals(Expense.exp.get(j).expName)) {
                c = Expense.exp.get(j).cName;
            }
        }
        for (int i = 0; i < Group.grp.size(); i++) {
            if (Group.grp.get(i).expName.equals(eName)) {
                int a = 0;
                for(int j=0;j<Expense.exp.size();j++){
                if(Expense.exp.get(j).expName.equals(eName)){
                    a = Expense.exp.get(j).amt;
                }
                }
                if(Group.grp.get(i).fName.equals(c)){}
                else{
                Group.grp.get(i).amt = a/n;
                }
            }
        }
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            groupExp();
        } catch (Exception e) {
        }
    }

    static void history1(String a, String b, String c, String d){
        history.add(a+", You have "+ b + " the " + c + " Expense - " + d);
    }

    static void history2(String a,String b, String c,String d){
        history.add(a+", You have "+ b + " " + c + " to the Expense - " + d);
    }

    static void history(){
        System.out.print("\033[H\033[2J");

        if(history.size()==0){
            System.out.println("No historys yet!");
        }
        for(int i=0;i<history.size();i++){
            System.out.println(history.get(i));
        }
        System.out.println();
        System.out.println("Press ENTER for back");
        try{
            System.in.read();
            function();
        }
        catch(Exception e) {}
    }
}

class Expense{
    public int eID;
    public String expName;
    public String cName;
    public LocalDate date;
    public int amt;
    Expense(int eID, String cName,String expName,LocalDate date,int amt){
        this.eID = eID;
        this.cName = cName;
        this.expName = expName;
        this.date = date;
        this.amt = amt;
    }

    static List<Expense> exp = new ArrayList<>();
    static List<Expense> nonexp = new ArrayList<>();
}

class Group{
    public String fName;
    public String expName;
    public int amt;
    Group(String fName, String expName, int amt){
        this.fName = fName;
        this.expName = expName;
        this.amt = amt;
    }

    static List<Group> grp = new ArrayList<>();
    static List<Group> nongrp = new ArrayList<>();
}

class Account{
    public int aId;
    public String aName;
    public String aMail;
    public String aPass;
    public int aWallet;
    Account(int aId, String aName,String aMail,String aPass,int aWallet){
        this.aId = aId;
        this.aName = aName;
        this.aMail = aMail;
        this.aPass = aPass;
        this.aWallet = aWallet;
    }

    static List<Account> acc = new ArrayList<>();
}

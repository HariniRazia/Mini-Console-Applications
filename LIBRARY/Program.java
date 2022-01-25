import java.util.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Program{
    private static Scanner sc = new Scanner(System.in);
    static LocalDate cDate = LocalDate.now();
    static LocalDate uDate = cDate.plusDays(15);
        
    public static void main(String[] args){
        Admin.adminInfo.add(new Admin("Harini","harini@gmail.com","48"));

        Books.books.add(new Books(Books.bkId,"Panchatantra","Vishnu", 57402, 5, 500));
        Books.bkId++;
        Books.books.add(new Books(Books.bkId, "Akbar Birbal", "Monisha", 52481, 5, 500));
        Books.bkId++;
        Books.books.add(new Books(Books.bkId, "Tenali Raman", "Jayaprada", 64512, 5, 500));
        Books.bkId++;
        Books.books.add(new Books(Books.bkId, "Finding Nemo", "Andrew", 24891, 5, 600));
        Books.bkId++;
        Books.books.add(new Books(Books.bkId, "Baby's Day Out", "John", 64981, 5, 600));
        Books.bkId++;

        Borrower.user.add(new Borrower("Harini","razia@gmail.com","12345", 1500));


        System.out.print("\033[H\033[2J");
        System.out.println("1. Admin");
        System.out.println("2. Borrower");
        System.out.println("3. Exit");
        int op = sc.nextInt();
        switch(op){
            case 1:
                Admin.main();
                break;
            case 2:
                Borrower.main();
                break;
            case 3:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid input");
        }
    }
}

class Admin{
    private static Scanner sc = new Scanner(System.in);
    public String adName;
    public String adMail;
    public String adPass;
    Admin(String adName, String adMail, String adPass){
        this.adName = adName;
        this.adMail = adMail;
        this.adPass = adPass;
    }
    static List<Admin> adminInfo = new ArrayList<>();

    static int returnFine = 2;
    static double bookLost = 0.5;
    static double overDue = 0.8;
    static int lostCard = 10;

    static boolean flag = false;
    static List<String> cart = new ArrayList<>();

    static void main(){
        System.out.print("\033[H\033[2J");
        System.out.println("Enter AdminID: ");
        String name = sc.nextLine();
        boolean nm = false;
        boolean ps = false;
        for(int i=0;i<adminInfo.size();i++){
            if(adminInfo.get(i).adMail.equals(name)){
                nm = true;
                System.out.print("\033[H\033[2J");
                System.out.println("Enter Password: ");
                String pass = sc.next();
                if(adminInfo.get(i).adPass.equals(pass)){
                    ps = true;
                    adFct();
                }
            }
        }
        if(!nm){
            System.out.println("Invalid ID");
        }
        else if(!ps){
            System.out.println("Invalid Password");
        }
        System.out.println();
        System.out.println("Press ENTER for back");
        try{
            System.in.read();
            Program.main(null);
        }
        catch(Exception e) {}
    }

    static void adFct(){
        System.out.print("\033[H\033[2J");
        System.out.println("1. View & Modify books");
        System.out.println("2. Add Admin");
        System.out.println("3. Add Borrower");
        System.out.println("4. Manage Fine Amount");
        System.out.println("5. Books Status");
        System.out.println("6. Search books");
        System.out.println("7. Back");
        int op = sc.nextInt();
        switch (op){
            case 1:
                modifybooks();
                break;
            case 2:
                addAdmin();
                break;
            case 3:
                flag = true;
                addUser();
                break;
            case 4:
                manageFine();
                break;
            case 5:
                miniStat();
                break;
            case 6:
                search();
                break;
            case 7:
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
                catch(Exception e) {}
        } 
    }

    static void manageFine(){
        System.out.print("\033[H\033[2J");
        System.out.println("Return fine/day : ");
        int a = sc.nextInt();
        returnFine = a;
        System.out.println();
        System.out.println("Over due fine :");
        double b = sc.nextDouble();
        overDue = b;
        System.out.println();
        System.out.println("Book Lost fine :");
        double c = sc.nextDouble();
        bookLost = c;
        System.out.println();
        System.out.println("Membership card lost fine:");
        int d = sc.nextInt();
        lostCard = d;

        System.out.println();
        System.out.println("Fine amount updated");
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            adFct();
        } catch (Exception e) {
        }
    }

    static void search(){
        System.out.print("\033[H\033[2J");
        System.out.println("Enter Book Name: ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.println();
        System.out.println("Enter ISBN number: ");
        int isbn = sc.nextInt();
        System.out.print("\033[H\033[2J");
        System.out.printf("%-10s%-20s%-15s%-15s%-15s%-15s", "ID", "NAME", "AUTHOR", "ISBN No.", "STOCK", "RATE");
        System.out.println();
        System.out.println();
        boolean r = false;
        for (int i = 0; i < Books.books.size(); i++) {
            if (Books.books.get(i).bName.equals(name) && (Books.books.get(i).isbn == isbn)) {
                r = true;
                System.out.printf("%-10s%-20s%-15s%-15s%-15s%-15s", Books.books.get(i).bId, Books.books.get(i).bName, Books.books.get(i).bAuthor,Books.books.get(i).isbn,Books.books.get(i).bQuant,Books.books.get(i).bRate);
            }
        }
        if (!r) {
            System.out.println("Book not found");
        }
        System.out.println();
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            adFct();
        } catch (Exception e) {
        }
    }

    static void miniStat(){
        System.out.print("\033[H\033[2J");
        System.out.println("1. More Frequently Borrowed Books");
        System.out.println("2. Less Frequently Borrowed Books");
        System.out.println("3. Not Borrowed Books");
        System.out.println("4. Over due'd Books");
        System.out.println("5. Status of Books");
        System.out.println("6. Back");
        int op = sc.nextInt();
        switch(op){
            case 1:
                moreFreq();
                break;
            case 2:
                lessFreq();
                break;
            case 3:
                none();
                break;
            case 4:
                overdue();
                break;
            case 5:
                status();
                break;
            case 6:
                adFct();
                break;
            default:
                System.out.println("Invalid input");
                System.out.println();
                System.out.println("Press ENTER for back");
                try{
                    System.in.read();
                    adFct();
                }
                catch(Exception e) {}
        }
    }

    static void overdue(){
        System.out.print("\033[H\033[2J");
        System.out.println("Enter return Date: ");
        int n = sc.nextInt();
        System.out.print("\033[H\033[2J");
        System.out.printf("%-10s%-20s%-15s%-15s", "ID", "NAME", "AUTHOR", "ISBN No.");
        System.out.println();
        for(int i=0;i<Borw.bro2.size();i++){
        LocalDate newDate = Program.cDate.plusDays(n);
        int result = Borw.bro2.get(i).to.compareTo(newDate);
        if (result < 0) {
            System.out.printf("%-10s%-20s%-15s%-15s", Borw.bro2.get(i).bId , Borw.bro2.get(i).bName, 
                    Borw.bro2.get(i).bAuthor, Borw.bro2.get(i).isbn);
                    System.out.println();
        }
    }
    System.out.println();
    System.out.println("Press ENTER for back");
    try {
        System.in.read();
        miniStat();
    } catch (Exception e) {
    }
    }

    static void none() {
        System.out.print("\033[H\033[2J");
        System.out.printf("%-10s%-20s%-15s%-15s", "ID", "NAME", "AUTHOR", "ISBN No.");
        for (int i = 0; i < Books.books.size(); i++) {
            if (Books.books.get(i).bQuant == 5) {
                System.out.println();
                System.out.println();
                System.out.printf("%-10s%-20s%-15s%-15s", Books.books.get(i).bId, Books.books.get(i).bName,
                        Books.books.get(i).bAuthor, Books.books.get(i).isbn);
                System.out.println();
            }
        }
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            miniStat();
        } catch (Exception e) {
        }
    }

    static void lessFreq() {
        System.out.print("\033[H\033[2J");
        System.out.printf("%-10s%-20s%-15s%-15s", "ID", "NAME", "AUTHOR", "ISBN No.");
        for (int i = 0; i < Books.books.size(); i++) {
            if (Books.books.get(i).bQuant == 4 ) {
                System.out.println();
                System.out.println();
                System.out.printf("%-10s%-20s%-15s%-15s", Books.books.get(i).bId, Books.books.get(i).bName,
                        Books.books.get(i).bAuthor, Books.books.get(i).isbn);
                System.out.println();
            }
        }
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            miniStat();
        } catch (Exception e) {
        }
    }

    static void moreFreq(){
        System.out.print("\033[H\033[2J");
        System.out.printf("%-10s%-20s%-15s%-15s", "ID", "NAME", "AUTHOR", "ISBN No.");
        for(int i=0;i<Books.books.size();i++){
            if(Books.books.get(i).bQuant <= 3 ){
                System.out.println();
                System.out.println();
                    System.out.printf("%-10s%-20s%-15s%-15s", Books.books.get(i).bId, Books.books.get(i).bName, Books.books.get(i).bAuthor, Books.books.get(i).isbn);
                    System.out.println();
                }
            }
        System.out.println();
        System.out.println("Press ENTER for back");
        try{
            System.in.read();
            miniStat();
        }
        catch (Exception e) {}
    }

    static void status(){
        System.out.print("\033[H\033[2J");
        System.out.printf("%-15s%-10s%-20s%-15s%-15s%-15s%-15s","USER", "ID", "NAME", "AUTHOR", "ISBN No.", "RATE", "DUE");
        System.out.println();
        System.out.println();
        for (int i = 0; i < Borw.bro2.size(); i++) {
            System.out.printf("%-15s%-10s%-20s%-15s%-15s%-15s%-15s",
                    Borw.bro2.get(i).cName, Borw.bro2.get(i).bId, Borw.bro2.get(i).bName,
                    Borw.bro2.get(i).bAuthor, Borw.bro2.get(i).isbn, Borw.bro2.get(i).bRate,
                    Borw.bro2.get(i).to);
            System.out.println();
        }
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            miniStat();
        } catch (Exception e) {
        }
    }

    static void addUser(){
        System.out.print("\033[H\033[2J");
        System.out.println("Enter UserName: ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.println("Enter User ID: ");
        String mail = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.println("Enter Password: ");
        String pass = sc.next();

        Borrower.user.add(new Borrower(name, mail, pass, 1500));
        System.out.println("Borrower Added");
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            if(flag){
                adFct();
            }
            else{
                Program.main(null);
            }
            
        } catch (Exception e) {
        }
    }

    static void addAdmin(){
        System.out.print("\033[H\033[2J");
        System.out.println("Enter Admin Name:");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.println("Enter Admin Mail");
        String mail = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.println("Enter Password: ");
        String pass = sc.nextLine();

        adminInfo.add(new Admin(name,mail,pass));
        System.out.println("Admin Added");
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            Program.main(null);
        } catch (Exception e) {
        }
    }

    static void modifybooks(){
        System.out.print("\033[H\033[2J");
        System.out.println("1. View Books");
        System.out.println("2. Add Books");
        System.out.println("3. Delete Books");
        System.out.println("4. Update Books");
        System.out.println("5. Back");
        int op = sc.nextInt();
        switch (op){
            case 1:
                viewBook();
                break;
            case 2:
                addBook();
                break;
            case 3:
                dlteBook();
                break;
            case 4:
                uptBook();
                break;
            case 5:
                 adFct();
                 break;
            default:
                System.out.println("Invalid input");
                System.out.println();
                System.out.println("Press ENTER for back");
                try{
                    System.in.read();
                    adFct();
                }
                catch(Exception e) {}
        }
    }

    static void uptBook(){
        System.out.print("\033[H\033[2J");
        System.out.println("Enter BookName: ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.println("Enter ISBN number: ");
        int isbn = sc.nextInt();
        boolean r = false;
        for (int i = 0; i < Books.books.size(); i++) {
            if (Books.books.get(i).bName.equals(name) && (Books.books.get(i).isbn == isbn)) {
                r = true;
                System.out.print("\033[H\033[2J");
                System.out.println("Enter new ISBN number: ");
                isbn = sc.nextInt();
                Books.books.get(i).isbn = isbn;
                System.out.print("\033[H\033[2J");
                System.out.println("Enter new Quantity:");
                int qua = sc.nextInt();
                Books.books.get(i).bQuant = qua;
                System.out.print("\033[H\033[2J");
                System.out.println("Enter new Rate: ");
                int rate = sc.nextInt();
                Books.books.get(i).bRate = rate;
                System.out.print("\033[H\033[2J");
                System.out.println("Book Updated Successfully");
            }
        }
        if(!r){
            System.out.println("Book not found");
        }
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            modifybooks();
        } catch (Exception e) {
        }

    }

    static void viewBook(){
        System.out.print("\033[H\033[2J");
        System.out.printf("%-10s%-20s%-15s%-15s%-15s%-15s","ID","NAME","AUTHOR","ISBN No.","STOCK","RATE");
        System.out.println();
        System.out.println();
        for(int i=0;i<Books.books.size();i++){
            System.out.printf("%-10s%-20s%-15s%-15s%-15s%-15s",Books.books.get(i).bId, Books.books.get(i).bName,
                    Books.books.get(i).bAuthor, Books.books.get(i).isbn, Books.books.get(i).bQuant,
                    Books.books.get(i).bRate);
            System.out.println();
        }

        System.out.println();
        System.out.println("Press ENTER for back");
        try{
            System.in.read();
            modifybooks();
        }
        catch (Exception e){}
    }

    static void dlteBook(){
        System.out.print("\033[H\033[2J");
        System.out.println("Enter bookName: ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.println("Enter ISBN number: ");
        int isbn = sc.nextInt();
        boolean r = false;
        for(int i=0;i<Books.books.size();i++){
            if(Books.books.get(i).bName.equals(name) && (Books.books.get(i).isbn == isbn)){
                r = true;
                Books.books.remove(i);
                System.out.print("\033[H\033[2J");
                System.out.println("Book Removed Successfully");
            }
        }

        if(!r){
            System.out.println("Book not found");
        }
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            modifybooks();
        } catch (Exception e) {
        }
    }

    static void addBook(){
        System.out.print("\033[H\033[2J");
        System.out.println("Enter BookName: ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.println("Enter AuthorName: ");
        String author = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.println("Enter ISBN number: ");
        int isbn = sc.nextInt();
        System.out.print("\033[H\033[2J");
        System.out.println("Enter Quantity: ");
        int qua = sc.nextInt();
        System.out.print("\033[H\033[2J");
        System.out.println("Enter Rate: ");
        int rate = sc.nextInt();

        Books.books.add(new Books(Books.bkId, name, author, isbn, qua, rate));
        Books.bkId++;
        System.out.print("\033[H\033[2J");
        System.out.println("Book Added Successfully");
        System.out.println();
        System.out.println("Press ENTER for back");
        try{
            System.in.read();
            modifybooks();
        }
        catch (Exception e){}
    }
}

class Books{
    public int bId;
    public String bName;
    public String bAuthor;
    public int isbn;
    public int bQuant;
    public int bRate;

    Books(int bId,String bName, String bAuthor, int isbn, int bQuant, int bRate){
        this.bId = bId;
        this.bName = bName;
        this.bAuthor = bAuthor;
        this.isbn = isbn;
        this.bQuant = bQuant;
        this.bRate = bRate;
    }

    static List<Books> books = new ArrayList<>();
    static int bkId = 1;
}

class Borw{
    public int bId;
    public String cName;
    public String bName;
    public String bAuthor;
    public int isbn;
    public int bRate;
    public LocalDate from;
    public LocalDate to;

    Borw(String cName, int bId,String bName, String bAuthor, int isbn, int bRate, LocalDate from,LocalDate to ){
        this.cName = cName;
        this.bId = bId;
        this.bName = bName;
        this.bAuthor = bAuthor;
        this.isbn = isbn;
        this.bRate = bRate;
        this.from = from;
        this.to = to;
    }

    static List<Borw> bro = new ArrayList<>();
    static List<Borw> bro2 = new ArrayList<>();
}


class Borrower{
    
    private static Scanner sc = new Scanner(System.in);
    static int times;
    static String cName = "";
    
    public static int iniAmt;
    public String uName;
    public String uMail;
    public String uPass;

    Borrower(String uName, String uMail, String uPass, int iniAmt){
        this.uName = uName;
        this.uMail = uMail;
        this.uPass = uPass;
        this.iniAmt = iniAmt;
    }

    static ArrayList<Borrower> user = new ArrayList<>();
    static ArrayList<String> hist = new ArrayList<>();

    static void main(){
        System.out.print("\033[H\033[2J");
        System.out.println("1. Login");
        System.out.println("2. SignUp");
        System.out.println("3. Back");
        int op = sc.nextInt();
        switch(op){
            case 1:
                login();
                break;
            case 2:
                Admin.flag = false;
                Admin.addUser();
                break;
            case 3:
                Program.main(null);
                break;
            default:
                System.out.println("Invalid Input");
                System.out.println();
                System.out.println("Press ENTER for back");
                try{
                    System.in.read();
                    Program.main(null);
                }
                catch(Exception e) {}
        }
    }

    static void login(){
        System.out.print("\033[H\033[2J");
        System.out.println("Enter User ID: ");
        sc.nextLine();
        String mail = sc.nextLine();
        boolean nm = false;
        boolean ps = false;
        for (int i = 0; i < user.size(); i++) {
            if (user.get(i).uMail.equals(mail)) {
                nm = true;
                System.out.print("\033[H\033[2J");
                System.out.println("Enter Password: ");
                String pass = sc.next();
                if (user.get(i).uPass.equals(pass)) {
                    ps = true;
                    cName = user.get(i).uName;
                    times = 1;
                    Borw.bro.clear();
                    uFct();
                }
            }
        }
        if (!nm) {
            System.out.println("Invalid ID");
        } else if (!ps) {
            System.out.println("Invalid Password");
        }
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            Program.main(null);
        } catch (Exception e) {
        }
    }

    static void uFct(){
         System.out.print("\033[H\033[2J");
         System.out.println("1. View Books");
         System.out.println("2. Search Book");
         System.out.println("3. Renew books");
         System.out.println("4. Return book");
         System.out.println("5. history");
         System.out.println("6. Cart");
         System.out.println("7. Back");
         int op = sc.nextInt();
         switch(op){
            case 1:
                viewBook();
                break;
            case 2:
                search();
                break;
            case 3:
                renew();
                break;
            case 4:
                retrn();
                break;
            case 5:
                history();
                break;
            case 6:
                cart();
                break;
            case 7:
                main();
                break;
            default:
                System.out.println("Invalid input");
                System.out.println();
                System.out.println("Press ENTER for back");
                try{
                    System.in.read();
                    main();
                }
                catch(Exception e) {}
         }
    }

    static void history(){
        System.out.print("\033[H\033[2J");
        for(int i=0;i<hist.size();i++){
        System.out.println(hist.get(i));
        }

        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            uFct();
        } catch (Exception e) {
        }
    }

    static void history2(String a, String b, String c){
        hist.add(a+", You have " + b +" " +c);
    }

    static void retrn(){
        System.out.print("\033[H\033[2J");
        System.out.println("Enter Book Name:");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.println();
        System.out.println("Enter ISBN number :");
        int isbn = sc.nextInt();
        System.out.println();
        System.out.println("Enter return Date: ");
        int n = sc.nextInt();
        int fine = fine(n);
        if(fine == 0){
            System.out.println("No fine, You have returned before DUE Date");
        }
        else{
            history2(cName, "fined Rs.", String.valueOf(fine));
            System.out.println("You will be charged Rs." + fine + " for you DELAY!");
            for(int i=0;i<Borrower.user.size();i++){
            if(Borrower.user.get(i).uName.equals(cName)){
                Borrower.user.get(i).iniAmt = Borrower.user.get(i).iniAmt - fine;
            }
            }
        }
        
        System.out.println();
        System.out.println("Do you have Membership Card? y/n");
        String s = sc.next().toLowerCase();
        if(s.equals("n")){
            fine = 10;
            history2(cName, "fined Rs.", String.valueOf(fine));
            System.out.println("You will be charged Rs." + fine);
            for (int i = 0; i < Borrower.user.size(); i++) {
                if (Borrower.user.get(i).uName.equals(cName)) {
                    Borrower.user.get(i).iniAmt = Borrower.user.get(i).iniAmt - fine;
                }
            }
        }

        System.out.println();
        System.out.println("Lost the Book? y/n");
        s = sc.next().toLowerCase();
        if(s.equals("y")){
            for(int j=0;j<Books.books.size();j++){
            if(Books.books.get(j).bName.equals(name) && (Books.books.get(j).isbn == isbn)){
            fine = (int)((Admin.bookLost)*(Books.books.get(j).bRate));
            history2(cName, "fined Rs.", String.valueOf(fine));
            System.out.println("You will be charged Rs." + fine);
            for (int i = 0; i < Borrower.user.size(); i++) {
                if (Borrower.user.get(i).uName.equals(cName)) {
                    Borrower.user.get(i).iniAmt = Borrower.user.get(i).iniAmt - fine;
                }
            }
        }
        }
        }
        else{
            for(int j=0;j<Borw.bro2.size();j++){
            if(Borw.bro2.get(j).bName.equals(name) && (Borw.bro2.get(j).isbn == isbn)){
                
            history2(cName, "returned the Book:", Borw.bro2.get(j).bName);
            Borw.bro2.remove(j);
            }
            }
            for(int j=0;j<Books.books.size();j++){
            if(Books.books.get(j).bName.equals(name) && (Books.books.get(j).isbn == isbn)){
                Books.books.get(j).bQuant = Books.books.get(j).bQuant +1;
            }
        }
         }
         System.out.println();
         System.out.println("Book Return Successfully");
         System.out.println();
         System.out.println("Press ENTER for back");
         try{
             System.in.read();
             uFct();
         }
         catch(Exception e) {}
    }

    static int fine(int n){
        int fine = 0;
        LocalDate newDate = Program.cDate.plusDays(n);
        int result = Program.uDate.compareTo(newDate);
        
        if (result == 0 && result > 0) {
            fine = 0;
        } 
        else if (result < 0) {
            long no = ChronoUnit.DAYS.between(Program.uDate, newDate);
            fine = (int) (no*2);
        }
        return fine;
    }

    static void renew(){
        System.out.print("\033[H\033[2J");
        System.out.println("Enter Book Name:");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.println("Enter ISBN number: ");
        int isbn = sc.nextInt();
        for (int i = 0; i < Borw.bro2.size(); i++) {
            if (Borw.bro2.get(i).bName.equals(name) && (Borw.bro2.get(i).isbn == isbn)) {
                System.out.println("Confirm renewal? y/n");
                String s = sc.next().toLowerCase();
                if(s.equals("y")){
                    Borw.bro2.get(i).from = Program.cDate;
                    Borw.bro2.get(i).to = Program.uDate;
                    System.out.println();
                    System.out.println("Book renewed");
                    System.out.println("Your new Due Date is : "+ Program.uDate);
                }
            }
        }
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            uFct();
        } catch (Exception e) {
        }
    }

    static void viewBook() {
        System.out.print("\033[H\033[2J");
        System.out.printf("%-10s%-20s%-15s%-15s%-15s%-15s", "ID", "NAME", "AUTHOR", "ISBN No.", "STOCK", "RATE");
        System.out.println();
        System.out.println();
        for (int i = 0; i < Books.books.size(); i++) {
            System.out.printf("%-10s%-20s%-15s%-15s%-15s%-15s", Books.books.get(i).bId, Books.books.get(i).bName,
                    Books.books.get(i).bAuthor, Books.books.get(i).isbn, Books.books.get(i).bQuant,
                    Books.books.get(i).bRate);
            System.out.println();
        }
        System.out.println();
        System.out.println("Select ID to Add to Cart:");
        int n = sc.nextInt();
        if(n==0){
            uFct();
        }
        else{
        Borw.bro.add(new Borw(cName, Books.books.get(n-1).bId,Books.books.get(n-1).bName,Books.books.get(n-1).bAuthor,Books.books.get(n-1).isbn,Books.books.get(n-1).bRate,Program.cDate,Program.uDate));
        System.out.println();
        System.out.println("Added to the cart");
        }
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            uFct();
        } catch (Exception e) {
        }
    }

    static void cart(){
        System.out.print("\033[H\033[2J");
        System.out.printf("%-10s%-20s%-15s%-15s%-15s%-15s", "ID", "NAME", "AUTHOR", "ISBN No.", "RATE","DUE date");
        System.out.println();
        System.out.println();
        for (int i = 0; i < Borw.bro.size(); i++) {
            System.out.printf("%-10s%-20s%-15s%-15s%-15s%-15s", Borw.bro.get(i).bId, Borw.bro.get(i).bName,
                    Borw.bro.get(i).bAuthor, Borw.bro.get(i).isbn, Borw.bro.get(i).bRate, Borw.bro.get(i).to);
            System.out.println();
        }

        System.out.println();
        for (int i = 0; i < Borw.bro.size(); i++) {
            System.out.println("Name: "+ Borw.bro.get(i).bName + "\nISBN No. : " + Borw.bro.get(i).isbn);
            if(times<=3){
            System.out.println("Do you want to Borrow? y/n");
            String s = sc.next().toLowerCase();
            if(s.equals("y")){
                 Borw.bro2.add(new Borw(cName,Borw.bro.get(i).bId, Borw.bro.get(i).bName,
                    Borw.bro.get(i).bAuthor, Borw.bro.get(i).isbn, Borw.bro.get(i).bRate, Borw.bro.get(i).from, Borw.bro.get(i).to));
                
                    times++;
                for(int j=0;j<Books.books.size();j++){
                    if(Books.books.get(j).bId == Borw.bro.get(i).bId){
                        Books.books.get(j).bQuant = Books.books.get(j).bQuant - 1;
                    }
                }
                history2(cName, "borrowed the Book:", Borw.bro.get(i).bName);
                System.out.println();
                System.out.println("Thank you for Borrowing");
                System.out.println("Your Due is : " + Program.uDate);
                System.out.println("Returning after due will be charged accordingly");
                System.out.println();
            }
        }
        else{
            System.out.println("Exceeded Max Limit");
        }
        }


        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            uFct();
        } catch (Exception e) {
        }
    }

    static void search() {
        System.out.print("\033[H\033[2J");
        System.out.println("Enter Book Name: ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.println();
        System.out.println("Enter ISBN number: ");
        int isbn = sc.nextInt();
        System.out.print("\033[H\033[2J");
        System.out.printf("%-10s%-20s%-15s%-15s%-15s%-15s", "ID", "NAME", "AUTHOR", "ISBN No.", "STOCK", "RATE");
        System.out.println();
        System.out.println();
        boolean r = false;
        for (int i = 0; i < Books.books.size(); i++) {
            if (Books.books.get(i).bName.equals(name) && (Books.books.get(i).isbn == isbn)) {
                r = true;
                System.out.printf("%-10s%-20s%-15s%-15s%-15s%-15s", Books.books.get(i).bId, Books.books.get(i).bName,
                        Books.books.get(i).bAuthor, Books.books.get(i).isbn, Books.books.get(i).bQuant,
                        Books.books.get(i).bRate);
            System.out.println();
            }
        }

        System.out.println();
        System.out.println("Select ID to Add to Cart:");
        int n = sc.nextInt();
        if(n>0 && n<=Books.books.size()){
        Borw.bro.add(new Borw(cName, Books.books.get(n - 1).bId, Books.books.get(n - 1).bName, Books.books.get(n - 1).bAuthor,
                Books.books.get(n - 1).isbn, Books.books.get(n - 1).bRate, Program.cDate, Program.uDate));
                System.out.println();
                System.out.println("Added to the cart");
        }

        if (!r) {
            System.out.println("Book not found");
        }
        System.out.println();
        System.out.println();
        System.out.println("Press ENTER for back");
        try {
            System.in.read();
            uFct();
        } catch (Exception e) {
        }
    }
}

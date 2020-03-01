package com.company.app;

import com.company.database.Database;
import com.company.database.Filemanager;
import com.company.database.NotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;


public class Javabanken {
    Filemanager filemanager = Filemanager.getInstance(); //Singelton
    Scanner scanner = new Scanner(System.in);
    Account currentAccount1;
    Account currentAccount2;
    AccountnumberGenerator ANG = new AccountnumberGenerator();
    ArrayList<Account> accounts;
    Database db = Database.getInstance();
    private Class<Account> accountClass;
    private java.lang.Object Object;


    public Javabanken() {
        accounts = filemanager.readFile("Javabanken", Account.class);


    }

    public void printMenu() {
        System.out.println("1: Deposit");
        System.out.println("2: Withdraw");
        System.out.println("3: Transfer");
        System.out.println("4: Add new customer");
        System.out.println("5: Edit customer name");
        System.out.println("6: Exit");
        int input = Integer.valueOf(scanner.nextLine());
        switch (input) {
            case 1:
                deposit();
                break;
            case 2:
                withdraw();
                break;
            case 3:
                transfer();
                break;
            case 4:
                createNewCustomer();
                break;
            case 5:
                editName();
                break;
            case 6:
            default:
        }
    }


    public void setAccount1() {
        long ssn = Validation.longNumber("Enter Social Sequrity Number: ");
        Optional<Account> acc1 = accounts.
                stream()
                .filter(account -> {
                    //System.out.println(account.getSsn());
                    return Long.valueOf(account.getSsn()) == ssn;
                })
                .findAny();
        if (acc1.isPresent()) {
            System.out.println("du skrev rätt SSN: " + acc1.get());
            currentAccount1 = acc1.get();
        } else {
            System.out.println("Social Security Number not found, try again");
            setAccount1();
        }

    }

    public void setAccount2() {
        long ssn = Validation.longNumber("Enter Social Sequrity Number: ");
        Optional<Account> acc2 = accounts.
                stream()
                .filter(account -> {
                    //System.out.println(account.getSsn());
                    return Long.valueOf(account.getSsn()) == ssn;
                })
                .findAny();
        if (acc2.isPresent()) {
            System.out.println("du skrev rätt ssn" + acc2.get());
            currentAccount2 = acc2.get();
        } else {
            System.out.println("Social Security Number not found, try again");
            setAccount2();
        }
    }

    public void deposit() {
        setAccount1();
        String balance = currentAccount1.getBalance();
        double amount = Validation.floatingNumber("Enter how much you want to deposit: ");
        currentAccount1.deposit(amount);
        System.out.println("New balance: " + currentAccount1.getBalance());
        String amountToFile = String.valueOf(currentAccount1.getBalance());
        Filemanager.edit("Javabanken", balance, amountToFile);

    }

    public void withdraw() {
        setAccount1();
        String balance = currentAccount1.getBalance();
        double amount = Validation.floatingNumber("Enter how much you want to withdraw: ");
        currentAccount1.withdraw(amount);
        System.out.println("New saldo: " + currentAccount1.getBalance());
        String amountToFile = String.valueOf(currentAccount1.getBalance());
        Filemanager.edit("Javabanken", balance, amountToFile);

    }

    public void transfer() {
        setAccount1();
        String balanceC1 = currentAccount1.getBalance();
        System.out.println("Current balance: " + currentAccount1.getBalance());
        setAccount2();
        String balanceC2 = currentAccount2.getBalance();
        double amount = Validation.floatingNumber("Enter how much you want to transfer: ");
        currentAccount1.transfer(currentAccount2, amount);
        System.out.println("Balance transferer:" + currentAccount1.getBalance());
        System.out.println("Balance reciever: " + currentAccount2.getBalance());
        String amountToFileTransferer = String.valueOf(currentAccount1.getBalance());
        String amountToFileReciever = String.valueOf(currentAccount2.getBalance());
        Filemanager.edit("Javabanken", balanceC1, amountToFileTransferer);
        Filemanager.edit("Javabanken", balanceC2, amountToFileReciever);
    }


    public void createNewCustomer() {
        getAnnotation();
        String name;
        name = Validation.string("Enter firstname and lastname: ");
        long ssn;
        ssn = Validation.longNumber("Enter Social Sequrity number: ");
        Account account = new Account(String.valueOf(ANG.randomNumber()), name, String.valueOf(ssn));
        accounts.add(account);
        db.write("Javabanken", "" + account, false);

    }

    public void editName() {
        setAccount1();
        String name = currentAccount1.getAccountHolder();
        String newName = Validation.string("Enter new name");
        Filemanager.edit("Javabanken", name, newName);

    }

    public void getAnnotation() {
        try {
            Field field = Account.class.getField("accountHolder");
            Annotation annotation = field.getAnnotation(NotNull.class);
            if (annotation instanceof NotNull) {
                NotNull customAnnotation = (NotNull) annotation;
                System.out.println("message: " + customAnnotation.message());
            }

        } catch (NoSuchFieldException nf) {

        }


    }
    }







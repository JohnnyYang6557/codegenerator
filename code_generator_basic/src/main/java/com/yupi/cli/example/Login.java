package com.yupi.cli.example;

import picocli.CommandLine;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;

public class Login implements Callable<Integer> {
    @Option(names = {"-u", "--user"}, arity = "0..1", description = "User name", interactive = true, prompt = "input your username: ")
    String user;

    @Option(names = {"-p", "--password"}, arity = "0..1", description = "Password", interactive = true, prompt = "input your password: ")
    String password;
    @Option(names = {"-cp", "--checkPassword"}, arity = "0..1", description = "Check Password", interactive = true, prompt = "check password: ")
    String checkPassword;

    public Integer call() throws Exception {
        System.out.println("user = " + user);
        System.out.println("password = " + password);
        System.out.println("checkPassword = " + checkPassword);
        return 0;
    }

    public static void main(String[] args) {
        new CommandLine(new Login()).execute("-u","admin", "-p", "-cp");
    }
}

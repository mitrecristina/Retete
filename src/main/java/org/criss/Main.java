package org.criss;
import org.apache.commons.lang3.tuple.Pair;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) throws IOException {

        if (args == null || args.length != 1) {
            throw new RuntimeException("Invalid Arguments");
        }

        String propFile = args[0];
        Properties props = new Properties();
        props.load(new FileInputStream(propFile));
        System.setProperty("inputfile", props.getProperty("inputfile"));
        System.setProperty("outputfile", props.getProperty("outputfile"));

        Readexcel excel = new Readexcel();

        List<Mancare> listamacare;

        listamacare = excel.metoda();
        Operatii o = new Operatii();


        Map<Pair<Integer,Long>, List<Mancare>> map = o.programzi(5, listamacare,1500);

        for (Map.Entry<Pair<Integer,Long>, List<Mancare>> entry : map.entrySet()) {

            Integer numarzile = entry.getKey().getLeft();

            List<Mancare> meniu = entry.getValue();

            StringBuilder sb = new StringBuilder();

            for (Mancare b : meniu) {

                sb.append(b.getNumemancare());
                sb.append(", " );
            }
            System.out.println("Numar zilei : " + numarzile + " ,"+ " Calorii : " + entry.getKey().getRight());
            System.out.print("Meniul zilei : " + sb.toString().replaceAll(", $",""));


            System.out.println();
            System.out.println();
        }

        //afisareMap(map);

        try {
            Write w = new Write(map);
            w.doWrite();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void afisareMap(Map<Integer, List<Mancare>> map)  {
        map.entrySet().stream().forEach(entry -> {
            System.out.println("Numarul Zilei: " + entry.getKey());
            System.out.println("Meniul Zilei : " + entry.getValue()
                    .stream()
                    .map(Mancare::getNumemancare)
                    .collect(Collectors.joining(",")));
        });
    }}










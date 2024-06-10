package org.criss;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

public class Operatii {

    public void omasa(List<Mancare> m) {

        List<Mancare> micdejun = new ArrayList<>();
        List<Mancare> pranz = new ArrayList<>();
        List<Mancare> cina = new ArrayList<>();

        for (Mancare bila : m) {
            if (bila.getMicdejun() == 'Y') {
                micdejun.add(bila);
            }
            if (bila.getPranz() == 'Y') {
                pranz.add(bila);
            }
            if (bila.getCina() == 'Y') {
                cina.add(bila);
            }
        }
        Collections.shuffle(micdejun);
        Collections.shuffle(pranz);
        Collections.shuffle(cina);
        System.out.println(micdejun.get(0).getNumemancare());
        if (Objects.equals(pranz.get(0).getNumemancare(), cina.get(0).getNumemancare())) {
            System.out.println(pranz.get(1).getNumemancare());
        } else {
            System.out.println(pranz.get(0).getNumemancare());
        }
        System.out.println(cina.get(0).getNumemancare());
    }

    public Map<Pair<Integer,Long>, List<Mancare>> programzi(int n, List<Mancare> listaMancare, long maxcalorii) {

        List<Mancare> micdejun = new ArrayList<>();
        List<Mancare> pranz = new ArrayList<>();
        List<Mancare> cina = new ArrayList<>();
        List<Mancare> gustare = new ArrayList<>();


        for (Mancare m : listaMancare) {
            if (m.getMicdejun() == 'Y') {
                micdejun.add(m);

            }
            if (m.getPranz() == 'Y') {
                pranz.add(m);

            }
            if (m.getCina() == 'Y') {
                cina.add(m);
            }
            if (m.getGustare() == 'Y') {
                gustare.add(m);
            }
        }

        Map<Pair<Integer,Long>, List<Mancare>> map = new LinkedHashMap<>();
        for (int a = 0; a < n; a++) {
            List<Mancare> ziuarespectiva = new LinkedList<>(); // renunt

            Collections.shuffle(micdejun);
            Collections.shuffle(pranz);
            Collections.shuffle(cina);
            Collections.shuffle(gustare);

            Set<String> mancaruri = new LinkedHashSet<>();

            Mancare md = getRandomElementNotSeenBefore(mancaruri, micdejun);
            mancaruri.add(md.getNumemancare());

            Mancare p = getRandomElementNotSeenBefore(mancaruri, pranz);
            mancaruri.add(p.getNumemancare());

            Mancare c = getRandomElementNotSeenBefore(mancaruri, cina);
            mancaruri.add(c.getNumemancare());

            Mancare g = getRandomElementNotSeenBefore(mancaruri, gustare);

            ziuarespectiva.add(md);
            ziuarespectiva.add(p);
            ziuarespectiva.add(c);

            long calorii = 0;

            calorii += regula3(md.getCalorii(), md.getGrame());
            calorii += regula3(p.getCalorii(), p.getGrame());
            calorii += regula3(c.getCalorii(), c.getGrame());

            if (calorii < maxcalorii) {
                ziuarespectiva.add(g);
                calorii += regula3(g.getCalorii(), g.getGrame());
            }


            map.put(new ImmutablePair<>(a+1, calorii), ziuarespectiva);

        }

        return map;


    }

    private Mancare getRandomElementNotSeenBefore(Set<String> set, List<Mancare> list) {
        int i = 0;
        while (true) {
            try {
                String mancare = list.get(i).getNumemancare();
                if (!set.contains(mancare)) {
                    return list.get(i);
                } else {
                    i++;
                }
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
                break;
            }
        }

        throw new RuntimeException("Nu sunt destule mancaruri");
    }

    private long regula3(long calorii, long grame) {
        return (calorii * grame) / 100;
    }



}

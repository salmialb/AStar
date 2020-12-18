package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Rutt {
    public Rutt() {
        showNodesAndLinks(createGraph());
        Scanner input = new Scanner(System.in);
        int startInt = 0;
        int endInt = 0;
        ArrayList<Node> graph = createGraph();
        System.out.println("Ange startpunkten(hki, tpe, tku, jyv, kpo, lhi):");
        String start = input.nextLine();
        System.out.println("Ange destinationen(hki, tpe, tku, jyv, kpo, lhi):");
        String end = input.nextLine();
        switch (start) {
            case "hki":
                startInt = 0;
                break;
            case "tpe":
                startInt = 1;
                break;
            case "tku":
                startInt = 2;
                break;
            case "jyv":
                startInt = 3;
                break;
            case "kpo":
                startInt = 4;
                break;
            case "lhi":
                startInt = 5;
                break;

        }
        switch (end) {
            case "hki":
                endInt = 0;
                break;
            case "tpe":
                endInt = 1;
                break;
            case "tku":
                endInt = 2;
                break;
            case "jyv":
                endInt = 3;
                break;
            case "kpo":
                endInt = 4;
                break;
            case "lhi":
                endInt = 5;
                break;

        }

        ArrayList<Node> route = getRoute(graph.get(startInt), graph.get(endInt));
        System.out.println("Kortaste rutten är: ");
        System.out.println("1. " + graph.get(startInt).getName());
        for (int i = 0; i < route.size(); i++) {

            System.out.println(2 + i + ". " + route.get(i).getName());
        }
    }

    public static ArrayList<Node> createGraph() {
        //Skapar en nod för varje tågstation
        Node hki = new Node("Helsingfors", 60.1640504, 24.7600896);
        Node tpe = new Node("Tammerfors", 61.6277369, 23.5501169);
        Node tku = new Node("Abo", 60.4327477, 22.0853171);
        Node jyv = new Node("Jyväskylä", 62.1373432, 25.0954598);
        Node kpo = new Node("Kuopio", 62.9950487, 26.556762);
        Node lhi = new Node("Lahtis", 60.9948736, 25.5747703);

        //Förbindelser från Helsingfors tågstation
        hki.addNeighbour(tpe); //Tammerfors
        hki.addNeighbour(tku); //Åbo
        hki.addNeighbour(lhi); //Lahtis

        //Förbindelser från Tammerfors tågstation
        tpe.addNeighbour(hki); //Helsingfors
        tpe.addNeighbour(tku); //Åbo
        tpe.addNeighbour(jyv); //Jyväskylä
        tpe.addNeighbour(lhi); //Lahtis

        //Förbindelser från Åbo tågstation
        tku.addNeighbour(hki); //Helsingfors
        tku.addNeighbour(tpe); //Tammerfors

        //Förbindelser från Jyväskylä tågstation
        jyv.addNeighbour(tpe); //Tammerfors

        //Förbindelser från Kuopio tågstation
        kpo.addNeighbour(lhi); //Lahtis

        //Förbindelser från Lahtis tågstation
        lhi.addNeighbour(hki); //Helsingors
        lhi.addNeighbour(tpe); //Tammerfors
        lhi.addNeighbour(kpo); //Kuopio

        //Skapar en lista för grafen och sätter in alla noder
        ArrayList<Node> graph = new ArrayList();
        graph.add(hki);
        graph.add(tpe);
        graph.add(tku);
        graph.add(jyv);
        graph.add(kpo);
        graph.add(lhi);


        return graph;


    }


    public static void showNodesAndLinks(ArrayList<Node> g) {
        for (int i = 0; i < g.size(); i++) {
            System.out.println("\nNamn: " + g.get(i).getName());
            System.out.println("Neighbours:");
            for (int j = 0; j < g.get(i).getNeighbours().size(); j++) {
                System.out.println(" " + g.get(i).getNeighbours().get(j).getName());
            }
        }
    }

    public ArrayList<Node> getRoute(Node source, Node destination) {
        ArrayList<Node> candidates = new ArrayList<>();
        ArrayList<Node> visited = new ArrayList<>();
        ArrayList<Node> route = new ArrayList<>();
        Node current = source;
        boolean done = false;
        double F;

        while (!done) {
            double minF = 0;
            Node next = null;
            for (int i = 0; i < current.getNeighbours().size(); i++) {
                if (!candidates.contains(current.getNeighbours().get(i)) && !visited.contains(current.getNeighbours().get(i))) {
                    candidates.add(current.getNeighbours().get(i));
                    current.getNeighbours().get(i).previous = current;
                }

            }
            for (int i = 0; i < candidates.size(); i++) {
                if (candidates.get(i) == destination) {
                    done = true;
                    break;
                } else {
                    F = Node.getF(source, destination);

                    if (minF == 0 || minF > F) {
                        minF = F;
                        next = candidates.get(i);


                    }
                }
            }

            if (!done) {
                current = next;
                visited.add(current);
                candidates.remove(current);
            }
        }
        current = destination;

        while (current != source) {
            route.add(0, current);
            current = current.previous;
        }

        return route;
    }
}

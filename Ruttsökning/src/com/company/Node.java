package com.company;

import java.util.ArrayList;

public class Node {
   private String name;
    private double latitude;
    private double longitude;
    Node previous;

    private  ArrayList<Node> neighbours = new ArrayList<>();
    public Node(){
      }
    public String getName(){
        return name;
    }

    public void setName(String n){
        n = name;
    }
    public double getLatitude(){
        return  latitude;
    }
    public void setLatitude(double la){
        la = latitude;
    }
    public double getLongitude(){
        return  longitude;
    }
    public void setLongitude(double lo){
        lo = longitude;
    }
    public void setPrevious(Node prev){
        prev = previous;
    }
    public  Node getPrevious(){
        return  previous;
    }
    public void addNeighbour(Node n){
    neighbours.add(n);
    }
    public  ArrayList<Node> getNeighbours(){
        return neighbours;
    }


    public Node(String city, double lon, double la) {
        name = city;
        longitude = lon;
        latitude = la;

    }


    public static double getDistance(double lon1, double lat1, double lon2, double lat2)
    {
        lon1 = lon1*Math.PI/180.0;
        lat1 = lat1*Math.PI/180.0;
        lon2 = lon2*Math.PI/180.0;
        lat2 = lat2*Math.PI/180.0;


        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat/2), 2) + Math.cos(lat1) * Math.cos(lat2) *        Math.pow(Math.sin(dlon/2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double km = 6367 * c;

        return km;
    }
    public static double calculateH(Node dest, Node start){
        double h =  getDistance(start.getLongitude(),start.getLatitude(),dest.getLongitude(),dest.getLatitude());




    return h;
    }
    public static double calculateG(Node start){
        double G = 0;
        Node current = start;
        while(current != start){
            G += getDistance(current.longitude, current.latitude, current.previous.longitude, current.previous.latitude);
            current = current.previous;
        }

        return G;
    }


    public static double getF(Node start, Node end){
        double F = calculateH(start,end) + calculateG(start);
        return F;
    }


}


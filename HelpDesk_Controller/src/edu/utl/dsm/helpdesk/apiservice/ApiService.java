package edu.utl.dsm.helpdesk.apiservice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class ApiService {
    public StringBuilder librosCentralizados() throws Exception{
    //URL ruta = new URL("http://10.16.15.19:8084/HelDesk_web/api/book/getAllPublic");
    URL ruta = new URL("http://192.168.116.74:8084/HelDesk_web/api/book/getAllPublic");
    //URL ruta = new URL("http://192.168.100.43:8084/HelDesk_web/api/book/getAllPublic");
        HttpURLConnection conn = (HttpURLConnection) ruta.openConnection();
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        StringBuilder response = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String cadena;
            while ((cadena = input.readLine()) != null) {
                response.append(cadena);
            }
        }
        return response;
    }
    
    public StringBuilder insertarOtros(String nombre, String descripcion, String tema) throws Exception{
    //URL ruta = new URL("http://10.16.15.19:8084/HelDesk_web/api/book/getAllPublic");
    URL ruta = new URL("http://192.168.116.74:8084/HelDesk_web/api/book/insertPublic?"
            +"nombreL="+nombre+"&descripcionL="+descripcion+"&temaL="+tema);
    //URL ruta = new URL("http://192.168.100.43:8084/HelDesk_web/api/book/getAllPublic");
        HttpURLConnection conn = (HttpURLConnection) ruta.openConnection();
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        StringBuilder response = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String cadena;
            while ((cadena = input.readLine()) != null) {
                response.append(cadena);
            }
        }
        return response;
    }
    
    public StringBuilder actualizarOtros(String libro) throws Exception{
    //URL ruta = new URL("http://10.16.15.19:8084/HelDesk_web/api/book/getAllPublic");
    URL ruta = new URL("http://192.168.116.74:8084/HelDesk_web/api/book/updatePublic?"
            +"libro=" + libro);
    //URL ruta = new URL("http://192.168.100.43:8084/HelDesk_web/api/book/getAllPublic");
        HttpURLConnection conn = (HttpURLConnection) ruta.openConnection();
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        StringBuilder response = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String cadena;
            while ((cadena = input.readLine()) != null) {
                response.append(cadena);
            }
        }
        return response;
    }
}

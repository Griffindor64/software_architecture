package edu.utl.dsm.helpdesk.apiservice;

import edu.utl.dsm.helpdesk.model.Libro;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class ApiService {

    public String getToken(String nUsuario,String contrasenna ) throws MalformedURLException, ProtocolException, UnsupportedEncodingException, IOException {
        URL url = new URL("http://192.168.100.43:3000/api/token");
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("usuario", nUsuario);
        params.put("contrasena", contrasenna);

        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (postData.length() != 0) {
                postData.append('&');
            }
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()),
                    "UTF-8"));
        }
        System.out.println(postData.toString());
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.getOutputStream().write(postDataBytes);
        Reader in = new BufferedReader(new InputStreamReader(
                conn.getInputStream(), "UTF-8"));
        StringBuilder response = new StringBuilder();
        String line;

        BufferedReader inn = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        while ((line = inn.readLine()) != null) {
            response.append(line);
        }

        return response.toString();
    }

    public String guardarLibroCentralizado(Libro libro, String token) throws MalformedURLException, ProtocolException, UnsupportedEncodingException, IOException {
        URL url = new URL("http://192.168.100.43:3000/api/registrar-libro");
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("libro_id", libro.getId());
        params.put("libro_nombre", libro.getNombre());
        params.put("tema", libro.getTema());
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (postData.length() != 0) {
                postData.append('&');
            }
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()),
                    "UTF-8"));
        }
        System.out.println(postData.toString());
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("Authorization", token);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.getOutputStream().write(postDataBytes);
        Reader in = new BufferedReader(new InputStreamReader(
                conn.getInputStream(), "UTF-8"));
        StringBuilder response = new StringBuilder();
        String line;

        BufferedReader inn = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        while ((line = inn.readLine()) != null) {
            response.append(line);
        }

        return response.toString();
    }

    public String buscar(String filtro, String token) throws MalformedURLException, ProtocolException, UnsupportedEncodingException, IOException {
        URL url = new URL("http://192.168.100.43:3000/api/buscar-libro");
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("filtro", filtro);
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (postData.length() != 0) {
                postData.append('&');
            }
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()),
                    "UTF-8"));
        }
        System.out.println(postData.toString());
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("Authorization", token);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.getOutputStream().write(postDataBytes);
        Reader in = new BufferedReader(new InputStreamReader(
                conn.getInputStream(), "UTF-8"));
        StringBuilder response = new StringBuilder();
        String line;

        BufferedReader inn = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        while ((line = inn.readLine()) != null) {
            response.append(line);
        }

        return response.toString();
    }

    public String obtenerLibro(String universidad_libro_id, String universidad_id, String token) throws MalformedURLException, ProtocolException, UnsupportedEncodingException, IOException {
        URL url = new URL("http://192.168.100.43:3000/api/recuperar-libro");
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("universidad_libro_id", universidad_libro_id);
        params.put("universidad_id", universidad_id);
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (postData.length() != 0) {
                postData.append('&');
            }
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()),
                    "UTF-8"));
        }
        System.out.println(postData.toString());
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("Authorization", token);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.getOutputStream().write(postDataBytes);
        Reader in = new BufferedReader(new InputStreamReader(
                conn.getInputStream(), "UTF-8"));
        StringBuilder response = new StringBuilder();
        String line;

        BufferedReader inn = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        while ((line = inn.readLine()) != null) {
            response.append(line);
        }

        return response.toString();
    }

    public String actualizarUni(String nueva_contrasena, String nombre_universidad, String grupo, String metodo, String url_recuperacion_libro, String token) throws MalformedURLException, ProtocolException, UnsupportedEncodingException, IOException {
        URL url = new URL("http://192.168.100.43:3000/api/actualizar-perfil");
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("nueva_contrasena", nueva_contrasena);
        params.put("nombre_universidad", nombre_universidad);
        params.put("grupo", grupo);
        params.put("metodo", metodo);
        params.put("url_recuperacion_libro", url_recuperacion_libro);

        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (postData.length() != 0) {
                postData.append('&');
            }
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()),
                    "UTF-8"));
        }
        System.out.println(postData.toString());
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("Authorization", token);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.getOutputStream().write(postDataBytes);
        Reader in = new BufferedReader(new InputStreamReader(
                conn.getInputStream(), "UTF-8"));
        StringBuilder response = new StringBuilder();
        String line;

        BufferedReader inn = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        while ((line = inn.readLine()) != null) {
            response.append(line);
        }

        return response.toString();
    }
}

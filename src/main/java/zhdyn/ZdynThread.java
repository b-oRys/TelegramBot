package zhdyn;

import storages.database.ConnectionDB;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ZdynThread extends Thread {


    @Override
    public void run() {

        //try {

            ConnectionDB connectionDB = new ConnectionDB();
            connectionDB.getConnection();
/*
            ResultSet rs = connectionDB.runSqlQuery("select current_user");

            while (rs.next()){
                System.out.println(rs.getString("ena"));
            }

            rs.close();*/
            //connectionDB.closeConexion();
       /* } catch (SQLException e) {
            e.printStackTrace();
        }*/



        System.out.println(System.getenv("PORT"));
        int port = Integer.parseInt(System.getenv("PORT"));


        try {
            ServerSocket ss = new ServerSocket(port);

            while (true){
                Socket socket = ss.accept();

//                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                output.writeUTF("Hello");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

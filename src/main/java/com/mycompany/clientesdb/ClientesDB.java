package com.mycompany.clientesdb;

import com.mycompany.clientesdb.igu.Principal;
import com.mycompany.clientesdb.igu.Splash;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientesDB {

    public static void main(String[] args) {
        /*
        Principal ventanaPrincipal = new Principal();
        ventanaPrincipal.setVisible(true);
        ventanaPrincipal.setLocationRelativeTo(null);
         */

        Runnable run = () -> {
            Splash splash = new Splash();
            splash.setVisible(true);
            splash.setLocationRelativeTo(null);

            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ClientesDB.class.getName()).log(Level.SEVERE, null, ex);
            }

            splash.dispose();

            Principal ventanaPrincipal = new Principal();
            ventanaPrincipal.setVisible(true);
            ventanaPrincipal.setLocationRelativeTo(null);

        };
        
        Thread hiloSplash = new Thread(run);
        hiloSplash.start();

    }
}

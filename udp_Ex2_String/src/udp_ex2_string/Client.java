package udp_ex2_string;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Client {
    
    public static void main(String[] args) {
        
        try {
            DatagramSocket datagramSocket = new DatagramSocket();
            
            //Gui thong tin sv+ code;
            byte[] sendata = ";B16DCCN151;101".getBytes();
            DatagramPacket senPecket = new DatagramPacket(sendata, sendata.length, InetAddress.getByName("localhost"), 1108);
            datagramSocket.send(senPecket);
            
            
            //Nhan ve chuoi ky tu
            byte[] data =  new byte[1024];
            DatagramPacket dataPacket = new DatagramPacket(data, data.length);
            datagramSocket.receive(dataPacket);
            
            //Xu ly chuoi nhan duoc
            String str = new String(dataPacket.getData()).trim();
            String str1[] = str.split(";");
            String ip = str1[0];
            String out = str1[1];
            
            String Normal ="";
            String Special = "";
                        
            for(int i=0; i<out.length(); i++){
                char c = out.charAt(i);
                if((c>='a' && c<='z') || (c>='A' && c<='Z') || (c>='0' && c<='9')) {
                    Normal+=c;
                }
                else{
                    Special+=c;
                }
            }
            
            //Gui tra chuoi
            String ans = ip + ";" + Normal +","+Special;
            System.out.println(ans);
            byte[] strAns = ans.getBytes();
            DatagramPacket datasen = new DatagramPacket(strAns, strAns.length, InetAddress.getByName("localhost"), 1108);
            datagramSocket.send(datasen);
            
        
            
            
        } catch (SocketException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
}

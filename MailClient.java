/**
 * A class to model a simple email client. The client is run by a
 * particular user, and sends and retrieves mail via a particular server.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class MailClient
{
    // The server used for sending and receiving.
    private MailServer server;
    // The user running this client.
    private String user;
    
    private int veces;
    
    private String to;
    private String asunto;
    private String message;
    private int contador = 0;
    private MailItem ultimoEmail;
    /**
     * Create a mail client run by user and attached to the given server.
     */
    public MailClient(MailServer server, String user)
    {
        this.server = server;
        this.user = user;
        
    }

    /**
     * Return the next mail item (if any) for this user.
     */
    public MailItem getNextMailItem()
    {
        MailItem item = server.getNextMailItem(user);
        if ( item.getMessage() == "regalo" || item.getMessage() == "viagra"){
            return null;
        }
        return server.getNextMailItem(user);
        
    }
    

    /**
     * Print the next mail item (if any) for this user to the text 
     * terminal.
     */
    public void printNextMailItem()
    {
        //funcionalidad 4 y parte de lo que queda de la 6
        
        MailItem item = server.getNextMailItem(user);
        server.post(item);
        String de = item.getFrom();
        String Mensaje = item.getMessage();
        String para = item.getTo();
        String asun = item.getAsunto();
        int reg = Mensaje.indexOf("regalo");
        int vi = Mensaje.indexOf("viagra");
        int comp = Mensaje.indexOf("?=?");
            if (comp == -1){
                System.out.println("no es un mensaje encritado");
                
                if ((item.getMessage().indexOf("viagra")!=-1)||(item.getMessage().indexOf("regalo")!=-1)){
                    item = null;
                }
        
                item.print();
        }else {
           
           String crip = Mensaje.replace("a" ,"!").replace("e" , "@").replace("i", "#").replace("o", "$").replace("u","%");
           System.out.println("From: " + de);
           System.out.println("To: " + para);
           System.out.println("Asunto: " + asun); 
           System.out.println("Message encrypted: " + crip);
        }
        
        if(item == null) {
            System.out.println("No new mail.");
        }
        
        
       
    }

    /**
     * Send the given message to the given recipient via
     * the attached mail server.
     * @param to The intended recipient.
     * @param message The text of the message to be sent.
     */
    public void sendMailItem(String to, String asunto, String message)
    {
        MailItem item = new MailItem(user,to, asunto , message);
        server.post(item);
        contador ++;
    }
    //funcionalidad 1
    
    public  int getNumerosCorreo(String who){
        int totalcorreos = server.howManyMailItems(who);
        return totalcorreos;
        
    }
    
    // funcionaidad 2 
    public void getDatosCorreo(int veces){
        int cont = 0;
        String espacio = " ";
        MailItem correo = server.getNextMailItem(user);
        if(correo  == null) {
            System.out.println("no tienes ningun mensaje nuevo");
        }
        while (correo != null && cont < veces) {
            correo.print();
            cont ++;
        }
        
    }
    
    //funcionalidad 3
    public void Descargar(){
        MailItem item = server.getNextMailItem(user);
        String gracias = "He recibido tu mensaje, gracias";
        String asuntoOriginal = "Re: " + item.getAsunto();
        sendMailItem(item.getFrom(), asuntoOriginal, gracias);
        server.post(item);
        
        item.print();
        
    }
    // funcionalidad 5
    public void total (){
        String espacio = " ";
        int recibidos = server.howManyMailItems(user);
        int enviados = server.howManyMailItems(user);
        int caracteres = user.length();
        
        System.out.println("tienes: " + recibidos +  espacio + "correos recibidos");
        System.out.println("tienes: " + contador + espacio + "correos enviados");
        System.out.println("el usuario con el numero de caracteres mas largo es: " + user + espacio + "y tiene" + espacio + caracteres); 
    
        }


//funcionalidad 6 

public void encriptado(String to, String asunto, String message){
    MailItem item = new MailItem(user,to, asunto , message);
    server.post(item);
    
}

}
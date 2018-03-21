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
    private MailItem correo;
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
        
        MailItem item = server.getNextMailItem(user);
        server.post(item);
        String Mensaje = item.getMessage();
        int comp = Mensaje.indexOf("?=?");
            if (comp == -1){
                System.out.println("no es un mensaje encritado");
                item.print();
        }else {
           
           String crip = Mensaje.replace("a" ,"!").replace("e" , "@").replace("i", "#").replace("o", "$").replace("u","%");
           System.out.println("Message encrypted: " + crip);
           item.print();
        }
        
        if(item == null) {
            System.out.println("No new mail.");
        }
        String spam = item.getMessage();
             if ( item.getMessage() == "regalo" || item.getMessage() == "viagra"){
            System.out.println("ese mensaje contiene spam");
        
        } else {

             System.out.println("Message encrypted: ");
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
        
        this.to = to;
        this.asunto = asunto;
        this.message = message;
        MailItem item = new MailItem(user,to, asunto , message);
        server.post(item);
        contador ++;
    }
    
    public  int getNumerosCorreo(String who){
        int totalcorreos = server.howManyMailItems(who);
        return totalcorreos;
        
    }
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
    public void Descargar(){
        
        MailItem item = server.getNextMailItem(user);
        if(item  == null) {
            System.out.println("no tienes ningun mensaje nuevo");
        }else {
            item.print();
            System.out.println("gracias" + item.getFrom() + "he recibido tu correo");
            sendMailItem(to, asunto, message);
            
        }
        
        
        
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
    String contenido = item.getMessage();
    String crip = contenido.replace("a", "!");
    
}

}
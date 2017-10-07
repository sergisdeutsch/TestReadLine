import java.io.*;

class TestReadLine {
    public static void main(String[] args) {
        EditableBufferedReader in = new EditableBufferedReader(new InputStreamReader(System.in));
        in.setRaw();
        String str= null;
        try {
                str = in.readLine();
            } catch(IOException e) {
                e.printStackTrace();
            }
        System.out.print(str);
         in.unsetRaw();              
    }
}

class EditableBufferedReader extends BufferedReader{
    public EditableBufferedReader(Reader in){
        super(in);
    }
    
    @Override
    public String readLine() throws IOException{
        int cr;
        int auxiliar,auxiliar2;
        String str = null;
        //char frase='';
        cr = 0;
        while(cr!= 122){
            cr = read();
            //frase = (char)cr;
            //System.out.print(frase);
            if (96<cr || cr<123){
                str = Integer.toString(cr);
            }
            if (cr>299){
                str = Integer.toString(cr);
            }

            if (cr==295) {
            	str="enviar un suprimir";
            }
            return str;
        }
        return "";
    }
    @Override
    public int read() throws IOException{
        int cr=0;
        int aux,aux2,aux3;
        cr = super.read(); 
        if (cr == 27){
                aux = super.read();
                if (aux == 91){
                    aux2 = super.read();
                    if(aux2 == 65 || aux2 == 66 || aux2 == 67 || aux2 ==68) {
                    	cr = cr + aux2 + aux2 + 117;
                    }
                    if(aux == 51) {
                    	aux3 = super.read();
                    	if(aux3 == 126) {
                    		cr = cr + aux2 + aux2 + aux3;
                    	}
                    	
                    }
                    
                    //str = Integer.toString(cr)+ Integer.toString(auxiliar)+Integer.toString(auxiliar2);
                }
                //str = Integer.toString(cr)+ Integer.toString(auxiliar);
            }
        return cr;
    }
    

    public void setRaw(){
        String[] cmd = {"/bin/sh","-c","stty -echo raw </dev/tty"};
        executarComanda(cmd);
    }
    public void unsetRaw(){
        String[] cmd = {"/bin/sh","-c","stty echo cooked </dev/tty"};
        executarComanda(cmd);
    }
    private void executarComanda(String[] command){
        try{
            Runtime.getRuntime().exec(command);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
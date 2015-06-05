import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import scea.core.aplicacao.Resultado;
import scea.core.interfaces.IStrategy;
import scea.dominio.modelo.EntidadeDominio;

    /**
     * Classe para manipular a execução de tarefas agendadas automaticamentes
     * @author Jean C Becker
     * @version 1.0
     */
public class AgendarTransacao implements IStrategy{

    Timer timer;
    /**
     * Método para iniciar a execução das tarefas.
     */
    public void iniciar() {

        timer = new Timer();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 6);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date time = calendar.getTime();
        timer.schedule(new tarefasDiarias(), time);
    }
    
    
    public void parar() {
        timer.cancel();
    }

    @Override
    public Resultado processar(EntidadeDominio entidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public class tarefasDiarias extends TimerTask {

        public void run() {
            //Aqui ficam as tarefas que vão ser executadas...
        }
    }
}
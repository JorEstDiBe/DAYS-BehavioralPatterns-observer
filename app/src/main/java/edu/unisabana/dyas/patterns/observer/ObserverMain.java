package edu.unisabana.dyas.patterns.observer;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import edu.unisabana.dyas.patterns.observer.impl.ConfigurationManager;
import edu.unisabana.dyas.patterns.observer.impl.observers.DateFormatObserver;
import edu.unisabana.dyas.patterns.observer.impl.observers.MoneyFormatObserver;

/**
 * Ejemplo práctico del patrón Observer.
 * Se simula la actualización de la configuración del sistema y se notifica a los observadores.
 * 
 * Nota: Se asume que la clase ConfigurationManager extiende de java.util.Observable
 * y que los observadores implementan java.util.Observer, mostrando su actualización en consola.
 */
public class ObserverMain {

    public static void main(String[] args) {
        // 1. Obtener la instancia única de ConfigurationManager (Singleton)
        ConfigurationManager conf = ConfigurationManager.getInstance();

        // 2. Configuración inicial (valores por default)
        conf.setDefaultDateFormat(new SimpleDateFormat("dd/MM/yyy"));
        conf.setMoneyFormat(new DecimalFormat("##.00"));
        System.out.println("Congifuracion predeterminada ('Default')");

        // 3. Crear y registrar los observadores
        DateFormatObserver dateFormatObserver = new DateFormatObserver();
        MoneyFormatObserver moneyFormatObserver = new MoneyFormatObserver();
        conf.addObserver(dateFormatObserver);
        conf.addObserver(moneyFormatObserver);
        System.out.println("Añadir Observadores.\n");

        // 4. Primer cambio de configuración: Se espera que los observadores notifiquen el cambio.
        System.out.println("Changing configuration - Primer Cambio formato: ");
        conf.setDefaultDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
        conf.setMoneyFormat(new DecimalFormat("###,#00.00"));
        System.out.println();

        // 5. Segundo cambio de configuración: Se notifica nuevamente a los observadores.
        System.out.println("Changing configuration - Segundo Cambio formato: ");
        conf.setDefaultDateFormat(new SimpleDateFormat("MM/yyyy/dd"));
        conf.setMoneyFormat(new DecimalFormat("###,#00"));
        
        // 6. Remover los observadores: A partir de aquí, no se recibirán notificaciones
        conf.removeObserver(dateFormatObserver);
        conf.removeObserver(moneyFormatObserver);
        System.out.println("Observadores Removidos.\n");

        // 7. Tercer cambio de configuración: No habrá notificación ya que los observadores fueron removidos y se cumple con el formato
        System.out.println("Changing configuration - Tercer Cambio (No se notifican los observadores ya que cumple con el formato ('Default'): ");
        conf.setDefaultDateFormat(new SimpleDateFormat("MM/yyyy"));
        conf.setMoneyFormat(new DecimalFormat("###,##0.00"));
    }
}

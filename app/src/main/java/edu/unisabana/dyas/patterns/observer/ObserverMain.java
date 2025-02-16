package edu.unisabana.dyas.patterns.observer;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import edu.unisabana.dyas.patterns.observer.impl.ConfigurationManager;
import edu.unisabana.dyas.patterns.observer.impl.observers.DateFormatObserver;
import edu.unisabana.dyas.patterns.observer.impl.observers.MoneyFormatObserver;

/**
 * @author cesarvefe
 
 */
public class ObserverMain {

    public static void main(String[] args) 
    {
        ConfigurationManager conf = ConfigurationManager.getInstance();

         //Se establecen los valores por defecto de los formatos
         conf.setDefaultDateFormat(new SimpleDateFormat("yyyy/MM/dd"));
         conf.setMoneyFormat(new DecimalFormat("##.00"));
         System.out.println("Established configuration");
         
         //Se dan de alta lo observadores
         DateFormatObserver dateFormatObserver = new DateFormatObserver();
         MoneyFormatObserver moneyFormatObserver = new MoneyFormatObserver();
         conf.addObserver(dateFormatObserver);
         conf.addObserver(moneyFormatObserver);

        Scanner scanner = new Scanner(System.in);

        // Ciclo en ejecución para cambios en runtime o tiempo real.
        while (true) 
        {
            System.out.println("----------------------------------");
            System.out.println("\nSelecciona Cambio de formato:");
            System.out.println("1. Fecha");
            System.out.println("2. Moneda");
            System.out.println("3. Salir");
            System.out.print("Ingresar opcion: ");
            int instruccion = scanner.nextInt();
            scanner.nextLine(); 

            switch (instruccion) 
            {
                case 1: //Opcion de cambio de formato de fecha
                    System.out.println("---------------------------------");
                    System.out.println("Selecciona un formato de fecha:");
                    System.out.println("1. dd/MM/yyyy");
                    System.out.println("2. yyyy-MM-dd");
                    System.out.println("3. MM-dd-yyyy");
                    System.out.print("Ingresa: ");
                    int FormatoFecha = scanner.nextInt();
                    scanner.nextLine();

                    switch (FormatoFecha) 
                    {
                        case 1:
                            conf.setDefaultDateFormat(new SimpleDateFormat("dd/MM/yyyy"));
                            break;
                        case 2:
                            conf.setDefaultDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
                            break;
                        case 3:
                            conf.setDefaultDateFormat(new SimpleDateFormat("MM-dd-yyyy"));
                            break;
                        default:
                            System.out.println("Instruccion invalida, intente de nuevo...");
                            break;
                    }
                    break;

                case 2: //Opcion de cambio de formato de dinero
                    System.out.println("-----------------------------------");
                    System.out.println("Selecciona un formato de dinero:");
                    System.out.println("1. ###,#00.00");
                    System.out.println("2. ###,#00");
                    System.out.println("3. ###,###.00");
                    System.out.print("Ingresa: ");
                    int FormatoMoneda = scanner.nextInt();
                    scanner.nextLine();

                    switch (FormatoMoneda) 
                    {
                        case 1:
                            conf.setMoneyFormat(new DecimalFormat("###,#00.00"));
                            break;
                        case 2:
                            conf.setMoneyFormat(new DecimalFormat("###,#00"));
                            break;
                        case 3:
                            conf.setMoneyFormat(new DecimalFormat("###,##0.00"));
                            break;
                        default:
                            System.out.println("Instruccion invalida, intente de nuevo...");
                            break;
                    }
                    break;

                default:
                    System.out.println("Instruccion invalida, intente de nuevo...");
                    break;
            }
            
            if (instruccion == 3) 
            {
                break;  // Salir del ciclo
            }
        }

        scanner.close();
        System.out.println("¡Adios!");
    }
}
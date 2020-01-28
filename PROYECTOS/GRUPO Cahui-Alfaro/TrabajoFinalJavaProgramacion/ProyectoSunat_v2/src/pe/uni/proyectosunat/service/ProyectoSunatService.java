
package pe.uni.proyectosunat.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import javax.swing.JOptionPane;
import pe.uni.proyectosunat.app.dto.ProyectoSunatDto;
import pe.uni.proyectosunat.view.OperacionSunatView;



public class ProyectoSunatService {
    
    
    public ProyectoSunatDto procesar(ProyectoSunatDto dto){
        
        //Variables
        double deduccion;
        double rentaNeta;
        double totalRentas;
        double deduccion2;
        double caso_512;
        double caso_513;
        double caso_120;
        //Proceso
        
        deduccion = deduccion(dto.getRenta_cuarta());
        rentaNeta = rentaNeta(dto.getRenta_cuarta());
        totalRentas = totalRentas(dto.getRenta_cuarta(), dto.getRenta_quinta());
        deduccion2 = deduccion2(dto.getRenta_cuarta(), dto.getRenta_quinta());
        caso_512 =  cas_512(dto.getRenta_cuarta(), dto.getRenta_quinta());
        caso_513 =  cas_513(dto.getRenta_cuarta(), dto.getRenta_quinta());
        caso_120 =  cas_120(dto.getRenta_cuarta(), dto.getRenta_quinta());
        
        //Reporte
        
        
        dto.setDeduccion(deduccion);
        dto.setRentaNeta(rentaNeta);
        dto.setTotalRentas(totalRentas);
        dto.setDeduccion2(deduccion2);
        dto.setCas_512(caso_512);
        dto.setCas_513(caso_513);
        dto.setCas_120(caso_120);
        return dto;
                
        //Fin de Proceso
    }
    
    
    
    private double deduccion(double rentaCuarta){
        
        //Variable
        double deduccion;
        
        //Proceso
        deduccion = 0.20 * rentaCuarta;
        
        //Reporte
        return deduccion;
    }
    
    
    private double rentaNeta(double rentaCuarta){
        
        //Variable
        double renta;
        
        //Proceso
        renta = rentaCuarta - deduccion(rentaCuarta);
        
        //Reporte
        return renta;
    
    }
    
    private double totalRentas(double rentaCuarta, double rentaQuinta){
        
        //Variable
        double totalRenta;
      
        //Proceso
        totalRenta = rentaQuinta + rentaNeta(rentaCuarta);
        
        //Reporte
        return totalRenta;
    }
    
    private double deduccion2(double rentaCuarta, double rentaQuinta){
        
        //Variable 
        double deduccion2 = 29400.0;
        
        //Proceso
        if(totalRentas(rentaCuarta, rentaQuinta)<29400.0){
            deduccion2 = totalRentas(rentaCuarta, rentaQuinta);
        }
        
        //Reporte
        return deduccion2;
     
    }
    
    private double cas_512(double rentaCuarta, double rentaQuinta){
        
        //Variable
        double cas_512;
        
        //Proceso
        cas_512 = totalRentas(rentaCuarta, rentaQuinta) - deduccion2(rentaCuarta, rentaQuinta);
        
        //Reporte
        return cas_512;
    }
    
    private double cas_513(double rentaCuarta,double rentaQuinta){
                
        //Variable
        double cas_513 = 29400.0;
        
        //Proceso
        cas_513=totalRentas(rentaCuarta, rentaQuinta)-deduccion2(rentaCuarta, rentaQuinta);
        
        //Reporte
        return cas_513;
        
    }
    
    private double cas_120(double rentaCuarta,double rentaQuinta){
                
        //Variable
        double cas_120_temp,cas_120=0;
        int uit=4200;
        int limit1=uit*5;
        int limit2=uit*20;
        int limit3=uit*35;
        int limit4=uit*45;
        
        //Proceso
        cas_120_temp = cas_513(rentaCuarta, rentaQuinta);
        if (cas_120_temp<=limit1) {cas_120=cas_120_temp*0.08;}
        if (cas_120_temp>limit1 && cas_120_temp<=limit2){cas_120=(limit1*0.08)+(cas_120_temp-limit1)*0.14;}
        if (cas_120_temp>limit2 && cas_120_temp<=limit3){cas_120=(limit1*0.08)+((limit2-limit1)*0.14)+((cas_120_temp-limit2)*0.17);}
        if (cas_120_temp>limit3 && cas_120_temp<=limit4){cas_120=(limit1*0.08)+((limit2-limit1)*0.14)+((limit3-limit2)*0.17)+((cas_120_temp-limit3)*0.2);}
        if (cas_120_temp>limit4){cas_120=(limit1*0.08)+((limit2-limit1)*0.14)+((limit3-limit2)*0.17)+((limit4-limit3)*0.2)+((cas_120_temp-limit4)*0.3);}
        
        //Reporte
        return cas_120;
        
        
        
    }
    public static Connection ConectarSQLServer() throws ClassNotFoundException, SQLException{
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String connectString = "jdbc:sqlserver://B204-07:1433;";
            //"databaseName=db_fisca_exp";
        String user = "yanet";
        String password = "yanecita";
        Class.forName(driver);
        Connection con = DriverManager.getConnection(connectString, user , password);
        return con;
    }
    
   
    
    
}

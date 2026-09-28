
package com.mycompany.prueba;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Scanner;
import java.util.Date;

import java.io.FileOutputStream;
import java.sql.ResultSetMetaData;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;




public class Prueba {

    public static void main(String[] args)  {
        Scanner sc=new Scanner(System.in);
        String url = "jdbc:mysql://localhost:3306/Empleados";
        String user = "root";
        String password = "";
        
         boolean emp;
         int opciones=-1;
         int opUpdate;
         int opConsulta;
         int idEmp ;
         String nombre ;
         String posicion ;
         BigDecimal salario;
         String fecha_ingreso;
         Date fecha = new Date();
         String departamento ;
         
          
         
         String update1 =  "UPDATE empleados SET nombre = ?  WHERE Id = ?";
         String update2 =  "UPDATE empleados SET posicion = ?  WHERE Id = ?";
         String update3 =  "UPDATE empleados SET salario = ?  WHERE Id = ?";
         String update4 =  "UPDATE empleados SET departamento = ?  WHERE Id = ?";
         String valId = "SELECT * FROM empleados WHERE id = ? ";
         String consul = "SELECT * FROM empleados";
         String delete = "DELETE FROM empleados WHERE id = ?";
         String insertSQL = "INSERT INTO empleados (nombre, posicion, salario, fecha_ingreso, departamento) VALUES (?, ?, ?, ?, ?)";
         String query = "SELECT * FROM employees WHERE id = ?";
         
         
         
         
         
         
         while(opciones!=0){
             System.out.println("elige una opcion");
         System.out.println("1-insertar\n 2-Actualizar\n3-Eliminar regisro\n4-Concultar registro\n5-Crear Excel\n0-salir");
         opciones=sc.nextInt();
         sc.nextLine();
         switch (opciones) {
            case 1:
                System.out.println("Ingresa el nombre");
         nombre=sc.nextLine();
         
         System.out.println("Ingresa el puesto");
         posicion=sc.nextLine();
         
         System.out.println("Ingresa el salario");
         salario=sc.nextBigDecimal();
         sc.nextLine();
         
         //System.out.println("Ingresa la fecha de entrada (yyyy-MM-dd)");
         //fecha_ingreso=sc.nextLine();
         
         
         System.out.println("Ingresa el departamento de trabajo");
         departamento=sc.nextLine();
         
         
         try(Connection conn = DriverManager.getConnection(url, user, password);
                 PreparedStatement consulta = conn.prepareStatement(insertSQL)) {
            
            Timestamp fechaAc = new Timestamp(fecha.getTime());
             consulta.setString(1, nombre);
            consulta.setString(2, posicion);
            consulta.setBigDecimal(3, salario);
            consulta.setTimestamp(4, fechaAc);
            consulta.setString(5, departamento);
            
            int filasInsertadas = consulta.executeUpdate();
            
           
             System.out.println("filas insertadas" + filasInsertadas);

            System.out.println("\nConexión exitosa a MySQL");
           
             //System.out.println(" Tabla 'employees' creada con éxito");
            conn.close();
            
        } catch (SQLException e) {
            System.out.println("Error de conexión");
             
            e.printStackTrace();
        }
       
         break;
         
         
            case 2:
                
         System.out.println("Ingresa el id del empleado a modificar");
         idEmp=sc.nextInt();
         
               try(Connection conn = DriverManager.getConnection(url, user, password);
                 PreparedStatement consulta = conn.prepareStatement(valId)) {
            
            
             
             consulta.setInt(1, idEmp);

             ResultSet rs = consulta.executeQuery();
             
                
            
           if(rs.next()){
                emp=true; 
              }else{System.out.println("el empleado no existe"); return;}
             //System.out.println(" Tabla 'employees' creada con éxito");
            conn.close();
            
        } catch (SQLException e) {
            System.out.println("Error de conexión");
             
            e.printStackTrace();
        }
         
         
         
         System.out.println("Que deseas modificar\n1-Nombre del empleado.\n2-posicion.\n3-salario.\n4-departamnto.");
         opUpdate=sc.nextInt();
         sc.nextLine();
         
         if(opUpdate ==1){
             
             System.out.println("Ingresa el nomobre");
             nombre=sc.nextLine();
             
            try(Connection conn = DriverManager.getConnection(url, user, password);
                 PreparedStatement consulta = conn.prepareStatement(update1)) {
            
            
             consulta.setString(1, nombre);
             consulta.setInt(2, idEmp);

             int nuevoRe = consulta.executeUpdate();
             
                System.out.println(nuevoRe);
            
           if(nuevoRe>0){
                 System.out.println("Nombre actualizado");
              }else{System.out.println("No se encontro el empleado");}
             //System.out.println(" Tabla 'employees' creada con éxito");
            conn.close();
            
        } catch (SQLException e) {
            System.out.println("Error de conexión");
             
            e.printStackTrace();
        }
         }
         ////////////////////////////7///////////////////////////////////
         
                  if(opUpdate ==2){
             
             System.out.println("Ingresa la nueva posicion del empleado");
             posicion=sc.nextLine();
             
            try(Connection conn = DriverManager.getConnection(url, user, password);
                 PreparedStatement consulta = conn.prepareStatement(update2)) {
            
            
             consulta.setString(1, posicion);
             consulta.setInt(2, idEmp);

             int nuevoRe = consulta.executeUpdate();
             
                System.out.println(nuevoRe);
            
           if(nuevoRe>0){
                 System.out.println("Nueva posicion actualizada\n");
              }else{System.out.println("No se puedo actualizar\n");}
             //System.out.println(" Tabla 'employees' creada con éxito");
            conn.close();
            
        } catch (SQLException e) {
            System.out.println("Error de conexión");
             
            e.printStackTrace();
        }
         }
         ////////////////////////////////////////////////////////////////////
             if(opUpdate ==3){
             
             System.out.println("Ingresa el nuevo salario del empleado");
            salario=sc.nextBigDecimal();
             
            try(Connection conn = DriverManager.getConnection(url, user, password);
                 PreparedStatement consulta = conn.prepareStatement(update3)) {
            
            
             consulta.setBigDecimal(1, salario);
             consulta.setInt(2, idEmp);

             int nuevoRe = consulta.executeUpdate();
             
                System.out.println(nuevoRe);
            
           if(nuevoRe>0){
                 System.out.println("Salario nuevo actualizado\n");
              }else{System.out.println("No se puedo actualizar\n");}
             //System.out.println(" Tabla 'employees' creada con éxito");
            conn.close();
            
        } catch (SQLException e) {
            System.out.println("Error de conexión");
             
            e.printStackTrace();
        }    
             }        
         
         /////////////////////////////////////////////////////////////////
             
                   if(opUpdate ==4){
             
             System.out.println("Ingresa el nuevo departamento del empleado");
            departamento=sc.nextLine();
             
            try(Connection conn = DriverManager.getConnection(url, user, password);
                 PreparedStatement consulta = conn.prepareStatement(update4)) {
            
            
             consulta.setString(1, departamento);
             consulta.setInt(2, idEmp);

             int nuevoRe = consulta.executeUpdate();
             
                System.out.println(nuevoRe);
            
           if(nuevoRe>0){
                 System.out.println("Departamento actualizado\n");
              }else{System.out.println("No se puedo actualizar\n");}
             //System.out.println(" Tabla 'employees' creada con éxito");
            conn.close();
            
        } catch (SQLException e) {
            System.out.println("Error de conexión");
             
            e.printStackTrace();
        }    
             } 
         
         
         break;
         
         
         
        case 3:
                System.out.println("Ingresa ek Id del empleado a eliminar");
                idEmp=sc.nextInt();
                
                try (Connection conn = DriverManager.getConnection(url, user, password);
                       PreparedStatement consulta = conn.prepareStatement(valId) ){
                
                
                    consulta.setInt(1, idEmp);
                    
                    ResultSet re=consulta.executeQuery();
                    if(re.next()){
                    emp=true;
                    try (Connection conn2 = DriverManager.getConnection(url, user, password);
                       PreparedStatement consulta2 = conn2.prepareStatement(delete) ){
                
                
                    consulta2.setInt(1, idEmp);
                    
                    int reDelete=consulta2.executeUpdate();
                    
                    if(reDelete>0){
                        System.out.println("Registro eleminado exitosamente");
                    }else{
                        System.out.println("No se pudo eliminar el registro");
                    }
                    
                    conn2.close();
                }catch(SQLException e){
                        System.out.println("Error de conexión");
             
                    e.printStackTrace();
                        
                  }
                    }else{System.out.println("El empleado que deseas eliminar no esxiste"); return;}
                    
                    conn.close();
                }catch(SQLException e){
                        System.out.println("Error de conexión");
             
                    e.printStackTrace();
                        
                  }
                
                
                
                
                
        break;
                
        case 4:
            
            System.out.println("Que consulta deceas hacer?\n\n1-empleado en especifico.\n2-todos los empleados.");
            opConsulta=sc.nextInt();
            sc.nextLine();
            
            
            if(opConsulta==1){
                
                System.out.println("Ingresa el Id del empleado a consultar.\n");
                idEmp=sc.nextInt();
                sc.nextLine();
                
                
                
                
            try(Connection conn = DriverManager.getConnection(url, user, password);
                 PreparedStatement consulta = conn.prepareStatement(valId)) {
            
            
          
             consulta.setInt(1, idEmp);

            ResultSet con=consulta.executeQuery();
            if (con.next()){
                emp=true;
                System.out.println("Empleado encontrado.\n");
              
                
System.out.println("Id: "+con.getInt(1)+"\n" 
                 +"Nombre: "+ con.getString(2)+"\n"
                 +"Posocion: "+ con.getString(3)+"\n"
                 +"Salario: "+ con.getBigDecimal(4)+"\n"
                 +"Fecha de ingreso: "+ con.getDate(5)+"\n"
                 +"Departamento: "+con.getString(6)+"\n");
                
                
            }else{System.out.println("El empleado no exixte\n"); return;}
            conn.close();
            
        } catch (SQLException e) {
            System.out.println("Error de conexión");
             
            e.printStackTrace();
        }    
             } 
            
            if(opConsulta==2){
            
                  try(Connection conn = DriverManager.getConnection(url, user, password);
                 PreparedStatement consulta = conn.prepareStatement(consul)) {
            

            ResultSet con=consulta.executeQuery();
            while(con.next()){
                System.out.println("\n\n");
            System.out.println("Id: "+con.getInt(1)+"\n" 
                 +"Nombre: "+ con.getString(2)+"\n"
                 +"Posocion: "+ con.getString(3)+"\n"
                 +"Salario: "+ con.getBigDecimal(4)+"\n"
                 +"Fecha de ingreso: "+ con.getDate(5)+"\n"
                 +"Departamento: "+con.getString(6)+"\n");
            
            }
            conn.close();
            
        } catch (SQLException e) {
            System.out.println("Error de conexión");
             
            e.printStackTrace();
        }
            
            
            
            }
         
            
              
         
            
            break;
                
            
            
            
        case 5:
             
    
            break;
            
            
            
            case 0:
                System.out.println("adios");
                System.exit(0);
             break;
                
                
                
                
            default:
                System.out.println("");
        }
         
         
         }
         

        
        
        }
}
        
    


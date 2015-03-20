/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rangolibre.view;

/**
 *
 * @author aleaguado
 */
      
    import java.awt.Color;  
    import java.awt.Component;  
    import java.awt.Font;  
      
    import javax.swing.JTable;  
import static javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS;
import static javax.swing.JTable.AUTO_RESIZE_OFF;
import javax.swing.border.SoftBevelBorder;
    import javax.swing.table.DefaultTableCellRenderer;  
      
    public class LineSelectionTableCellrender extends DefaultTableCellRenderer   
    {  
      
        @Override  
        public Component getTableCellRendererComponent(  
            JTable table,  
            Object value,  
            boolean isSelected,  
            boolean hasFocus,  
            int row,  
            int column)  
        {  
            Component result = super.getTableCellRendererComponent(  
                table,  
                value,  
                isSelected,  
                hasFocus,  
                row,  
                column  
            );  
            
            setBorder(new SoftBevelBorder(SoftBevelBorder.RAISED));
            
            table.setAutoResizeMode(AUTO_RESIZE_OFF);
           // table.setFillsViewportHeight(true);
            
            
                
           // table.setRowHeight(30);
            

            if(isSelected) {  
                result.setFont(new Font("arial", Font.BOLD, 16));  
                result.setForeground(Color.WHITE);  
                result.setBackground(Color.DARK_GRAY);  
            }else {  
                result.setFont(new Font("arial", Font.PLAIN, 18));  
                result.setForeground(Color.BLACK);  
                result.setBackground(Color.WHITE);  
            }  
            return result;  
        }  
          
    }  

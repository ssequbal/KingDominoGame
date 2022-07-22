import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame_Settings extends MainFrame implements ActionListener
{
    private javax.swing.JLabel ChangeContrast;
    private javax.swing.JLabel ChangeSaturation;
    private JButton ContrastField;
    private javax.swing.JLabel DisplaySetting;
    private JButton ReturnButton;
    private JButton SaturationField;
    private javax.swing.JSeparator Separator;
    
    public Frame_Settings(MainUI UI)
    {
        super("Main Menu",UI); 
        
        ActionListener returning = new ActionListener(){public void actionPerformed(ActionEvent e){
            UI.ChangeFrame(new Frame_MainMenu(UI));
        }};

        ChangeContrast = new javax.swing.JLabel("Dark Mode");
        ChangeSaturation = new javax.swing.JLabel("Change Saturation");
        ReturnButton = new JButton("Return");
        ContrastField = new JButton("OFF");
        SaturationField = new JButton("OFF");

        ActionListener darkmode = new ActionListener(){public void actionPerformed(ActionEvent e){  // THIS IS THE CONTRAST FROM THE USE CASE
            if (UI.returnColor() == Color.WHITE) {
                
                ContrastField.setText("ON");
                UI.ChangeColor(Color.BLACK);
                


            }
            else{
               ContrastField.setText("OFF");
               UI.ChangeColor(Color.WHITE);
            }

        }
    };
        ActionListener saturation = new ActionListener(){public void actionPerformed(ActionEvent e){  // THIS IS THE Saturation FROM THE USE CASE
            if ((UI.returnColor() == Color.WHITE) || ((UI.returnColor() == Color.BLACK) )) {
                SaturationField.setText("ON");
                UI.ChangeColor(Color.ORANGE);
                


            }
            else{
            UI.ChangeColor(Color.WHITE);
            SaturationField.setText("OFF");
            }

        }
    };
        DisplaySetting = new javax.swing.JLabel("Display Settings");
        Separator = new javax.swing.JSeparator();

        ContrastField.addActionListener(darkmode);
        SaturationField.addActionListener(saturation);
        ReturnButton.addActionListener(returning);

        //#region Layout
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Separator, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ReturnButton))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(DisplaySetting)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(ChangeContrast)
                                        .addGap(9, 9, 9)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ContrastField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ChangeSaturation)
                                .addGap(18, 18, 18)
                                .addComponent(SaturationField, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 340, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(DisplaySetting)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ChangeContrast)
                    .addComponent(ContrastField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ChangeSaturation)
                    .addComponent(SaturationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(73, 73, 73)
                .addComponent(Separator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 298, Short.MAX_VALUE)
                .addComponent(ReturnButton)
                .addContainerGap())
        );
        //#endregion 

        this.pack();
    }
    public void actionPerformed(ActionEvent evt) {
        /*
        JButton button = (JButton)evt.getSource();
        String name = button.getName();
        if(name == "BrightnessField"){
            //Integer s = Integer.parseInt(evt.getActionCommand());
        }else if(name=="ContrastField"){

        }else if(name=="SaturationFied"){

        }
        */
    }
    private void ReturnButtonActionPerformed(ActionEvent evt){
        UI.ChangeFrame(new Frame_Settings(UI));
    }
}

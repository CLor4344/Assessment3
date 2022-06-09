/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package vehicleservicemanagementsystem;

import vsms.model.IVSMSModel;
import vsms.model.VSMSModel;
import vsms.presenter.VSMSPresenter;
import vsms.view.IVSMSView;
import vsms.view.VSMSView;

/**
 *
 * @author Colbey
 */
public class VehicleServiceManagementSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //create and instance of VehiceServiceModel
        IVSMSModel ivm = new VSMSModel();
        //create and instance of VehiceServiceView
        IVSMSView ivv = new VSMSView();
        //create and instance of VehiceServicePresenter
        VSMSPresenter ip = new VSMSPresenter(ivv,ivm);
        
        ivv.bind(ip);
        
        
    }
    
}

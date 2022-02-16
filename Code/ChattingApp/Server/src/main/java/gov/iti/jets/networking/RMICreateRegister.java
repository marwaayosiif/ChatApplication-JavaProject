package gov.iti.jets.networking;

import gov.iti.jets.service.Impl.LoginImpl;
import gov.iti.jets.service.Impl.RegisterImpl;
import gov.iti.jets.service.LoginInt;
import gov.iti.jets.service.RegisterInt;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMICreateRegister {
    private static RMICreateRegister rmiCreateRegister = new RMICreateRegister();
    private RMICreateRegister() {
        try {
            RegisterInt register = new RegisterImpl();
            LoginInt loginService = new LoginImpl();
            Registry registry = LocateRegistry.createRegistry(4004);
            registry.rebind("RegisterService",register);
            registry.rebind("loginService",loginService);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static RMICreateRegister getInstance() {
        return rmiCreateRegister;
    }


}
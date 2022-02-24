package gov.iti.jets.networking;


import gov.iti.jets.common.interfaces.*;
import gov.iti.jets.service.Impl.*;


import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMICreateRegister {
    private static RMICreateRegister rmiCreateRegister = new RMICreateRegister();
    private RMICreateRegister() {
        try {
            RegisterInt register = new RegisterImpl();
            LoginInt loginService = new LoginImpl();
            ContactListInt contactListInt = new ContactListImpl();
            ServerMessageInt message = new ServerMessageImpl();
            FriendRequestInt friendRequestInt= new FriendRequestImpl();
            AddGroupChatInt addGroupChatInt= new AddGroupChatImpl();
            GroupListInt groupListInt = new GroupListImpl();
            UpdateUserInt updateUserInt = new UpdateUserImpl();
            Registry registry;
//            try{
                registry = LocateRegistry.getRegistry(7000);
//            }catch(Exception e){
//                registry = LocateRegistry.createRegistry(7000);
//            }
            registry.rebind("RegisterService",register);
            registry.rebind("loginService",loginService);
            registry.rebind("ContactListService",contactListInt);
            registry.rebind("MessageService", message);
            registry.rebind("FriendRequestService", friendRequestInt);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
    public static RMICreateRegister getInstance() {
        return rmiCreateRegister;
    }


}

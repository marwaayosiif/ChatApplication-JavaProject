package gov.iti.jets.common.interfaces;



import gov.iti.jets.common.dtos.ContactDto;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface AddGroupChatInt extends Remote {
    Boolean  addGroupChat(int id) throws RemoteException;
}